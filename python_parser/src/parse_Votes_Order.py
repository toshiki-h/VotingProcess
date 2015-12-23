#!/usr/bin/python3
##################
# Author:Toshiki Hirao
# CreatedOn: 2015/08/10
# Summary: This extract the flow in review process.
##################

### Import lib
import re
import sys
import csv
import time
import MySQLdb
from collections import defaultdict
from Utils import ReviewerFunctions
from Utils import VoterFunctions

### Set ReviewId to list
Id_list = []
status_list = []
for row in open(sys.argv[1], "r"):
	words = row.strip().split(",")
	Id_list.append(words[0])
	#Own_list.append(words[1])
	status_list.append(words[1])

### Access into MySQLdb
cnct = MySQLdb.connect(db="qt",user="root", passwd="hirao")
csr = cnct.cursor()

i = 0
#for (Id, Own) in zip(Id_list, Own_list):
for (Id, st) in zip(Id_list, status_list):
	csr.execute("select ReviewId, Message, AuthorId, WrittenOn from Comment where ReviewId = '"+Id+"' order by WrittenOn asc;")
	lines = csr.fetchall()

	message = []
	author = []
	for line in lines:	## fix correct order from db messages.
		message.append(line[1].replace("\n", "<br>"))
		author.append(line[2])
	assert len(message) == len(author)

	reviewedAutorList = []	# the list of Authors who wrote vote comment
	voterClassList = []
	f = 0
	assert len(reviewedAutorList) == 0

	### Parse comments
	i = 0
	for (m, a) in zip(message, author):
		i = i + 1
		if(ReviewerFunctions.IsBotTest(a) == True):
			#if Id == "12338":
			#	print str(Id)+ "," + str(i)+":" + str("Passed IsBotTest")
			continue
		else:
			pass
		score = ReviewerFunctions.IsVote(m)
		#if Id == "12338":
			#print str(Id)+ "," + str(i) + "->" + str(a)
		if(score == 1):	# +2, +1 comments
			if(a in reviewedAutorList):
				if VoterFunctions.HasSameVoteScore(Id, a, voterClassList, score) != True:
					VoterFunctions.updateVoteScore(Id, a, voterClassList, score)
				else:
					pass
			else:
				#if Id == "12338":
				#	print str(Id)+ "," + str(i) + "," + str("Positive:") + str(m) + ":" + str(a)
				voterClassList.append(VoterFunctions.MakeVoterClass(a, score))
				reviewedAutorList.append(a)

		if(score == -1):
			if(a in reviewedAutorList):
				if VoterFunctions.HasSameVoteScore(Id, a, voterClassList, score) != True:
					VoterFunctions.updateVoteScore(Id, a, voterClassList, score)
				else:
					pass
			else:
				#if Id == "12338":
				#	print str(Id)+ "," + str(i) + "," + str("Negative:") + str(m) + ":" + str(a)
				voterClassList.append(VoterFunctions.MakeVoterClass(a, score))
				reviewedAutorList.append(a)
	assert len(reviewedAutorList) == len(voterClassList)


 	### Output
	#print "ReviewId LenOfPass status 1 2 3 4 5 6 7 8 9 10"
	write = sys.stdout.write
	print str(Id)+","+str(len(reviewedAutorList))+","+str(st),
	for voterClass in voterClassList:
		write(",")
		write(str(voterClass.vote)),
	print
	#assert len(aut) == len(process)-1

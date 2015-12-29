#!/usr/bin/python3
##################
# Author:Toshiki Hirao
# CreatedOn: 2015/12/27
# Summary: This program can analyze the total review time, total votes and # of update
##################

### Import lib
import re
import sys
import csv
import time
import MySQLdb
from collections import defaultdict
from datetime import datetime
from Utils import ReviewerFunctions
from Utils import VoterFunctions

### main
FMT = '%Y-%m-%d %H:%M:%S'
index = 0
print "Id,Status,Claster,AverageCorrectness,TotalDays,NumOfUpdated,NumOfVotes"
for line in open(sys.argv[1], "r"):
    if index == 0:
        index = index + 1
        continue
    #reviewId, NumOfreviewers, averageCorrectness, status, claster
    words = line.split(",")
    Id = words[0].strip()
    reviewers = words[1].strip()
    averageCorrectness = words[2].strip()
    status = words[3].strip()
    claster = words[4].strip()


    ### Access into MySQLdb
    cnct = MySQLdb.connect(db="qt",user="root", passwd="hirao")
    csr0 = cnct.cursor()
    csr1 = cnct.cursor()
    csr2 = cnct.cursor()
    csr3 = cnct.cursor()
    csr0.execute("select ReviewId, PatchSetId, CreatedOn from PatchSet where ReviewId = '"+Id+"' order by PatchSetId asc limit 1")
    csr1.execute("select ReviewId, Message, AuthorId, WrittenOn from Comment where ReviewId = '"+Id+"' order by WrittenOn desc limit 1;")
    csr2.execute("select ReviewId, CreatedOn, NumberOfPatches from Review where ReviewId = '"+Id+"';")
    results0 = csr0.fetchall()
    results1 = csr1.fetchall()
    results2 = csr2.fetchall()
    assert len(results0) == 1
    assert len(results1) == 1
    assert len(results2) == 1
    for result in results0:
        createdOn = result[2]
    for result in results1:
        lastWrittenOn = result[3]
    for result in results2:
        updated = result[2]

    diff = lastWrittenOn - createdOn
    #print str(lastWrittenOn)+"-"+str(createdOn)
    if diff.total_seconds() < 0:
        continue
    csr3.execute("select ReviewId, Message, AuthorId, WrittenOn from Comment where ReviewId = '"+Id+"' order by WrittenOn asc;")
    results3 = csr3.fetchall()
    NumOfVotes = 0
    for result in results3:
        message = result[1].replace("\n", "<br>")
        authorId = result[2]
        if ReviewerFunctions.IsDecisionMaking(message) != 0:
            break
        if ReviewerFunctions.IsBotTest(authorId) == False and ReviewerFunctions.IsVote(message) != 0:
            NumOfVotes = NumOfVotes + 1
            #print message

    #print Id,averageCorrectness,total,days,NumOfUpdated,NumOfVotes
    print str(Id)+","+str(status)+","+str(claster)+","+str(averageCorrectness)+","+str(diff.total_seconds()/60/60/24)+","+str(updated)+","+str(NumOfVotes)

    #print str(lastWrittenOn)+"****"+str(createdOn)+"****"+str(updated)

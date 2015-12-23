#!/usr/bin/python3
##################
# Author:Toshiki Hirao
# CreatedOn: 2015/09/18
# Summary: This program is to define Functions for reviewer information.
##################
import re
import sys
import csv
import time
import MySQLdb
from collections import defaultdict
from datetime import datetime
from Class import ReviewerClass

### Define Functions
# JudgeVoteScore(m)
# @m:message
######
# If Score is '+1' -> return 1
# If Score is '-1' -> return -1
# If Score isn't '+1' or '-1' -> return 0
######
def IsVote(m): # (Regular expression)
    # Score +1
	p1 = re.compile(r'Patch Set [1-9]*: Looks good to me, but someone else must approve') #
	p2 = re.compile(r'Patch Set [1-9]*: Works for me')
	p3 = re.compile(r'Patch Set [1-9]*: Verified')
	p4 = re.compile(r'Patch Set [1-9]*: Sanity review passed') #
	p5 = re.compile(r'Patch Set [1-9]*: Code-Review\+1')
	p6 = re.compile(r'Patch Set [1-9]*: Workflow\+1')
	# Score +2
	p7 = re.compile(r'Patch Set [1-9]*: Looks good to me, approved') #
	p8 = re.compile(r'Patch Set [1-9]*: Looks good to me<br>')
	if p1.match(m) or p2.match(m) or p3.match(m) or p4.match(m) or p5.match(m) or p6.match(m) or p7.match(m) or p8.match(m):
		return 1

    # Score -1
	p1 = re.compile(r"Patch Set [1-9]*: I would prefer that you didn'*t submit this") #
	p2 = re.compile(r'Patch Set [1-9]*: Sanity problems found') #
	p3 = re.compile(r'Patch Set [1-9]*: Code-Review-1')
	p4 = re.compile(r'Patch Set [1-9]*: Workflow-1')
	p5 = re.compile(r"Patch Set [1-9]*: Doesn'*t seem to work")
	p6 = re.compile(r"Patch Set [1-9]*: I would prefer that you didn'*t merge this")
	p7 = re.compile(r'Patch Set [1-9]*: No score')
	# Score -2
	p8 = re.compile(r'Patch Set [1-9]*: Do not merge') #
	p9 = re.compile(r'Patch Set [1-9]*: Do not submit') #
	p10 = re.compile(r'Patch Set [1-9]*: Major sanity problems found') #
	p11 = re.compile(r'Patch Set [1-9]*: Fails') #
	if p1.match(m) or p2.match(m) or p3.match(m) or p4.match(m) or p5.match(m) or p6.match(m) or p7.match(m) or p8.match(m) or p9.match(m) or p10.match(m) or p11.match(m):
		return -1

    # Score 0
	"""
	p1 = re.compile(r'Patch Set [1-9]*: No score')
	if p1.match(m):
		return 0
	"""
	# No Score
	return 0

def IsReviewerClass(r, reviewer_class):
	assert r > 0
	if reviewer_class[r] != 0:
		return True
	else:
		#print "False"
		return False

def MakeReviewerClass(r, reviewer_class):
	assert r > 0 and reviewer_class[r] == 0
	reviewer_class[r] = ReviewerClass.Reviewer(r)
	#print type(ReviewerClass.Reviewer(r))
	return r

def IsCorrectVoting(r, s, judge):
	if (s > 0 and judge > 0) or (s < 0 and judge < 0):
		assert (s == 1 and judge == 2) or (s == -1 and judge == -2)
		return True
	else:
		assert (s == -1 and judge == 2) or (s == 1 and judge == -2)
		return False

# Judge whether this message is "Abandoned" or "Change has been successfully cherry picked", or not.
# @comment:message
def IsDecisionMaking(comment):
	# Negative
	pattern1 = re.compile(r'Patch Set 1: Abandoned') 					# "abandoned" about patchSet1
	pattern2 = re.compile(r'Uploaded patch set [1-9]*') #
	# Positive
	pattern3 = re.compile(r'Change has been successfully cherry-picked')

	if(pattern1.match(comment) or pattern2.match(comment)):
		return -1
	if(pattern3.match(comment)):
		return 1
	return 0

# Judge the author is Auto test machine or not
# @author:message author
def IsBotTest(author):
	### Qt
	# 1000049 -> Qt Sanity Bot
	# -1 	  -> Gerrit System
	# 1000060 -> Qt Continuous Integration System
	# 1000191 -> Qt Submodule Update Bot
	# 1002169 -> Qt Doc Bot

	###OpenStack
	# 3       -> Jenkins

	if(author == 1000049 or author == 3):
		return True	## The author is Auto Test.
	else:
		return False

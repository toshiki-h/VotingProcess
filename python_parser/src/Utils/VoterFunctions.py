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
from Class import VoterClass

def MakeVoterClass(authorId, score): # (Regular expression)
    return VoterClass.Voter(authorId, score)

def SearchVoterClass(authorId, authorList):
    for c in authorList:
        if c.id == authorId:
            return c
    return None

def HasSameVoteScore(Id, authorId, authorList, score):
    assert SearchVoterClass(authorId, authorList) != None
    voterClass = SearchVoterClass(authorId, authorList)
    if voterClass.vote == score:
        return True
    else:
        #if Id == "12338":
        #    print "HasVote/" + str(voterClass.vote) + ":::" + str(score)
        return False

def updateVoteScore(Id, authorId, authorList, score):
    assert SearchVoterClass(authorId, authorList) != None
    voterClass = SearchVoterClass(authorId, authorList)
    pre = voterClass.vote
    voterClass.vote = score
    #if Id == "12338":
    #    print "authorId:" + str(authorId) + "," + "Pre-score:" + str(pre) + ", Current-score:"+str(voterClass.vote)

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
for line in open(sys.argv[1], "r"):
    if index == 0:
        index = index + 1
        continue
    #reviewId, NumOfreviewers, averageCorrectness, status
    words = line.split(",")
    #words[0]==reviewId
    Id = words[0]
    ### Access into MySQLdb
    cnct = MySQLdb.connect(db="qt",user="root", passwd="hirao")
    csr1 = cnct.cursor()
    csr2 = cnct.cursor()
    csr1.execute("select ReviewId, Message, AuthorId, WrittenOn from Comment where ReviewId = '"+Id+"' order by WrittenOn desc limit 1;")
    csr2.execute("select ReviewId, CreatedOn, NumberOfPatches from Review where ReviewId = '"+Id+"';")
    results1 = csr1.fetchall()
    results2 = csr2.fetchall()
    assert len(results1) == 1
    assert len(results2) == 1
    for result in results1:
        lastWrittenOn = result[3]
    for result in results2:
        createdOn = result[1]
        updated = result[2]
    diff = lastWrittenOn - createdOn
    assert diff.total_seconds() >= 0
    #print total days, NumOfUpdated, NumOfVotes
    print str(diff.total_seconds()/60/60/24)+","+str(updated)

    #print str(lastWrittenOn)+"****"+str(createdOn)+"****"+str(updated)

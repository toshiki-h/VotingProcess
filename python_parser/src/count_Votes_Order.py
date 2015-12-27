#!/usr/bin/python3
##################
# Author:Toshiki Hirao
# CreatedOn: 2015/08/12
# Summary: count vote process from parse_Votes_Order.py
##################

### Import lib
import re
import sys
import csv
import time
import MySQLdb
from collections import defaultdict
from datetime import datetime

### main
i = 1
p = int(sys.argv[2])
n = int(sys.argv[3])
o = sys.argv[4]


idList = []
assert len(sys.argv) == 5
assert p >= 0 and n >= 0
assert "-c" in sys.argv[4] or "-r" in sys.argv[4]
# ReviewId, NumVotedAuthor, len(process), status(0:ab, 1:merged)
rows = 0
rows_me = 0
rows_ab = 0
for p in range(0,int(sys.argv[2])):
    row_all = 0
    for n in range(0,int(sys.argv[3])):
        me = 0
        ab = 0
        allCt = 0
        for line in open(sys.argv[1], "r"):
            words = line.strip().split(",")
            process = words[3:]
            #print process
            if process.count("1") == p and process.count("-1") == n:
                #print words[1]+":"+words[2]
                #print i
                idList.append(words[0])
                allCt = allCt + 1
                if words[2] == "merged": # Merged
                    me = me + 1
                if words[2] == "abandoned": # Abandoned
                    ab = ab + 1
        i = i + 1
        ### Output
        if "c" in o:
            print(" & %d,%d" % (me, ab)),
            #print("All:%d\nM:%d\nA:%d\nRe:%d\n" % (allCt, me, ab, re))
        if "-r" in o:
            print(idList)
        row_all = row_all + (me + ab)
        rows_me = rows_me + me
        rows_ab = rows_ab + ab
    print "\\ \hline " # + str(row_all)
    rows = rows + row_all
#print("All = %d, All_me = %d, All_ab = %d"  % (rows, rows_me, rows_ab))

#!/usr/bin/python3
##################
# Author:Toshiki Hirao
# CreatedOn: 2015/12/01
# Summary: This program is to count current judge and incurrent judge.
##################

#comment is not correct

### Import lib
import re
import sys
import csv
import time
import MySQLdb
from collections import defaultdict
from datetime import datetime

### Validation
assert len(sys.argv) == 2

### Connect DB
cnct = MySQLdb.connect(db="openstack",user="root", passwd="hirao") #openstack or qt
csr = cnct.cursor()

###
for line in open(sys.argv[1], "r"):
    words = line.split(",")
    Id = words[0]
    csr.execute("select ReviewId, Project from Review where ReviewId = '"+str(Id)+"';")
    info = csr.fetchall()
    assert len(info) == 1
    for inf in info:
        project = inf[1]
        print str(line.strip())+","+str(inf[1])

#!/usr/bin/python3
##################
# Author:Toshiki Hirao
# CreatedOn: 2015/09/18
# Summary: This program is to define class for reviewer information.
##################

### Class Difinition
class Voter:
    """ Init """
    def __init__(self, authorId, score):
        self.id = authorId;
        self.vote = score;

    """ Method """
    def updatVote(self, score):
        self.vote = score;

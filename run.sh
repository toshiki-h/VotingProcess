#!/bin/tcsh

mvn package -DskipTests=true
java -jar ./target/hirao-0.0.1-SNAPSHOT-jar-with-dependencies.jar testCsv.csv

#!/bin/bash

mkdir -p target

mvn package &> target/my-app-1.0-SNAPSHOT.jar-log.txt
java -cp target/app-1.0-SNAPSHOT.jar protectors.RPG

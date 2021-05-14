#!/bin/bash

mkdir -p target

mvn package &> target/my-app-1.0-SNAPSHOT.jar-log.txt
java -cp target/project-protectors-1.0-SNAPSHOT.jar io.github._13alazs.RPG

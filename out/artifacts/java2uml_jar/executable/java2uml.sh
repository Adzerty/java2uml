#!/bin/bash
javac -d ../fichierCompile ../fichierJava/*.java && 
java -Dfile.encoding=UTF-8 -jar java2uml.jar
#!/usr/bin/env zsh

javac -parameters -d ../fichierCompile ../fichierJava/*.java &&
java -Dfile.encoding=UTF-8 -jar java2uml_GUI.jar

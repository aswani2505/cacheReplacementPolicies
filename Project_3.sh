#!/bin/sh

echo "Compiling LRU and ARC.."

javac LRU.java
javac ARC.java

echo "Compilation Complete.."

echo "Running LRU.."
java LRU

echo "LRU Complete.."

echo "Running ARC.."
java ARC

echo "ARC Complete.."

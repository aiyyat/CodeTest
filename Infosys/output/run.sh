#!/bin/sh
java -jar Calculator-1.0-SNAPSHOT.jar "3 + 4" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "3 + 4 + 5" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "3 + 4 * 5" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "3 - 4 * 5" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "3 / 4 * 5" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "3 + 4 / 5" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "Sine 30" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "3 * Sine 30" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "2 ^ 4" >> output.txt
echo "------------------------------------------">>output.txt
java -jar Calculator-1.0-SNAPSHOT.jar "3 * Tan 45" >> output.txt
echo "------------------------------------------">>output.txt	
cat output.txt

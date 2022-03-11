set DIR_PROJECT=src/classes
del /s %DIR_BIN%\*.class >NUL
javac -d src/classes -sourcepath src src/main/*.java -encoding UTF8
cd src/classes
java main.Main -encoding UTF8

pause
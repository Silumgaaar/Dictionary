set DIR_PROJECT=src/classes
del /s %DIR_BIN%\*.class >NUL
chcp 1251
javac -d src/classes -sourcepath src src/main/*.java 
cd src/classes
java main.Main 

pause
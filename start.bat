set DIR_PROJECT=src/classes
del /s %DIR_BIN%\*.class >NUL
chcp 1251
javac -d src/classes -sourcepath src src/main/*.java
echo D|xcopy src\resources src\classes\resources /e
cd src/classes
java main.Main
pause
set DIR_PROJECT=classes
del /s %DIR_BIN%\*.class >NUL
chcp 1251
cd Main
javac -d ../classes *.java
cd..
cd classes
java Main.Main
pause
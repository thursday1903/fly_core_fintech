@echo off
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181
set Path=%JAVA_HOME%\bin;%Path%
TITLE = Application (Fly auto load cache process)
java -Xms32m -Xmx512m -jar -Xmx512m fly_core_fintech-0.0.1.jar
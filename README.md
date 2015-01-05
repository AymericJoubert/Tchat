Tchat
=====

A tchat in Java based on Client Server architecture (like MSN)


Manual :
--------

git clone repo
cd Tchat

Compile sources and launch software :
-------------------------------------

export $CLASSPATH="$HOME/.../Tchat/libs/postgresql-9.3-1102.jdbc41.jar"
javac -sourcepath sources -d classes sources/Client/*.java
javac -sourcepath sources -d classes sources/Server/*.java

java -classpath $CLASSPATH:classes Server.ServerTchat  # Server
java -classpath $CLASSPATH:classes Client.ClientTchat  # Client


Launch client via jar file :
----------------------------

java -jar ./jars/TchatLastVersion.jar

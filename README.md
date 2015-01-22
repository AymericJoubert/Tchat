Tchat
=====

A tchat in Java based on Client Server architecture (like MSN)


Done Featunes :
---------------

Registration <br/>


Next Steps :
---------------

Gui for User Registering <br/>
Possibility to add contacts <br/>
Configuration File <br/>
Launch via Jar File <br>
Style
Connection Pool <br/>
Data encryption <br/>


Used Libraries :
----------------
postgresql-9.3-1102.jdbc41.jar


Manual (Work In Progress) :
---------------------------

git clone repo <br/>
cd Tchat <br/>


Compile sources and launch software :
-------------------------------------

export $CLASSPATH="$HOME/.../Tchat/libs/postgresql-9.3-1102.jdbc41.jar" <br/>
javac -sourcepath sources -d classes sources/Client/*.java <br/>
javac -sourcepath sources -d classes sources/Server/*.java <br/>

java -classpath $CLASSPATH:classes Server.ServerTchat  # Server <br/>
java -classpath $CLASSPATH:classes Client.ClientTchat  # Client <br/>


Launch client via jar file :
----------------------------

java -jar ./jars/TchatLastVersion.jar <br/>

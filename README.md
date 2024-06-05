Chat Application

The current client server application was designed using Java and employs the use of threads and socket connections. It involves the use of 2 classes one for the server and one for the client. 
Both servers can be ran on the command line and can communicate with one another using. 
On the clients side, the user can input the port number of their choice, while the hostname however, is hardcoded in as the local host "127.0.0.1" as it is running on the same machine. 
This host was retrieved from an article from Medium, authored by Barhoumi, 2021. The client class also uses 2 threads to handle input from the server and output data to the server. 
The layout of this class was consulted from other resources mainly from the previously named, Barhoumi, Amit Gyawali, 2020, the Javadocs on the Socket class and Anas Khan, 2023. 
The class has a sender and a receiver thread with handles IO and both override the inherited run() method from Runnable. The programme then terminates when the user inputs the word exit.
The Server class was similar to the first and also implements the runnable interface and and overrides run(). 
This class consists of both a main and a “ClientHandler” class, where main contains the port number and then delegates the behaviour to the ClientHandler class. 
This class like the client class surrounds the reading in and writing out of data with a try catch statement and also uses a BufferedReader to read bytes into strings and uses PrintWriter to write bytes out to the client. 
This run class employs a while loop to keep the server open unless the clients message equal’s ‘exit’. After which it disconnects from the server, but the previous infinite loop in the main class will keep it listening on the port.
The design of this class was also referenced from previously named sources as well as geeksforgeeks on multithreaded servers and baeldung on their guide to java sockets. 
A majority of the design of this and the client class was coded using a combination of the lecture notes, video and provided code stubs, geeksforgeeks, Barhoumi and Gyawali’s articles on designing a socket connection.
Since users can choose the port, they would like to connect to, the port that the server is listening on is displayed to the user if they would like to connect to the local server port. 
If the user fails to input a valid port address the connection attempt will time out. If, however, if it has connected to the server but was interrupted it will be caught by the IOException block 
and a list of errors will be printed out to the user.

To Run

In order to run the programme, users must use their command line and change directory (cd) into the location of the application on their device. After which javac [Name of project].java should be inputted into the command line,
i.e. javac ClientConnection.java. Followed up by javac [Name of project].java i.e. javac ClientConnection.java. 
At this point the application has started up and the instructions will be displayed on screen i.e. it will ask for the port number of the users choosing and will also display the port number the server is listening on.

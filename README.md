# server-client-thread-java1

File trasnfer from server to client &amp; vice versa using socket connection using multi-threading

    				 READ_ME

This java code represents a simple implementation of client-server file transfer protocol. In this there is only single server and there can be any number of clients. A single server handles connections with the multiple clients. The code contains socket programming to establish connections. The server will be waiting on the port 5106 and the clients can connect to the server using this port number.
The server can handle multiple clients at the same time using threads. For each connection a thread is initiated and started. Each thread individually handles a client.
Running the programs:

- Run the server program on one terminal and run client program on two or more terminals so that we have multiple clients handled by the server.
- For establishing connection enter command “ ftpclient 5106” . On each client terminal run this command to establish individual connection with server.
- To retrieve a file from the server enter “ get filename”.
- To upload a file to the server enter “ upload filename”.

Commands :
Establishing connection with server – “ftpclient 5106”.
Request a file – “get filename”.
Upload a file – “upload filename”.

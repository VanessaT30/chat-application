package ie.atu.nt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection {
	public static void main(String[] args) throws IOException {
		String hostname = "127.0.0.1";
		int port;
		final Socket clientSocket; // socket used by client to send and recieve data from server
		final BufferedReader in; // object to read data from socket
		final PrintWriter out; // object to write data into socket
		final Scanner sc = new Scanner(System.in); // object to read data from user's keybord
			
		System.out.println("Please type in the port number of the server you would like to connect to: ");
		
		int serverPort = ServerConnection.port;
		System.out.println("Note: The port the server is listening on is: " + serverPort);
		port = sc.nextInt();
		// surrounded by try resources
		try  {
			// New socket object and connect to the server
			clientSocket = new Socket(hostname, port);
			clientSocket.setSoTimeout(15000);// Sets a timeout for the socket of 15 seconds			
			System.out.println("Server connected on host " + hostname + "type 'exit' to quit the application.");


			
			 // Initialised BufferedReader to read data from the socket's input stream
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// Initialised PrintWriter to write data to the socket's output stream and auto flushes
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			// New thread for communication being sent to the server
			Thread sender = new Thread(new Runnable() {
				String msg;

				// Method inherrited from Runnable being overriden
				@Override
				public void run() {
					// infinite loop until if statement condition is  met
					while (true) {
						System.out.println("If you would like to disconnect, please type 'exit'");
						System.out.print("Enter message to send to server: ");

						// reads input from the user
						msg = sc.nextLine();
						// Write the message to the socket's output stream
						out.println(msg);
						out.flush();	
						
			            if (msg.equals("exit")) {
			                break;  // exit the loop if user enters "exit"
			            }
					}
				}
			});
			// Thread started
			sender.start();

			// // New thread for communication being sent to the server
			Thread receiver = new Thread(new Runnable() {
				String msg;

				// Similar method to above with a new thread (recevier) running it
				@Override
				public void run() {
					try {
						// reads message from server
						msg = in.readLine();
						// Continue while there are messages from the server
						while (msg != null) {
							System.out.println("Server replied: " + msg);
							msg = in.readLine();						
						}
						System.out.println("Server out of service");
						// closing the printWriter and the clientSocket whrn finished
						out.close();
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			// Thread started
			receiver.start();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
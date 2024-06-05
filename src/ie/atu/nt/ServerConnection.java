package ie.atu.nt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ServerConnection {
	public static final int port = 6000;

	public static void main(String[] args) {

		// Infinite loop to listen for client connections
		try (ServerSocket serverSocket = new ServerSocket(port)) {

			while (true) {
				System.out.println("Server listening on port " + port);
				// socket object to receive incoming client requests
				Socket clientSocket = serverSocket.accept();
				System.out.println("Accepted new connection from " + clientSocket.getInetAddress() + ", port "
						+ clientSocket.getPort());
				System.out.println("If you would like to disconnect, please type 'exit'");

				// Create a new thread to handle the client
				// delegates work to Client Handler class
				Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket));
				clientHandlerThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//New class that handles communication with the client
class ClientHandler implements Runnable {
	private Socket clientSocket;
	final Scanner sc = new Scanner(System.in);

	// Constructor
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	// Inherited thread method run is overriden again
	@Override
	public void run() {
		PrintWriter output = null;
		BufferedReader input = null;

		try {
			// while listening get the input stream of the client
			// get the inputstream of client
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// get the outputstream of client and autoflushes all messages
			output = new PrintWriter(clientSocket.getOutputStream(), true);

			Date now = new Date();
			output.write("It is currently: " + now + "\r\n");

			String msg;
			// Read messages from the client until 'exit' is received
			while ((msg = input.readLine()) != "exit") {
				// writing the received message from client
				// print the message string to the console - print on new line

				System.out.println("Received from client: " + clientSocket.getInetAddress() + ": " + msg);

				// if the user types in "exit" it will disconnect
				if (msg.equals("exit")) {
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {// Close resources in the finally block
				if (output != null) {
					output.close();
				}
				if (input != null) {
					input.close();
					clientSocket.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

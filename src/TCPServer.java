/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishab
 */
import java.io.*;
import java.net.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable{
    
        static boolean running = false;
	public static void start() throws IOException {
            System.out.println(InetAddress.getLocalHost());
		int portNumber = 1504;
		System.out.println("Creating server socket on port " + portNumber);
		ServerSocket serverSocket = new ServerSocket(portNumber);
                running=true;
                Socket socket = serverSocket.accept();
		while (true) {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("What's you name?");

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = br.readLine();
                        if(str.contains("!"))
                        BarcodeInput.setInput(str.substring(str.lastIndexOf("!")+1));
                        else
                        BarcodeInput.setInput(str);
                        System.out.println(BarcodeInput.getInput());
		}
	}

    @Override
    public void run() {
        try{
                   start();
               }
               catch(Exception e)
               {
                e.printStackTrace();
               }
    }
}

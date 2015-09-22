/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author ngo
 */
public class Client implements Runnable {
    
    // Socket d'envoi et de retour de paquets
    private DatagramSocket datagramSocket;
        
    // Adresse IP du serveur à laquelle on va se connecter
    private InetAddress IPAddress;

    // Output
    private ObjectOutput out = null;
    private ByteArrayOutputStream baos = null;
	
    // Input
    private ObjectInputStream ois = null;
    private ByteArrayInputStream bais = null;
	
    private Socket socket = null;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
	
    public Thread thread;
    private boolean running = false;
    private String ip;
    private int port;
	
    public void start(String ip, int port) {
	this.ip = ip;
	this.port = port;
	running = true;
	thread = new Thread(this, "ClientThread");
	thread.start();
    }

    public void stop() {
	running = false;
        
        // Ferme les flux de sortie
	try {
            baos.close();
        } catch(Exception e) {}
	try {
            out.close();
        } catch(Exception e) {}
	try {
            datagramSocket.close();
        } catch(Exception e) {}
	
        // Ferme les flux d'entrée
	try {
            inputStream.close();
        } catch(Exception e) {}
	try {
            outputStream.close();
        } catch(Exception e) {}
	try {
            socket.close();
        } catch(Exception e) {}
        
	try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
		
    public void run() {
	try {
            socket = new Socket(ip, port);
            
            // Input and output streams
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            // Flushes this output stream and forces any buffered output bytes to be written out.
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            
            // System.out.println("Connected to server!");
			
            // Client reçoit le message
            Data data = (Data) inputStream.readObject();
            
            // Client envoie le message
            outputStream.flush();
	} catch(ConnectException e) {
            e.printStackTrace();
            stop();
	} catch(Exception e) {
            e.printStackTrace();
            stop();
	}
		
	try {
            inputStream.close();
        } catch(Exception e) {}
	try {
            outputStream.close();
        } catch(Exception e) {}
	try {
            socket.close();
        } catch(Exception e) {}
		
	try {
            // Dispositif socket
            datagramSocket = new DatagramSocket();
            // Adresse IP à laquelle on se connecte
            IPAddress = InetAddress.getByName(ip);
			
            Thread.sleep(2);
	} catch(Exception e) {
            e.printStackTrace();
            stop();
	}
		
	int cout = 0;
	while(running) {
            try {	
		// Envoyé par le serveur
		baos = new ByteArrayOutputStream();
		out = new ObjectOutputStream(baos);
		byte sentData[] = baos.toByteArray();
		DatagramPacket sendPacket = new DatagramPacket(sentData, sentData.length, IPAddress, port);
		datagramSocket.send(sendPacket);
				
		byte receiveData[] = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		datagramSocket.setSoTimeout(32);
		datagramSocket.receive(receivePacket);
		bais = new ByteArrayInputStream(receiveData);
		ois = new ObjectInputStream(bais);
				
		cout = 0;
				
		Thread.sleep(5);
            } catch(SocketTimeoutException e) {
		cout++;
		if(cout > 127) {
                    System.out.println("Server Disconnected!");
                    stop();
		}
            } catch(Exception e) {
		e.printStackTrace();
		stop();
            }
	}
    }
    
}

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
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author ngo
 */
public class Server implements Runnable {
    
    private DatagramSocket datagramSocket;
    private InetAddress IPAddress;
    
    // output
    private ObjectOutput out = null;
    private ByteArrayOutputStream baos = null;

    //input
    private ObjectInputStream ois = null;
    private ByteArrayInputStream bais = null;
	
    private ServerSocket server = null;
    private Socket socket = null;
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
	
    public Thread thread;
    private boolean running = false;
    private int port;

    public void start(int port) {
	this.port = port;
	running = true;
	thread = new Thread(this, "ServerThread");
	thread.start();
    }
	
    public void stop() {
	running = false;
	try {
            datagramSocket.close();
        } catch(Exception e) {}
	try {
            out.close();
        } catch(Exception e) {}
	try {
            baos.close();
        } catch(Exception e) {}
	try {
            ois.close();
        } catch(Exception e) {}
	try {
            bais.close();
        } catch(Exception e) {}
		
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
            server.close();
        } catch(Exception e) {}
	try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
    public void run() {
	try {			
            //System.out.println("Starting server on port " + port + ".");
            server = new ServerSocket(port);
            
            //System.out.println("Waiting for client to join...");
            socket = server.accept();

            System.out.println("Client joined!");
            
            // input and output streams
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream()); 
            outputStream.flush();
			
            // sever sends message
            outputStream.flush();
			
            // sever receive message

	} catch (IllegalArgumentException e) {
            e.printStackTrace();
            stop();
	} catch(BindException e) {
            e.printStackTrace();
            stop();
	} catch (Exception e) {
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
            server.close();
        } catch(Exception e) {}
		
	try {
            datagramSocket = new DatagramSocket(port);
	} catch (Exception e1) {
            e1.printStackTrace();
	}
		
	int cout = 0;
	boolean getAddress = true;
	while(running) {
            try {
		// Reçu du client
		byte receiveData[] = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		datagramSocket.setSoTimeout(32);
		datagramSocket.receive(receivePacket);
				
		if(getAddress) {
                    IPAddress = receivePacket.getAddress();
                    port = receivePacket.getPort();
                    getAddress = false;
		}
				
                cout = 0;
				
		bais = new ByteArrayInputStream(receiveData);
                ois = new ObjectInputStream(bais);
				
		Data data = (Data) ois.readObject();
				
		baos = new ByteArrayOutputStream();
		out = new ObjectOutputStream(baos);
		byte sendData[] = baos.toByteArray();
	            
	        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	        datagramSocket.send(sendPacket);
            } catch (SocketTimeoutException e) {
		cout++;
		if(cout > 127) {
                    System.out.println("Client Disconnected!");
                    stop();	
		}
            } catch (Exception e) {
		e.printStackTrace();
		stop();
            }
	}
	stop();
    }
    
}

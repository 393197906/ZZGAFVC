package com.massclouds.ns.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketUtil {
	
	/**
	 * 创建ServerSocket
	 * @param port
	 * @return
	 */
	public static ServerSocket getServerSocket(int port){
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("------ServerSocket创建成功，Port:"+port);
			return server;
		} catch (IOException e) {
			if(server!=null && !server.isClosed()){
				try {
					server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			throw new RuntimeException("创建ServerSocket时发生异常，Port:"+port,e);
		}
	}
	
	/**
	 * 获取Socket
	 * @param server
	 * @return
	 */
	public static Socket getSocket(ServerSocket server){
		Socket socket = null;
		try {
			socket = server.accept();
			System.out.println("------Socket连接成功，IP:"+socket.getInetAddress());
			return socket;
		} catch (IOException e) {
			if(socket!=null && !socket.isClosed()){
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			throw new RuntimeException("创建Socket时发送异常",e);
		}
	}
	
}
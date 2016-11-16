package com.massclouds.ns.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

@Service
public class SocketServer implements Runnable {  
    private int port = 4511;    //监听端口
    private boolean status = true;  
    private ServerSocket server = null;  
    
    //单列模式
    private static SocketServer so = null;
    public static SocketServer getObj(){
    	if(so==null){
    		System.out.println("初始化");
    		so = new SocketServer();
    	}
    	return so;
    }
    
    private SocketServer(){
    	new PolicyServer().startPolicy();
    	startSocket(); 
    }
    
    @Override  
    public void run() {  
        //创建Socket服务器  
        server = SocketUtil.getServerSocket(port);  
        while(status){  
            Socket socket = SocketUtil.getSocket(server);  
            new Thread(new SocketThread(socket,"UTF-8")).start();  
        }  
    }  
      
    /** 
     * 启动服务器 
     */  
    private void startSocket(){  
        new Thread(this).start();  
    }  
      
    /** 
     * 关闭服务器 
     */  
    public void stopSocket(){  
        status = false;  
        if(server!=null && !server.isClosed()){  
            try {  
                server.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    public static void main(String[] args) {
    	SocketServer.getObj();

	}
}  
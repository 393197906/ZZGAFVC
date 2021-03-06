//SocketBridge.hx
class SocketBridge {
 
         static var socket = new flash.net.Socket();
        
         static var jsScope;
         static var getMsg = "";

             static function main() {
                  
                   if (flash.external.ExternalInterface.available) {
                      
                   jsScope = flash.Lib.current.loaderInfo.parameters.scope;
                  
                   if (jsScope == null) {
                            jsScope = "";
                   } else {
                            jsScope += ".";
                   }
 
                   /* Calls the javascript load method once the SWF has loaded */
                   flash.external.ExternalInterface.call("setTimeout", jsScope + "loaded()");                    
                  
                   // Set event listeners for socket

                   // CONNECT
                   socket.addEventListener(flash.events.Event.CONNECT, function(e) : Void {
                                     trace("Connected to server");
                                     flash.external.ExternalInterface.call("setTimeout", jsScope + "connected()", 0);
                            }
                   );
 
                   // CLOSE
                   socket.addEventListener(flash.events.Event.CLOSE, function(e) : Void {
                                     trace("Disconnected from server");
                                     flash.external.ExternalInterface.call("setTimeout", jsScope + "disconnected()", 0);                               
                            }
                   );
                           
 
                   // IO ERROR
                   socket.addEventListener(flash.events.IOErrorEvent.IO_ERROR, function(e) : Void {
                                     trace("IOERROR : " +  e.text);
                                     flash.external.ExternalInterface.call("setTimeout", jsScope + "ioError('" + e.text + "')" ,0);
                            }
                   );
 
                   // SECURITY ERROR
                   socket.addEventListener(flash.events.SecurityErrorEvent.SECURITY_ERROR, function(e) : Void {
                                     trace("SECURITY ERROR : " +  e.text);
                                     flash.external.ExternalInterface.call("setTimeout", jsScope + "securityError('" +e.text+ "')", 0);
                            }
                   );
 
                   // SOCKET DATA
                   socket.addEventListener(flash.events.ProgressEvent.SOCKET_DATA, function(e) : Void {
                                               var msg = socket.readUTFBytes(socket.bytesAvailable);
                                               getMsg += msg;
                                               var endInfo = getMsg.substr(-5,5);
                                               if(endInfo=="!@#$%"){
                                                    trace("Received mama: " + msg.length );
                                                    flash.external.ExternalInterface.call("setTimeout", jsScope + "receive('" + getMsg + "')", 0);
                                                    getMsg="";
                                               }

                                     }
                            );
 
                   /* Set External Interface Callbacks */
                  
                   // connect(host, port)
                   flash.external.ExternalInterface.addCallback("connect", connect);                  
                  
                   // disconnect()
                   flash.external.ExternalInterface.addCallback("close", close);                                               
                  
                   // send()
                   flash.external.ExternalInterface.addCallback("send", send);           
                  
                   // log()
                   flash.external.ExternalInterface.addCallback("log", log);
 
             } else {
                   trace("Flash external interface not available");
             }  
 
    }
   
    /**
     * Connect to new socket server
     * @param host The host the socket server resides on
     * @param port The socket servers port
     */
    static function connect(host : String, port : String) {
             trace("Connecting to socket server at " + host + ":" + port); 
                   socket.connect(host, Std.parseInt(port));  
                 
    }
   
    /**
     * Disconnect the socket
     */
    static function close() {
             if (socket.connected) {
                  trace("Closing current connection");
                            socket.close();
                   } else {
                            trace("Cannot disconnect to server because there is no connection!");
                   }
    }
 
    /**
     * Write string to the socket
     */
    static function send(msg) {
             if (socket.connected) {
                      trace("WritingXielipeng '" + msg + "' to server");
                            //socket.writeMultiByte(msg,"gb2312");
                            socket.writeUTFBytes(msg);
                            socket.flush();
                   } else {
                            trace("Cannot write to server because there is no connection!");                 
                   }
    }   
 
    /**
     * Log
     */
    static function log(msg) {
             trace("log : "+msg);
    }  
 
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HiloChatDifuncion implements Runnable{
    Socket socketChatMultiple;
    private DataInputStream in;
    private DataOutputStream out;
    HashMap<String,Socket> clientes= new HashMap<String,Socket>();
    public HiloChatDifuncion(Socket accept,HashMap clientes) {
        this.socketChatMultiple = accept;
        this.clientes=clientes;
        
    }
    @Override
    public void run() {
        try {
            in = new DataInputStream(socketChatMultiple.getInputStream());//recibir las entradas que se produzcan de los clientes que se hayan conectado:
            out = new DataOutputStream(socketChatMultiple.getOutputStream());//enviarinformación al cliente
            out.writeUTF("Bienvenido al Multichat\n");
            
             while(true){
               String recibido = in.readUTF();//mensaje que lee del servidor
               //Cuando se recibe un mensaje se envia a todos los usuarios conectados 
                for (int i = 0; i < clientes.size(); i++) {
                     Object keys[]=clientes.keySet().toArray();
                    out = new DataOutputStream(clientes.get((String)keys[i]).getOutputStream());  
                    out.writeUTF((String)keys[i]+recibido+"");
                }
            }     
        } catch (IOException ex) {
            for (int i = 0; i < clientes.size(); i++) {
                if(clientes.get(i) == socketChatMultiple){
                    clientes.remove(i);
                    break;
                } 
            }
        }
    }
    public void start(){
        Thread t= new Thread(this);
        t.start();
        
    }

    
    
    
}

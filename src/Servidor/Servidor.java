/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {
    private ServerSocket serverSocket;
    private int port;
    
    public Servidor(int port) {
        this.port = port;
        conexionSokects();
    }
     private void conexionSokects(){
        try {
            serverSocket= new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Error de puerto");
        }
    }
     
     
    
}

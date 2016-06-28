/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Cliente implements Runnable{
        
    Socket cliente;
//    private int port;
//    private String serverName;
     private DataInputStream in; //stream de entrada que esté listo a recibir todas las respuestas que el servidor le envíe
     private DataOutputStream out; //stream de salida para enviar información al socket del servidor
     private String mensajes = "";
     private JTextArea panel;
     public Cliente(JTextArea panel){
         this.panel=panel;
         try {
            cliente= new Socket("localhost",9020);
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
            
        } catch (IOException ex) {
            System.out.println("error conexion cliente");
            ex.printStackTrace();
        }
    }
    @Override
    public void run() {
         try{
            //Ciclo infinito que escucha por mensajes del servidor y los muestra en el panel
            while(true){
                mensajes += in.readUTF();
                panel.setText(mensajes);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void enviarMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void star(){
        Thread t =new Thread(this);
        t.start();
    }
    
    
}

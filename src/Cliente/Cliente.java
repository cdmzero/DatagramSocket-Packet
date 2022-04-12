/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;


import java.net.*;
import java.io.*;

public class Cliente {


  public static void main(String args[]) throws SocketException {

  DatagramSocket dsc = new DatagramSocket();
  
  System.out.println("Socket cerrado?"+ dsc.isClosed());
  
  byte[] bufferSalida = new byte[512];
  // Declaramos el puerto del servidor
  int puertoServidorRemoto = 6789;
          try {
            // Componemos el mensaje
            String mensaje = "Hola soy tu vecino";
            // Lo pasamos al buffer en bytes
            bufferSalida = mensaje.getBytes();
            
            
            // Datos del Servidor
            // Declaramos la direccion del host local
            InetAddress hostServidorRemoto = InetAddress.getByName("localhost"); // Devuelve la IP del host local
            // Declaramos la direccion del servidor
            //InetAddress hostServidorRemoto = hostLocal; // Por motivos obvios estamos en la misma maquina haciendo las pruebas
            
            // Declaramos el datagrama a enviar al servidor
            DatagramPacket datagramaSalida = new DatagramPacket(bufferSalida, bufferSalida.length, hostServidorRemoto,puertoServidorRemoto);
            // Enviamos el datagrama
            dsc.send(datagramaSalida);
            // Notificamos del envio
            System.out.println("Mensaje enviado al servidor");
            
  
            //MENSAJE ENTRANTE

            // Declaramos y reservamos el buffer para alamcenar el datagrama entrante
            byte[] buferRecepcion = new byte[1024];
            // Declaramos el datagrama a recibir y el buffer que lo almacena
            DatagramPacket datagramaEntrada = new DatagramPacket(buferRecepcion, buferRecepcion.length);
            // Recibimos el datagrama
            dsc.receive(datagramaEntrada);
            // Notificamos de la recepcion
            System.out.println("Nuevo mensaje recibido");
            // Pasamos el buffer en bytes a String el datagrama recibido
            String MensajeEntrante = new String(datagramaEntrada.getData());
            // Mostramos el mensaje
            System.out.print("Cuerpo del mensaje: " + MensajeEntrante +"\n fin");
            // Cerramos el socket
        
          dsc.close();
          
          dsc = null;
            
      
    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}
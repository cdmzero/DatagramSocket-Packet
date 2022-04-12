/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.net.*;
import java.io.*;

public class Servidor {

  public static void main (String args[]) {

      
     
    try {
      
      // Exponemos el puerto local para recibir datagramas
      DatagramSocket ds = new DatagramSocket(6789);

      
      //Declaramos y reservamos el buffer para almacenar datagramas
      byte[] bufferRecepcion = new byte[512];

      
      //Esperamos a que nos envien datagramas
      
        System.out.println("Esperando por mensajes...");
      while (true) {
        
         // Declaramos el datagrama a recibir y el buffer que lo almacena
        DatagramPacket datagramaEntrada = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

        // Recibimos el datagrama
        ds.receive(datagramaEntrada);

        // Conseguimos la info del host recibido
        System.out.print("Datagrama recibido del host: " + datagramaEntrada.getAddress());
        System.out.println(" desde el puerto remoto: " + datagramaEntrada.getPort());

        // Declaramos el datagrama de salida a partir de los datos del detagrama de Entrada 
        DatagramPacket datagramaSalida = new DatagramPacket(datagramaEntrada.getData(),datagramaEntrada.getLength(), datagramaEntrada.getAddress(), datagramaEntrada.getPort());

        // Enviamos el datagrama de salida, que en sí es un clon del datagrama de entrada
        ds.send(datagramaSalida);
        
        System.out.println("Mensaje reenviado al cliente");
        ds.close();
        
     
      }

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }

}
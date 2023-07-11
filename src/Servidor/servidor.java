/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

/**
 *
 * @author DELL
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;
import Conexion.*;
import java.util.LinkedList;

public class servidor {
    static int PUERTO = 5000;
    ServerSocket sc;
    Socket so;
    DataOutputStream salida;
    DataInputStream entrada;
    String mensajeRecibido, respuesta;
    Comando comando;
    public void initServidor(){
        
        Scanner teclado = new Scanner(System.in);
        try{
            sc = new ServerSocket(PUERTO);
            so = new Socket();
            
            System.out.println("Esperando conexión...");
            so = sc.accept();
            System.out.println("Se conecto uno...");
            entrada = new DataInputStream(so.getInputStream());
            salida = new DataOutputStream(so.getOutputStream());
            String msn = "";
            while(!msn.equals("x")){
                
                mensajeRecibido = entrada.readUTF();//Leemos respuesta
                LinkedList<String> mensaje=new LinkedList<String>();
                mensaje.add(mensajeRecibido);
                System.out.println(mensajeRecibido);
                System.out.println("Escriba un msn para enviar");
                msn = teclado.nextLine();
                salida.writeUTF(""+msn);//enviamos mensaje

            }
            sc.close();
        }catch(Exception e){

        }
        }

    public static void main(String[] args){
        servidor o = new servidor();
        o.initServidor();
    }
}

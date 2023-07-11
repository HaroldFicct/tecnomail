 package Servidor;

import Conexion.Comando;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

public class Servicio extends Thread {

    private boolean isRunning;

    public Servicio() {
        this.isRunning = false;
    }

    public void iniciarAnalizador() {
        System.out.println("-- ANALIZADOR INICIADO --");
        this.isRunning = true;
        this.start();
    }

    public void deteneraAnalizador() {
        this.isRunning = false;
        this.stop();
        System.out.println("-- ANALIZADOR DETENIDO --");
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        Comando a = new Comando();
        a.setStat(); //obtiene la cantidad de mensajes recibidos
        int statAnterior = a.getStat();  //obtiene la cantidad de los mensajes recibidos
        while (true) {
            try {
                Thread.sleep(1500);//1.5s
                if (statAnterior < a.getStat()) {//61<62
                    statAnterior++;
                    a.setStat(statAnterior);
                    a.setFrom();
                    a.separar();
                    a.escoger();
                    
                }
                a.setStat();
            } catch (InterruptedException | MessagingException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
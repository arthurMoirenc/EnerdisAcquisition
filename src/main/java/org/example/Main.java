package org.example;

import LiaisonSerie.LiaisonSerie;
import jssc.SerialPortException;
import org.example.clavier.In;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws InterruptedException, SerialPortException {
        System.out.println("Le numero de l'esclave : ");
        org.example.ModBus modbus ;
        modbus= new org.example.ModBus(In.readByte());
        System.out.println("Le COM ?");
        modbus.connecterEsclave(In.readString(),9600,8,0,1);
        try {
            while (true) {
                try {
                    float resultatFrequence = modbus.lectureFrequence();
                    float resultatTension = modbus.lectureTension();
                    float resultatPuissance = modbus.lecturePuissance();
                    float resultatIntensite = modbus.lectureIntensite();

                    LocalDateTime dateHeureActuelle = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    Donnee donneeFrequence = new Donnee(dateHeureActuelle.format(formatter), resultatFrequence,"HZ");
                    DonneeDAO.addDonnee(donneeFrequence);

                    Donnee donneeTension = new Donnee(dateHeureActuelle.format(formatter), resultatTension,"V");
                    DonneeDAO.addDonnee(donneeTension);

                    Donnee donneePuissance = new Donnee(dateHeureActuelle.format(formatter), resultatPuissance,"KW");
                    DonneeDAO.addDonnee(donneePuissance);

                    Donnee donneeIntensite = new Donnee(dateHeureActuelle.format(formatter), resultatIntensite,"A");
                    DonneeDAO.addDonnee(donneeIntensite);

                    Thread.sleep(60000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
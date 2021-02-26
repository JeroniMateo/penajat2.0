package com.jaume.penjat;

import java.awt.*;

public class Penjat {

    public static void main(String[] args) {
        Tauler tauler = new Tauler();
        Puntuacio puntuacio = new Puntuacio();
        float total = 0f;
        int partides = 0;
        String participant = "";
        boolean continuar = true;

        participant = Util.makeQuestion("Com te noms? ");

        while (continuar ){
            String dificultat = Util.makeQuestion("Selecciona la dificultat");
            while(!Util.isNumeric(dificultat)){
                dificultat = Util.makeQuestion("Selecciona la dificultat");
            }
            String paraula = puntuacio.getParaulaSecretaDificultat(Integer.parseInt(dificultat));
            tauler.inicialitzarPartida(paraula, puntuacio.getIntents());
            System.out.println(tauler.imprimir());
            while (!tauler.hasGuanyat() && tauler.getIntents() > 0){
                System.out.println(tauler.imprimirVides());
                String letra = Util.makeQuestion("Indica la lletra");
                System.out.println(tauler.verificar(letra));
                System.out.println(tauler.imprimir());
            }

            if (tauler.hasGuanyat()) {
                System.out.println("Ho has encertat");
            }else{
                System.out.println("Has perdut");
            }
            float p = puntuacio.calcularPuntuacio(tauler.getPalabraEndevinada(),tauler.getIntents());
            System.out.println("Has tardat "+puntuacio.getTemps() +" segons");
            System.out.println(participant+ " has aconseguit "+p +" punts");
            total += p;
            partides++;
            continuar = (Util.makeQuestion("Vols tornar a jugar? (si/no)").equals("si"));
        }

        System.out.println("");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Has aconseguit un total de  "+total +" punts amb "+partides+" partides.");
        System.out.println("-----------------------------------------------------------------------");
    }

}

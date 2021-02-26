package com.jaume.penjat;

public class Tauler {

    private char[] paraulaSecreta;
    private String[] palabraEndevinada;
    private int intents;
    private int totalIntents;

    public char[] getParaulaSecreta() {
        return paraulaSecreta;
    }

    public int getIntents() {
        return intents;
    }

    public int getTotalIntents() {
        return totalIntents;
    }

    public String[] getPalabraEndevinada() {
        return palabraEndevinada;
    }

    public void setPalabraEndevinada(String[] palabraEndevinada) {
        this.palabraEndevinada = palabraEndevinada;
    }

    public void setIntents(int intents) {
        this.intents = intents;
    }

    public void setParaulaSecreta(char[] paraulaSecreta) {
        this.paraulaSecreta = paraulaSecreta;
    }

    public void setTotalIntents(int totalIntents) {
        this.totalIntents = totalIntents;
    }

    public Tauler() {
        paraulaSecreta = new char[0];
        palabraEndevinada = new String[0];
        intents = 0;
        totalIntents = 0;
    }

    public void inicialitzarPartida(String p, int i){
        paraulaSecreta = new char[p.length()];
        for (int n = 0; n < p.length(); n++) {
            paraulaSecreta[n] = p.charAt(n) ;
        }
        palabraEndevinada = new String[p.length()];
        for (int n = 0; n < p.length(); n++) {
            if(p.charAt(n) == new Character(' ')){
                palabraEndevinada[n] = " " ;
            }
        }
        intents = i;
        totalIntents = i;

    }

    public String imprimir() {
        String result = "";
        for (int i = 0; i < palabraEndevinada.length; i++) {
            if (palabraEndevinada[i] == null) {
                result = result+"_";
            }else if(palabraEndevinada[i] == " "){
                result = result+" ";
            }else {
                result = result+palabraEndevinada[i];
            }
        }
        return result;
    }

    public String verificar(String letra) {
        if(letra.length() > 1){
            return "Lletra incorrecte";
        }else{
            boolean exist = false;
            for (int i = 0; i < paraulaSecreta.length; i++) {
                if (paraulaSecreta[i] == letra.charAt(0)) {
                    exist = true;
                    palabraEndevinada[i] = letra;
                }
            }
            if(!exist){
                restarIntent();
            }
        }
        return"";
    }

    public String imprimirVides(){
        String message = String.format("Et queden %s vides de %s",intents, totalIntents);
        if(intents == 1) message = String.format("Et queda %s vida de %s",intents, totalIntents);
        return message;
    }

    public void restarIntent() {
        this.intents--;
    }

    public boolean hasGuanyat() {
        boolean valor = true;
        for (int i = 0; i < palabraEndevinada.length; i++) {
            if (palabraEndevinada[i] == null) {
                return false;
            }
        }
        return valor;
    }

}

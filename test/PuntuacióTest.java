package test;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.jaume.penjat.Puntuacio;
import com.jaume.penjat.Tauler;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class PuntuacióTest {

    Tauler  tauler = new Tauler();
    Puntuacio puntuacio = new Puntuacio();


    void reiniciar(int dificultat) {
        String paraula = puntuacio.getParaulaSecretaDificultat(dificultat);
        tauler.inicialitzarPartida(paraula, tauler.getIntents());
        for (int i = 0; i < tauler.getParaulaSecreta().length; i++) {
            tauler.verificar(String.valueOf(paraula.charAt(i)));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void getParaulaSecretaDificultat(int dificultat) {
        puntuacio.getParaulaSecretaDificultat(dificultat);
        int vides = 0;
        switch (dificultat) {
            case 1:
                dificultat = 1;
                vides = 5;
                break;
            case 2:
                dificultat = 2;
                vides = 4;
                break;
            case 3:
                dificultat = 3;
                vides = 3;
                break;
            default:
                break;
        }
        if (dificultat == 1 || dificultat == 2 || dificultat == 3) {
            Assertions.assertEquals(vides, puntuacio.getIntents());
        } else {
            Assertions.assertEquals("error", puntuacio.getParaula());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void calcularPuntuacioVictoria(int dificultat) {
        reiniciar(dificultat);
        float puntsGuanyats = puntuacio.calcularPuntuacio(tauler.getPalabraEndevinada(), puntuacio.getIntents());
        float puntsFinals = 1000f;
        float percentatge;
        float puntsTemps = 100f;
        switch (dificultat) {
            case 1:
                dificultat = 1;
                percentatge = 0.1f;
                puntsTemps *= puntuacio.getDificultat();
                if (Arrays.stream(puntuacio.getLetresBonus()).anyMatch(puntuacio.getParaula()::contains)) {
                    percentatge += 0.01f;
                    puntsFinals *= percentatge;
                    puntsFinals += puntsTemps;
                    Assertions.assertEquals(puntsFinals, puntsGuanyats);
                } else {
                    puntsFinals *= percentatge;
                    puntsFinals += puntsTemps;
                    Assertions.assertEquals(puntsFinals, puntsGuanyats);
                }
                break;
            case 2:
                dificultat = 2;
                percentatge = 0.2f;
                puntsTemps *= puntuacio.getDificultat();
                if (Arrays.stream(puntuacio.getLetresBonus()).anyMatch(puntuacio.getParaula()::contains)) {
                    percentatge += 0.01f;
                    puntsFinals *= percentatge;
                    puntsFinals += puntsTemps;
                    Assertions.assertEquals(puntsFinals, puntsGuanyats);
                } else {
                    puntsFinals *= percentatge;
                    puntsFinals += puntsTemps;
                    Assertions.assertEquals(puntsFinals, puntsGuanyats);
                }
                break;
            case 3:
                dificultat = 3;
                percentatge = 0.3f;
                puntsTemps *= puntuacio.getDificultat();
                if (Arrays.stream(puntuacio.getLetresBonus()).anyMatch(puntuacio.getParaula()::contains)) {
                    percentatge += 0.01f;
                    puntsFinals *= percentatge;
                    puntsFinals += puntsTemps;
                    Assertions.assertEquals(puntsFinals, puntsGuanyats);
                } else {
                    puntsFinals *= percentatge;
                    puntsFinals += puntsTemps;
                    Assertions.assertEquals(puntsFinals, puntsGuanyats);
                }
                break;
            default:
                break;
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void calcularPercentage(int dificultat) {
        Float percentatge = 0f;
        puntuacio.getParaulaSecretaDificultat(dificultat);
        Float nivell = (float) puntuacio.getDificultat() / 10;
        switch (dificultat) {
            case 1:
                dificultat = 1;
                percentatge = percentatge + 0.1f;
                break;
            case 2:
                dificultat = 2;
                percentatge = percentatge + 0.2f;
                break;
            case 3:
                dificultat = 3;
                percentatge = percentatge + 0.3f;
                break;
            default:
                break;
        }
        Assertions.assertEquals(percentatge, nivell);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void comprovarPuntuacioError(int dificultat) {
        this.puntuacio.setDificultat(dificultat);
        String[] paraules1 = {"paraula"};
        Float puntuacioFinal = 10f * this.puntuacio.getDificultat() * paraules1[0].length();
        Assertions.assertEquals(puntuacioFinal, this.puntuacio.calcularPuntuacio(new String[]{paraules1[0]}, this.puntuacio.getIntents()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void comprovarIniciTemps(int dificultat) {
        this.puntuacio.getParaulaSecretaDificultat(dificultat);
        long inici = System.currentTimeMillis();
        Assertions.assertEquals(inici, this.puntuacio.getInici());
    }
}

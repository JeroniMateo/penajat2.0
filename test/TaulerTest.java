package test;

import com.jaume.penjat.Tauler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaulerTest {

    private Tauler tauler;

    @BeforeEach
    void reiniciar(){
        this.tauler = new Tauler();
        this.tauler.inicialitzarPartida("paraula", 4);
    }

    @Test
    void inicialitzarPartidaParaulaSecreta() {
        assertArrayEquals(new char[]{'p', 'a', 'r', 'a', 'u', 'l', 'a'}, this.tauler.getParaulaSecreta());
    }

    @Test
    void inicialitzarPartidaNombreIntents() {
        assertEquals(4, this.tauler.getIntents());
    }

    @Test
    void verificarEntradaIncorrecte() {
        assertEquals("Lletra incorrecte",this.tauler.verificar("ll"));
    }

    @Test
    void verificarEntradaCorrecteEncertat() {
        this.tauler.verificar("a");
        assertArrayEquals(new String[]{ null,"a",null,"a",null,null,"a" },this.tauler.getPalabraEndevinada());
    }

    @Test
    void verificarEntradaCorrecteErrada() {
        this.tauler.verificar("n");
        assertEquals(3,this.tauler.getIntents());
    }

    @Test
    void imprimirCapEncert() {
        assertEquals("_______", this.tauler.imprimir());
    }

    @Test
    void imprimirAmbLletres() {
        this.tauler.verificar("a");
        assertEquals("_a_a__a", this.tauler.imprimir());
    }

    @Test
    void imprimirTotEncertat() {
        this.tauler.verificar("a");
        this.tauler.verificar("p");
        this.tauler.verificar("r");
        this.tauler.verificar("u");
        this.tauler.verificar("l");
        assertEquals("paraula", this.tauler.imprimir());
    }



    @Test
    void imprimirVidesPlural() {
        assertEquals("Et queden 4 vides de 4", this.tauler.imprimirVides());
    }

    @Test
    void imprimirVidesSingular() {
        this.tauler.verificar("n");
        this.tauler.verificar("n");
        this.tauler.verificar("n");
        assertEquals("Et queda 1 vida de 4", this.tauler.imprimirVides());
    }

    @Test
    void restarIntents() {
        this.tauler.verificar("n");
        assertEquals(3, this.tauler.getIntents());
    }

    @Test
    void hasGuanyatTrue() {
        this.tauler.verificar("a");
        this.tauler.verificar("p");
        this.tauler.verificar("r");
        this.tauler.verificar("u");
        this.tauler.verificar("l");
        assertTrue(this.tauler.hasGuanyat());
    }

    @Test
    void hasGuanyatFalse() {
        assertFalse(this.tauler.hasGuanyat());
    }
}
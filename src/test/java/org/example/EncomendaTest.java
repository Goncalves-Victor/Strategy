package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncomendaTest {

    @Test
    void deveCalcularFreteCorreios() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFreteCorreios(10.0f, 5.0f);
        assertEquals(25.0f, encomenda.getFrete(), 0.001f);
    }

    @Test
    void deveCalcularFreteDHL() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFreteDHL(10.0f, 5.0f);
        assertEquals(43.0f, encomenda.getFrete(), 0.001f);
    }

    @Test
    void deveCalcularFreteFedEx() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFreteFedEx(10.0f, 5.0f);
        assertEquals(37.5f, encomenda.getFrete(), 0.001f);
    }

    @Test
    void deveAceitarPesoNoLimiteDosCorreios() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFreteCorreios(10.0f, 30.0f);
        assertEquals(75.0f, encomenda.getFrete(), 0.001f);
    }

    @Test
    void naoDeveAceitarPesoAcimaDoLimiteDosCorreios() {
        Encomenda encomenda = new Encomenda();
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> encomenda.calcularFreteCorreios(10.0f, 30.1f));
        assertEquals("Peso acima do limite dos Correios", erro.getMessage());
    }

    @Test
    void naoDeveAceitarDistanciaOuPesoInvalidos() {
        float[] invalidos = {-1.0f, Float.NaN, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY};
        for (float valor : invalidos) {
            Encomenda encomenda = new Encomenda();
            assertThrows(IllegalArgumentException.class,
                    () -> encomenda.calcularFreteCorreios(valor, 5.0f));
            assertThrows(IllegalArgumentException.class,
                    () -> encomenda.calcularFreteDHL(10.0f, valor));
        }
    }

    @Test
    void deveAceitarDistanciaEPesoZero() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFreteCorreios(0.0f, 0.0f);
        assertEquals(10.0f, encomenda.getFrete(), 0.001f);
    }

    @Test
    void deveTrocarDeTransportadoraNaMesmaEncomenda() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFreteCorreios(10.0f, 5.0f);
        assertEquals(25.0f, encomenda.getFrete(), 0.001f);
        encomenda.calcularFreteDHL(10.0f, 5.0f);
        assertEquals(43.0f, encomenda.getFrete(), 0.001f);
    }

    @Test
    void deveCalcularFreteComEstrategiaInformada() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFrete(10.0f, 5.0f, new FreteFedEx());
        assertEquals(37.5f, encomenda.getFrete(), 0.001f);
    }

    @Test
    void deveDelegarDistanciaEPesoParaEstrategiaPersonalizada() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFrete(10.0f, 5.0f, (distanciaKm, pesoKg) -> {
            assertEquals(10.0f, distanciaKm);
            assertEquals(5.0f, pesoKg);
            return 12.5f;
        });
        assertEquals(12.5f, encomenda.getFrete(), 0.001f);
    }

    @Test
    void naoDeveAceitarTransportadoraNula() {
        Encomenda encomenda = new Encomenda();
        assertThrows(IllegalArgumentException.class,
                () -> encomenda.calcularFrete(10.0f, 5.0f, null));
    }

    @Test
    void devePreservarFreteAnteriorQuandoNovoCalculoFalhar() {
        Encomenda encomenda = new Encomenda();
        encomenda.calcularFreteDHL(10.0f, 5.0f);
        assertThrows(IllegalArgumentException.class,
                () -> encomenda.calcularFreteCorreios(10.0f, 31.0f));
        assertEquals(43.0f, encomenda.getFrete(), 0.001f);
    }
}

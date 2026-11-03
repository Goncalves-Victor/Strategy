package org.example;

public class Encomenda {

    private float frete;

    public float getFrete() {
        return frete;
    }

    public void calcularFreteCorreios(float distanciaKm, float pesoKg) {
        calcularFrete(distanciaKm, pesoKg, new FreteCorreios());
    }

    public void calcularFreteDHL(float distanciaKm, float pesoKg) {
        calcularFrete(distanciaKm, pesoKg, new FreteDHL());
    }

    public void calcularFreteFedEx(float distanciaKm, float pesoKg) {
        calcularFrete(distanciaKm, pesoKg, new FreteFedEx());
    }

    public void calcularFrete(float distanciaKm, float pesoKg, CalculoFrete calculo) {
        CalculadoraFrete calculadora = new CalculadoraFrete(distanciaKm, pesoKg);
        this.frete = calculadora.calcular(calculo);
    }
}

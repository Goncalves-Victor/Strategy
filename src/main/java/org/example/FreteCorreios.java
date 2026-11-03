package org.example;

public class FreteCorreios implements CalculoFrete {

    @Override
    public float calcular(float distanciaKm, float pesoKg) {
        if (pesoKg > 30.0f) {
            throw new IllegalArgumentException("Peso acima do limite dos Correios");
        }
        return 10.0f + 0.5f * distanciaKm + 2.0f * pesoKg;
    }
}

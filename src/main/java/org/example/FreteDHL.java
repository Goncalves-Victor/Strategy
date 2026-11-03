package org.example;

public class FreteDHL implements CalculoFrete {

    @Override
    public float calcular(float distanciaKm, float pesoKg) {
        return 20.0f + 0.8f * distanciaKm + 3.0f * pesoKg;
    }
}

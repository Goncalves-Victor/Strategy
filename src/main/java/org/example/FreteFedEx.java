package org.example;

public class FreteFedEx implements CalculoFrete {

    @Override
    public float calcular(float distanciaKm, float pesoKg) {
        return 18.0f + 0.7f * distanciaKm + 2.5f * pesoKg;
    }
}

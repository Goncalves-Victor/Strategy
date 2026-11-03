package org.example;

public class CalculadoraFrete {

    private final float distanciaKm;
    private final float pesoKg;

    public CalculadoraFrete(float distanciaKm, float pesoKg) {
        if (!Float.isFinite(distanciaKm) || !Float.isFinite(pesoKg)
                || distanciaKm < 0 || pesoKg < 0) {
            throw new IllegalArgumentException("Distância e peso devem ser finitos e não negativos");
        }
        this.distanciaKm = distanciaKm;
        this.pesoKg = pesoKg;
    }

    public float calcular(CalculoFrete calculo) {
        if (calculo == null) {
            throw new IllegalArgumentException("Informe uma transportadora");
        }
        return calculo.calcular(distanciaKm, pesoKg);
    }
}

package com.models;

public class Camion extends Vehiculo {
    public Camion(String matricula, String marca, String modelo, String carroceria, String combustible, 
                  double consumo, Integer plazas, double kilometros, double precioCompra, boolean alquilado) {
        super(matricula, "camion", marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precioCompra);
    }
}


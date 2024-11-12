package com.models;

public class Coche extends Vehiculo {
    public Coche(String matricula, String marca, String modelo, String carroceria, String combustible, 
                 double consumo, double plazas, double kilometros, double precioCompra, boolean alquilado) {
        super(matricula, "coche", marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precioCompra, alquilado);
    }
}


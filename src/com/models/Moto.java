package com.models;

public class Moto extends Vehiculo {
    public Moto(String matricula, String marca, String modelo, String carroceria, String combustible, 
                double consumo, double kilometros, double precioCompra, boolean alquilado) {
        super(matricula, "moto", marca, modelo, carroceria, combustible, consumo, 0, kilometros, precioCompra);
    }
}


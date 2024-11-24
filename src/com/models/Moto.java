package com.models;

/**
 * Clase que representa una moto
 * 
 * Proporciona el constructor específico para las motos
 * 
 * 
 * 
 * @author [Sara MJ]
 * @version 1.0
 */
public class Moto extends Vehiculo {
    public Moto(String matricula, String marca, String modelo, String carroceria, String combustible, 
                double consumo, double kilometros, double precioCompra, boolean alquilado) {
        super(matricula, "moto", marca, modelo, carroceria, combustible, consumo, 0, kilometros, precioCompra, false);
    }
}


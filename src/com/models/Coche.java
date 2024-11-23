package com.models;

/**
 * Clase que representa un coche
 * 
 * Proporciona el constructor específico para los coches
 * 
 * 
 * 
 * @author [Sara MJ]
 * @version 1.0
 */
public class Coche extends Vehiculo {
    public Coche(String matricula, String marca, String modelo, String carroceria, String combustible, 
                 double consumo, Integer plazas, double kilometros, double precioCompra, boolean alquilado) {
        super(matricula, "coche", marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precioCompra);
    }
}


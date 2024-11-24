package com.models;

/**
 * Clase que representa un camion
 * 
 * Proporciona el constructor específico para los camiones
 * 
 * 
 * 
 * @author [Sara MJ]
 * @version 1.0
 */
public class Camion extends Vehiculo {
    public Camion(String matricula, String marca, String modelo, String carroceria, String combustible, 
                  double consumo, Integer plazas, double kilometros, double precioCompra, boolean alquilado) {
        super(matricula, "camion", marca, modelo, carroceria, combustible, consumo, plazas, kilometros, precioCompra, false);
    }
}


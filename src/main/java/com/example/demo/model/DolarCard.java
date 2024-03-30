package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DolarCard {
    private String moneda;
    private String casa;
    private String nombre;
    private double compra;
    private double venta;
    private LocalDateTime fechaActualizacion;

    public double getPromedio() {
        return((getCompra() + getVenta())/2);
    }
}

package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//Los 2 constructores uno con argumentos y uno vacio
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Flight {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String origin;
   private String destiny;
   private String departureTime;
   private String arrivingTime;
   private double price;
   private String frequency;

   //muchos a uno, primero lo externo
   @ManyToOne
   //con que atributo se van a relacionar
   @JoinColumn(name = "company_id")
   private Company company;


   public Flight(String origin, String destiny, String departureTime, String arrivingTime, double price, String frequency) {
      this.origin = origin;
      this.destiny = destiny;
      this.departureTime = departureTime;
      this.arrivingTime = arrivingTime;
      this.price = price;
      this.frequency = frequency;
   }

}
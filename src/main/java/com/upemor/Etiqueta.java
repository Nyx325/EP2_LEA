package com.upemor;

public class Etiqueta {
  private Vertice vertice;
  private double costo;

  public Etiqueta(Vertice vertice, double costo) {
    this.vertice = vertice;
    this.costo = costo;
  }

  public Vertice getVertice() {
    return vertice;
  }

  public void setVertice(Vertice vertice) {
    this.vertice = vertice;
  }

  public double getCosto() {
    return costo;
  }

  public void setCosto(double costo) {
    this.costo = costo;
  }
}

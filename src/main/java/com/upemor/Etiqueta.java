package com.upemor;

public class Etiqueta {
  private Vertice vertice;
  private double costo;
  private Dias dia;

  public Etiqueta(Vertice vertice, double costo, Dias dia) {
    this.vertice = vertice;
    this.costo = costo;
    this.dia = dia;
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

  @Override
  public String toString() {
    return "[" + vertice + "," + costo + "," + dia + "]";
  }
}

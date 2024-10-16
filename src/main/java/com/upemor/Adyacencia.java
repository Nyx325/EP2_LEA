package com.upemor;

public class Adyacencia {
  private Vertice vertice;
  private double costo[];

  public static final int LUNES = 0;
  public static final int MARTES = 1;
  public static final int MIERCOLES = 2;
  public static final int JUEVES = 3;
  public static final int VIERNES = 4;
  public static final int SABADO = 5;
  public static final int DOMINGO = 6;

  public Adyacencia(Vertice vertice, double costo, Dias dia) {
    this.vertice = vertice;
    this.costo = new double[7];
    this.costo[dia.getValue()] = costo;
  }

  public void setCosto(double costo, Dias dia) {
    this.costo[dia.getValue()] = costo;
  }

  public double getCosto(Dias dia) {
    return costo[dia.getValue()];
  }

  public void setVertice(Vertice vertice) {
    this.vertice = vertice;
  }

  public Vertice getVertice() {
    return vertice;
  }

  public String stringyfy(Dias dia) {
    return "[" + vertice + "," + costo[dia.getValue()] + "," + dia + "]";
  }
}

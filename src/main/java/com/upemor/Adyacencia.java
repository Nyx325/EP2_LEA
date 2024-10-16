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

  public Adyacencia(Vertice vertice, double costo, int dia) throws Exception {
    if (dia < 0 || dia > 6)
      throw new Exception("Día inválido");

    this.vertice = vertice;
    this.costo = new double[7];
    this.costo[dia] = costo;
  }

  public void setCosto(double costo, int dia) throws Exception {
    if (dia < 0 || dia > 6)
      throw new Exception("Dia inválido");
    this.costo[dia] = costo;
  }

  public double getCosto(int dia) throws Exception {
    if (dia < 0 || dia > 6)
      throw new Exception("Dia inválido");
    return costo[dia];
  }

  public void setVertice(Vertice vertice) {
    this.vertice = vertice;
  }

  public Vertice getVertice() {
    return vertice;
  }

  public String stringyfy(int dia) throws Exception {
    if (dia < 0 || dia > 6)
      throw new Exception("Día inválido");

    String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo" };
    return "[" + vertice + "," + costo[dia] + "," + dias[dia] + "]";
  }
}

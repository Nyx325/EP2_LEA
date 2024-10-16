package com.upemor;

public class Ciudad extends Vertice {
  private int id;
  private static int idCuenta = 0;

  public Ciudad(String nombre) {
    super(nombre);
    this.id = Ciudad.idCuenta;
    Ciudad.idCuenta++;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return id + ":" + nombre;
  }
}

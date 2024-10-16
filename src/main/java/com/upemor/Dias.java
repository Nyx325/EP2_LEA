package com.upemor;

public enum Dias {
  LUNES(0),
  MARTES(1),
  MIERCOLES(2),
  JUEVES(3),
  VIERNES(4),
  SABADO(5),
  DOMINGO(6);

  String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo" };

  private final int value;

  Dias(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return dias[value];
  }
}

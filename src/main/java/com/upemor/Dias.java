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

  public static Dias fromInt(int value) {
    switch (value) {
      case 0:
        return LUNES;
      case 1:
        return MARTES;
      case 2:
        return MIERCOLES;
      case 3:
        return JUEVES;
      case 4:
        return VIERNES;
      case 5:
        return JUEVES;
      case 6:
        return VIERNES;
      default:
        return LUNES;
    }
  }

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

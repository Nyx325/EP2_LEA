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
        return Dias.LUNES;
      case 1:
        return Dias.MARTES;
      case 2:
        return Dias.MIERCOLES;
      case 3:
        return Dias.JUEVES;
      case 4:
        return Dias.VIERNES;
      case 5:
        return Dias.SABADO;
      case 6:
        return Dias.DOMINGO;
      default:
        return Dias.LUNES;
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

package com.upemor;

import java.util.Map;

public class Vertice {
  private String nombre;
  private Map<String, Adyacencia> adyacencias;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Map<String, Adyacencia> getAdyacencias() {
    return adyacencias;
  }
}

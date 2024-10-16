package com.upemor;

import java.util.Map;

public class Vertice {
  private String nombre;
  private Map<String, Adyacencia> adyacencias;
  private Etiqueta etiqueta;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Map<String, Adyacencia> getAdyacencias() {
    return adyacencias;
  }

  public Etiqueta getEtiqueta() {
    return etiqueta;
  }

  public void setEtiqueta(Etiqueta etiqueta) {
    this.etiqueta = etiqueta;
  }
}

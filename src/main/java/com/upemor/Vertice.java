package com.upemor;

import java.util.HashMap;
import java.util.Map;

public class Vertice {
  protected String nombre;
  protected Map<String, Adyacencia> adyacencias;
  protected Etiqueta etiqueta;

  public Vertice(String nombre) {
    this.nombre = nombre;
    this.adyacencias = new HashMap<>();
    this.etiqueta = null;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void addAdyacencia(Adyacencia ady) {
    this.adyacencias.put(ady.getVertice().nombre, ady);
  }

  public void addAdyacencias(Adyacencia... adys) {
    for (Adyacencia adyacencia : adys) {
      this.addAdyacencia(adyacencia);
    }
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

  @Override
  public String toString() {
    return nombre;
  }
}

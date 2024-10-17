package com.upemor;

import java.util.Stack;

/**
 * Pila
 */
public class Pila extends Stack<Vertice> implements VerticesPendientes {
  public Pila() {
    super();
  }

  @Override
  public void agregar(Vertice coso) {
    this.add(coso);
  }

  @Override
  public Vertice obtener() {
    return this.pop();
  }

  @Override
  public boolean vacio() {
    return this.isEmpty();
  }
}

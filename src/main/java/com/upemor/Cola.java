package com.upemor;

import java.util.LinkedList;

public class Cola extends LinkedList<Vertice> implements VerticesPendientes {
  public Cola() {
    super();
  }

  @Override
  public void agregar(Vertice coso) {
    this.add(coso);
  }

  @Override
  public Vertice obtener() {
    return this.poll();
  }

  @Override
  public boolean vacio() {
    return this.isEmpty();
  }
}

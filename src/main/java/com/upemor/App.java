package com.upemor;

public class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    Vertice v1 = new Vertice("1");
    Vertice v2 = new Vertice("2");
    Vertice v3 = new Vertice("3");
    Vertice v4 = new Vertice("4");
    Vertice v5 = new Vertice("5");

    try {
      v1.addAdyacencias(new Adyacencia(v2, 1, Adyacencia.LUNES), new Adyacencia(v4, 1, Adyacencia.LUNES));
      v2.addAdyacencias(new Adyacencia(v1, 1, Adyacencia.LUNES), new Adyacencia(v3, 1, Adyacencia.LUNES),
          new Adyacencia(v4, 1, Adyacencia.LUNES));
      v3.addAdyacencias(new Adyacencia(v4, 1, 0), new Adyacencia(v2, 1, 0), new Adyacencia(v5, 1, 0));
      v4.addAdyacencias(new Adyacencia(v1, 1, 0), new Adyacencia(v2, 1, 0), new Adyacencia(v3, 1, 0),
          new Adyacencia(v5, 1, 0));
      v5.addAdyacencias(new Adyacencia(v4, 1, 0), new Adyacencia(v3, 1, 0));

      Grafo grafo = new Grafo("Grafinator, the last one");
      grafo.addAll(v1, v2, v3, v4, v5);

      grafo.mostrarListaAdyacencias(Adyacencia.LUNES);
      grafo.generarCaminoMasCorto(v3, Adyacencia.LUNES);
      grafo.mostrarListaAdyacencias(Adyacencia.LUNES);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}

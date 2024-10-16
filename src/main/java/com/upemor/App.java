package com.upemor;

public class App {
  Grafo grafo;

  public static void main(String[] args) {
    System.out.println("Hello World!");

    Vertice v1 = new Vertice("1");
    Vertice v2 = new Vertice("2");
    Vertice v3 = new Vertice("3");
    Vertice v4 = new Vertice("4");
    Vertice v5 = new Vertice("5");

    v1.addAdyacencias(
        new Adyacencia(v2, 1, Dias.LUNES),
        new Adyacencia(v4, 1, Dias.LUNES));

    v2.addAdyacencias(
        new Adyacencia(v1, 1, Dias.LUNES),
        new Adyacencia(v3, 1, Dias.LUNES),
        new Adyacencia(v4, 1, Dias.LUNES));

    v3.addAdyacencias(
        new Adyacencia(v4, 1, Dias.LUNES),
        new Adyacencia(v2, 1, Dias.LUNES),
        new Adyacencia(v5, 1, Dias.LUNES));

    v4.addAdyacencias(
        new Adyacencia(v1, 1, Dias.LUNES),
        new Adyacencia(v2, 1, Dias.LUNES),
        new Adyacencia(v3, 1, Dias.LUNES),
        new Adyacencia(v5, 1, Dias.LUNES));

    v5.addAdyacencias(
        new Adyacencia(v4, 1, Dias.LUNES),
        new Adyacencia(v3, 1, Dias.LUNES));

    Grafo grafo = new Grafo("Grafinator, the last one");
    grafo.addAll(v1, v2, v3, v4, v5);

    grafo.mostrarListaAdyacencias(Dias.LUNES);
    grafo.generarCaminoMasCorto(v3, Dias.LUNES);
    grafo.mostrarListaAdyacencias(Dias.LUNES);
  }
}

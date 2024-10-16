package com.upemor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Grafo {
  private Map<String, Vertice> vertices;
  private String nombre;

  public Grafo(String nombre) {
    this.nombre = nombre;
    this.vertices = new HashMap<>();
  }

  public void add(Vertice v) {
    vertices.put(v.getNombre(), v);
  }

  public void addAll(Vertice... vList) {
    for (Vertice v : vList) {
      this.add(v);
    }
  }

  public String toString() {
    return nombre + " [Grado: " + vertices.size() + "]";
  }

  public void mostrarGradoVertices() {
    for (String key : vertices.keySet()) {
      Vertice v = vertices.get(key);
      System.out.println(v + " (Grado: " + v.getAdyacencias().size() + ")");
    }
  }

  public void mostrarAdyacencias() {
    System.out.println("Grafo: " + nombre);
    for (String key : vertices.keySet()) {
      Vertice vertex = vertices.get(key);
      System.out.println(vertex + " {");
      for (String keyAdy : vertex.getAdyacencias().keySet()) {
        Vertice v = vertex.getAdyacencias().get(keyAdy).getVertice();
        System.out.println("   " + v);
      }
      System.out.println("}");
    }
  }

  public void recorridoEnAnchura(Vertice inicio) {
    Queue<Vertice> queue = new LinkedList<>();
    Set<Vertice> visited = new HashSet<>();

    Vertice startVertice = inicio;
    if (startVertice == null) {
      System.out.println("Vértice de inicio no encontrado.");
      return;
    }

    System.out.print(
        "Recorrido en anchura empezando por: " + startVertice.getNombre() + " {");

    queue.add(inicio);
    visited.add(inicio);

    while (!queue.isEmpty()) {
      Vertice current = queue.poll();
      System.out.print(" " + current.getNombre());

      for (String key : current.getAdyacencias().keySet()) {
        Vertice neighbor = current.getAdyacencias().get(key).getVertice();
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
        }
      }
    }
    System.out.println("}");
  }

  public void recorridoEnProfundidad(Vertice inicio) {
    Stack<Vertice> stack = new Stack<>();
    Set<Vertice> visited = new HashSet<>();

    Vertice startVertice = inicio;

    if (startVertice == null) {
      System.out.println("Vértice de inicio no encontrado.");
      return;
    }

    System.out.print("Recorrido en profundidad empezando por: " +
        startVertice.getNombre() + " {");

    stack.add(inicio);
    visited.add(inicio);

    while (!stack.isEmpty()) {
      Vertice current = stack.pop();
      System.out.print(" " + current.getNombre());

      for (String key : current.getAdyacencias().keySet()) {
        Vertice neighbor = current.getAdyacencias().get(key).getVertice();
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          stack.add(neighbor);
        }
      }
    }
    System.out.println("}");
  }

  public void caminoMasCorto(Vertice inicio, int dia) throws Exception {
    if (dia < 0 || dia > 6)
      throw new Exception("Día inválido");

    Queue<Vertice> queue = new LinkedList<>();
    Set<Vertice> visited = new HashSet<>();
    visited.add(inicio);
    inicio.setEtiqueta(new Etiqueta(null, 0));
    queue.add(inicio);

    Vertice actual = null;
    while (!queue.isEmpty()) {
      actual = queue.poll();

      for (String key : actual.getAdyacencias().keySet()) {
        Adyacencia a = actual.getAdyacencias().get(key);
        Etiqueta nueva = new Etiqueta(actual, actual.getEtiqueta().getCosto() + a.getCosto(dia));
        if (!visited.contains(a.getVertice())) {
          visited.add(a.getVertice());
          a.getVertice().setEtiqueta(nueva);
          queue.add(a.getVertice());
        }
        if (nueva.getCosto() < a.getVertice().getEtiqueta().getCosto()) {
          a.getVertice().setEtiqueta(nueva);
        }
      }

    }
  }
}

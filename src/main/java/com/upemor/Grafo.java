package com.upemor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Grafo {
  private Map<String, Vertice> vertices;
  private String nombre;

  public Grafo(String nombre) {
    this.nombre = nombre;
    this.vertices = new HashMap<>();
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Map<String, Vertice> getVertices() {
    return vertices;
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

  public void mostrarListaAdyacencias(Dias dia) {
    System.out.println("\nLista de adyacencias dia " + dia);
    for (String key : vertices.keySet()) {
      Vertice vertice = vertices.get(key);
      System.out.print(vertice + " " + vertice.getEtiqueta() + " -> {");
      for (String k : vertice.getAdyacencias().keySet()) {
        Adyacencia adyacencia = vertice.getAdyacencias().get(k);
        System.out.print(adyacencia.toString(dia) + ", ");
      }
      System.out.println("})");
    }
    System.out.println("\n");
  }

  public void generarCaminoMasCorto(Vertice destino, Dias dia) {

    Queue<Vertice> queue = new LinkedList<>();
    Set<Vertice> visited = new HashSet<>();
    visited.add(destino);
    destino.setEtiqueta(new Etiqueta(null, 0, dia));
    queue.add(destino);

    Vertice actual = null;
    while (!queue.isEmpty()) {
      actual = queue.poll();

      for (String key : actual.getAdyacencias().keySet()) {
        Adyacencia a = actual.getAdyacencias().get(key);
        Etiqueta nueva = new Etiqueta(actual, actual.getEtiqueta().getCosto() + a.getCosto(dia), dia);
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

  public static final String ANCHURA = "anchura";
  public static final String PROFUNDIDAD = "profundidad";
  public static final String DERECHA = "derecha";
  public static final String IZQUIERDA = "izquierda";

  public void recorrido(Vertice inicio, String tipo, String direccion) {
    if (inicio == null) {
      System.out.println("Vértice de inicio no encontrado.");
      return;
    }

    if (!tipo.equals(ANCHURA) && !tipo.equals(PROFUNDIDAD)) {
      System.out.println("Error: Tipo inválido");
      return;
    }

    if (!direccion.equals(DERECHA) && !direccion.equals(IZQUIERDA)) {
      System.out.println("Error: Dirección inválido");
      return;
    }

    Set<Vertice> visitados = new HashSet<>();
    VerticesPendientes pendientes;
    if (tipo.equals(ANCHURA))
      pendientes = new Cola();
    else
      pendientes = new Pila();

    System.out.print("Recorrido en " + tipo + " por " + direccion + ": {");
    pendientes.agregar(inicio);
    visitados.add(inicio);

    while (!pendientes.vacio()) {
      Vertice actual = pendientes.obtener();
      System.out.print(" " + actual.getNombre());

      List<String> adyacencias = new ArrayList<>(actual.getAdyacencias().keySet());

      if (direccion.equals(IZQUIERDA))
        Collections.reverse(adyacencias);

      for (String key : adyacencias) {
        Vertice vecino = actual.getAdyacencias().get(key).getVertice();
        if (!visitados.contains(vecino)) {
          visitados.add(vecino);
          pendientes.agregar(vecino);
        }
      }
    }
    System.out.println("}");
  }

  public void caminoMasCorto(Vertice destino, Dias dia, String tipo, String direccion) {
    if (destino == null) {
      System.out.println("Vértice de inicio no encontrado.");
      return;
    }

    if (!tipo.equals(ANCHURA) && !tipo.equals(PROFUNDIDAD)) {
      System.out.println("Error: Tipo inválido");
      return;
    }

    if (!direccion.equals(DERECHA) && !direccion.equals(IZQUIERDA)) {
      System.out.println("Error: Dirección inválido");
      return;
    }

    Set<Vertice> visitados = new HashSet<>();
    VerticesPendientes pendientes;
    if (tipo.equals(ANCHURA))
      pendientes = new Cola();
    else
      pendientes = new Pila();

    System.out.print("Recorrido en " + tipo + " por " + direccion + ": {");
    pendientes.agregar(destino);
    visitados.add(destino);
    destino.setEtiqueta(new Etiqueta(null, 0, dia));

    while (!pendientes.vacio()) {
      Vertice actual = pendientes.obtener();
      System.out.print(" " + actual.getNombre());

      List<String> adyacencias = new ArrayList<>(actual.getAdyacencias().keySet());

      if (direccion.equals(IZQUIERDA))
        Collections.reverse(adyacencias);

      for (String key : adyacencias) {
        Adyacencia a = actual.getAdyacencias().get(key);
        Etiqueta nueva = new Etiqueta(actual, actual.getEtiqueta().getCosto() + a.getCosto(dia), dia);
        if (!visitados.contains(a.getVertice())) {
          visitados.add(a.getVertice());
          a.getVertice().setEtiqueta(nueva);
          pendientes.agregar(a.getVertice());
        }
        if (nueva.getCosto() < a.getVertice().getEtiqueta().getCosto()) {
          a.getVertice().setEtiqueta(nueva);
        }
        System.out.println(nueva);
      }
    }
    System.out.println("}");
  }
}

package com.upemor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {
  private Map<String, Vertice> vertices;
  private String nombre;

  public static final String ANCHURA = "anchura";
  public static final String PROFUNDIDAD = "profundidad";
  public static final String DERECHA = "derecha";
  public static final String IZQUIERDA = "izquierda";

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

  public void recorrido(Vertice inicio, String tipo, String direccion) {
    Set<Vertice> visitados = new HashSet<>();
    VerticesPendientes pendientes = tipo.equals(ANCHURA) ? new Cola() : new Pila();

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
    Set<Vertice> visitados = new HashSet<>();
    VerticesPendientes pendientes = tipo.equals(ANCHURA) ? new Cola() : new Pila();

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

  public Grafo generarArbol(Vertice inicio, Dias dia, String tipo, String direccion) {
    this.caminoMasCorto(inicio, dia, tipo, direccion);

    Grafo arbol = new Grafo("Arbol iniciando por " + inicio.getNombre());

    for (String key : this.getVertices().keySet()) {
      Vertice v = this.getVertices().get(key);
      Vertice vArbol = new Vertice(v.getNombre());
      vArbol.setEtiqueta(v.getEtiqueta());
      arbol.add(vArbol);
    }

    for (String key : this.getVertices().keySet()) {
      Vertice nodoGrafo = this.getVertices().get(key);
      Vertice hijo = arbol.getVertices().get(key);

      Etiqueta etiqueta = nodoGrafo.getEtiqueta();
      if (etiqueta.getVertice() == null)
        continue;

      Vertice padre = arbol.getVertices().get(etiqueta.getVertice().getNombre());
      Vertice padreGrafo = this.getVertices().get(etiqueta.getVertice().getNombre());

      Adyacencia adyArbol = new Adyacencia(hijo);

      adyArbol.setCosto(padreGrafo.getAdyacencias().get(hijo.getNombre()).getCosto(dia), dia);
      padre.addAdyacencia(adyArbol);
    }

    System.out.println("Adyacencias " + arbol.getNombre());
    arbol.mostrarListaAdyacencias(dia);
    return arbol;
  }
}

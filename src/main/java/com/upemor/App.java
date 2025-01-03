package com.upemor;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class App extends VistaConsola {
  public Grafo grafo;

  public static void main(String[] args) {
    App app = new App();
    app.menu();
  }

  public App() {
    grafo = new Grafo("Grafo");
  }

  public Dias capturarDia() {
    int opc;
    while (true) {
      System.out.println("Un día de la semana");
      opc = (int) capturarLong("Considera 1 como Lunes y 7 como domingo");

      if (opc < 1 || opc > 7)
        System.out.println("Número inválido");
      else
        return Dias.fromInt(opc - 1);
    }
  }

  public String capturarDireccion() {
    int opc;
    while (true) {
      System.out.println("Elije un tipo de recorrido en anchura");
      System.out.println("0) Por derecha");
      System.out.println("1) Por izquierda");
      opc = (int) capturarLong("Ingresa una opción");

      switch (opc) {
        case 0:
          return Grafo.DERECHA;
        case 1:
          return Grafo.IZQUIERDA;
        default:
          System.out.println("Opción no válida");
          break;
      }
    }
  }

  public String capturarRecorrido() {
    int opc;
    while (true) {
      System.out.println("Elije un tipo de recorrido");
      System.out.println("0) Anchura");
      System.out.println("1) Profundidad");
      opc = (int) capturarLong("Ingresa una opción");

      switch (opc) {
        case 0:
          return Grafo.ANCHURA;
        case 1:
          return Grafo.PROFUNDIDAD;
        default:
          System.out.println("Opción no válida");
          break;
      }
    }
  }

  public void menu() {
    int opc;
    do {
      System.out.println("Bienvenido");
      System.out.println("0) Salir");
      System.out.println("1) Importar grafo");
      System.out.println("2) Recorrer arbol");
      System.out.println("3) Camino más corto");
      System.out.println("4) Arbol");
      opc = (int) capturarLong("Ingresa una opción");

      try {
        switch (opc) {
          case 0:
            return;
          case 1:
            this.importarGrafo();
            break;
          case 2:
            this.recorrido();
            break;
          case 3:
            this.caminoMasCorto();
            break;
          case 4:
            this.arbol();
          default:
            break;
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error: " + e.toString());
      }

    } while (true);
  }

  public void importarGrafo() throws Exception {
    String ruta = capturarString("Ingresa la ruta de tu archivo");
    File archivo = new File(ruta);
    List<String> lineas = Files.readAllLines(archivo.toPath());

    grafo = new Grafo("Grafo");

    boolean primerLinea = true;
    for (String linea : lineas) {
      if (primerLinea) {
        String[] ciudades = linea.split(",");
        for (String ciudad : ciudades) {
          Ciudad c = new Ciudad(ciudad);
          grafo.add(c);
        }

        primerLinea = false;
        continue;
      }

      String[] adyacencias = linea.split(";");

      Vertice ciudad = grafo.getVertices().get(adyacencias[0]);
      if (ciudad == null)
        continue;

      for (int i = 1; i < adyacencias.length; i++) {
        String[] inserteNombreVar = adyacencias[i].split(",");
        Vertice ciudadAdy = grafo.getVertices().get(inserteNombreVar[0]);

        if (ciudadAdy == null)
          continue;

        Adyacencia ady = new Adyacencia(ciudadAdy);
        for (int j = 1; j < inserteNombreVar.length; j++) {
          double costo;
          try {
            costo = Double.parseDouble(inserteNombreVar[j]);
          } catch (NumberFormatException e) {
            costo = Integer.MAX_VALUE;
            System.out.println("Error al convertir el peso: " + inserteNombreVar[j] + " colocnado peso: " + costo);
          }
          Dias d = Dias.fromInt(j - 1);
          ady.setCosto(costo, d);
        }
        ciudad.addAdyacencia(ady);
      }
    }

    for (Dias dia : Dias.values()) {
      grafo.mostrarListaAdyacencias(dia);
    }
  }

  public void recorrido() {
    String nodoInicial, recorrido, direccion;
    Vertice inicio;
    int opc;
    do {
      System.out.println("0) Volver al menú");
      System.out.println("1) Ingresar nodo inicial");
      opc = (int) capturarLong("Ingresa una opción");

      switch (opc) {
        case 0:
          return;
        case 1:
          nodoInicial = capturarString("Ingresa el nodo inicial");
          break;
        default:
          System.out.println("Opción no válida");
          continue;
      }

      inicio = grafo.getVertices().get(nodoInicial);

      if (inicio == null) {
        System.out.println("No se encontró el nodo " + nodoInicial);
        continue;
      }

      recorrido = capturarRecorrido();
      direccion = capturarDireccion();
      grafo.recorrido(inicio, recorrido, direccion);
    } while (true);
  }

  public void caminoMasCorto() {
    Dias dia;
    String nodoInicial, recorrido, direccion;
    Vertice inicio;
    int opc;
    do {
      System.out.println("0) Volver al menú");
      System.out.println("1) Ingresar nodo inicial");
      opc = (int) capturarLong("Ingresa una opción");

      switch (opc) {
        case 0:
          return;
        case 1:
          nodoInicial = capturarString("Ingresa el nodo inicial");
          break;
        default:
          System.out.println("Opción no válida");
          continue;
      }
      inicio = grafo.getVertices().get(nodoInicial);

      if (inicio == null) {
        System.out.println("No se encontró el nodo " + nodoInicial);
        continue;
      }

      dia = capturarDia();
      recorrido = capturarRecorrido();
      direccion = capturarDireccion();
      grafo.caminoMasCorto(inicio, dia, recorrido, direccion);
      grafo.mostrarListaAdyacencias(dia);
    } while (true);
  }

  public void arbol() {
    String nodoInicial, recorrido, direccion;
    Vertice inicio;
    Dias dia;
    int opc;
    do {
      System.out.println("0) Volver al menú");
      System.out.println("1) Ingresar nodo inicial");
      opc = (int) capturarLong("Ingresa una opción");

      switch (opc) {
        case 0:
          return;
        case 1:
          nodoInicial = capturarString("Ingresa el nodo inicial");
          break;
        default:
          System.out.println("Opción no válida");
          continue;
      }
      inicio = grafo.getVertices().get(nodoInicial);

      if (inicio == null) {
        System.out.println("No se encontró el nodo " + nodoInicial);
        continue;
      }

      dia = capturarDia();
      recorrido = capturarRecorrido();
      direccion = capturarDireccion();
      System.out.println("Dia " + dia + " Recorrido " + recorrido + " Direccion " + direccion);
      grafo.generarArbol(inicio, dia, recorrido, direccion);
    } while (true);
  }
}

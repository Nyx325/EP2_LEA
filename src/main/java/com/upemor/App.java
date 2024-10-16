package com.upemor;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class App extends VistaConsola {
  public Grafo grafo;

  public void menu() {
    int opc;
    do {
      System.out.println("Bienvenido");
      System.out.println("0) Salir");
      System.out.println("1) Importar grafo");
      opc = (int) capturarLong("Ingresa una opción");

      try {
        switch (opc) {
          case 0:
            return;
          case 1:
            this.importarGrafo();
            break;

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

  public static void main(String[] args) {
    App app = new App();
    app.menu();
  }
}

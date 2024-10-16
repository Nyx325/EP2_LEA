package com.upemor;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class VistaConsola {
  private Scanner scanner = new Scanner(System.in);

  public String capturarString(String msg) {
    String valor;
    while (true) {
      try {
        System.out.print(msg + ": ");
        valor = scanner.nextLine().trim();
        return valor;
      } catch (InputMismatchException e) {
        System.out.println("No se pudo obtener el dato, ingrese nuevamente");
      }
    }
  }

  public long capturarLong(String msg) {
    long valor;
    while (true) {
      try {
        System.out.print(msg + ": ");
        valor = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        return valor;
      } catch (InputMismatchException e) {
        System.out.println("El dato debe ser un entero");
        scanner.nextLine(); // Consumir el salto de línea después de la excepción
      }
    }
  }

  public double capturarDouble(String msg) {
    double valor;
    while (true) {
      try {
        System.out.println(msg + ": ");
        valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        return valor;
      } catch (InputMismatchException e) {
        System.out.println("El dato debe ser un real");
        scanner.nextLine(); // Consumir el salto de línea después de la excepción
      }
    }
  }
}

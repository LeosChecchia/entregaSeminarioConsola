/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.consola;

/**
 *
 * @author Leoch
 */

import com.mycompany.consola.TelefonoInvalidoException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Clase principal
public class Consola {

    static ArrayList<Cliente> listaClientes = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer
                switch (opcion) {
                    case 1:
                        crearCliente();
                        break;
                    case 2:
                        leerClientes();
                        break;
                    case 3:
                        editarCliente();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, elija una opción del 1 al 5.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                scanner.nextLine(); // Limpiar el buffer
                opcion = 0; // Reiniciar opción para volver a mostrar el menú
            }
        } while (opcion != 5);
    }

    public static void mostrarMenu() {
        System.out.println("\nGestión de Clientes");
        System.out.println("1. Crear Cliente");
        System.out.println("2. Leer Clientes");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void validarTelefono(String telefono) throws TelefonoInvalidoException {
        if (!telefono.matches("\\d+")) {
            throw new TelefonoInvalidoException("El teléfono solo debe contener números.");
        }
    }

    public static void crearCliente() {
        Cliente cliente = new Cliente();

        System.out.print("Ingrese nombre: ");
        cliente.setNombre(scanner.nextLine());

        System.out.print("Ingrese apellido: ");
        cliente.setApellido(scanner.nextLine());

        System.out.print("Ingrese dirección: ");
        cliente.setDireccion(scanner.nextLine());

        System.out.print("Ingrese teléfono: ");
        String telefono = scanner.nextLine();
        try {
            validarTelefono(telefono);
            cliente.setTelefono(telefono);
        } catch (TelefonoInvalidoException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Ingrese empresa: ");
        cliente.setEmpresa(scanner.nextLine());

        listaClientes.add(cliente);
        System.out.println("Cliente creado exitosamente.");
    }

    public static void leerClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes para mostrar.");
            return;
        }

        System.out.println("Lista de Clientes:");
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            System.out.println((i + 1) + ". " + cliente);
        }
    }

    public static void editarCliente() {
        leerClientes();
        if (listaClientes.isEmpty()) {
            return;
        }

        System.out.print("Ingrese el número del cliente a editar: ");
        int index;
        try {
            index = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpiar el buffer
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            scanner.nextLine(); // Limpiar el buffer
            return;
        }

        if (index >= 0 && index < listaClientes.size()) {
            Cliente cliente = listaClientes.get(index);

            System.out.print("Nuevo nombre (actual: " + cliente.getNombre() + "): ");
            cliente.setNombre(scanner.nextLine());

            System.out.print("Nuevo apellido (actual: " + cliente.getApellido() + "): ");
            cliente.setApellido(scanner.nextLine());

            System.out.print("Nueva dirección (actual: " + cliente.getDireccion() + "): ");
            cliente.setDireccion(scanner.nextLine());

            System.out.print("Nuevo teléfono (actual: " + cliente.getTelefono() + "): ");
            String telefono = scanner.nextLine();
            try {
                validarTelefono(telefono);
                cliente.setTelefono(telefono);
            } catch (TelefonoInvalidoException e) {
                System.out.println(e.getMessage());
                return;
            }

            System.out.print("Nueva empresa (actual: " + cliente.getEmpresa() + "): ");
            cliente.setEmpresa(scanner.nextLine());

            System.out.println("Cliente actualizado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public static void eliminarCliente() {
        leerClientes();
        if (listaClientes.isEmpty()) {
            return;
        }

        System.out.print("Ingrese el número del cliente a eliminar: ");
        int index;
        try {
            index = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpiar el buffer
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            scanner.nextLine(); // Limpiar el buffer
            return;
        }

        if (index >= 0 && index < listaClientes.size()) {
            listaClientes.remove(index);
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}

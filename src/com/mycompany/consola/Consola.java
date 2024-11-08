/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.consola;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import practica01.Conexion;

public class Consola {

    static Conexion conexion = new Conexion("sistemapedidos");
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
                        conexion.desconectar();
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

    public static void crearCliente() {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese empresa: ");
        String empresa = scanner.nextLine();

        try (Connection cx = conexion.conectar();
             PreparedStatement stmt = cx.prepareStatement("INSERT INTO Clientes (nombre, apellido, direccion, telefono, empresa) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, direccion);
            stmt.setString(4, telefono);
            stmt.setString(5, empresa);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente creado exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    public static void leerClientes() {
        try (Connection cx = conexion.conectar();
             Statement stmt = cx.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Clientes")) {

            ArrayList<Cliente> listaClientes = new ArrayList<>();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmpresa(rs.getString("empresa"));
                listaClientes.add(cliente);
            }

            if (listaClientes.isEmpty()) {
                System.out.println("No hay clientes para mostrar.");
            } else {
                System.out.println("Lista de Clientes:");
                for (int i = 0; i < listaClientes.size(); i++) {
                    System.out.println((i + 1) + ". " + listaClientes.get(i));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer los clientes: " + e.getMessage());
        }
    }

    public static void editarCliente() {
        System.out.print("Ingrese el nombre del cliente a editar: ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo apellido: ");
        String nuevoApellido = scanner.nextLine();

        System.out.print("Nueva dirección: ");
        String nuevaDireccion = scanner.nextLine();

        System.out.print("Nuevo teléfono: ");
        String nuevoTelefono = scanner.nextLine();

        System.out.print("Nueva empresa: ");
        String nuevaEmpresa = scanner.nextLine();

        try (Connection cx = conexion.conectar();
             PreparedStatement stmt = cx.prepareStatement("UPDATE Clientes SET apellido=?, direccion=?, telefono=?, empresa=? WHERE nombre=?")) {
            stmt.setString(1, nuevoApellido);
            stmt.setString(2, nuevaDireccion);
            stmt.setString(3, nuevoTelefono);
            stmt.setString(4, nuevaEmpresa);
            stmt.setString(5, nombre);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente actualizado exitosamente.");
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al editar el cliente: " + e.getMessage());
        }
    }

    public static void eliminarCliente() {
        System.out.print("Ingrese el nombre del cliente a eliminar: ");
        String nombre = scanner.nextLine();

        try (Connection cx = conexion.conectar();
             PreparedStatement stmt = cx.prepareStatement("DELETE FROM Clientes WHERE nombre=?")) {
            stmt.setString(1, nombre);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado exitosamente.");
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }
    }
}

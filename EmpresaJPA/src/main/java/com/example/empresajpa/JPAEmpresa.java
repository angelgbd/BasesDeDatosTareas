package com.example.empresajpa;


import java.time.LocalDate;
import java.util.List;

public class JPAEmpresa {

    public static void main(String[] args) {
        DAO dao = new DAO();

        // --- 1. Insertar empleados de prueba ---
        System.out.println("--- INSERTANDO EMPLEADOS ---");
        dao.insertar(new Empleado("Ana López", "ana.lopez@correo.com", 35000.0, Estatus.ACTIVO, LocalDate.of(2022, 1, 15)));
        dao.insertar(new Empleado("Carlos Ruiz", "carlos.ruiz@correo.com", 42000.0, Estatus.ACTIVO, LocalDate.of(2021, 5, 20)));
        dao.insertar(new Empleado("Laura Marín", "laura.marin@correo.com", 28000.0, Estatus.INACTIVO, LocalDate.of(2023, 3, 10)));

        // --- 2. Listar todos los empleados ---
        System.out.println("\n--- LISTADO DE TODOS LOS EMPLEADOS ---");
        List<Empleado> empleados = dao.listar();
        empleados.forEach(System.out::println);

        // --- 3. Modificar un empleado ---
        System.out.println("\n--- MODIFICANDO EMPLEADO CON ID 2 ---");
        Empleado empleadoAModificar = dao.buscar(2L);
        if (empleadoAModificar != null) {
            empleadoAModificar.setNombre("Carlos A. Ruiz");
            empleadoAModificar.setEmail("carlos.a.ruiz@empresa.com");
            dao.actualizar(empleadoAModificar);
        }

        System.out.println("\n--- LISTADO DESPUÉS DE MODIFICAR ---");
        dao.listar().forEach(System.out::println);

        // --- 4. Usar la función aumentarSalario ---
        System.out.println("\n--- AUMENTANDO SALARIO AL EMPLEADO CON ID 1 (10%) ---");
        dao.aumentarSalario(1L, 10.0);
        
        System.out.println("Empleado con ID 1 después del aumento:");
        System.out.println(dao.buscar(1L));

        // --- 5. Eliminar un empleado ---
        System.out.println("\n--- ELIMINANDO EMPLEADO CON ID 3 ---");
        dao.eliminar(3L);

        // --- 6. Listar nuevamente ---
        System.out.println("\n--- LISTADO FINAL ---");
        dao.listar().forEach(System.out::println);
    }
}
package org.example;

import org.example.logica.Empleados;
import org.example.persistencia.ControladorPersistencia;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, ParseException {

        ControladorPersistencia controlador = new ControladorPersistencia();

        /*
        Crear empleados para hacer pruebas

        Empleados empleado2 = new Empleados(6, "Marco", "Juan", "Empleado", 40000, new Date(System.currentTimeMillis()));
        Empleados empleado3 = new Empleados(2, "Mario", "Goma", "Programador", 40000, new Date(System.currentTimeMillis()));
        Empleados empleado4 = new Empleados(3, "Maria", "Perez", "CEO", 40000, new Date(System.currentTimeMillis()));
        Empleados empleado5 = new Empleados(4, "Anton", "Marquez", "Empleado", 40000, new Date(System.currentTimeMillis()));
        Empleados empleado6 = new Empleados(5, "Alex", "Gomez", "Practicas", 40000, new Date(System.currentTimeMillis()));
        controlador.crearEmpleado(empleado2);
        controlador.crearEmpleado(empleado3);
        controlador.crearEmpleado(empleado4);
        controlador.crearEmpleado(empleado5);
        controlador.crearEmpleado(empleado6);
        */

        int flag;
        int id;

        flag = 1;
        id = 0;

        Empleados empleado = new Empleados();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("***************************** Bienvenido al sistema *****************************");

        while(flag > 0){
            //Menu de seleccion
            System.out.println("Que operacion desea realizar? \n Seleccione el numero\n 1 - Añadir Empleado\n 2 - Borrar Empleado\n 3 - Buscar Empleados\n 4 - Mostar Lista Empleados\n 5 - Editar Empleado\n 0 - Salir");
            flag = scanner.nextInt();
            switch (flag) {
                case 1:
                    System.out.println("+++ Añadir Empleado +++\n");

                    id = controlador.findLastIdEmpleado();
                    empleado = crearEmpleadoUsuario(id);
                    controlador.crearEmpleado(empleado);

                    System.out.println("\nUsuario añadido\n");
                    System.out.println("ID: " + empleado.getId() + "\nNombre: " + empleado.getNombre() + "\nApellido: " + empleado.getApellido() + "\nCargo: " + empleado.getCargo() + "\nSalario: " + empleado.getSalario() + "\nFecha de Inicio: " + empleado.getFechaInicio() + "\n");
                    break;
                case 2:
                    System.out.println("+++ Borrar Empleado +++");

                    System.out.println("     Lista");

                    List<Empleados> empleadosListBorrar = controlador.findEmpleadosList();

                    for (Empleados empleados : empleadosListBorrar) {
                        System.out.println("----- Empleado " + empleados.getId() + " -----");
                        System.out.println("\nNombre: " + empleados.getNombre() + "\nApellido: " + empleados.getApellido() + "\nCargo: " + empleados.getCargo() + "\nSalario: " + empleados.getSalario() + "\nFecha de Inicio: " + empleados.getFechaInicio() + "\n");
                    }

                    System.out.println(" - Indicar el id del usuario que desea eliminar");
                    id = scanner.nextInt();

                    //Comprobar si existe para que no de error al borrarlo
                    if(controlador.findEmpleadoId(id) == false){
                        System.out.println("\nEl id que indicaste no existe\n");
                    } else {
                        controlador.eliminarEmpleado(id);
                        System.out.println("\nEl usuario de id: " + id + " ha sido eliminado\n");
                    }
                    break;
                case 3:
                    System.out.println("+++ Buscar Empleado +++");

                    System.out.println(" - Indicar el id del usuario que desea buscar");
                    id = scanner.nextInt();

                    if(controlador.findEmpleadoId(id) == false){
                        System.out.println("\nEl id que indicaste no existe\n");
                    } else {
                    empleado = controlador.findEmpleado(id);
                    System.out.println("\nID: " + empleado.getId() + "\nNombre: " + empleado.getNombre() + "\nApellido: " + empleado.getApellido() + "\nCargo: " + empleado.getCargo() + "\nSalario: " + empleado.getSalario() + "\nFecha de Inicio: " + empleado.getFechaInicio() + "\n");
                    }
                    break;
                case 4:
                    System.out.println("+++ Mostar Lista Empleados +++");

                    List<Empleados> empleadosList = controlador.findEmpleadosList();

                    for (Empleados empleados : empleadosList) {
                        System.out.println("----- Empleado " + empleados.getId() + " -----");
                        System.out.println("\nNombre: " + empleados.getNombre() + "\nApellido: " + empleados.getApellido() + "\nCargo: " + empleados.getCargo() + "\nSalario: " + empleados.getSalario() + "\nFecha de Inicio: " + empleados.getFechaInicio() + "\n");
                    }
                    break;
                case 5:
                    System.out.println("+++ Editar Empleado +++");

                    System.out.println(" - Indicar el id del usuario que desea modificar");
                    id = scanner.nextInt();

                    //Comprobar si existe para que no de error
                    if(controlador.findEmpleadoId(id) == false){
                        System.out.println("\nEl id que indicaste no existe\n");
                    } else {
                        System.out.println(" - Empleado seleccinado: " + id);
                        empleado = controlador.findEmpleado(id);
                        //Llamar al metod para modificarlo
                        empleado = modificarEmpleadoUsuario(empleado);
                        System.out.println("\nEl usuario de id: " + id + " ha sido modificado\n Modificaciones: ");
                        System.out.println("\nID: " + empleado.getId() + "\nNombre: " + empleado.getNombre() + "\nApellido: " + empleado.getApellido() + "\nCargo: " + empleado.getCargo() + "\nSalario: " + empleado.getSalario() + "\nFecha de Inicio: " + empleado.getFechaInicio() + "\n");
                    }
                    break;
                case 0:
                    System.out.println("***************************** Final del sistema *****************************");
                    break;
                default:
                    System.out.println("\nSeleccione un numero valido por favor\n");
            }
        }
    }

    //metodo de creacion de empleado, usando el ultimo id para crearlo, pidiendole al usuario todos los valores que desea añadir
    public static Empleados crearEmpleadoUsuario(int id) throws ParseException {
        //Cargamos variables
        Empleados empleado = new Empleados();
        String nombre;
        String apellido;
        String cargo;
        double salario;
        String fechaUsuario;
        Date utilFecha;

        Scanner scannerCrear = new Scanner(System.in);

        System.out.println("  - Nombre del Empleado");
        nombre = scannerCrear.next();
        System.out.println("  - Apellido del Empleado");
        apellido = scannerCrear.next();
        System.out.println("  - Cargo del Empleado");
        cargo = scannerCrear.next();
        System.out.println("  - Salario del Empleado");
        salario = scannerCrear.nextDouble();
        System.out.println("  - Fecha de Inicio del Empleado  |  Ingrese una fecha en formato (dd-MM-yyyy)");
        fechaUsuario = scannerCrear.next();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //convertimos el String en un Date
        utilFecha = sdf.parse(fechaUsuario);
        //pasamos el date a sqlDate (no se puede parsear de String a dateSql)
        java.sql.Date sqlDate = new java.sql.Date(utilFecha.getTime());

        //Cargamos las variabes en el empleado
        empleado.setId(id);
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setCargo(cargo);
        empleado.setSalario(salario);
        empleado.setFechaInicio(sqlDate);

        return empleado;
    }

    public static Empleados modificarEmpleadoUsuario(Empleados empleado) throws ParseException {

        int flag;
        flag = 1;

        Scanner scannerModificar = new Scanner(System.in);

        //menu de seleccion
        while(flag > 0) {
            System.out.println("\nID: " + empleado.getId() + "\nNombre: " + empleado.getNombre() + "\nApellido: " + empleado.getApellido() + "\nCargo: " + empleado.getCargo() + "\nSalario: " + empleado.getSalario() + "\nFecha de Inicio: " + empleado.getFechaInicio() + "\n");
            System.out.println("Que variable deseas editar? \n Seleccione el numero\n 1 - Nombre\n 2 - Apellido\n 3 - Cargo\n 4 - Salario\n 5 - Fecha de Inicio\n 0 - Dejar de Modificar");
            flag = scannerModificar.nextInt();
            switch (flag) {
                case 1:
                    System.out.println("Modificar Nombre\n Nombre Actual: " + empleado.getNombre() + "\nEscribe el nuevo nombre");
                    empleado.setNombre(scannerModificar.next());
                    break;
                case 2:
                    System.out.println("Modificar Apellido\n Apellido Actual: " + empleado.getApellido() + "\nEscribe el nuevo Apellido");
                    empleado.setApellido(scannerModificar.next());
                    break;
                case 3:
                    System.out.println("Modificar Cargo\n Cargo Actual: " + empleado.getCargo() + "\nEscribe el nuevo Cargo");
                    empleado.setCargo(scannerModificar.next());
                    break;
                case 4:
                    System.out.println("Modificar Salario\n Salario Actual: " + empleado.getSalario() + "\nEscribe el nuevo Salario");
                    empleado.setSalario(scannerModificar.nextDouble());
                    break;
                case 5:
                    System.out.println("Modificar Fecha de Inicio\n Fecha de Inicio Actual: " + empleado.getFechaInicio() + "\nEscribe el nuevo Fecha de Inicio |  Ingrese una fecha en formato (dd-MM-yyyy)");

                    Date utilFecha;
                    String fechaUsuario = scannerModificar.next();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    //convertimos el String en un Date
                    utilFecha = sdf.parse(fechaUsuario);
                    //pasamos el date a sqlDate (no se puede parsear de String a dateSql)
                    java.sql.Date sqlDate = new java.sql.Date(utilFecha.getTime());

                    empleado.setFechaInicio(sqlDate);
                    break;
                case 0:
                    System.out.println("Finalizar Edicion");
                    break;
                default:
                    System.out.println("Seleccione un numero valido por favor");
            }
        }
        return empleado;
    }
}
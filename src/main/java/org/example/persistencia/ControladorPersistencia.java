package org.example.persistencia;

import org.example.logica.Empleados;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersistencia {
    //Controlador para manejar el CRUD
    EmpleadoJPAController empladoJPA = new EmpleadoJPAController();


    // metodos que llaman al CRUD
    public void crearEmpleado(Empleados empleado){
        empladoJPA.create(empleado);
    }

    public void eliminarEmpleado(int id){
        empladoJPA.destroy(id);
    }

    public Empleados findEmpleado(int id){
        return empladoJPA.findEmpleado(id);
    }

    public boolean findEmpleadoId(int id){ return empladoJPA.findEmpleadoId(id); }

    //Mostrar toda la lista de empleados en lugar de solo uno
    public List<Empleados> findEmpleadosList() {
        return empladoJPA.findEmpleadosList();
    }

    public void editarEmpleado(Empleados empleados) {
        try {
            empladoJPA.edit(empleados);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int findLastIdEmpleado(){
        return empladoJPA.findLastIdEmpleado();
    }
}

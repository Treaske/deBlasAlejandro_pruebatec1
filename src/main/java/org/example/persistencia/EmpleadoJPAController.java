package org.example.persistencia;

import org.example.logica.Empleados;
import org.example.persistencia.excepciones.NonexistentEntityException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmpleadoJPAController {
    private EntityManagerFactory emf = null;

    //Controlador JPA que carge la base de datos
    public EmpleadoJPAController() {
        this.emf = Persistence.createEntityManagerFactory("empresaPU");
    }

    //Crear entidad
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    //Crear empleado
    public void create(Empleados empleado){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Borrar empleado
    public void destroy(int id) throws NonexistentEntityException{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados empleado;
            try {
                empleado = em.getReference(Empleados.class, id);
                empleado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El empleado con " + id + " no existe", enfe);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Editar empleado, protegiendo el programa mediante una exepcion si no existe el id aportado
    public void edit(Empleados empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            empleados = em.merge(empleados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = empleados.getId();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("El platillo de id " + id + " no existe, seleccione otro");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Encontrar empleado mediante id
    public Empleados findEmpleado(int id){
        EntityManager em = null;
        em = getEntityManager();
        return em.find(Empleados.class, id);
    }

    //Comprobar si existe el usuario
    public boolean findEmpleadoId(int id){
        EntityManager em = null;
        em = getEntityManager();
        Empleados empleado = em.find(Empleados.class, id);

        if (empleado != null) {
            return true; // El empleado existe en la base de datos
        } else {
            return false; // El empleado no existe en la base de datos
        }
    }

    //Mostrar todos los empleados en la Lista
    public List<Empleados> findEmpleadosList() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Empleados> cq = em.getCriteriaBuilder().createQuery(Empleados.class);
            cq.select(cq.from(Empleados.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    //Mostrar solo los de cargo especifico
    public List<Empleados> findEmpleadosListCargo(String cargo) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Empleados> cq = cb.createQuery(Empleados.class);
            //se debe utilizar root, debido a que el Criteria Builder no soporta listas
            Root<Empleados> empleado = cq.from(Empleados.class);

            // Agrega una condición para filtrar por el cargo específico
            cq.select(empleado).where(cb.equal(empleado.get("cargo"), cargo));

            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }


    //Metodo que se usara para encontrar el ultimo id y sumar uno para añadir un nuevo usuario
    public int findLastIdEmpleado(){
        int id = 0;
        List<Empleados> lista = findEmpleadosList();
        for (Empleados empleado : lista) {
            if(empleado.getId()>id)
                id = empleado.getId();
        }
        id += 1;
        return id;
    }
}

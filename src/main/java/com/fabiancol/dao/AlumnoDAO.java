package com.fabiancol.dao;

import java.util.List;
import jakarta.persistence.*;
import com.fabiancol.domain.Alumno;

public class AlumnoDAO extends GenericoDAO {

    public List<Alumno> mostrar() {
        em = null;
        try {
            em = getEntityManager();
            String consulta = "SELECT a FROM Alumno a";
            Query query = em.createQuery(consulta);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void ingresar(Alumno alumno) {
        em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(System.out);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void modificar(Alumno alumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(alumno);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(System.out);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void eliminar(Alumno alumno) {
        em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(alumno));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(System.out);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Alumno buscarPorID(int id) {
        em = null;
        try {
            em = getEntityManager();
            return em.find(Alumno.class, id);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}

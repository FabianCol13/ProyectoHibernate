package com.fabiancol.dao;
 // @author FabianCol
import jakarta.persistence.*;


public abstract class GenericoDAO {

    protected static EntityManager em;
    private static EntityManagerFactory emf;
    private static final String PU = "pruebaH";

    public GenericoDAO() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PU);
        }
    }

    protected EntityManager getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }

    protected void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    protected void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
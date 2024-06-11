package com.fabiancol.dao;
 // @author FabianCol
import static com.fabiancol.dao.GenericoDAO.em;
import com.fabiancol.domain.Curso;

import jakarta.persistence.Query;
import java.util.List;


public class CursoDAO extends GenericoDAO {

    public List<Curso> listar() {
        String consulta = "SELECT c FROM Curso c";
        em = getEntityManager();
        Query query = em.createQuery(consulta);
        return query.getResultList();
    }

    public void insertar(Curso curso) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
      public void actualizar(Curso curso) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
      
        public void eliminar(Curso curso) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(curso));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
        
       
     
        public Object buscarPorID (Curso curso){
        em = getEntityManager();
        return em.find(Curso.class, curso.getId_curso());
        }

   
    
    
        
        
        
}
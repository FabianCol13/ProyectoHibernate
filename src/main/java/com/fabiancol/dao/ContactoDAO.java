package com.fabiancol.dao;
 // @author FabianCol
import com.fabiancol.domain.Contacto;
import jakarta.persistence.Query;
import java.util.List;





public class ContactoDAO extends GenericoDAO {

    public List<Contacto> listar() {
        String consulta = "SELECT c FROM Contacto c";
        em = getEntityManager();
        Query query = em.createQuery(consulta);
        return query.getResultList();
    }

    public void insertar(Contacto contacto) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(contacto);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
      public void actualizar(Contacto contacto) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(contacto);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
      
        public void eliminar(Contacto contacto) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(contacto));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
        
        public Object buscarPorID (Contacto contacto){
        em = getEntityManager();
        return em.find(Contacto.class, contacto.getId_contacto());
        }
}

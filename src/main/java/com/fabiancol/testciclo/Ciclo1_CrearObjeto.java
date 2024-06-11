package com.fabiancol.testciclo;

// Importamos las clases necesarias de JPA y la entidad Contacto
import com.fabiancol.dao.GenericoDAO;
import com.fabiancol.domain.Contacto;



/**
 * Clase principal para demostrar el ciclo de vida de una entidad JPA.
 * Se muestra cómo crear, persistir y separar una entidad usando JPA.
 */
public class Ciclo1_CrearObjeto extends GenericoDAO {

    public static void main(String[] args) {
        // Crear instancia de la clase para usar métodos no estáticos
        Ciclo1_CrearObjeto ciclo = new Ciclo1_CrearObjeto();

        ciclo.persistirContacto();
    }

    public void persistirContacto() {
        // Estado 1: Transitorio
        // Crear una nueva instancia de la entidad Contacto
        Contacto contacto = new Contacto();

        // Asignar valores a los atributos del contacto
        contacto.setEmail_contacto("videofabian@gmail.com");
        contacto.setTel_contacto("777777777");

        // Estado 2: Persistente
        try {
            // Obtener EntityManager
            em = getEntityManager();

            // Iniciar una transacción
            em.getTransaction().begin();

            // Persistir la instancia de Contacto
            em.persist(contacto);

            // Confirmar la transacción
            em.getTransaction().commit();
        } catch (Exception e) {
            if( em == null)
            e.printStackTrace(System.out);
        } finally {
            // Cerrar el EntityManager
            em.close();
        }

        // Estado 3: Separado
        System.out.println("contacto = " + contacto);

        // Cerrar la fábrica de EntityManagers
        closeEntityManagerFactory();
    }
}

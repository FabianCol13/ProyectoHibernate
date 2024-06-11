package com.fabiancol.testciclo;

import com.fabiancol.dao.GenericoDAO;
import com.fabiancol.domain.Contacto;



/**
 * Clase principal para demostrar cómo modificar un objeto JPA.
 * Se muestra el ciclo de vida de la entidad desde ser encontrada,
 * modificada y sincronizada nuevamente en la base de datos.
 */
public class Ciclo3_ModificarObjeto extends GenericoDAO {

    public static void main(String[] args) {
        // Crear instancia de la clase para usar métodos no estáticos
        Ciclo3_ModificarObjeto ciclo = new Ciclo3_ModificarObjeto();

        // Llamar al método para modificar el contacto por ID
        ciclo.modificarContactoPorId(13); // Pasa el ID que deseas modificar
    }

    public void modificarContactoPorId(int id) {
        try {
            // Obtener EntityManager desde GenericDAO
            em = getEntityManager();

            // Estado Persistente: Recuperación de la entidad desde la base de datos
            // Buscar la entidad Contacto en la base de datos usando su ID
            Contacto contacto = em.find(Contacto.class, id);

            if (contacto != null) {
                // Modificación en memoria (la entidad ya está en estado persistente porque está gestionada por el EntityManager)
                // Cambiar el teléfono y el email del contacto
                contacto.setTel_contacto("3236547891");
                contacto.setEmail_contacto("videoSENA@gmail.com");

                // Iniciar una transacción para realizar la actualización
                em.getTransaction().begin();

                // Estado Persistente en la Base de Datos:
                // Usar `merge` para sincronizar la entidad modificada con la base de datos
                em.merge(contacto);

                // Confirmar la transacción para guardar los cambios en la base de datos
                em.getTransaction().commit();

                // Mostrar la entidad Contacto con la información modificada
                System.out.println("contacto = " + contacto);
            } else {
                System.out.println("No se encontró un contacto con el ID especificado.");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            // Cerrar el EntityManager usando el método de GenericDAO
            em.close();
            closeEntityManagerFactory();
        }
    }
}

package com.fabiancol.testciclo;

import com.fabiancol.dao.GenericoDAO;
import com.fabiancol.domain.Contacto;



/**
 * Clase principal para demostrar cómo eliminar un objeto JPA de la base de datos.
 * Se muestra el ciclo de vida de la entidad desde ser encontrada,
 * gestionada y luego eliminada de la base de datos.
 */
public class Ciclo4_EliminarObjeto extends GenericoDAO {

    public static void main(String[] args) {
        // Crear instancia de la clase para usar métodos no estáticos
        Ciclo4_EliminarObjeto ciclo = new Ciclo4_EliminarObjeto();

        // Llamar al método para eliminar el contacto por ID
        ciclo.eliminarContactoPorId(13); // Pasa el ID que deseas eliminar
    }

    public void eliminarContactoPorId(int id) {
        em = null;
        try {
            // Obtener EntityManager desde GenericDAO
            em = getEntityManager();

            // Estado Persistente: Recuperación de la entidad desde la base de datos
            Contacto contacto = em.find(Contacto.class, id);

            if (contacto != null) {
                // Iniciar una transacción para realizar la eliminación
                em.getTransaction().begin();

                // Estado Persistente en la Base de Datos:
                // Usar `remove` para eliminar la entidad de la base de datos.
                // Asegurar que la entidad esté en el contexto de persistencia en caso de que no lo esté ya.
                em.remove(em.merge(contacto));

                // Confirmar la transacción para eliminar la entidad de la base de datos
                em.getTransaction().commit();

                // Mostrar la entidad Contacto eliminada
                System.out.println("Datos eliminados = " + contacto);
            } else {
                System.out.println("No se encontró un contacto con el ID especificado.");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            // Cerrar el EntityManager y la fábrica de EntityManagers para liberar recursos
            em.close();
            closeEntityManagerFactory();
        }
    }
}

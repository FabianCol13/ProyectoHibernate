package com.fabiancol.testciclo;

import com.fabiancol.dao.GenericoDAO;
import com.fabiancol.domain.Contacto;



/**
 * Clase principal para demostrar cómo obtener un objeto JPA desde la base de datos.
 * Se muestra el uso de EntityManager para encontrar una entidad por su ID.
 */
public class Ciclo2_ObtenerObjeto extends GenericoDAO {

    public static void main(String[] args) {
        // Crear instancia de la clase para usar métodos no estáticos
        Ciclo2_ObtenerObjeto ciclo = new Ciclo2_ObtenerObjeto();
        
        ciclo.obtenerContactoPorId(13); // Pasa el ID que deseas buscar
    }

    public void obtenerContactoPorId(int id) {
        em = null;
        try {
            // Obtener EntityManager desde GenericDAO
            em = getEntityManager();

            // Buscar la entidad Contacto en la base de datos usando su ID
            Contacto contacto = em.find(Contacto.class, id);

            // Mostrar la entidad Contacto encontrada
            if (contacto != null) {
                System.out.println("contacto: " + contacto);
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

package com.fabiancol.test;

import com.fabiancol.dao.CursoDAO;
import com.fabiancol.dao.GenericoDAO;
import com.fabiancol.domain.Curso;
import java.util.List;

public class TestFull extends GenericoDAO {

    public static void main(String[] args) {
        TestFull prueba1 = new TestFull();
        prueba1.mostarCursos();
        System.out.println("Llamado a la función insertar");
        prueba1.insertarCurso();
        System.out.println("Llamado a consultar ID 3");
        prueba1.obtenerCursoPorId(3);
        System.out.println("LLamado a modificar");
        prueba1.modificarCursoPorId(3);
        System.out.println("LLamado a Eliminar");
        prueba1.eliminarCursoPorId(13);
    }

    public void mostarCursos() {
        System.out.println("___________________________Listar Cursos_________________________:");
        // Estado Transitorio
        CursoDAO cursoDAOListar = new CursoDAO();
        List<Curso> cursos = cursoDAOListar.listar();
        for (Curso c : cursos) {
            System.out.println("Curso: " + c.getNombre_curso() + ", Precio: " + c.getPrecio_curso());
        }
    }

    public void insertarCurso() {
        System.out.println("___________________________Insertar Curso_________________________:");

        // Estado Transitorio
        Curso curso = new Curso();
        curso.setNombre_curso("Curso Final");
        curso.setPrecio_curso(255000);

        try {
            em = getEntityManager();
            // Estado Persistente - Inicio de la transacción
            em.getTransaction().begin();
            em.persist(curso); // Persistencia del nuevo curso en la base de datos
            em.getTransaction().commit(); // Confirmación de la transacción
        } catch (Exception e) {
            if (em == null) {
                e.printStackTrace(System.out);
            }
        } finally {
            em.close(); // Estado Detached - Cierre del EntityManager
        }
        System.out.println("Nuevo Curso = " + curso);
    }

    public void obtenerCursoPorId(int id) {
        System.out.println("___________________________Obtener Curso ID: " + id + "_________________________:");

        // Estado Transitorio
        em = null;
        try {
            em = getEntityManager();
            // Estado Persistente - Búsqueda del curso por su ID
            Curso curso = em.find(Curso.class, id);
            if (curso != null) {
                System.out.println("curso: " + curso);
            } else {
                System.out.println("No se encontró un curso con el ID especificado.");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close(); // Estado Detached - Cierre del EntityManager
            }
        }
    }

    public void modificarCursoPorId(int id) {
        System.out.println("___________________________Modificar Curso ID: " + id + "_________________________:");

        // Estado Transitorio
        em = null;
        try {
            em = getEntityManager();
            // Estado Persistente - Inicio de la transacción
            em.getTransaction().begin();
            Curso curso = em.find(Curso.class, id); // Búsqueda del curso por su ID

            if (curso != null) {
                curso.setNombre_curso("DEMO");
                curso.setPrecio_curso(30.000);
                em.merge(curso); // Actualización del curso en la base de datos
                em.getTransaction().commit(); // Confirmación de la transacción
                System.out.println("Curso a modificar " + curso);
            } else {
                System.out.println("No se encontró un curso con el ID especificado.");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback en caso de error
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Estado Detached - Cierre del EntityManager
            }
        }
    }

    public void eliminarCursoPorId(int id) {
        System.out.println("___________________________Eliminar Curso ID: " + id + "_________________________:");

        // Estado Transitorio
        em = null;
        try {
            em = getEntityManager();
            // Estado Persistente - Búsqueda del curso por su ID
            Curso curso = em.find(Curso.class, id);
            if (curso != null) {
                em.getTransaction().begin();
                em.remove(em.merge(curso)); // Eliminación del curso de la base de datos
                em.getTransaction().commit(); // Confirmación de la transacción
                System.out.println("Datos eliminados = " + curso);
            } else {
                System.out.println("No se encontró un curso con el ID especificado.");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close(); // Estado Detached - Cierre del EntityManager
            }
            closeEntityManagerFactory();
        }
    }
}

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ud04_02Departamentos.entity.Departamentos;

public class LibDept {
	
	public static void agregar(SessionFactory sf, Departamentos departamento) throws Exception {
		// Variable para manejar la transacción.
		Transaction tx = null;
		try (Session session = sf.openSession() ){
	        // Inicia una nueva transacción.
	        tx = session.beginTransaction();
	        // Convierte el objeto en una fila de la tabla y almacenamos en memoria
	        session.persist(departamento);
	        // Guarda los cambios en la BD.
	        tx.commit();
		} catch (Exception e) {
			if (tx != null) {
		        // Si hay una transacción activa deshace los cambios.
				tx.rollback();
			}
			throw e;
		}
	}
	
	
	public static Departamentos obtenerPorID(SessionFactory sf, int id) throws Exception {
		try (Session session = sf.openSession() ){
	        // adquiere y devuelve el objeto relacionado con la ID
	        Departamentos departamento = session.get(Departamentos.class, id);
	        return departamento;
		} catch (Exception e) {
			throw e;
		}
	}
	
}

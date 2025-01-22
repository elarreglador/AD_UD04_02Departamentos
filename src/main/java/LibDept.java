import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ud04_02Departamentos.entity.Departamentos;

public class LibDept {
	
	public static boolean agregar(SessionFactory sf, Departamentos departamento) throws Exception {
		// Verifica que no exista el departamento
		if ( !LibDept.existeID(sf, departamento.getDeptNo()) ) {
			// Variable para manejar la transacción.
			Transaction tx = null;
			try (Session session = sf.openSession() ){
		        // Inicia una nueva transacción.
		        tx = session.beginTransaction();
		        // Convierte el objeto en una fila de la tabla y almacenamos en memoria
		        session.persist(departamento);
		        // Guarda los cambios en la BD.
		        tx.commit();
		        return true;
			} catch (Exception e) {
				if (tx != null) {
			        // Si hay una transacción activa deshace los cambios.
					tx.rollback();
				}
				throw e;
			}
		} else {
			return false;
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
	
	
	public static boolean existeID(SessionFactory sf, int id) throws Exception {
		try (Session session = sf.openSession() ){
	        // adquiere y devuelve el objeto relacionado con la ID
	        Departamentos resuelve = LibDept.obtenerPorID(sf, id);
	        if ( resuelve == null ) {
	        	return false;
	        }
	        return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
}

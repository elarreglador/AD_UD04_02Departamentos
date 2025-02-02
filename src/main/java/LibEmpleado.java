import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ud04_02Departamentos.entity.Empleados;

public class LibEmpleado {
	
	public static boolean agregar(SessionFactory sf, Empleados empleado) throws Exception {
		// Verifica que no exista el empleado
		if ( !LibEmpleado.existeID(sf, empleado.getEmpNo()) ) {
			// Variable para manejar la transacción.
			Transaction tx = null;
			try (Session session = sf.openSession() ){
		        // Inicia una nueva transacción.
		        tx = session.beginTransaction();
		        // Convierte el objeto en una fila de la tabla y almacenamos en memoria
		        session.persist(empleado);
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
	
	
	public static boolean eliminar(SessionFactory sf, int id) throws Exception {
		// Verifica que no exista el empleado
		if ( LibEmpleado.existeID(sf, id) ) {
			// Variable para manejar la transacción.
			Transaction tx = null;
			try (Session session = sf.openSession() ){
				// Obtiene el departamento
				Empleados empleado = LibEmpleado.obtenerPorID(sf, id);
		        // Inicia una nueva transacción.
		        tx = session.beginTransaction();
		        // elimina la fila de la memoria
		        session.remove(empleado);
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
	
	
	public static void sincronizar (SessionFactory sf, Empleados empleado) 
			throws Exception {
	    try (Session session = sf.openSession()) {
	        Transaction tx = session.beginTransaction();
	        try {
	            session.merge(empleado); // Sincroniza
	            tx.commit(); // Guarda los cambios en la BD.
	        } catch (Exception e) {
	            if (tx != null) tx.rollback(); // Revertir si error
	            throw e;
	        }
	    }
	}
	
	
	public static Empleados obtenerPorID(SessionFactory sf, int id) throws Exception {
		try (Session session = sf.openSession() ){
	        // adquiere y devuelve el objeto relacionado con la ID
			Empleados emleado = session.get(Empleados.class, id);
	        return emleado;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static boolean existeID(SessionFactory sf, int id) throws Exception {
		try (Session session = sf.openSession() ){
	        // adquiere y devuelve el objeto relacionado con la ID
	        Empleados resuelve = LibEmpleado.obtenerPorID(sf, id);
	        if ( resuelve == null ) {
	        	return false;
	        }
	        return true;
		} catch (Exception e) {
			throw e;
		}
	}

}

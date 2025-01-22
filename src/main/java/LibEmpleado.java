import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ud04_02Departamentos.entity.Empleados;

public class LibEmpleado {
	
	public static void agregar(SessionFactory sf, Empleados empleado) throws Exception {
		// Variable para manejar la transacción.
		Transaction tx = null;
		try (Session session = sf.openSession() ){
	        // Inicia una nueva transacción.
	        tx = session.beginTransaction();
	        // Convierte el objeto en una fila de la tabla y almacenamos en memoria
	        session.persist(empleado);
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

}

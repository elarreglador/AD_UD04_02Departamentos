import javax.transaction.SystemException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ud04_02Departamentos.entity.Departamentos;

public class Main {

	public static void main(String[] args) throws Exception, SystemException {
		
        // Variable para manejar la transacción.
		Transaction tx = null;
		
		// Iniciamos sesion en la BD y verificamos conexion
    	SessionFactory sf = HibernateUtil.getSessionFactory();
    	try ( Session session = sf.openSession() ) {
        
	        if ( session != null ) {
	        	System.out.println("Sesion abierta");
	        } else {
	        	System.out.println("Excepcion al abrir la sesion");
	        }
	        
	        // Generamos un nuevo departamento
	        Departamentos departamento = new Departamentos(778);
	        departamento.setDnombre("New department"); // Max. 15 caracteres
	        departamento.setLoc("Valencia");
	        
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

}

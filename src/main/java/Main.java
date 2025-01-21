import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	public static void main(String[] args) {
		// Llamamos a la funciona para generar una sessionFactory
    	SessionFactory sf = HibernateUtil.getSessionFactory();
    	
    	Session session = sf.openSession();
        
        if ( session != null ) {
        	System.out.println("Sesion abierta");
        } else {
        	System.out.println("Excepcion al abrir la sesion");
        }
        
        session.close();

	}

}

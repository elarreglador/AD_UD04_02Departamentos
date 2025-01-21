import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	// Singleton template with lazy initialization
	private static SessionFactory instance = null;
	private HibernateUtil() {}
	public static SessionFactory getSessionFactory() {
		if (instance == null) {
			instance = new Configuration().configure().buildSessionFactory();
		}
		return instance;
	}
	
	
	// Main para verificar funcionamiento
	public static void main(String[] args) {
		// Crea una instancia llamando a la funcion 
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();
		
		if ( session != null ) {
        	System.out.println("Sesion abierta");
        } else {
        	System.out.println("Excepcion al abrir la sesion");
        }
		
		session.close();
	}


}

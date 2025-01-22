import javax.transaction.SystemException;
import org.hibernate.SessionFactory;
import ud04_02Departamentos.entity.Departamentos;

public class Main {

	public static void main(String[] args) throws Exception, SystemException {
		
		// Generamos una BD SessionFactory singleton en el try
		try ( SessionFactory sf = HibernateUtil.getSessionFactory() ) {
				        
	        // Generamos un nuevo departamento
	        Departamentos departamento = new Departamentos(778);
	        departamento.setDnombre("New department"); // Max. 15 caracteres
	        departamento.setLoc("Valencia");
	        
	        LibDept.agregar(sf, departamento);
			        
		}
	}

}

import javax.transaction.SystemException;
import org.hibernate.SessionFactory;
import ud04_02Departamentos.entity.Departamentos;
import ud04_02Departamentos.entity.Empleados;

public class Main {

	public static void main(String[] args) throws Exception, SystemException {
		
		// Generamos una BD SessionFactory singleton en el try
		try ( SessionFactory sf = HibernateUtil.getSessionFactory() ) {
				        
	        // Generamos un nuevo departamento
	        Departamentos departamento = new Departamentos(778);
	        departamento.setDnombre("New department"); // Max. 15 caracteres
	        departamento.setLoc("Valencia");
	        
	        // Generamos un nuevo empleado
	        Empleados empleado = new Empleados(234);
	        empleado.setDepartamentos(departamento); // indicamos su departamento
	        empleado.setApellido("Marcial");
	        empleado.setOficio("Tornero");
	        empleado.setSalario(1200f);
	        
	        // Guardamos departamento y empleado
	        LibDept.agregar(sf, departamento);
	        LibEmpleado.agregar(sf, empleado);
			        
		}
	}

}

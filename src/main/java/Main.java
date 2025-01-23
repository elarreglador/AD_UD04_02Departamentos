import javax.transaction.SystemException;
import org.hibernate.SessionFactory;
import ud04_02Departamentos.entity.Departamentos;
import ud04_02Departamentos.entity.Empleados;

public class Main {

	public static void main(String[] args) throws Exception, SystemException {
		
		// Generamos una BD SessionFactory singleton en el try
		try ( SessionFactory sf = HibernateUtil.getSessionFactory() ) {
	        boolean creado;
	        boolean eliminado;
	        
	        // Generamos y guardamos nuevo departamento
	        Departamentos departamento = new Departamentos(778);
	        departamento.setDnombre("Nou departament"); // Max. 15 caracteres
	        departamento.setLoc("Valencia");
	        creado = LibDept.agregar(sf, departamento);
	        System.out.println("Generado nuevo departamento: " + creado);
	        
	        // Generamos y guardamos nuevo empleado1
	        Empleados empleado1 = new Empleados(234);
	        empleado1.setDepartamentos(departamento); // indicamos su departamento
	        empleado1.setApellido("Escribano");
	        empleado1.setOficio("Tornero");
	        empleado1.setSalario(1200f);
	        creado = LibEmpleado.agregar(sf, empleado1);
	        System.out.println("Generado nuevo empleado: " + creado);
	        
	        // Generamos y guardamos nuevo empleado2 si no existe
	        Empleados empleado2 = null;
	        if (!LibEmpleado.existeID(sf, 235)) {
		        empleado2 = new Empleados(235);
		        empleado2.setDepartamentos(
		    	        // indicamos departamento por su ID
		        		LibDept.obtenerPorID(sf, 1)
		        ); 
		        empleado2.setApellido("Ruiz");
		        empleado2.setOficio("Vendedor");
		        empleado2.setSalario(1100f);
		        creado = LibEmpleado.agregar(sf, empleado2);
		        System.out.println("Generado nuevo empleado: " + creado);
	        } else {
	        	// Si existe lo recuperamos de la BD
	        	empleado2 = LibEmpleado.obtenerPorID(sf, 235);
	        }
	        
	        // Elimina el empleado1
	        eliminado = LibEmpleado.eliminar(sf, empleado1.getEmpNo());
	        System.out.println("empleado con ID:234 eliminado: " + eliminado);
	        
	        // Aumenta el sueldo del empleado2 70EUR
	        float oldSalario = empleado2.getSalario();
	        float newSalario = oldSalario + 150f;
	        empleado2.setSalario( newSalario );
	        LibEmpleado.sincronizar(sf, empleado2);
	        System.out.println("El salario de " + empleado2.getApellido() +
	        		" era de " + oldSalario + "EUR y ahora es de " +
	        		empleado2.getSalario() + "EUR.");
		} catch (Exception e) {
			System.out.println("EXCEPCION: " + e);
		}
	}

}

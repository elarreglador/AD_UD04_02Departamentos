import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ud04_02Departamentos.entity.Empleados;
import ud04_02Departamentos.entity.Departamentos;
import java.util.List;

public class PruebaHibernate {
    public static void main(String[] args) {
    	try ( SessionFactory sf = new Configuration().configure()
    			.buildSessionFactory() ){
	        try ( Session session = sf.openSession() ) {
	        	
		        // Muchas consultas requieren un @OverWrite de toString()
		        // Consulta de empleados ( imprime objeto.toString() )
	        	System.out.println("\nConsulta de empleados");
		        Query<Empleados> query = session.createQuery( 
		        		"FROM Empleados", Empleados.class );
		        List<Empleados> list = query.list();
		        list.forEach(e-> System.out.println( e.toString() ) );
		        
		    // Consulta de departamentos mas elegante ( imprime objeto )
		        System.out.println("\nConsulta de departamentos");
		        session.createQuery("FROM Departamentos", 
		        		Departamentos.class)
		        	.list()
		        	.forEach(e-> System.out.println( e.toString() ) );
		        
		    // Empleados que cobren más de un mínimo
		        System.out.println("\nEmpleados que cobren > $minimo");
		        float minimo = 1000f;
		        Query<Empleados> query3 = session.createQuery(
		                "FROM Empleados E " +
		                "WHERE E.salario > :minimo", Empleados.class);
		        query3.setParameter("minimo", minimo);
		        List<Empleados> resultados = query3.list();
		        resultados.forEach(r -> System.out.println(r.getApellido() 
		        		+ " | " + r.getSalario()));
		        
		    // Empleados de departamento Ventas
		        System.out.println("\nEmpleados de departamento Ventas");
		        String dep = "ventas";
		        Query<Empleados> query4 = session.createQuery(
		                "FROM Empleados E " +
		                "WHERE E.departamentos.dnombre = :dep", 
		                Empleados.class);
		        query4.setParameter("dep", dep);
		        List<Empleados> resultados4 = query4.list();
		        resultados4.forEach(r -> System.out.println("Emp_no: " + 
		        r.getEmpNo() + ", " + r.getApellido()));
		        
		    // Empleados de departamento Ventas con salario > 1500
		        System.out.println("\nEmpl. dep Ventas con salario >1500");
		        String dep5 = "ventas";
		        Query<Empleados> query5 = session.createQuery(
		                "FROM Empleados E " +
		                "WHERE E.departamentos.dnombre = :dep5 " +
		                "AND E.salario > 1500", Empleados.class);
		        query5.setParameter("dep5", dep5);
		        List<Empleados> resultados5 = query5.list();
		        resultados5.forEach(r -> System.out.println(
		        		"Emp_no: " + r.getEmpNo() + ", " + r.getApellido() 
		        		+ ", " + r.getSalario() + "EUR.") );
		        
		    // Apellidos y la ciudad donde trabaja 
		        System.out.println("\nApellido y ciudad donde trabaja");
		        Query<Empleados> query6 = session.createQuery(
		                "FROM Empleados E ", Empleados.class);
		        List<Empleados> resultados6 = query6.list();
		        resultados6.forEach(r -> System.out.println( 
		        		r.getApellido() + " trabaja en " + 
		        		r.getDepartamentos().getLoc() ) );
		        
		     // Apellidos y la ciudad donde trabaja ordenados
		        System.out.println("\nApellido y ciudad ordenados");
		        Query<Empleados> query6b = session.createQuery(
		                "FROM Empleados E " +
		                "ORDER by E.departamentos.loc asc, " +
		                "E.apellido asc", 
		                Empleados.class);
		        List<Empleados> resultados6b = query6b.list();
		        resultados6b.forEach(r -> System.out.println( 
		        		r.getApellido() + " trabaja en " + 
		        		r.getDepartamentos().getLoc() ) );
		        
		    // Empleado más veterano
		        System.out.println("\nEmpleado más veterano");
		        Empleados empleado = session.createQuery(
		                "FROM Empleados E " +
		                "WHERE E.fechaAlta = " +
		                "(SELECT min(E1.fechaAlta) FROM Empleados E1)",
		                Empleados.class
		        ).list().get(0);  // Nos quedamos con el primer resultado 
		        System.out.println(empleado.toString());
		        
		    //número de empleados en cada departamento
		        System.out.println("\nnúmero de empleados por depart.");
		        Query<Departamentos> query7 = session.createQuery(
		                "FROM Departamentos ", Departamentos.class);
		        List<Departamentos> resultados7 = query7.list();
		        resultados7.forEach(r -> System.out.println( 
		        		r.getDnombre() + " tiene " + 
		        		r.getempleados().size() + " empleados." ) );
		        		        
	        } catch (Exception e) { System.out.println("Exception " + e); }
    	} catch (Exception e) { System.out.println("Exception : " + e); }
    }
}

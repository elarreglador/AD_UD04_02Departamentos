import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import ud04_02Departamentos.entity.Empleados;
import ud04_02Departamentos.entity.Departamentos;

import java.util.List;

public class PruebaHibernate {
    public static void main(String[] args) {
    	try ( SessionFactory sf = new Configuration().configure().buildSessionFactory() ){
	        try ( Session session = sf.openSession() ) {
		        
		        // Muchas consultas requieren un @OverWrite de toString() del objeto
		        // Consulta de empleados ( imprime objeto.toString() )
	        	System.out.println("\nConsulta de empleados");
		        Query<Empleados> query = session.createQuery( "FROM Empleados", Empleados.class );
		        List<Empleados> list = query.list();
		        list.forEach(e-> System.out.println( e.toString() ) );
		        
		        // Consulta de departamentos mas elegante ( imprime objeto.toString() )
		        System.out.println("\nConsulta de departamentos mas elegante");
		        session.createQuery("FROM Departamentos", Departamentos.class)
		        	.list()
		        	.forEach(e-> System.out.println( e.toString() ) );
		        
		        // Empleados que cobren mas de un minimo ( imprime dos campos )
		        System.out.println("\nEmpleados que cobren mas de un minimo");
		        float minimo = 1000f;
		        Query<Object[]> query3 = session.createQuery(
		                "SELECT E.apellido, E.salario " +
		                "FROM Empleados E " +
		                "WHERE E.salario > :minimo", Object[].class);
		        query3.setParameter("minimo", minimo);
		        List<Object[]> resultados = query3.list();
		        resultados.forEach(r -> System.out.println( r[0] + " | " + r[1]));
		        
		        // Empleados de departamento Ventas
		        System.out.println("\nEmpleados de departamento Ventas");
		        String dep = "ventas";
		        Query<Object[]> query4 = session.createQuery(
		                "SELECT E.empNo, E.apellido " +
		                "FROM Empleados E " +
		                "WHERE E.departamentos.dnombre = :dep", Object[].class);
		        query4.setParameter("dep", dep);
		        List<Object[]> resultados4 = query4.list();
		        resultados4.forEach(r -> System.out.println("Emp_no: " + r[0] + ", " + r[1]));
		        
		        // Empleados de departamento Ventas con salario > 1500
		        System.out.println("\nEmpleados de departamento Ventas con salario > 1500");
		        String dep5 = "ventas";
		        Query<Object[]> query5 = session.createQuery(
		                "SELECT E.empNo, E.apellido, E.salario " +
		                "FROM Empleados E " +
		                "WHERE E.departamentos.dnombre = :dep5 " +
		                "AND E.salario > 1500", Object[].class);
		        query5.setParameter("dep5", dep5);
		        List<Object[]> resultados5 = query5.list();
		        resultados5.forEach(r -> System.out.println("Emp_no: " + r[0] + ", " + r[1] + ", " + r[2] + "EUR."));
	
	        } catch (Exception e) {
	        	System.out.println("Exception en session: " + e);
	        }
    	} catch (Exception e) {
        	System.out.println("Exception en SessionFactory: " + e);
    	}
    }
}

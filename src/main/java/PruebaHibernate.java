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
		        System.out.println("Listado 1: Listado de Empleados");
		        Query<Empleados> query = session.createQuery( "FROM Empleados", Empleados.class );
		        List<Empleados> list = query.list();
		        list.forEach(e-> System.out.println( e.toString() ) );
		        
		        // Consulta de empleados mas elegante ( imprime objeto.toString() )
		        System.out.println("Listado 2: Listado de Empleados");
		        session.createQuery("FROM Empleados", Empleados.class)
		        	.list()
		        	.forEach(e-> System.out.println( e.toString() ) );
		        
		        // Empleados que cobren mas de un minimo ( imprime dos campos )
		        float minimo = 1000f;
		        Query<Object[]> query3 = session.createQuery(
		                "SELECT E.apellido, E.salario " +
		                "FROM Empleados E " +
		                "WHERE E.salario > :minimo", Object[].class);
		        query3.setParameter("minimo", minimo);
		        List<Object[]> resultados = query3.list();
		        resultados.forEach(r -> System.out.println( r[0] + " | " + r[1]));
	
	        } catch (Exception e) {
	        	System.out.println("Exception en session: " + e);
	        }
    	} catch (Exception e) {
        	System.out.println("Exception en SessionFactory: " + e);
    	}
    }
}

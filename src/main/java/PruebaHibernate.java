import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ud04_02Departamentos.entity.Empleados;
import ud04_02Departamentos.entity.Departamentos;

import java.util.List;

public class PruebaHibernate {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        
        System.out.println("Listado 1");
        Query<Empleados> query = session.createQuery( "from Empleados", Empleados.class );
        List<Empleados> list = query.list();
        list.forEach(e-> System.out.println( e.toString() ) );
        
        System.out.println("Listado 2");
        session.createQuery("from Empleados", Empleados.class)
        	.list()
        	.forEach(e-> System.out.println( e.toString() ) );
        
        System.out.println("Listado 3");
        Query<Departamentos> query2 = session.createQuery( "from Departamentos", Departamentos.class );
        List<Departamentos> list2 = query2.list();
        list2.forEach(e-> System.out.println( e.toString() ) );
        
        System.out.println("Listado 4");
        session.createQuery("from Departamentos", Departamentos.class)
        	.list()
        	.forEach(e-> System.out.println( e.toString() ) );

        session.close();
        factory.close();
    }
}

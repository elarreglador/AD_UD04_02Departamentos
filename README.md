# UD04_02Departamentos

Este es un proyecto para comprender el funcionamiento de Hibernate, un ORM java que permite trabajar con bases de datos como si fueran objetos.

Cada Tabla/objeto (entidad) se considera que tiene columnas/atributos, al trabajar con objetos, podemos usar getters y setters para leer y cambiar sus atributos, y estos se almacenaran en la base de datos en forma de filas de una tabla.



## Archivos

Main.java - Cuerpo principal de la app

HibernateUtil.java - Breve libreria propia de conexion a BD

Departamentos.java - Pojo de la clase Departamentos

Empleados.java - Pojo de la clase Empleados

LibDept.java - Herramientas auxiliares de los departamentos

LibEmpleado.java - Herramientas auxiliares de los empleados

## Creacion de la base de datos

'''
CREATE DATABASE ejemplo;
USE ejemplo;
CREATE TABLE departamentos (
dept_NO INT NOT NULL PRIMARY KEY,
dnombre VARCHAR (15),
loc VARCHAR (15));
CREATE TABLE empleados (
Emp_no INT NOT NULL PRIMARY KEY,
apellido VARCHAR (20) ,
oficio VARCHAR (15) ,
dir INT,
fecha_alta DATE,
salario FLOAT (6,2),
comision FLOAT(6,2),
dept_NO INT,
FOREIGN KEY(dept_NO) REFERENCES departamentos(dept_NO));
INSERT INTO departamentos VALUES (1, 'Ventas', 'VALENCIA');
INSERT INTO departamentos VALUES (2, 'Administracion', 'MADRID');
INSERT INTO departamentos VALUES (3, 'Ingenieria', 'BARCELONA');
INSERT INTO departamentos VALUES (4, 'Fabricacion', 'BARCELONA');
INSERT INTO empleados VALUES (1, 'Garcia', 'Comercial', 1, '2019-01-01', 1200, 20, 1);
INSERT INTO empleados VALUES (2, 'Martinez', 'Comercial', 1, '2020-01-01', 1800, 15, 1);
INSERT INTO empleados VALUES (3, 'Torres', 'Tecnico Com', 1, '2020-01-01', 1800, 15, 1);
INSERT INTO empleados VALUES (4, 'Pérez', 'Administrativo', 1, '2019-02-01', 1300, 0, 2);
INSERT INTO empleados VALUES (5, 'López', 'Ing Jefe', 1, '2019-01-01', 2200, 5, 3);
INSERT INTO empleados VALUES (6, 'Sánchez', 'Ingeniero', 1, '2019-01-01', 1800, 5, 3);
'''

## Descripcion de la BD

'''
ejemplo
 |
 L departamentos
 |  L dept_NO - INT NOT NULL PRIMARY KEY,
 |  L loc - VARCHAR (15)
 |  L dnombre - VARCHAR (15)
 L empleados
    L Emp_no - INT NOT NULL PRIMARY KEY
    L oficio - VARCHAR (15)
    L dept_NO - INT
    L comision - FLOAT (6,2)
    L fecha_alta - DATE
    L salario - FLOAT (6,2)
    L dir - INT
    L apellido - VARCHAR (20)
'''



# Trabajo Final Tienda 
Este trabajo práctico ha sido realizado para el módulo de programación del primer curso de desarrollo de aplicaciones web.


Es un proyecto maven.

## Requisitos:
Qué cosas necesitamos antes de empezar:

Instalaciones:
```
Sqlite3 
```
Las librerías necesarias están vinculadas al pom.xml.

## Creación de base de datos
En el proyecto quiero recalcar que ya tenemos el archivo **compra.db** asi que si tenemos dicho fichero el paso siguiente **no es necesario hacerlo.**


**IMPORTANTE**


Si no tenemos el archivo por cualquier motivo pueden seguir estas indicaciones:


Necesitamos crear base de datos en SQLite. 

Este script de java nos serviría para ello.
Lo creamos y lo llamamos JDBC.java
```
import java.sql.*;
//esta clase es para crear la tabla. para poder hacerlo, primero hay que poseer un archivo con extension .db
//en nuestro caso tenemos compra.db

public class JDBC {
	public static void main(String[] args) {
		Connection conn = null;
		try{
			String url = "jdbc:sqlite:compra.db";
			String sql = "CREATE TABLE IF NOT EXISTS compra (Cliente TEXT,Producto TEXT,Cantidad Double,Precio DOUBLE,ID INTEGER, Fecha TIMESTAMP);";
			conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
}
```
Una vez hecho esto, entramos en sqlite con el comando:
```
sqlite3 compra.db
```
Y visualizamos la tabla con el comando **.tables**.


Debe de salir la tabla compra. 

Una vez ejecutado el programa podemos visualizar los datos que contiene nuestra base de datos de la siguiente forma:
```
sqlite3 compra.db
select * from compra;
```
En estas URLs podemos visualizar la creación para saber más acerca de esto:

* https://www.imaginanet.com/blog/primeros-pasos-con-sqlite3-comandos-basicos.html

 
## Ejecución
Para ejecutar, nos vamos al raíz del proyecto maven (donde visualizamos el pom.xml y la carpeta src) y ejecutamos los comandos siguientes:
```
$  mvn compile
$  mvn dependency:copy-dependencies package
$  java -cp target/nombre-archivo.jar:target/dependency/*:. tienda
```


## ¿Qué se ha usado?
Para este trabajo usamos JSON para leer el catálogo de productos y JDBC (Java Data Base Connectivity) para almacenar los pedidos en una base de datos. Están añadidas las dependencias en el fichero pom.xml.

## Contenido del proyecto:

Tienda.java     --> Es la clase principal, cuyo funcionamiento se explica más adelante.

Compra.java     --> Crea un objeto compra, que contiene la clase Articulo, y la clase Persona implementadas.

Articulo.java   --> Objeto articulo, que contiene el nombre y el precio del articulo.

Person.java     --> Objeto persona, que contiene el nombre de una persona.

DAOCompra.java  --> Interface que se usará para hacer implementaciones de base de datos.

JDBCCompra.java --> Contiene métodos para conectarse a la bd, para grabar, y para consultar la base de datos.

productos.json  --> Aquí se guarda el catálogo de compras, en formato json.

compra.db       --> Es el fichero de base de datos, que contiene la tabla "compra".

## Fin:
Este programa tiene el fin de guardar una lista de compras o factura de compras.  
Al ejecutar mostrará una serie de productos disponibles, de los cuales el cliente deberá elegir el deseado y la cantidad que desee. 

Una vez elegidos todos los productos, se mostrará el precio total por producto.  
Seguidamente nos dará la opción de visualizar la factura a través de una base de datos, donde se almacenan los datos de las facturas (persona, producto, cantidad, precio e ID).

## ¿Cómo funciona?
En un principio, el programa Tienda.java coge informacion del fichero json, para asi poseer un catalogo de productos permitidos, que contienen nombre y precio. Una vez obtenido el catalogo, el programa hace una nueva compra(ahi es donde entran las clases Articulo,Compra, y Person), y empieza a pedir datos por teclado, para definir dicha compra(nombre de la persona, que articulo se quiere comprar, etc..).  

El programa es capaz de que una persona, pueda tener uno o mas articulos, y tambien puede tener una o mas compras(que estan distribuidos por ID) por lo que, se pueden repetir nombres en las personas.Cada vez que se hace una entrada, al finalizar, el programa guarda la informacion en la base de datos del fichero compra.db(aqui es donde se empieza a usar El fichero DAOCompra, y su implementación). 

Una vez que ya se acaban con las entradas, existe la posibilidad de consultar los datos. Al ir por ese camino, se muestra un listado completo, mostrando todos los datos que hay guardados en la base de datos, y despues de eso, te ofrece 3 posibilidades: Consultar por nombre, Consultar por producto, y consultar por ID de compra. Finalmente, cuando ya no se quiere consultar mas, finaliza el programa.

## Cambios anteriores redactados:
Los cambios para la v0.2:  
*Se le ha añadido fecha y hora a la factura de la compra, tanto a la clase Compra como a la compra guardada en la base de datos compra.db. *
*Pequeñas mejoras de código.*
## Cambios en progreso fecha 2020:
*In progress*


*Maven*

## Autores:
* **Robert Marius Puiu** - *Initial work* - *Enlazamiento entre código, mejoras y agregado de código adicional.*
* **Guillermo Pérez Aragón** - *Initial work* - *Aportación de clases, DAOs, fechas y mejoras de código*
* **Francisco Javier Bernal Ramírez** - *In progress* 
* **Samuel Rivera Peñalosa** - *In progress* 

&copy;

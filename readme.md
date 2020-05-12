# Trabajo Final Tienda 
Este trabajo práctico ha sido realizado para el módulo de programación del primer curso de desarrollo de aplicaciones web.


Es un proyecto maven.

## Requisitos:
Qué necesitamos antes de empezar:

Instalaciones:
```
Sqlite3 

MySql
```
Sobre cómo instalar, os dejamos unos manuales:

- https://www.digitalocean.com/community/tutorials/como-instalar-mysql-en-ubuntu-18-04-es


- https://www.ochobitshacenunbyte.com/2019/10/01/instalacion-y-uso-basico-de-sqlite-en-ubuntu-18-04/




## Creación de base de datos
Usaremos el script CompraSqlite.java si queremos crear la base de datos sqlite y el CompraMysql para crear la base de datos mysql.


Ambos scripts están en la carpeta *crearBDs*


**IMPORTANTE**


En el archivo CompraSqlite.java debemos cambiar la ruta de la url para que coincida con la vuestra.


Para crear las bases de datos con ambos scripts necesitamos los drivers de mysql y sqlite para compilar y ejecutar. Debemos de guardar en una carpeta llamada de la forma qu queramos los archivos .jars, por ejemplo nosotros la llamamos misjars.


Podemos encontrar los drivers en estas URLs:

 - https://mvnrepository.com/artifact/mysql/mysql-connector-java
 
 
 - https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc



**SQLITE**


*Compilar*
```
$ javac -cp ~/misjars/*:. CompraSqlite.java
```
*Ejecutar*

```
$ java -cp ~/misjars/*:. CompraSqlite
```



**MYSQL**


*Compilar*
```
$ javac -cp ~/misjars/*:. CompraMysql.java
```

*Ejecutar*
```
$ java -cp ~/misjars/*:. CompraMysql
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



Para mysql entraremos con el siguiente comando
```
$ mysql -u root -p
```
Compobamos las base de datos con:
```
SHOW DATABASES;
```
Usamos la bd con:
```
USE compra;
```
Podemos ver la tabla con:
```
SHOW TABLE;
```
o con:
```
DESCRIBE compra;
```


## Antes de ejecutar
*IMPORTANTE*


En el fichero Beans.xml habra que elegir que base de datos queremos comentando una u otra.


También habrá que cambiar la ruta de la base de datos sqlite para que coincida con la vuestra.


 
## Ejecución del proyecto
Para ejecutar, nos vamos al raíz del proyecto maven (donde visualizamos el pom.xml y la carpeta src) y ejecutamos los comandos siguientes:
```
$  mvn compile
$  mvn dependency:copy-dependencies package
$  java -cp target/nombre-archivo.jar:target/dependency/*:. tienda
```
Para ejecutar el proyecto con JavaFx 
```
$  mvn compile
$  mvn dependency:copy-dependencies package
$  java -cp target/nombre-archivo.jar:target/dependency/*:. javafx
```


## Contenido del proyecto:

tienda.java     --> Es la clase principal, cuyo funcionamiento se explica más adelante.

Compra.java     --> Crea un objeto compra, que contiene la clase Articulo, y la clase Persona implementadas.

Articulo.java   --> Objeto articulo, que contiene el nombre y el precio del articulo.

Person.java     --> Objeto persona, que contiene el nombre de una persona.

DAOCompra.java  --> Interface que se usará para hacer implementaciones de base de datos.

JDBCCompra.java --> Contiene métodos para conectarse a la bd, para grabar, y para consultar la base de datos.

productos.json  --> Aquí se guarda el catálogo de compras, en formato json.



## Fin:
Este programa tiene el fin de guardar una lista de compras o factura de compras.  
Al ejecutar mostrará una serie de productos disponibles, de los cuales el cliente deberá elegir el deseado y la cantidad que desee. 

Una vez elegidos todos los productos, se mostrará el precio total por producto.  
Seguidamente nos dará la opción de visualizar la factura a través de una base de datos, donde se almacenan los datos de las facturas (persona, producto, cantidad, precio e ID).

## ¿Cómo funciona?
En un principio, el programa tienda.java coge informacion del fichero json, para asi poseer un catalogo de productos permitidos, que contienen nombre y precio. Una vez obtenido el catalogo, el programa hace una nueva compra(ahi es donde entran las clases Articulo,Compra, y Person), y empieza a pedir datos por teclado, para definir dicha compra(nombre de la persona, que articulo se quiere comprar, etc..).  

El programa es capaz de que una persona, pueda tener uno o mas articulos, y tambien puede tener una o mas compras(que estan distribuidos por ID) por lo que, se pueden repetir nombres en las personas.Cada vez que se hace una entrada, al finalizar, el programa guarda la informacion en la base de datos del fichero compra.db(aqui es donde se empieza a usar El fichero DAOCompra, y su implementación). 

Una vez que ya se acaban con las entradas, existe la posibilidad de consultar los datos. Al ir por ese camino, se muestra un listado completo, mostrando todos los datos que hay guardados en la base de datos, y despues de eso, te ofrece 3 posibilidades: Consultar por nombre, Consultar por producto, y consultar por ID de compra. Finalmente, cuando ya no se quiere consultar mas, finaliza el programa.

## Cambios anteriores redactados:
Los cambios para la v0.2: 
- Se le ha añadido fecha y hora a la factura de la compra, tanto a la clase Compra como a la compra guardada en la base de datos compra.db
- Pequeñas mejoras de código.
## Cambios en progreso fecha 2020 *In Progress*:
* Parametrización de datos de bases de datos en fichero Beans.xml, carpeta Resources
* Arreglar fallos de orden de comandos, concretamente en el archivo JDBCCompra.java, donde los ResultSet de dos métodos no estaban correctos
* Utilización de más de una base de datos
* Utilización de Spring Framework
* Añadido reglas de sintaxis en las órdenes condicionales (IgnoreCase) para una mejor interacción con el usuario
* Maven

## Autores:
* **Robert Marius Puiu** - *Initial work* - *Enlazamiento entre código, mejoras y agregado de código adicional.*
* **Guillermo Pérez Aragón** - *Initial work* - *Aportación de clases, DAOs, fechas y mejoras de código*
* **Francisco Javier Bernal Ramírez** - *In progress* 
* **Samuel Rivera Peñalosa** - *In progress* 

&copy;

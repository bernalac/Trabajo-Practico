//imports de lo que usaremos
import java.util.ArrayList;
import java.io.Console;
import java.io.FileReader;
import java.io.BufferedReader;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Double;
import javax.sql.DataSource;
import java.util.Scanner;

public class tienda{
	public static void main(String args[]) throws Exception {
		DAOCompra daocompra = new JDBCCompra();//creacion de DAO para lo relacionado con la base de datos
		System.out.println("Dime que base de datos vas a usar (sqlite o mysql) ");
		Scanner sc = new Scanner(System.in);
		String respuesta1 = sc.nextLine();
		if (respuesta1.equalsIgnoreCase("sqlite")) {
			daocompra.crearSqlite();
		} else if (respuesta1.equalsIgnoreCase("mysql")) {
			daocompra.crearMysql();
		} else {
			System.out.println("No seleccionaste ninguna base de datos existente o algo parecido");
			System.out.println("Esperamos que la próxima selecciones alguna disponible");
			return;
		}
		//a continuacion, el codigo para leer el fichero json, que corresponde al catalogo de productos.	
		String line = new String("");
		String lin = null;
		ArrayList<String> listaProds = new ArrayList<String>();	
		BufferedReader br = new BufferedReader(new FileReader("productos.json"));		
		while((lin = br.readLine()) != null) {
          	line = line + lin;
        }
        JSONArray jsonProductos = new JSONArray(line);

        for (Object obj : jsonProductos){
        		String nombre = ((JSONObject) obj).getString("nombre");
        		String precio = ((JSONObject) obj).getString("precio");
        		listaProds.add(nombre + ":" + precio);//los datos se pasan a una lista,para tenerlos guardados y poder usarlos

        			System.out.println(nombre + ": " + precio + " €");

        	}
        //una vez leidos y guardados, se empieza con la compra
		Compra c;
		ArrayList<Compra> lista1 = new ArrayList<Compra>();
		Console console = null;
		console = System.console();
		while(true){//bucle para crear compras, con sus respectivos datos (persona, articulos, cantidad y precio)
			c = new Compra();
			Person p = new Person();
			System.out.println("Nombre persona: ");//nombre de la persona
			String persona = console.readLine();
			if(persona.equals("")){
					break;
				}
			p.setName(persona);//setteo nombre de la persona
			System.out.println("ID de compra: ");
			String idcomp = console.readLine();
			int idcom=Integer.parseInt(idcomp);
			
			while(true){//este while es para que una persona pueda comprar mas de un articulo en una compra
				c.setId(idcom);//setteo del id
				System.out.println("Articulos: ");//articulo
				String articulo = console.readLine();
				if(articulo.equals("")){
					break;
				}

				for(int i = 0;i<listaProds.size();i++){//aqui esta la informacion del json
					String[] a = listaProds.get(i).split(":");
					if(articulo.equals(a[0])){
						c.getArt().setNombre(articulo);//si el articulo está en la lista de productos disponibles, setteo del articulo

						Double preciop=Double.parseDouble(a[1]);
						System.out.println("Cantidad: ");//cantidad de articulos
						String canti = console.readLine();
						if(canti.equals("")){
							break;
						}
						int number = Integer.parseInt(canti);
						if (number < 0) {
							System.out.println("No puedes llevarte menos de 0.0 productos, es incoherente");
							break;
						}
						Double cant = Double.parseDouble(canti);
						c.getArt().setPrecio(preciop * cant);//setteo de precio por cantidad (precio final por producto)
						c.setCant(cant);//setteo de cantidad
						c.getPer().setName(p.getName());//setteo de la persona dentro de la clase Compra
						Date fechaFactura = new Date();//Creación de fecha
						c.setFecha(fechaFactura);//setteo a la compra de fecha/hora
						lista1.add(c);//añadido de la compra a la lista
						daocompra.grabar(c);//se guarda en la base de datos
					}
				}
				System.out.println("Mas articulos? S|N ");//Para agregar más articulos a la misma persona
				String masart = console.readLine();
				if(masart.equalsIgnoreCase("n")){
					c.setArticulos(lista1);
					break;
				}
				else if (masart.equalsIgnoreCase("s")){
					c = new Compra();//si queremos agregar más articulos crea una nueva compra y se le añaden articulos, al no haber creado una persona nueva
					//se le agrega la ultima persona creada, asi no crea conflictos de persona de compra = null
					//ni agregar articulos de otras personas.
				} else if (!masart.equalsIgnoreCase("n")||!masart.equalsIgnoreCase("s")){
					System.out.println("No me has dado una respuesta convincente");
					break;
				}
			}
			System.out.println("Mas entradas? S|N ");//Para agregar más entradas (personas y articulos)
			String masper = console.readLine();
			if(!masper.equalsIgnoreCase("s")){
				break;
			}
		}
		//Confirmación de registro en la base de datos, con la fecha de la factura.
		System.out.println("Los datos han quedado guardados en la base de datos. A fecha de "+ new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(c.getFecha()));
		//Se le cambia el formato de la fecha de Compra para que la muestre como nosotros la visualizamos dia-mes-año y hora:min:seg

		while(true){//aqui es donde esta la parte de consultas.
			System.out.println("Quieres consultar datos? S|N");
			String respuesta = console.readLine();
			if (respuesta.equalsIgnoreCase("s")){
				daocompra.consultart();//te muestra todos los datos guardados
				System.out.println("Para consultar por persona: N | Para mostrar por ID: I");
				String respuesta2 = console.readLine();
				if(respuesta2.equalsIgnoreCase("n")){
					System.out.println("Introduce nombre");
					String r = console.readLine();
					daocompra.consultarn(r);//te muestra los datos que contienen el nombre introducido
				}
				
				else if(respuesta2.equalsIgnoreCase("i")){
					System.out.println("Introduce id");
					String r = console.readLine();
					int r2=Integer.parseInt(r);
					daocompra.consultari(r2);//te muestra la compra de una persona
				}
				else{//si la opcion que se introdujo no es n, p, i que salte este error y que pregunte si desea consultar o no.
					System.out.println("Lo sentimos! Esa opcion no esta disponible");
				}
			}
			else{//Mensaje despedida cuando el cliente se vaya
				System.out.println("Vuelva pronto.");
				break;
			}
		}
	}
}

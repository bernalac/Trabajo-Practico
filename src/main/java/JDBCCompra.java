import java.util.ArrayList;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//aqui esta el codigo para grabar y consultar
public class JDBCCompra implements DAOCompra{
	private Connection conectar() {//se hace un metodo para la conexión, para asi no tener que copiar el codigo siempre
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Connection conn = null;
		try {
			DataSource ds = (DataSource) context.getBean("ds");;
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("Tenemos un problema, no conecta");
			System.out.println(e.getMessage());
		}
		return conn;

	}





	public void grabar(Compra c) {//codigo para grabar
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(c.getFecha().getTime());
		String sql = "INSERT INTO compra (Cliente,Producto,Cantidad,Precio,ID,Fecha) VALUES(?,?,?,?,?,?)";
		try (Connection conn = this.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, c.getPer().getName());
			pstmt.setString(2, c.getArt().getNombre());
			pstmt.setDouble(3, c.getCant());
			pstmt.setDouble(4, c.getArt().getPrecio());
			pstmt.setInt(5, c.getId());
			pstmt.setTimestamp(6, sqlDate);
			pstmt.executeUpdate();
		} catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}
	//a partir de aqui, el codigo es para consultar.
	public void consultart(){//aqui te muestra todo lo que hay almacenado en la base de datos
		try  {
			String sql1 = "SELECT DISTINCT(ID),Cliente FROM compra";
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("CLIENTE  ID");
			while (rs.next()) {
				 System.out.println(rs.getString("Cliente")+" --> "+rs.getInt("ID"));

			 }	
			
		} catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}

	public void consultarn(String nombre){//aqui te muestra solo aquellos con el nombre introducido
		try{
			String sql2 = "SELECT * FROM compra WHERE CLIENTE ='"+nombre+"'";
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {	
				System.out.println("Cliente: "+rs.getString("Cliente"));

				System.out.println(rs.getDouble("Cantidad")+" Kg. de "+rs.getString("Producto")+" por "+rs.getDouble("Precio") + " €, con ID " + rs.getInt("ID") + " a fecha de " + rs.getTimestamp("Fecha"));

			 }	
		}
		catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}


	public void consultari(int id){//aqui te muestra solo aquellos con la ID introducida.
		try{
			String sql2 = "SELECT * FROM compra WHERE ID ="+id;
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {	
				System.out.println("ID: " + rs.getInt("ID"));
				System.out.println("Esta ID le pertenece a: " + rs.getString("Cliente"));
				System.out.println(rs.getDouble("Cantidad")+" Kg. de "+rs.getString("Producto")+" por "+rs.getDouble("Precio") + " €, a fecha de " + rs.getTimestamp("Fecha"));

			 }	
		}
		catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}
}

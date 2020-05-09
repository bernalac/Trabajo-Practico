import java.sql.*;
import java.io.File;
import java.io.IOException;
//esta clase es para crear base de datos compra.db con la tabla compra

public class CompraSqlite {
    public static void main(String[] args) {
        Connection conn = null;
        File fichero = new File ("../../../compra.db");
        try{
            // A partir del objeto File creamos el fichero físicamente
            if (fichero.createNewFile()) {
                System.out.println("compra.db se ha creado correctamente");
            }
            else {
                System.out.println("No ha podido ser creado compra.db");
            }
            String url = "jdbc:sqlite:/media/samuel/PHILIPS UFD/1DAW/PROGRAMACION/cdaw1/samuelr/tercera/proyectos/proy3/Trabajo-Practico/compra.db";            
            String sql = "CREATE TABLE IF NOT EXISTS compra (Cliente TEXT,Producto TEXT,Cantidad Double,Precio DOUBLE,ID INTEGER, Fecha TIMESTAMP);";
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
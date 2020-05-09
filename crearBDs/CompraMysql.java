import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.TimeZone;

public class CompraMysql { 

    public static void main (String[] args) { 
        try { 
            String bd = "CREATE DATABASE IF NOT EXISTS compra;";
            String use = "USE compra;";
            String url = "jdbc:mysql://localhost:3306/";
            String sql = "CREATE TABLE IF NOT EXISTS compra (Cliente VARCHAR(30),Producto VARCHAR(30),Cantidad NUMERIC(4),Precio NUMERIC(4,2),ID NUMERIC(3), Fecha DATE);"; 
            Connection conn = DriverManager.getConnection(url,"root","root"); 
            Statement st = conn.createStatement(); 
            st.executeUpdate(bd);
            st.executeUpdate(use);
            st.executeUpdate(sql);
            conn.close(); 
        } catch (Exception e) { 
            System.err.println("OHH algo mal... [an exception]!"); 
            System.err.println(e.getMessage()); 
        } 
    }
} 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.*;

public class ArticulosAD{
	
    private Connection conexion;
    private Statement statement;
	private ArticulosDP articulosDP;
	private PrintWriter archivoSalida;
	private BufferedReader archivoEntrada;
	
	public ArticulosAD(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/AmazonTec","root","admin");
            
			System.out.println("Conexi—n exit—sa a la Base de Datos Banco, Driver JDBC Tipo 4");
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("Error con el Driver JDBC: " + cnfe);
		}
		catch(InstantiationException ie){
			System.out.println("Error al crear la instancia: " + ie);
		}
		catch(IllegalAccessException iae){
			System.out.println("Error. Acceso ilegal a la base de datos: " + iae);
		}
		catch(SQLException sqle){
			System.out.println("Error SQL: " + sqle);
		
		}
	}
	
	public String capturarArticulo(String datos){
		String insertArticulo = "";
        String respuesta = "";
        
        articulosDP = new ArticulosDP(datos);
        
        /* Crear String con instrucci—n SQL */
        insertArticulo = "INSERT INTO Producto VALUES(" + articulosDP.toSQLString() + ");";
        
        try {
            //1) Abrir la base de datos Banco
            statement = conexion.createStatement();
            
            //2) Capturar datos en la tabla correspondiente
            statement.executeUpdate(insertArticulo);
            
            //3) Cerrar la base de datos Banco
            statement.close();
            
            respuesta = "Datos: " + datos;
            System.out.println(conexion.nativeSQL(insertArticulo));
        }
        catch(SQLException sqle){
            	System.out.println("Error: " + sqle);
        }
        return respuesta;
	}
	
	public String consultarArticulos(){
        ResultSet result = null;
        String query = "";
        String respuesta = "";
        
        query = "SELECT * FROM Producto";
        
        articulosDP = new ArticulosDP();
        try{
            
            //1) Abrir la base de datos Banco
            statement = conexion.createStatement();
        
            //2) Procesar datos de la tabla resultante
            result = statement.executeQuery(query);
            
            while(result.next()){
                articulosDP.setClave(result.getString(1));
                articulosDP.setNombre(result.getString(2));
                articulosDP.setTipo(result.getString(3));
                articulosDP.setMarca(result.getString(4));
                articulosDP.setExistencia(result.getInt(5));
                articulosDP.setPrecio(result.getFloat(6));
                
                respuesta = respuesta + articulosDP.toString() + "\n";
            }
            
            //3) Cerra la base de datos banco
            statement.close();
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle){
            System.out.println("Error: \n" + sqle);
            respuesta = "No se pudo realizar la consulta";
        }
        
        return respuesta;
    }

	   public String consultarClave(String clave){
	        ResultSet result = null;
	        String query = "";
	        String respuesta = "";
	        
	        query = "SELECT * FROM Producto WHERE clave = '" + clave.toString() + "'";
	        
	        articulosDP = new ArticulosDP();
	        try{
	            
	            //1) Abrir la base de datos Banco
	            statement = conexion.createStatement();
	            
	            //2) Procesar datos de la tabla resultante
	            result = statement.executeQuery(query);
	            
	            if(result.next()){
	                articulosDP.setClave(result.getString(1));
	                articulosDP.setNombre(result.getString(2));
	                articulosDP.setTipo(result.getString(3));
	                articulosDP.setMarca(result.getString(4));
	                articulosDP.setExistencia(result.getInt(5));
	                articulosDP.setPrecio(result.getFloat(6));
	                
	                respuesta = respuesta + articulosDP.toString() + "\n";
	            }
	            
	            if(respuesta.equals(""))
	                respuesta = "CLAVE_NO_ENCONTRADA";
	            
	            //3) Cerra la base de datos banco
	            statement.close();
	            System.out.println(conexion.nativeSQL(query));
	        }
	        catch(SQLException sqle){
	            System.out.println("Error: \n" + sqle);
	            respuesta = "ERROR";
	        }
	        
	        return respuesta;
	    }
	   
	   public String consultarPor(String tipoConsulta, String str){
	        ResultSet result = null;
	        String query = "";
	        String respuesta = "";
	        
	        if (tipoConsulta.equals("TIPO"))
	        	query = "SELECT * FROM Producto WHERE tipo = '" + str.toString() + "'";
	        
	        if (tipoConsulta.equals("NOMBRE"))
	        	query = "SELECT * FROM Producto WHERE nombre = '" + str.toString() + "'";
	        
	        if (tipoConsulta.equals("PROVEEDOR"))
	        	query = "SELECT * FROM Producto WHERE proveedor = '" + str.toString() + "'";
	        
	        articulosDP = new ArticulosDP();
	        try{
	            
	            //1) Abrir la base de datos Banco
	            statement = conexion.createStatement();
	            
	            //2) Procesar datos de la tabla resultante
	            result = statement.executeQuery(query);
	            
	            while(result.next()){
	                articulosDP.setClave(result.getString(1));
	                articulosDP.setNombre(result.getString(2));
	                articulosDP.setTipo(result.getString(3));
	                articulosDP.setMarca(result.getString(4));
	                articulosDP.setExistencia(result.getInt(5));
	                articulosDP.setPrecio(result.getFloat(6));
	                
	                respuesta = respuesta + articulosDP.toString() + "\n";
	            }
	            
	            if(respuesta == ""){
		            if (tipoConsulta.equals("TIPO"))
		                respuesta = "No se encontr— ningœn art’culo de tipo: " + str;
		            if (tipoConsulta.equals("NOMBRE"))
		                respuesta = "No se encontr— ningœn art’culo con el nombre de: " + str;
		            if (tipoConsulta.equals("PROVEEDOR"))
		                respuesta = "No se encontr— ningœn art’culo del proveedor: " + str;
	            }
	            
	            //3) Cerra la base de datos banco
	            statement.close();
	            System.out.println(conexion.nativeSQL(query));
	        }
	        catch(SQLException sqle){
	            System.out.println("Error: \n" + sqle);
	            respuesta = "No se pudo realizar la consulta";
	        }
	        
	        return respuesta;
	    }
}
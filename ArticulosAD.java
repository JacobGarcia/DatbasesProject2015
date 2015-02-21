import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArticulosAD{
	
    private Connection conexion;
    private Statement statement;
	private ArticulosDP articulosDP;
	private VentasDP ventasDP;

	
	public ArticulosAD(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/AmazonTec?user=root");
            
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
            	respuesta = "CLAVE_DUPLICADA";
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
	   
	   private String consultarClave(String clave, String clause, String comparador){
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
	                articulosDP.setPrecio(result.getFloat(6));
	                
	                if(clause.equals(""))
	                	respuesta = respuesta + articulosDP.toStringVentas();
	                
	                if(clause.equals("PROVEEDOR")){
	                	if(comparador.equals(articulosDP.getMarca()))
	                		respuesta = respuesta + articulosDP.toStringVentas();
	                }
	                
	                if(clause.equals("TIPO")){
	                	if(comparador.equals(articulosDP.getTipo()))
	                		respuesta = respuesta + articulosDP.toStringVentas();
	                }
	            }
	            
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
		
	    public String venderArticulos(int cantidad, int existencia, String clave, String datos) {
	        String updateSQL = "";
	        String res = "";
	        String insertVenta = "";
	        existencia -= cantidad;
	        
	        ventasDP = new VentasDP(datos);
	        
	        try{
	            
	            //1) Abrir la base de datos Banco
	            statement = conexion.createStatement();
	            
	            updateSQL = "UPDATE Producto SET existencia = " + existencia + " WHERE clave = '" + clave.toString() + "'";
	            
	            //2) Ejecutar UPDATE Statement
	            statement.executeUpdate(updateSQL);
	            
	            res = "Venta exit—sa";
	            
	            //3) Crear sentencia INSERT
	            insertVenta = "INSERT INTO Ventas VALUES(" + ventasDP.toSQLString() + ");";
	            
	            //4) Capturar datos en la tabla correspondiente
	            statement.executeUpdate(insertVenta);
	            
	            //5) Cerra la base de datos banco
	            statement.close();
	            System.out.println(conexion.nativeSQL(updateSQL));
	            System.out.println(conexion.nativeSQL(insertVenta));

	        }
	        catch(SQLException sqle){
	            System.out.println("Error: \n" + sqle);
	            res = "ERROR";
	        }
	        
	        return res;
		}

		public String consultarVentas() {
	        ResultSet result = null;
	        String query = "";
	        String respuesta = "";
	        
	        query = "SELECT * FROM Ventas";
	        
	        ventasDP = new VentasDP();
	        articulosDP = new ArticulosDP();
	        try{
	            
	            //1) Abrir la base de datos Banco
	            statement = conexion.createStatement();
	            
	            //2) Procesar datos de la tabla Ventas
	            result = statement.executeQuery(query);
	            
	            while(result.next()){
	            	ventasDP.setClave(result.getString(1));
	            	respuesta = respuesta + consultarClave(result.getString(1), "", null);
	            	ventasDP.setCantidad(result.getInt(2));
	            	ventasDP.setVentaTotal(result.getFloat(3));
	            	ventasDP.setFecha(result.getString(4));
	            	ventasDP.setHora(result.getString(5));
	                
	                respuesta = respuesta + "_" + ventasDP.toString() + "\n";
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
		
		public String consultarVentasPor(String tipoConsulta, String string){
			 ResultSet result = null;
		     String query = "";
		     String respuesta = "", consulta = "";
		     
		     query = "SELECT * FROM Ventas";
		     ventasDP = new VentasDP();
		     try{
		            //1) Abrir la base de datos Banco
		            statement = conexion.createStatement();
		            
		            //2) Procesar datos de la tabla Ventas
		            result = statement.executeQuery(query);
		            
		            while(result.next()){
		            	ventasDP.setClave(result.getString(1));
		            	consulta = consultarClave(result.getString(1), tipoConsulta, string);
		            	if (!consulta.equals("")) {
		            		respuesta += consulta;
		            		ventasDP.setCantidad(result.getInt(2));
			            	ventasDP.setVentaTotal(result.getFloat(3));
			            	ventasDP.setFecha(result.getString(4));
			            	ventasDP.setHora(result.getString(5));
			              
			                respuesta = respuesta + "_" + ventasDP.toString() + "\n";
						}
		            }
		            
		            //3) Cerra la base de datos banco
		            statement.close();
		            
		            if (respuesta.equals("")){
		            	if (tipoConsulta.equals("PROVEEDOR")) {
							respuesta = "PROVEEDOR_NO_VENTA";
						}
		            	if (tipoConsulta.equals("TIPO")) {
							respuesta = "TIPO_NO_VENTA";
						}
		            }
		            
		            System.out.println(conexion.nativeSQL(query));
		        }
		        catch(SQLException sqle){
		            System.out.println("Error: \n" + sqle);
		            respuesta = "No se pudo realizar la consulta";
		        }
			return respuesta;
		}
	    
}
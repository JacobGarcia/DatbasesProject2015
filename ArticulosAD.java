import javax.swing.*;
import java.util.*;
import java.io.*;

public class ArticulosAD
{
	private ArticulosDP primero, ultimo, actual, anterior, primerVenta, actualVenta, ultimaVenta, anteriorVenta;
	private PrintWriter archivoSalida;
	private BufferedReader archivoEntrada;
	
	public ArticulosAD()
	{
		String datos = "";
		try
		{
			/******** Archivo de Artículos ***********/
			//1) Abrir Archivo
			archivoEntrada = new BufferedReader(new FileReader("Articulos.txt"));
			
			//2) Procesar datos del archivo
			while(archivoEntrada.ready())
			{
				datos = archivoEntrada.readLine();
				crearNodo(datos);
			}
			
			//3) Cerrar Archivo
			archivoEntrada.close();
			
			/******** Archivo de Ventas ***********/
			//1) Abrir Archivo
			archivoEntrada = new BufferedReader(new FileReader("Ventas.txt"));
			
			//2) Procesar datos del archivo
			while(archivoEntrada.ready())
			{
				datos = archivoEntrada.readLine();
				crearNodoVentas(datos);
			}
			
			//3) Cerrar Archivo
			archivoEntrada.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: " + ioe);
		}
	}
	
	private String consultarArchivo(String archivo)
	{
		String datos = "";
		try
		{
			//1) Abrir Archivo
			archivoEntrada = new BufferedReader(new FileReader(archivo));
			
			//2) Procesar datos del archivo
			while(archivoEntrada.ready())
			{
				datos = datos + archivoEntrada.readLine() + "\n";
			}
				
			//3) Cerrar Archivo
			archivoEntrada.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: " + ioe);
		}
		
		return datos;
	}

	public String crearNodo(String datos)
	{
		String respuesta = "Nuevo nodo creado: ";
		if(primero == null)
		{
			primero = new ArticulosDP(datos);
			ultimo = primero;
			ultimo.setNext(null);
			return respuesta + datos;
		}
		else
		{
			actual = new ArticulosDP(datos);
			ultimo.setNext(actual); //Link
			ultimo = actual;
			ultimo.setNext(null);
			return respuesta + datos;
		}
	}
	
	public String crearNodoVentas(String datos)
	{
		String respuesta = "Nuevo nodo creado: ";
		if(primerVenta == null)
		{
			primerVenta = new ArticulosDP(datos);
			ultimaVenta = primerVenta;
			ultimaVenta.setNext(null);
			return respuesta + datos;
		}
		else
		{
			actualVenta = new ArticulosDP(datos);
			ultimaVenta.setNext(actualVenta); //Link
			ultimaVenta = actualVenta;
			ultimaVenta.setNext(null);
			return respuesta + datos;
		}
	}
	
	public boolean vacia()
	{
		boolean vacia = false;
		
		if(primero == null)
			vacia = true;
		
		return vacia;
	}
	
	public String consultarNodos(String str)
	{
		String resultado = "";
		if(str.equals("VENTAS"))
			resultado = consultarNodosAD(primerVenta, actualVenta);
			
		if(str.equals("ARTICULOS"))
			resultado = consultarNodosAD(primero, actual);
			
		if(str.equals("ARTICULOS_ARCHIVO"))
			resultado = consultarArchivo("Articulos.txt");
			
		if(str.equals("VENTAS_ARCHIVO"))
			resultado = consultarArchivo("Ventas.txt");
			
		return resultado;
	}
	
	private String consultarNodosAD(ArticulosDP primero, ArticulosDP actual)
	{
		String datos = "";
		
		if(primero == null)
			datos = "LISTA_VACIA";
		else
		{
			actual = primero;
			
			while(actual != null)
			{
				datos = datos + actual.toString() + "\n";
				actual = actual.getNext();
			}
		}
		return datos;
	}

	public String consultarClave(String clave)
	{
		String datos = "";
		boolean encontrado = false;
		
		if(primero == null)
			datos = "LISTA_VACIA"; 
		else
		{
			actual = primero;
			while((actual != null)&&(encontrado == false))
			{
				String id = actual.getClave();
				if(clave.equals(id))
				{
					datos = actual.toString();
					encontrado = true;
				}
				else
				{
					anterior = actual;
					actual = actual.getNext();
				}
			}
			if(encontrado == false)
					datos = "CLAVE_NO_ENCONTRADA";
		}
		return datos;
	}
	
	public String consultarMarca(String marca)
	{
		String datos = "";
		boolean encontrado = false;
		
		if(primero == null)
			datos = "LISTA_VACIA";
		else
		{
			actual = primero;
			while(actual != null)
			{
				String brand = actual.getMarca();
				if(brand.equals(marca))
				{
					datos = datos + actual.toString() + "\n";
					encontrado = true;
				}
				anterior = actual;
				actual = actual.getNext();
			}
			if(encontrado == false)
					datos = "MARCA_NO_ENCONTRADA";
		}
		return datos;
	}
	
	public void crearNuevoNodoVentas(String datos, int venta)
	{
		String clave, nombre, marca, precio, datosVenta;
		StringTokenizer st = new StringTokenizer(datos, "_");
		clave = st.nextToken();
		nombre = st.nextToken();
		marca = st.nextToken();
		st.nextToken(); //Existencia
		precio = st.nextToken();
		
	    datosVenta = clave+"_"+nombre+"_"+marca+"_"+venta+"_"+precio;
	    
	    crearNodoVentas(datosVenta);
	}
	
	public String venderArticulos(int cantidad, int existencia)
	{
		int nuevaCantidad = 0;
		String nuevaExistencia = "", datos = "", datosVenta = "";
	
		nuevaCantidad = existencia - cantidad;
		nuevaExistencia = Integer.toString(nuevaCantidad);
		
		actual.setExistencia(nuevaExistencia);
		datos = actual.toString();
		
		crearNuevoNodoVentas(datos, cantidad);
		
		return datos;
	}
	
	public String datosListaArchivo(String str)
	{
		String respuesta = "";
		if(str.equals("ARTICULOS"))
			respuesta = datosListaArchivoAD("Articulos.txt", primero, actual);
			
		if(str.equals("VENTAS"))
			respuesta = datosListaArchivoAD("Ventas.txt", primerVenta, actualVenta);
			
		return respuesta;
	}
	
	public String datosListaArchivoAD(String str, ArticulosDP primero, ArticulosDP actual)
	{
		String resultado = "";
		
		if(primero == null)
			resultado = "LISTA_VACIA";
		else
		{
			try
			{
				//1) Abrir archivo
				archivoSalida = new PrintWriter(new FileWriter(str));
				
				//2) Procesar datos 
				actual = primero;
			
				while(actual != null)
				{
					archivoSalida.println(actual.toString());
					actual = actual.getNext();
				}
				
				//3) Cerrar Archivo
				archivoSalida.close();
			}
			catch(IOException ioe)
			{
				System.out.println("Error: " + ioe);
			}
		}
		return resultado;
	}
	
	public String borrarNodo(String clave)
	{ 
		if(actual == primero)
			primero = actual.getNext(); // Nodo siguiente a "Actual"; Segundo nodo
		else 
		{
			if(actual == ultimo)
			{
				ultimo = anterior; 
				ultimo.setNext(null); //Apuntar a un "null" en la siguiente dirección del último nodo.
			}
			else
				anterior.setNext(actual.getNext()); //Unir el nodo anterior con el siguiente del que se borra.
		}
		
		return "Nodo Borrado Exitósamente.";
	}
	
	public String modificarNodo(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		String clave, nombre, marca, existencia, precio, respuesta;
		
		clave   = st.nextToken();
		nombre  = st.nextToken();
		marca   = st.nextToken();
		existencia = st.nextToken();
		precio  = st.nextToken();
		
		//Nodo de Artículos
		actual.setNombre(nombre);
		actual.setMarca(marca);
		actual.setExistencia(existencia);			
		actual.setPrecio(precio);

		return datos;
	}
}
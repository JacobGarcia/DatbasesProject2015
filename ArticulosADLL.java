import javax.swing.*;
import java.util.*;
import java.io.*;

public class ArticulosADLL
{
	private ArticulosDP actual;
	private PrintWriter archivoSalida;
	private BufferedReader archivoEntrada;
	
	private LinkedList listaArticulos = new LinkedList();
	private LinkedList listaVentas   = new LinkedList();
	private int anterior = 0;
	
	public ArticulosADLL()
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
		listaArticulos.add(new ArticulosDP(datos));
		return respuesta + datos;
	}
	
	public String crearNodoVentas(String datos)
	{
		String respuesta = "Nuevo nodo creado: ";
		listaVentas.add(new ArticulosDP(datos));
		return respuesta + datos;
	}
	
	public boolean vacia()
	{
		boolean vacia = false;
		
		if(listaArticulos.size() == 0)
			vacia = true;
		
		return vacia;
	}
	
	public String consultarNodos(String str)
	{
		String resultado = "";
		if(str.equals("VENTAS"))
			resultado = consultarNodosAD(listaVentas);
			
		if(str.equals("ARTICULOS"))
			resultado = consultarNodosAD(listaArticulos);
			
		if(str.equals("ARTICULOS_ARCHIVO"))
			resultado = consultarArchivo("Articulos.txt");
			
		if(str.equals("VENTAS_ARCHIVO"))
			resultado = consultarArchivo("Ventas.txt");
			
		return resultado;
	}
	
	private String consultarNodosAD(LinkedList lista)
	{
		String datos = "";
		
		if(lista.size() == 0)
			datos = "LISTA_VACIA";
		else
		{
			for(int i = 0; i<lista.size(); i++)
				datos = datos + lista.get(i).toString() + "\n";
		}
		return datos;
	}

	public String consultarClave(String clave)
	{
		String datos = "", id = "";
		boolean encontrado = false;
		int i = 0;
		
		if(listaArticulos.size() == 0)
			datos = "LISTA_VACIA";
		else
		{
			while((i<listaArticulos.size())&&(encontrado == false))
			{
				actual = (ArticulosDP)listaArticulos.get(i);
				id = actual.getClave();
				
				if(id.equals(clave))
				{
					datos = actual.toString();
					encontrado = true;
					anterior = i;
				}	
				i++;
			}
			if(encontrado == false)
					datos = "CLAVE_NO_ENCONTRADA";
		}
		return datos;
	}
	
	public String consultarMarca(String marca)
	{
		String datos = "", brand = "";
		boolean encontrado = false;
		int i = 0;
		
		if(listaArticulos.size() == 0)
			datos = "LISTA_VACIA";
		else
		{
			while((i<listaArticulos.size()))
			{
				actual = (ArticulosDP)listaArticulos.get(i);
				brand = actual.getMarca();
				
				if(brand.equals(marca))
				{
					datos = datos + actual.toString() + "\n";
					encontrado = true;
					anterior = i;
				}	
				i++;
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
		String nuevaExistencia = "", datos = "";
	
		nuevaCantidad = existencia - cantidad;
		nuevaExistencia = Integer.toString(nuevaCantidad);
		
		actual = (ArticulosDP)listaArticulos.get(anterior);
		
		actual.setExistencia(nuevaExistencia);
		datos = actual.toString();
		
		crearNuevoNodoVentas(datos, cantidad);
		
		return datos;
	}
	
	public String datosListaArchivo(String str)
	{
		String respuesta = "";
		if(str.equals("ARTICULOS"))
			respuesta = datosListaArchivoAD("Articulos.txt", listaArticulos);
			
		if(str.equals("VENTAS"))
			respuesta = datosListaArchivoAD("Ventas.txt", listaVentas);
			
		return respuesta;
	}
	
	public String datosListaArchivoAD(String archivo, LinkedList lista)
	{
		String datos = "";
		
		if(lista.size() == 0)
			datos = "LISTA_VACIA";
		else
		{
			try
			{
				//1) Abrir archivo
				archivoSalida = new PrintWriter(new FileWriter(archivo));
				
				for(int i = 0; i<lista.size(); i++)
					archivoSalida.println(lista.get(i).toString());
				
				//3) Cerrar Archivo
				archivoSalida.close();
			}
			catch(IOException ioe)
			{
				System.out.println("Error: " + ioe);
			}
		}
		return datos;
	}
	
	public String borrarNodo(String clave)
	{ 
		listaArticulos.remove(anterior);
		
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
		
		actual = (ArticulosDP)listaArticulos.get(anterior);
		
		//Nodo de Artículos
		actual.setNombre(nombre);
		actual.setMarca(marca);
		actual.setExistencia(existencia);			
		actual.setPrecio(precio);

		return datos;
	}
}
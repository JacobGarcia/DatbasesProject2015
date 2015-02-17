import java.util.*;

public class ArticulosDP
{
	private String clave;
	private String nombre;
	private String existencia;
	private String marca;
	private String precio;

	private ArticulosDP next;
	
	//Constructors
	public ArticulosDP()
	{
		this.clave      = "";
		this.nombre     = "";
		this.marca		= "";
		this.existencia = "";
		this.precio		= "";
	}
	
	//String Tokenizer
	public ArticulosDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		
			this.clave 		= st.nextToken();
			this.nombre 	= st.nextToken();
			this.marca		= st.nextToken();
			this.existencia = st.nextToken();
			this.precio 	= st.nextToken();
	}
	
	//Accessors (Getters)
	public String getClave()
	{
		return this.clave;
	}

	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getExistencia()
	{
		return this.existencia;
	}

	public String getMarca()
	{
		return this.marca;
	}

	public String getPrecio()
	{
		return this.precio;
	}
	
		
	public ArticulosDP getNext()
	{
		return this.next;
	}
	
	//Mutators(Setters)
	public void setClave(String clave)
	{
		this.clave = clave;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public void setExistencia(String existencia)
	{
		this.existencia = existencia;
	}

	public void setMarca(String existencia)
	{
		this.marca = marca;
	}

	public void setPrecio(String precio)
	{
		this.precio = precio;
	}
	
	public void setNext(ArticulosDP dir)
	{
		this.next = dir;
	}
	
	//Final String
	public String toString()
	{
		return this.clave+"_"+this.nombre+"_"+this.marca+"_"+this.existencia+"_"+this.precio;
	}
}
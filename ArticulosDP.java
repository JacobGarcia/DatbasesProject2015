import java.util.*;

public class ArticulosDP
{
	private String clave;
	private String nombre;
	private int existencia;
	private String marca;
	private float precio;
	private String tipo;

	private ArticulosDP next;
	
	//Constructors
	public ArticulosDP()
	{
		this.clave      = "";
		this.nombre     = "";
		this.marca		= "";
		this.existencia = 0;
		this.precio		= (float) 0.0;
		this.tipo		= "";
	}
	
	//String Tokenizer
	public ArticulosDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		
			this.clave 		= st.nextToken();
			this.nombre 	= st.nextToken();
			this.tipo		= st.nextToken();
			this.marca		= st.nextToken();
			this.existencia = Integer.parseInt(st.nextToken());
			this.precio 	= Float.parseFloat(st.nextToken());
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
	
	public int getExistencia()
	{
		return this.existencia;
	}

	public String getMarca()
	{
		return this.marca;
	}

	public float getPrecio()
	{
		return this.precio;
	}
	
	public String getTipo()
	{
		return this.tipo;
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
	
	public void setExistencia(int existencia)
	{
		this.existencia = existencia;
	}

	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	public void setPrecio(float precio)
	{
		this.precio = precio;
	}
	
	public void setNext(ArticulosDP dir)
	{
		this.next = dir;
	}
	
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
	
	//Final String
	public String toString()
	{
		return this.clave+"_"+this.nombre+"_"+this.tipo+"_"+this.marca+"_"+this.existencia+"_"+this.precio;
	}
	
	public String toStringVentas()
	{
		return this.clave+"_"+this.nombre+"_"+this.tipo+"_"+this.marca+"_"+this.precio;
	}
	
    public String toSQLString(){
        return "'" + this.clave + "','" + this.nombre+"','"+this.tipo+"','"+this.marca+"'," + this.existencia + "," + this.precio;
    }
}
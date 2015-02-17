import java.util.*;

public class VentasDP
{
	private String clave;
	private int cantidad;
	private float ventaTotal;
	private String fecha;
	private String hora;
	
	//Constructors
	public VentasDP()
	{
		this.clave = "";
		this.cantidad = 0;
		this.ventaTotal = (float) 0.0;
		this.fecha = "";
		this.hora = "";
	}
	
	//String Tokenizer
	public VentasDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		
			this.clave 		= st.nextToken();
			this.cantidad 	= Integer.parseInt(st.nextToken());
			this.ventaTotal	= Float.parseFloat(st.nextToken());
			this.fecha		= st.nextToken();
			this.hora =		st.nextToken();
	}
	
	//Accessors (Getters)
	public String getClave()
	{
		return this.clave;
	}

	public int getCantidad()
	{
		return this.cantidad;
	}
	
	public float getVentaTotal()
	{
		return this.ventaTotal;
	}

	public String getFecha()
	{
		return this.fecha;
	}

	public String getHora()
	{
		return this.hora;
	}
	
		
	//Mutators(Setters)
	public void setClave(String clave)
	{
		this.clave = clave;
	}

	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}
	
	public void setVentaTotal(float ventaTotal)
	{
		this.ventaTotal = ventaTotal;
	}

	public void setFecha(String fecha)
	{
		this.fecha = fecha;
	}

	public void setHora(String hora)
	{
		this.hora = hora;
	}
	
    public String toSQLString(){
        return "'" + this.clave + "'," + this.cantidad+","+this.ventaTotal+",'"+this.fecha+"','" + this.hora + "'";
    }
}
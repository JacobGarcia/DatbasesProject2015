/*
 * ArticulosIUG.java
 *
 *  Created on: 13/02/2015
 *		Project #1
 *      Authors: Mario Jacob Garc’a Navarro & Luis Arturo Mendoza Reyes. All Rights Reserved 2015.
 *		IN THIS PROJECT A "STORE SYSTEM" WLL BE SIMULATED.
 *		WE WILL BE CREATING A DATA BASE & ADDING ELEMENTS TO IT. OTHER TASKS WILL BE DONE THROUGH.
 *		IT IS MAIN PURPOSE IS USING SQL METHODS IN ORDER TO UNDERSTAND HOW DBMS WORK.
 */
 
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
public class ArticulosIUG extends Frame implements ActionListener
{
	private JTextField tfClave,tfNombre, tfExistencia, tfMarca, tfPrecio, tfTipo;
	private JButton    bCapturar, bConsultar, bConsutlarTipo, bConsultarClave,
					   bVender, bRealizarTransaccion, bCancelar,
					   bConsultarVentas, bConsultarVentasProveedor, bConsultarVentasTipo, bSalir,
					   bConsultarNombre, bConsultarProveedor;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private ArticulosAD articulos = new ArticulosAD(); 
	
	public ArticulosIUG(){
		super("Proyecto Centro Comercial");
		
		//Inicializar los atributos
		tfClave 	   	= new JTextField();
		tfNombre   		= new JTextField();
		tfExistencia 	= new JTextField();
		tfPrecio		= new JTextField();
		tfMarca			= new JTextField();
		tfTipo			= new JTextField();
		taDatos    		= new JTextArea(13, 42);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(13, 2));
		
		p1.add(new Label("Clave"));
		p1.add(tfClave);

		p1.add(new Label("Nombre"));
		p1.add(tfNombre);
		
		p1.add(new Label("Tipo"));
		p1.add(tfTipo);
		
		p1.add(new Label("Proveedor")); 
		p1.add(tfMarca);
		
		p1.add(new Label("Unidades en Existencia")); 
		p1.add(tfExistencia);

		p1.add(new Label("Precio (Unitario)"));
		p1.add(tfPrecio);
		
		bCapturar = new JButton("Crear Art’culo");
		bCapturar.addActionListener(this);
		p1.add(bCapturar);
		
		bConsultar = new JButton("Consultar Art’culos");
		bConsultar.addActionListener(this);
		p1.add(bConsultar);
		
		bConsultarClave = new JButton("Consultar Art’culos por Clave");
		bConsultarClave.addActionListener(this);
		p1.add(bConsultarClave);

		bConsutlarTipo = new JButton("Consultar Art’culos por Tipo");
		bConsutlarTipo.addActionListener(this);
		p1.add(bConsutlarTipo);
		
		bConsultarNombre = new JButton("Consultar Art’culos por Nombre");
		bConsultarNombre.addActionListener(this);
		p1.add(bConsultarNombre);
		
		bConsultarProveedor = new JButton("Consultar Art’culos por Proveedor");
		bConsultarProveedor.addActionListener(this);
		p1.add(bConsultarProveedor);
		
		bVender = new JButton("Vender Art’culos");
		bVender.addActionListener(this);
		p1.add(bVender);
		
		bConsultarVentas = new JButton("Consultar Ventas");
		bConsultarVentas.addActionListener(this);
		p1.add(bConsultarVentas);
		
		bConsultarVentasProveedor = new JButton("Consultar Ventas por Proveedor");
		bConsultarVentasProveedor.addActionListener(this);
		p1.add(bConsultarVentasProveedor);
				
		bConsultarVentasTipo = new JButton("Consultar Ventas por Tipo");
		bConsultarVentasTipo.addActionListener(this);
		p1.add(bConsultarVentasTipo);
		
		bRealizarTransaccion = new JButton("Realizar Transacci—n");
		bRealizarTransaccion.addActionListener(this);
		p1.add(bRealizarTransaccion);
		
		bCancelar = new JButton("Cancelar Transacci—n");
		bCancelar.addActionListener(this);
		p1.add(bCancelar);
		
		bSalir = new JButton("Salir");
		bSalir.addActionListener(this);
		p1.add(bSalir);
				
		p2.setLayout(new FlowLayout());
		
		p2.add(p1);
		p2.add(new JScrollPane(taDatos));
		
		add(p2);
		setSize(550,650);
		setVisible(true);
		
		//Deshabilitar botones pertenecientes a "Ventas"
		bRealizarTransaccion.setEnabled(false);
		bCancelar.setEnabled(false);
	}

	public void clearFields(){
		tfClave.setText("");
		tfNombre.setText("");
		tfExistencia.setText("");
		tfMarca.setText("");
		tfPrecio.setText("");
		tfTipo.setText("");
	}
	
	public void habilitarBotones(boolean value){
		bCapturar.setEnabled(value); 
		bConsultar.setEnabled(value);
		bConsultarClave.setEnabled(value);
		bConsutlarTipo.setEnabled(value);
		bVender.setEnabled(value);
		bConsultarVentas.setEnabled(value);
		bConsultarVentasProveedor.setEnabled(value);
		bConsultarVentasTipo.setEnabled(value);
		bConsultarNombre.setEnabled(value);
		bConsultarProveedor.setEnabled(value);
		
		bRealizarTransaccion.setEnabled(!value);
		bCancelar.setEnabled(!value);
		
		tfClave.setEnabled(value);
		tfMarca.setEnabled(value);
		tfNombre.setEnabled(value);
		tfExistencia.setEnabled(value);
		tfPrecio.setEnabled(value);
		tfTipo.setEnabled(value);
	}
	
	private void mostrar(String str){
		StringTokenizer st = new StringTokenizer(str, "_");
					
		String clave = st.nextToken();
		String nombre = st.nextToken();
		String tipo	= st.nextToken();
		String marca = st.nextToken();
		String existencia = st.nextToken();
		String precio = st.nextToken();
				
		tfClave.setText(clave);
		tfNombre.setText(nombre);
		tfMarca.setText(marca);
		tfExistencia.setText(existencia);
		tfPrecio.setText(precio);
		tfTipo.setText(tipo);
	}
		
	public boolean notTokenizer(String str){
		Character array[] = new Character[str.length()];
		int i = 0;
		boolean token = false;
		
		//1) Transformar el String en un arreglo de caracteres
	    for(i = 0; i < str.length(); i++) 
      		array[i] = new Character(str.charAt(i));
      		
      	//2) Verificar que no existan tokens en el string, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
      	i = 0; // Reinicializar contador
      	while((i < str.length())&&(token == false))
      	{
      		if(array[i] == '_')
      			token = true;
      		i++;
      	}
      	
      	return token;
	}
	
	private String obtenerDatos(){
		boolean token = false;
		int inExistencia;
		float flPrecio;
		
		String clave      = tfClave.getText();
		String nombre     = tfNombre.getText();
        String existencia = tfExistencia.getText();
        String marca 	   = tfMarca.getText();
        String precio 	   = tfPrecio.getText();
        String tipo	   = tfTipo.getText();
        String datos = "";
		
		if(marca.equals("") || precio.equals("") || clave.equals("")||nombre.equals("")||existencia.equals("") || tipo.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
        	try
        	{
        		// Comprobar que los campos de existencia y precio sean numŽricos
        		inExistencia = Integer.parseInt(existencia);
        		flPrecio 	 = Float.parseFloat(precio);
        		
        		// Comprobar que los campos de existencia y precio no sean negativos
        		if((inExistencia<0)||(flPrecio<=0))
        			datos = "NEGATIVO";	
        		else
        		{
        			// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
				     token = notTokenizer(clave); //Clave
				     if(token == false)
				     {
				     	token = notTokenizer(nombre); //Nombre
				     	if(token == false)
					    {
					     	token = notTokenizer(marca); //Marca
					     	if (token == false) {
								token = notTokenizer(tipo); // Tipo
							}
					    }
		        	 }
		         	 if(token == false)
		        		 datos = clave+"_"+nombre+"_"+tipo+"_"+marca+"_"+existencia+"_"+precio;
		       	     else
		        		 datos = "TOKEN";
	        	}
        	}
        	catch(NumberFormatException nfe)
        	{
        		System.out.println("Error: " + nfe);
        		datos = "NO_NUMERICO";
        	}
        }
    
        return datos;
	}
	
	private void realizarVenta()
	{
		boolean cantidadCorrecta = false;
		String str = "", strError = "Debes introducir una cantidad v‡lida. Recuerda : \n 1) S—lo debes introducir nœmeros enteros; positivos \n 2) Debes dejar a lo m‡s cero art’culos en 'stock'" ;
		String strExistencia = tfExistencia.getText();
		int cantidad = 0;
		int existencia = Integer.parseInt(strExistencia);
			
		while(cantidadCorrecta == false)
		{
			str = JOptionPane.showInputDialog(null, "Cantidad a Vender: ");
			try
			{
				cantidad = Integer.parseInt(str);
				if((cantidad < 0)||(cantidad > existencia))
					JOptionPane.showMessageDialog(null, strError);
					else
					{
						cantidadCorrecta = true;
						String clave = tfClave.getText();
						String precio = tfPrecio.getText();
						float costo = Float.parseFloat(precio);
						
						Date date;
						SimpleDateFormat formatoFecha, formatoHora;
						
						date = new Date();
						
						/* MySQL Date format */
					    formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
						formatoHora  = new SimpleDateFormat("hh:mm:ss");
						
						String fecha = formatoFecha.format(date);
						String hora  = formatoHora.format(date);
						
						String datos = clave+"_"+cantidad+"_"+ (costo * cantidad) +"_" + fecha + "_" + hora;
						
						String resultado = articulos.venderArticulos(cantidad, existencia, clave, datos);
						habilitarBotones(true);
						print(resultado);
						clearFields();
					}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Error: " + nfe);
				JOptionPane.showMessageDialog(null, strError);
			}
		}
	}

	private String consultar(String elemento){
		String resultado = "";
		
		if (elemento.equals("TIPO")){
			String tipo = tfTipo.getText();
			
			if(tipo.equals(""))
					resultado = "TIPO_VACIO";
			else
				resultado = articulos.consultarPor("TIPO",tipo);
		}
		
		if (elemento.equals("NOMBRE")){
			String nombre = tfNombre.getText();
			
			if(nombre.equals(""))
					resultado = "NOMBRE_VACIO";
			else
				resultado = articulos.consultarPor("NOMBRE",nombre);
		}
		
		if (elemento.equals("PROVEEDOR")){
			String marca = tfMarca.getText();
			
			if(marca.equals(""))
					resultado = "PROVEEDOR_VACIO";
			else
				resultado = articulos.consultarPor("PROVEEDOR",marca);
		}
					
		if(elemento.equals("CLAVE")){
			String clave = tfClave.getText();

			if(clave.equals(""))
					resultado = "CLAVE_VACIA";
			else
				resultado = articulos.consultarClave(clave);
		}

		return resultado;
	}
	
	private String consultarVentas(String elemento){
		String resultado = "";
		if (elemento.equals("TIPO")){
			String tipo = tfTipo.getText();
			
			if(tipo.equals(""))
					resultado = "TIPO_VACIO";
			else
				resultado = articulos.consultarVentasPor("TIPO",tipo);
		}
		
		if (elemento.equals("PROVEEDOR")){
			String marca = tfMarca.getText();
			
			if(marca.equals(""))
					resultado = "PROVEEDOR_VACIO";
			else
				resultado = articulos.consultarVentasPor("PROVEEDOR",marca);
		}
					
		if(elemento.equals("CLAVE")){
			String clave = tfClave.getText();

			if(clave.equals(""))
					resultado = "CLAVE_VACIA";
			else
				resultado = articulos.consultarClave(clave);
		}

		return resultado;
	}
	private void print(String str){
		
		taDatos.setText("Message Log\n---------------------------------------------------------------\n");
		if(str.equals("NOMBRE_VACIO")||(str.equals("TIPO_VACIO"))||(str.equals("CLAVE_NO_ENCONTRADA"))||(str.equals("CAMPO_VACIO"))||(str.equals("PROVEEDOR_VACIO"))||(str.equals("TOKEN"))||(str.equals("NO_NUMERICO"))||(str.equals("NEGATIVO"))||(str.equals("CLAVE_VACIA"))||(str.equals("PROVEEDOR_NO_ENCONTRADO")||(str.equals("NO_VENTA"))||(str.equals("PROVEEDOR_NO_VENTA")) || (str.equals("TIPO_NO_VENTA")) || (str.equals("CLAVE_DUPLICADA"))))
		{
			if(str.equals("NOMBRE_VACIO"))
				taDatos.setText("El campo 'Nombre' se encuentra vac’o.");
				
			if(str.equals("TIPO_VACIO"))
				taDatos.setText("El campo 'Tipo' se encuentra vac’o.");
				
			if(str.equals("CLAVE_NO_ENCONTRADA"))
				taDatos.setText("La Clave '" + tfClave.getText() + "' no se encontr— en la base de datos.");
				
			if(str.equals("PROVEEDOR_NO_ENCONTRADO"))
				taDatos.setText("El Proveedor '" + tfMarca.getText() + "' no se encontr— en los base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("PROVEEDOR_VACIO"))
				taDatos.setText("El campo 'Proveedor' se encuentra vac’o.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");
			
			if(str.equals("NO_NUMERICO"))
				taDatos.setText("Los campos 'Existencia' y 'Precio' deben contener valores numŽricos.\nEl campo de 'Existencia' debe ser entero.");
				
			if(str.equals("NEGATIVO"))
				taDatos.setText("Los campos 'Existencia' y 'Precio' deben contener valores positivos.\nLa 'Existencia' puede ser 0, el 'Precio' en cambio, no.");
			
			if(str.equals("CLAVE_VACIA"))
				taDatos.setText("El campo 'Clave' se encuentra vac’o.");
			
			if(str.equals("CLAVE_DUPLICADA"))
				taDatos.setText("La clave '" + tfClave.getText() + "' ya se encuentra en la base de datos. Por favor introduce otra clave");
				
			if(str.equals("NO_VENTA"))
				taDatos.setText("No se pueden vender art’culos con la cantidad de '0'");
			
			if(str.equals("PROVEEDOR_NO_VENTA"))
				taDatos.setText("No se tienen ventas registradas para el proveedor '" + tfMarca.getText() + "'.");
			
			if(str.equals("TIPO_NO_VENTA"))
				taDatos.setText("No se tienen ventas registradas para el tipo '" + tfTipo.getText() + "'.");
				
		}
		else
			taDatos.setText(str);
			
	}
	
	
	/********************************************************************  ACTION PERFORMED  ***************************************************************************************************/  
		/*******************************************************************************************************************************************************************************/ 
			
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bCapturar)
		{
			//1) Obtener datos de los TextFields
            String datos = obtenerDatos();
            String resultado = "";
            
            //2) Comprobar que ninguno de los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado
			if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN")||datos.equals("NO_NUMERICO")||datos.equals("NEGATIVO"))
				print(datos);
			else
			{

				//3) Enviar los datos a la clase AD a travŽs del metodo capturarArticulo()
			    resultado = articulos.capturarArticulo(datos);
	
			    //4) Desplegar el resultado de la operaci—n
			    print(resultado);
			    
			    if(!resultado.equals("CLAVE_DUPLICADA"))
			    	//5) Quitar la informaci—nn de los TextFields
			    	clearFields();	
			}
		}
		
		if (e.getSource() == bConsultar){	
			String resultado = articulos.consultarArticulos();
			print(resultado);
		}

		if (e.getSource() == bConsultarClave){
			String resultado = consultar("CLAVE");
			if(resultado.equals("CLAVE_VACIA")||(resultado.equals("ERROR"))||(resultado.equals("CLAVE_NO_ENCONTRADA")))
				print(resultado);
			else{
				//Colocar los datos en los TextFields
				mostrar(resultado);	
				print(resultado);
			}	
		}
		
		if (e.getSource() == bConsutlarTipo){	
			String resultado = consultar("TIPO");
			print(resultado);
		}
		
		if (e.getSource() == bConsultarNombre){	
			String resultado = consultar("NOMBRE");
			print(resultado);
		}
		
		if (e.getSource() == bConsultarProveedor){	
			String resultado = consultar("PROVEEDOR");
			print(resultado);
		}
		
		if(e.getSource() == bVender){
			//1) Hacer una consulta de los datos para comprobar que exista la "Clave" o "Registro"
			String resultado = consultar("CLAVE");

			//2) Hacer las validaciones correspondientes
			if((resultado.equals("CLAVE_NO_ENCONTRADA"))||(resultado.equals("CLAVE_VACIA")))
				print(resultado);
			else{	
				mostrar(resultado);
				String strExistencia = tfExistencia.getText();
				int existencia = Integer.parseInt(strExistencia);
				
				if(existencia > 0){
					habilitarBotones(false);
					String datos = obtenerDatos();
					print(datos);
				}
				else
					print("NO_VENTA");
			}
		}
		
		if(e.getSource() == bRealizarTransaccion)
				realizarVenta();	
		
		if(e.getSource() == bCancelar){
			habilitarBotones(true);
			clearFields();
		}
				
		if (e.getSource() == bSalir)
			System.exit(0);
		
		if(e.getSource() == bConsultarVentas){
			String resultado = articulos.consultarVentas();
			print(resultado);
		}
		
		if(e.getSource() == bConsultarVentasProveedor){
			String resultado = consultarVentas("PROVEEDOR");
			print(resultado);
		}
		
				
		if(e.getSource() == bConsultarVentasTipo){
			String resultado = consultarVentas("TIPO");
			print(resultado);
		}
		
	}
	
	public static void main(String args[]){
		new ArticulosIUG();
	}
}

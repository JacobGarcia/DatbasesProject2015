/*
 * ArticulosIUG.java
 *
 *  Created on: 16/03/2014
 *		Project #2
 *      Authors: Mario Jacob Garc’a Navarro & Luis Arturo Mendoza Reyes. All Rights Reserved 2014.
 *		IN THIS PROJECT A "STORE SYSTEM" WLL BE SIMULATED.
 *		WE WILL BE CREATING A LINKED LIST & ADDING AND DELETING ELEMENTS FROM IT. OTHER TASKS WILL BE DONE THROUGH.
 *		IT IS MAIN PURPOSE IS EVADE USING METHODS CONTAINED IN THE CLASS "LINKEDLIST" IN ORDER TO UNDERSTAND HOW POINTERS AND OTHER ELEMENTS
 *		ARE WORKING IN RAM. CREATING SUCH ALGORITHMS WILL WORK THROUGH OTHER LANGUAGES LIKE C OR C++.
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
					   bVender, bRealizarTransaccion, bCancelar, bBorrar,
					   bConsultarVentas, bConsultarArchivo, bConsultarArchivoVentas, bSalir,
					   bModificar, bConsultarNombre, bConsultarProveedor;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private String clave, datos, nombre, existencia, precio, marca, resultado, controlador, tipo;
	
	private ArticulosAD articulos = new ArticulosAD(); 
	
	private String fecha, hora;
	
	private Date date;
	private SimpleDateFormat formatoFecha, formatoHora;
	
	public ArticulosIUG()
	{
		super("Proyecto Centro Comercial");
		
		//Inicializar los atributos
		tfClave 	   	= new JTextField();
		tfNombre   		= new JTextField();
		tfExistencia 	= new JTextField();
		tfPrecio		= new JTextField();
		tfMarca			= new JTextField();
		tfTipo			= new JTextField();
		taDatos    		= new JTextArea(13, 40);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(15, 2));
		
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
		
		bModificar = new JButton("Modificar Art’culos");
		bModificar.addActionListener(this);
		p1.add(bModificar);
		
		bBorrar = new JButton("Dar de Baja Art’culo");
		bBorrar.addActionListener(this);
		p1.add(bBorrar);
		
		bConsultarVentas = new JButton("Consultar Ventas");
		bConsultarVentas.addActionListener(this);
		p1.add(bConsultarVentas);
		
		bConsultarArchivo = new JButton("Consultar Archivo de Art’culos");
		bConsultarArchivo.addActionListener(this);
		p1.add(bConsultarArchivo);
				
		bConsultarArchivoVentas = new JButton("Consultar Archivo de Ventas");
		bConsultarArchivoVentas.addActionListener(this);
		p1.add(bConsultarArchivoVentas);
		
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
		setSize(600,700);
		setVisible(true);
		
		//Deshabilitar botones pertenecientes a "Modificar Datos"
		bRealizarTransaccion.setEnabled(false);
		bCancelar.setEnabled(false);
	}

	public JPanel getP2()
	{
		return this.p2;
	}
	
	public void clrFields()
	{
		tfClave.setText("");
		tfNombre.setText("");
		tfExistencia.setText("");
		tfMarca.setText("");
		tfPrecio.setText("");
		tfTipo.setText("");
	}
	
	public void habilitarBotones(boolean value)
	{
		bCapturar.setEnabled(value); 
		bConsultar.setEnabled(value);
		bConsultarClave.setEnabled(value);
		bConsutlarTipo.setEnabled(value);
		bVender.setEnabled(value);
		bModificar.setEnabled(value);
		bBorrar.setEnabled(value);
		bConsultarVentas.setEnabled(value);
		bConsultarArchivo.setEnabled(value);
		bConsultarArchivoVentas.setEnabled(value);
		bConsultarNombre.setEnabled(value);
		bConsultarProveedor.setEnabled(value);
		
		bRealizarTransaccion.setEnabled(!value);
		bCancelar.setEnabled(!value);
		
		tfClave.setEnabled(true);
	}
	
	public void habilitarCampos (boolean value)
	{
		tfClave.setEnabled(value);
		tfMarca.setEnabled(value);
		tfNombre.setEnabled(value);
		tfExistencia.setEnabled(value);
		tfPrecio.setEnabled(value);
		tfTipo.setEnabled(value);
	}
	
	private void mostrar(String str)
	{
		StringTokenizer st = new StringTokenizer(str, "_");
					
		clave = st.nextToken();
		nombre = st.nextToken();
		tipo	= st.nextToken();
		marca = st.nextToken();
		existencia = st.nextToken();
		precio = st.nextToken();
				
		tfClave.setText(clave);
		tfNombre.setText(nombre);
		tfMarca.setText(marca);
		tfExistencia.setText(existencia);
		tfPrecio.setText(precio);
		tfTipo.setText(tipo);
	}
		
	public boolean notTokenizer(String str)
	{
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
	
	private String obtenerDatos()
	{
		boolean token = false;
		int inExistencia;
		float flPrecio;
		
		clave      = tfClave.getText();
		nombre     = tfNombre.getText();
        existencia = tfExistencia.getText();
        marca 	   = tfMarca.getText();
        precio 	   = tfPrecio.getText();
        tipo	   = tfTipo.getText();
		
		if(marca.equals("") || precio.equals("") || clave.equals("")||nombre.equals("")||existencia.equals("") || tipo.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
        	try
        	{
        		// Comprobar que los campos de existencia y precio sean numéricos
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
								token = notTokenizer(tipo);
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
			str = JOptionPane.showInputDialog(null, "Cantidad a Vender = ");
			try
			{
				cantidad = Integer.parseInt(str);
				if((cantidad < 0)||(cantidad > existencia))
					JOptionPane.showMessageDialog(null, strError);
					else
					{
						cantidadCorrecta = true;
						clave = tfClave.getText();
						precio = tfPrecio.getText();
						float costo = Float.parseFloat(precio);
						date = new Date();
						
						/* MySQL Date format */
					    formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
						formatoHora  = new SimpleDateFormat("hh:mm:ss");
						
						/* Adicionar la fecha al textfield */
						fecha = formatoFecha.format(date);
						hora  = formatoHora.format(date);
						
						datos = clave+"_"+cantidad+"_"+ (costo * cantidad) +"_" + fecha + "_" + hora;
						
						resultado = articulos.venderArticulos(cantidad, existencia, clave, datos);
						habilitarCampos(true);
						habilitarBotones(true);
						print(resultado);
						clrFields();
					}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Error: " + nfe);
				JOptionPane.showMessageDialog(null, strError);
			}
		}
	}
	
	private void borrarNodo()
	{			
		/*Llamar al método que borra a los nodos*/
		//resultado = articulos.borrarNodo(clave);
		print(resultado);
		
		/*Regresar los botones y los campos a su estado original*/
		 habilitarBotones(true);
		 habilitarCampos(true);
				
		/*Quitar la informaci—n de los TextFields*/
		clrFields();
	}
	
	private void modificarNodo()
	{
		//1) Obtener datos de los TextFields
		datos = obtenerDatos();
			
		//Comprobar que ninguno de los campos esté vac’o, y en caso de que lo anterior no se cumpla, evitar enviar los datos en ese estado a los nodos
		if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN")||datos.equals("NO_NUMERICO")||datos.equals("NEGATIVO"))
			print(datos);
		else
		{
			//resultado = articulos.modificarNodo(datos);
				
			//Mostrarle los cambios al usuario 
			print("Cambios Realizados: " + resultado);
					
			//Devolver los botones y los campos a su estado original
			habilitarBotones(true);
			habilitarCampos(true);
					
			//Quitar la informaci—n de los TextFields
			clrFields();
		}
	}

	private String consultar(String elemento)	// Se busca si existe y manda el resultado (Nombre o Clave)	
	{
		
		if (elemento.equals("TIPO"))
		{
			tipo = tfTipo.getText();
			
			if(tipo.equals(""))
					resultado = "TIPO_VACIO";
				
			else
				resultado = articulos.consultarPor("TIPO",tipo);
		}
		
		if (elemento.equals("NOMBRE"))
		{
			nombre = tfNombre.getText();
			
			if(nombre.equals(""))
					resultado = "NOMBRE_VACIO";
				
			else
				resultado = articulos.consultarPor("NOMBRE",nombre);
		}
		
		if (elemento.equals("PROVEEDOR"))
		{
			marca = tfMarca.getText();
			
			if(marca.equals(""))
					resultado = "PROVEEDOR_VACIO";
				
			else
				resultado = articulos.consultarPor("PROVEEDOR",marca);
		}
					
		if(elemento.equals("CLAVE"))
		{
			clave = tfClave.getText();

			if(clave.equals(""))
					resultado = "CLAVE_VACIA";
				
			else
				resultado = articulos.consultarClave(clave);
		}
		if(elemento.equals("VENTAS"))
		{
			resultado = articulos.consultarVenta("VENTAS","");
		}

		return resultado;
	}
	
	private void print(String str)
	{
		if(str.equals("NOMBRE_VACIO")||(str.equals("TIPO_VACIO"))||(str.equals("CLAVE_NO_ENCONTRADA"))||(str.equals("CAMPO_VACIO"))||(str.equals("PROVEEDOR_VACIO"))||(str.equals("TOKEN"))||(str.equals("NO_NUMERICO"))||(str.equals("NEGATIVO"))||(str.equals("CLAVE_VACIA"))||(str.equals("MARCA_NO_ENCONTRADA")||(str.equals("NO_VENTA"))))
		{
			if(str.equals("NOMBRE_VACIO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nEl campo 'Nombre' se encuentra vac’o.");
				
			if(str.equals("TIPO_VACIO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nEl campo 'Tipo' se encuentra vac’o.");
				
			if(str.equals("CLAVE_NO_ENCONTRADA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nLa Clave '" + tfClave.getText() + "' no se encontr— en la base de datos.");
				
			if(str.equals("MARCA_NO_ENCONTRADA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nLa Marca '" + tfMarca.getText() + "' no se encontr— en los base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nTodos los campos deben contener datos.");
				
			if(str.equals("PROVEEDOR_VACIO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nEl campo 'Proveedor' se encuentra vac’o.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nLos datos que se capturan no pueden contener un '_'");
			if(str.equals("NO_NUMERICO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nLos campos 'Existencia' y 'Precio' deben contener valores numéricos.\nEl campo de 'Existencia' debe ser entero.");
				
			if(str.equals("NEGATIVO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nLos campos 'Existencia' y 'Precio' deben contener valores positivos.\nLa 'Existencia' puede ser 0, el 'Precio' en cambio, no.");
			
			if(str.equals("CLAVE_VACIA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nEl campo 'Clave' se encuentra vac’o.");
				
			if(str.equals("NO_VENTA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------\nNo se pueden vender art’culos con la cantidad de '0'");
				
		}
		else
			taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\n" + str);
			
	}
	
	
	/********************************************************************  ACTION PERFORMED  ***************************************************************************************************/  
		/*******************************************************************************************************************************************************************************/ 
			
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bCapturar)
		{
			//1) Obtener datos de los TextFields
            datos = obtenerDatos();
            
            //2) Comprobar que ninguno de los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado a los nodos
			if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN")||datos.equals("NO_NUMERICO")||datos.equals("NEGATIVO"))
				print(datos);
			else
			{

				//3) Enviar los datos a la clase AD a través del metodo crearNodo
			    resultado = articulos.capturarArticulo(datos);
	
			    //4) Desplegar el resultado de la operaci—n
			    print(resultado);
			     
			    //5) Quitar la informaci—nn de los TextFields
		        clrFields();	
			}
		}
		
		if (e.getSource() == bConsultar){	
			resultado = articulos.consultarArticulos();
			print(resultado);
		}

		if (e.getSource() == bConsultarClave)
		{
			resultado = consultar("CLAVE");
			if(resultado.equals("CLAVE_VACIA")||(resultado.equals("ERROR"))||(resultado.equals("CLAVE_NO_ENCONTRADA")))
				print(resultado);
			else
			{
				//Colocar los datos del nodo en los TextFields
				mostrar(resultado);	
			}	
		}
		
		if (e.getSource() == bConsutlarTipo)
		{	
			resultado = consultar("TIPO");
			print(resultado);
		}
		
		if (e.getSource() == bConsultarNombre){	
			resultado = consultar("NOMBRE");
			print(resultado);
		}
		
		if (e.getSource() == bConsultarProveedor){	
			resultado = consultar("PROVEEDOR");
			print(resultado);
		}
		
		if(e.getSource() == bVender)
		{
			//1) Hacer una consulta de los datos para comprobar que exista la "Clave" o "Registro"
			resultado = consultar("CLAVE");

			//2) Hacer las validaciones correspondientes
			if((resultado.equals("CLAVE_NO_ENCONTRADA"))||(resultado.equals("CLAVE_VACIA")))
				print(resultado);
			else		
			{	
				mostrar(resultado);
				String strExistencia = tfExistencia.getText();
				int existencia = Integer.parseInt(strExistencia);
				
				if(existencia > 0)
				{
					habilitarBotones(false);
					habilitarCampos(false);
					datos = obtenerDatos();
					print(datos);
					controlador = "VENTA";
				}
				else
					print("NO_VENTA");
			}
		}
		
		if(e.getSource() == bRealizarTransaccion)
		{
			if(controlador.equals("VENTA"))
				realizarVenta();
				
			if(controlador.equals("BORRAR"))
				borrarNodo();
				
			if(controlador.equals("MODIFICAR"))
				modificarNodo();	
		}
		
		if(e.getSource() == bCancelar)
		{
			habilitarBotones(true);
			habilitarCampos(true);
			clrFields();
		}
				
		if (e.getSource() == bSalir)
		{
			//String resultado = articulos.datosarticulosArchivo("ARTICULOS");
			System.out.println(resultado);
			
			//resultado = articulos.datosarticulosArchivo("VENTAS");
			System.out.println(resultado);
			System.exit(0);
		}
		
		if(e.getSource() == bConsultarVentas)
		{
			//resultado = articulos.consultarArticulos("VENTAS");
			resultado = consultar("VENTAS");
			print(resultado);
		}
		
		if(e.getSource() == bConsultarArchivo)
		{
		//	resultado = articulos.consultarArticulos("ARTICULOS_ARCHIVO");
			print(resultado);
		}
		
				
		if(e.getSource() == bConsultarArchivoVentas)
		{
			//resultado = articulos.consultarArticulos("VENTAS_ARCHIVO");
			print(resultado);
		}
		
		if(e.getSource() == bBorrar)
		{
			//1) Hacer una consulta de los datos para comprobar que exista la "Clave" o "Registro"
			resultado = consultar("CLAVE");
			
			//2) Comprobar que ninguno de los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado a los nodos
			if(resultado.equals("CLAVE_VACIA")||(resultado.equals("articulos_VACIA"))||(resultado.equals("CLAVE_NO_ENCONTRADA")))
				print(resultado);
			else
			{
				mostrar(resultado);
				habilitarBotones(false);
				habilitarCampos(false);
				controlador = "BORRAR";
			}
		}
		
		if(e.getSource() == bModificar)
		{
			//1) Hacer una consulta de los datos para comprobar que exista la "Clave" o "Registro"
			resultado = consultar("CLAVE");

			//2) Hacer las validaciones correspondientes
			if((resultado.equals("articulos_VACIA"))||(resultado.equals("CLAVE_NO_ENCONTRADA"))||(resultado.equals("CLAVE_VACIA")))
				print(resultado);
			else
			{
				mostrar(resultado);
				habilitarBotones(false);
				tfClave.setEnabled(false);//Evitar que el usuario cambie la clave para proteger funcionamiento correcto del sistema
				controlador = "MODIFICAR";
			}
		}
	}
	
	public static void main(String args[])
	{
		new ArticulosIUG();
	}
}

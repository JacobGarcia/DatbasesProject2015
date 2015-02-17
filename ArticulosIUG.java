/*
 * ArticulosIUG.java
 *
 *  Created on: 16/03/2014
 *		Project #2
 *      Authors: Mario Jacob García Navarro & Luis Arturo Mendoza Reyes. All Rights Reserved 2014.
 *		IN THIS PROJECT A "STORE SYSTEM" WLL BE SIMULATED.
 *		WE WILL BE CREATING A LINKED LIST & ADDING AND DELETING ELEMENTS FROM IT. OTHER TASKS WILL BE DONE THROUGH.
 *		IT IS MAIN PURPOSE IS EVADE USING METHODS CONTAINED IN THE CLASS "LINKEDLIST" IN ORDER TO UNDERSTAND HOW POINTERS AND OTHER ELEMENTS
 *		ARE WORKING IN RAM. CREATING SUCH ALGORITHMS WILL WORK THROUGH OTHER LANGUAGES LIKE C OR C++.
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.text.PlainDocument;

public class ArticulosIUG extends Frame implements ActionListener
{
	private JTextField tfClave,tfNombre, tfExistencia, tfMarca, tfPrecio;
	private JButton    bCapturar, bConsultar, bConsultarMarca, bConsultarClave,
					   bVender, bRealizarTransaccion, bCancelar, bBorrar,
					   bConsultarVentas, bConsultarArchivo, bConsultarArchivoVentas, bSalir,
					   bModificar;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private String clave, datos, nombre, existencia, precio, marca, resultado, controlador;
	
	//private ArticulosAD lista = new ArticulosAD(); 
	private ArticulosADLL lista = new ArticulosADLL();
	
	public ArticulosIUG()
	{
		super("Proyecto (Linked List)");
		
		//Inicializar los atributos
		tfClave 	   	= new JTextField();
		tfNombre   		= new JTextField();
		tfExistencia 	= new JTextField();
		tfPrecio		= new JTextField();
		tfMarca			= new JTextField();
		taDatos    		= new JTextArea(13, 40);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		clave      		= "";
		datos      		= "";
		nombre    		= "";
		existencia   	= "";
		marca 			= "";
		precio 			= "";
		resultado 		= "";
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(12, 2));
		
		p1.add(new Label("Clave"));
		p1.add(tfClave);

		p1.add(new Label("Nombre"));
		p1.add(tfNombre);

		p1.add(new Label("Marca")); 
		p1.add(tfMarca);
		
		p1.add(new Label("Unidades en Existencia")); 
		p1.add(tfExistencia);

		p1.add(new Label("Precio (Unitario)"));
		p1.add(tfPrecio);
			
		bCapturar = new JButton("Crear Artículo");
		bCapturar.addActionListener(this);
		p1.add(bCapturar);
		
		bConsultar = new JButton("Consultar Artículos");
		bConsultar.addActionListener(this);
		p1.add(bConsultar);
		
		bConsultarClave = new JButton("Consultar Artículos por Clave");
		bConsultarClave.addActionListener(this);
		p1.add(bConsultarClave);

		bConsultarMarca = new JButton("Consultar Artículos por Marca");
		bConsultarMarca.addActionListener(this);
		p1.add(bConsultarMarca);
		
		bVender = new JButton("Vender Artículos");
		bVender.addActionListener(this);
		p1.add(bVender);
		
		bModificar = new JButton("Modificar Artículos");
		bModificar.addActionListener(this);
		p1.add(bModificar);
		
		bBorrar = new JButton("Dar de Baja Artículo");
		bBorrar.addActionListener(this);
		p1.add(bBorrar);
		
		bConsultarVentas = new JButton("Consultar Ventas");
		bConsultarVentas.addActionListener(this);
		p1.add(bConsultarVentas);
		
		bConsultarArchivo = new JButton("Consultar Archivo de Artículos");
		bConsultarArchivo.addActionListener(this);
		p1.add(bConsultarArchivo);
				
		bConsultarArchivoVentas = new JButton("Consultar Archivo de Ventas");
		bConsultarArchivoVentas.addActionListener(this);
		p1.add(bConsultarArchivoVentas);
		
		bRealizarTransaccion = new JButton("Realizar Transacción");
		bRealizarTransaccion.addActionListener(this);
		p1.add(bRealizarTransaccion);
		
		bCancelar = new JButton("Cancelar Transacción");
		bCancelar.addActionListener(this);
		p1.add(bCancelar);
		
		bSalir = new JButton("Salir");
		bSalir.addActionListener(this);
		p1.add(bSalir);
				
		p2.setLayout(new FlowLayout());
		
		p2.add(p1);
		p2.add(new JScrollPane(taDatos));
		
		add(p2);
		// setSize(500,600);
		// setVisible(true);
		
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
	}
	
	public void habilitarBotones(boolean value)
	{
		bCapturar.setEnabled(value); 
		bConsultar.setEnabled(value);
		bConsultarClave.setEnabled(value);
		bConsultarMarca.setEnabled(value);
		bVender.setEnabled(value);
		bModificar.setEnabled(value);
		bBorrar.setEnabled(value);
		bConsultarVentas.setEnabled(value);
		bConsultarArchivo.setEnabled(value);
		bConsultarArchivoVentas.setEnabled(value);
		
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
	}
	
	private void mostrar(String str)
	{
		StringTokenizer st = new StringTokenizer(str, "_");
					
		clave = st.nextToken();
		nombre = st.nextToken();
		marca = st.nextToken();
		existencia = st.nextToken();
		precio = st.nextToken();
				
		tfClave.setText(clave);
		tfNombre.setText(nombre);
		tfMarca.setText(marca);
		tfExistencia.setText(existencia);
		tfPrecio.setText(precio);
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
		
		if(marca.equals("") || precio.equals("") || clave.equals("")||nombre.equals("")||existencia.equals(""))
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
					    }
		        	 }
		         	 if(token == false)
		        		 datos = clave+"_"+nombre+"_"+marca+"_"+existencia+"_"+precio;
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
		String str = "", strError = "Debes introducir una cantidad válida. Recuerda : \n 1) Sólo debes introducir números enteros; positivos \n 2) Debes dejar a lo más cero artículos en 'stock'" ;
		String strExistencia = tfExistencia.getText(), clave = "";
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
						resultado = lista.venderArticulos(cantidad, existencia);
						habilitarCampos(true);
						habilitarBotones(true);
						print(resultado);
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
		resultado = lista.borrarNodo(clave);
		print(resultado);
		
		/*Regresar los botones y los campos a su estado original*/
		 habilitarBotones(true);
		 habilitarCampos(true);
				
		/*Quitar la información de los TextFields*/
		clrFields();
	}
	
	private void modificarNodo()
	{
		//1) Obtener datos de los TextFields
		datos = obtenerDatos();
			
		//Comprobar que ninguno de los campos esté vacío, y en caso de que lo anterior no se cumpla, evitar enviar los datos en ese estado a los nodos
		if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN")||datos.equals("NO_NUMERICO")||datos.equals("NEGATIVO"))
			print(datos);
		else
		{
			resultado = lista.modificarNodo(datos);
				
			//Mostrarle los cambios al usuario 
			print("Cambios Realizados: " + resultado);
					
			//Devolver los botones y los campos a su estado original
			habilitarBotones(true);
			habilitarCampos(true);
					
			//Quitar la información de los TextFields
			clrFields();
		}
	}

	private String consultar(String elemento)	// Se busca si existe y manda el resultado (Nombre o Clave)	
	{
		boolean vacia = lista.vacia();

		if (elemento.equals("MARCA"))
		{
			marca = tfMarca.getText();
			
			if(marca.equals(""))
					resultado = "MARCA_VACIA";
			if(vacia == true)
				resultado = "LISTA_VACIA";
				
			if((!marca.equals(""))&&(vacia == false))
				resultado = lista.consultarMarca(marca);
		}
					
		if(elemento.equals("CLAVE"))
		{
			clave = tfClave.getText();

			if(clave.equals(""))
					resultado = "CLAVE_VACIA";
			if(vacia == true)
				resultado = "LISTA_VACIA";
				
			if((!clave.equals(""))&&(vacia == false))
				resultado = lista.consultarClave(clave);
		}
		
		if(elemento.equals("CLAVE_CAPTURAR"))
		{
			clave = tfClave.getText();
			resultado = lista.consultarClave(clave);
			if(resultado.equals("LISTA_VACIA"))
				resultado = "CLAVE_NO_ENCONTRADA";
		}

		return resultado;
	}
	
	private void print(String str)
	{
		if(str.equals("LISTA_VACIA")||(str.equals("MARCA_VACIA"))||(str.equals("CLAVE_NO_ENCONTRADA"))||(str.equals("CAMPO_VACIO"))||(str.equals("CLAVE_EXISTENTE"))||(str.equals("TOKEN"))||(str.equals("NO_NUMERICO"))||(str.equals("NEGATIVO"))||(str.equals("CLAVE_VACIA"))||(str.equals("MARCA_NO_ENCONTRADA")||(str.equals("NO_VENTA"))))
		{
			if(str.equals("LISTA_VACIA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nLa lista no tiene nodos.");
				
			if(str.equals("MARCA_VACIA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nEl campo 'Marca' se encuentra vacío.");
				
			if(str.equals("CLAVE_NO_ENCONTRADA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nLa Clave '" + tfClave.getText() + "' no se encontró en la lista.");
				
			if(str.equals("MARCA_NO_ENCONTRADA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nLa Marca '" + tfMarca.getText() + "' no se encontró en la lista.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nTodos los campos deben contener datos.");
				
			if(str.equals("CLAVE_EXISTENTE"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nLa clave ya existe en la lista.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nLos datos que se capturan no pueden contener un '_'");
			if(str.equals("NO_NUMERICO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nLos campos 'Existencia' y 'Precio' deben contener valores numéricos.\nEl campo de 'Existencia' debe ser entero.");
				
			if(str.equals("NEGATIVO"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nLos campos 'Existencia' y 'Precio' deben contener valores positivos.\nLa 'Existencia' puede ser 0, el 'Precio' en cambio, no.");
			
			if(str.equals("CLAVE_VACIA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nEl campo 'Clave' se encuentra vacío.");
				
			if(str.equals("NO_VENTA"))
				taDatos.setText("Message Log\n--------------------------------------------------------------------------------------------------------------\nNo se pueden vender artículos con la cantidad de '0'");
				
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
				//3) Comprobar que la clave no exista en el registro
				resultado = consultar("CLAVE_CAPTURAR");
				
				if(resultado.equals("CLAVE_NO_ENCONTRADA"))
				{
					//3) Enviar los datos a la clase AD a través del metodo crearNodo
			        resultado = lista.crearNodo(datos);
			
			        //4) Desplegar el resultado de la operación
			        print(resultado);
			        
			        //5) Quitar la informaciónn de los TextFields
			        clrFields();
				}
				else
					print("CLAVE_EXISTENTE");	
			}
		}
		
		if (e.getSource() == bConsultar)
		{	
			resultado = lista.consultarNodos("ARTICULOS");
			print(resultado);
		}

		if (e.getSource() == bConsultarClave)
		{
			resultado = consultar("CLAVE");
			if(resultado.equals("CLAVE_VACIA")||(resultado.equals("LISTA_VACIA"))||(resultado.equals("CLAVE_NO_ENCONTRADA")))
				print(resultado);
			else
			{
				//Colocar los datos del nodo en los TextFields
				mostrar(resultado);	
			}	
		}
		
		if (e.getSource() == bConsultarMarca)
		{	
			resultado = consultar("MARCA");
			print(resultado);
		}
		
		if(e.getSource() == bVender)
		{
			//1) Hacer una consulta de los datos para comprobar que exista la "Clave" o "Registro"
			resultado = consultar("CLAVE");

			//2) Hacer las validaciones correspondientes
			if((resultado.equals("LISTA_VACIA"))||(resultado.equals("CLAVE_NO_ENCONTRADA"))||(resultado.equals("CLAVE_VACIA")))
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
			String resultado = lista.datosListaArchivo("ARTICULOS");
			System.out.println(resultado);
			
			resultado = lista.datosListaArchivo("VENTAS");
			System.out.println(resultado);
			System.exit(0);
		}
		
		if(e.getSource() == bConsultarVentas)
		{
			resultado = lista.consultarNodos("VENTAS");
			print(resultado);
		}
		
		if(e.getSource() == bConsultarArchivo)
		{
			resultado = lista.consultarNodos("ARTICULOS_ARCHIVO");
			print(resultado);
		}
		
				
		if(e.getSource() == bConsultarArchivoVentas)
		{
			resultado = lista.consultarNodos("VENTAS_ARCHIVO");
			print(resultado);
		}
		
		if(e.getSource() == bBorrar)
		{
			//1) Hacer una consulta de los datos para comprobar que exista la "Clave" o "Registro"
			resultado = consultar("CLAVE");
			
			//2) Comprobar que ninguno de los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado a los nodos
			if(resultado.equals("CLAVE_VACIA")||(resultado.equals("LISTA_VACIA"))||(resultado.equals("CLAVE_NO_ENCONTRADA")))
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
			if((resultado.equals("LISTA_VACIA"))||(resultado.equals("CLAVE_NO_ENCONTRADA"))||(resultado.equals("CLAVE_VACIA")))
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

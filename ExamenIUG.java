import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ExamenIUG extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JMenuBar mbExamen;
	private JMenu mArticulos;
	private JMenuItem miArticuloAD, miArticuloADLL;
	private JPanel panel;
	private ArticulosIUG articulos = new ArticulosIUG();

	public ExamenIUG()
	{
		super("Examen Segundo Parcial");

		//Atributos
		mbExamen = new JMenuBar();

		mArticulos = new JMenu("Productos");

		miArticuloAD = new JMenuItem("Art’culos AD");
		miArticuloAD.addActionListener(this);

		miArticuloADLL = new JMenuItem("Art’culos ADLL");
		miArticuloADLL.addActionListener(this);

		panel = new JPanel();

		//Adicionar Objetos al MenuBar

		mArticulos.add(miArticuloAD);
		mArticulos.add(miArticuloADLL);

		mbExamen.add(mArticulos);

		//Vizualizar Frame

		setJMenuBar(mbExamen);
		setSize(700, 450);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == miArticuloAD )
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = articulos.getP2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}
	}


	public static void main(String args[])
	{
		// ExamenIUG examen = new ExamenIUG();
		// examen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new ExamenIUG();
	}
	

}

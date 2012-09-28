import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
*/
public class Calculator extends javax.swing.JFrame {
	private JPanel fondo;
	private JPanel elementos;
	private JToggleButton sumar;	
	private JScrollPane jScrollPanel;
	private JLabel Lhead;
	private JLabel Lresultado;
	private JTextField resultado;
	private JTextArea ingresar;
	private JToggleButton limpiar;
	private JLabel enternumber;
	String cadena;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Calculator inst = new Calculator();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);}
		});
	}	
	//constructor
	public Calculator() {
		super("String Calculator");
		initGUI();}
	
	//pinta la interfaz
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				fondo = new JPanel();
				getContentPane().add(fondo, BorderLayout.CENTER);
				{
					elementos = new JPanel();
					fondo.add(elementos);
					elementos.setLayout(null);
					elementos.setPreferredSize(new java.awt.Dimension(374, 291));
					{	enternumber = new JLabel();
						elementos.add(enternumber);
						enternumber.setText("Ingrese el numero");
						enternumber.setBounds(12, 73, 106, 16);	}
					{	sumar = new JToggleButton();
						elementos.add(sumar);
						sumar.setText("Sumar");
						sumar.setBounds(278, 139, 85, 23);
						sumar.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub								
								cadena = ingresar.getText();
								try {
									patron(cadena);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
					}
					{	limpiar = new JToggleButton();
						elementos.add(limpiar);
						limpiar.setText("limpiar");
						limpiar.setBounds(182, 139, 85, 24);
						limpiar.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								ingresar.setText("");
								resultado.setText("");
								ingresar.requestFocus();
							}
						});
					}
					{	resultado = new JTextField();
						elementos.add(resultado);
						resultado.setText("");
						resultado.setEditable(false);
						resultado.setBounds(130, 189, 232, 17);}
					{	Lresultado = new JLabel();
						elementos.add(Lresultado);
						Lresultado.setText("Resultado");
						Lresultado.setBounds(23, 189, 82, 16);}
					{	Lhead = new JLabel();
						elementos.add(Lhead);
						Lhead.setText("STRING CALCULATOR");
						Lhead.setBounds(118, 18, 160, 16);}					
					{
						jScrollPanel = new JScrollPane();
						elementos.add(jScrollPanel);
						jScrollPanel.setBounds(131, 61, 229, 55);
						{ingresar = new JTextArea();
						jScrollPanel.setViewportView(ingresar);
						ingresar.setBounds(130, 61, 232, 55);}					
					}
				}
			}
			pack();
			this.setSize(400, 289);
		} catch (Exception e) {	e.printStackTrace();}
	}

	//Método evaluacion delimitadores y suma
	protected void patron(String cadena) throws Exception {	
		String patron1 ="[-?0-9]+$|^//(.+)\n(.+)$";//evalua el inicio de lo ingresado a la  caja de texto
		String patroNegativos = "-[0-9]+";
		String negativos = "";
		int dato;
		int suma=0;	
		
		//evalua q no este vacia la caja de texto
		if (cadena.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese datos por favor");
			resultado.setText("0");
			ingresar.requestFocus();
		}else {								
			Pattern patroningreso=Pattern.compile(patron1);
			Matcher matcher= patroningreso.matcher(cadena);
			//pregunte si cumple con el patron de ingreso
			if(matcher.find()){					
				String var = matcher.group(1);//capture el valor despues de ^// y antes de /n
				if(var !=null)//si hay delimitadores nuevos
				cadena = matcher.group(2);//cadena tome los nuemros no el delimitador				
				patroningreso = Pattern.compile(patroNegativos);
				matcher = patroningreso.matcher(cadena);				
				while(matcher.find())
				{negativos+=matcher.group();}
				if(!negativos.isEmpty()){
					JOptionPane.showMessageDialog(null, "Ingrese solo numerosPositivos");
					ingresar.requestFocus();
					throw new Exception("Negativos no soportados "+negativos);}				
				String delimitadores=",|\n|"+var;//delimitadores por defecto o variable				
				cadena = cadena.replace(" ", "");//elimina espacios					
				String[] numeros = cadena.split(delimitadores);				
				//recorra la subcadena
				for (int i = 0; i < numeros.length; i++) {
					dato = Integer.parseInt(numeros[i]);
					if (dato<=1000) {												
						suma += dato;
						String resultsuma = Integer.toString(suma);						
						resultado.setText(resultsuma);}
				}				
			}else{JOptionPane.showMessageDialog(null, "Revise el patron de ingreso", "Error de ingreso", 0);}			
		}		
	}	
}

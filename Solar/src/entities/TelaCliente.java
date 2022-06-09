package entities;
//package entities;

import java.awt.Color;

//import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Utils.Endereco;
import Utils.ServicoDeCep;
import Utils.ValidaData;
import Utils.validaCNPJ;
import Utils.validaCPF;
import db.CadastroPojo;
import db.ContatoDAO;
import db.DB;
import db.TableFromMySqlDatabase;
import enums.TipoDoc;
import enums.estCivil;
//import tstJson.Main.Student;
//import Utils.ConsultaUrl;

public class TelaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	public JTextField txtNome ; 
	private JPanel contentPane;
	private JTextField txtLogradouro;
	private JTextField txtNumero;

	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;
	private JTextField txtObs ; 
	
	private Integer codCli = 0;
	private String opt;
	private String vrCpf;
	private String vrCnpj;
	String vrcompDoc[] = { "Nenhum", "Cpf", "Cnpj" };
	enums.estCivil  vtestCivil[]=enums.estCivil.values();
	//UF vtUF[]=UF.values(); 
	private Integer  tipoDoc = 0 ;  
	private JTextField txtIdt;
	private JTextField txtEmiss;
	private JTextField txtDtEmiss;
	private JTextField txtDtNasc;
	private JTextField txtEstCivil;
	private JTextField txtNacionalidade;
	private JTextField txtNaturalidade ; 

	private JLabel lblTitn = new JLabel(" Norte ");
	private JLabel lblLat = new JLabel("Latitude.:");
	private JTextField txtLatn = new JTextField("0");
	private JLabel lblLng = new JLabel("Longitude.:");
	private JTextField txtLngn = new JTextField("0");
	private JLabel lblTits = new JLabel(" Sul ");
	private JLabel lblLats = new JLabel("Latitude.:");
	private JTextField txtLats = new JTextField("0");
	private JLabel lblLngs = new JLabel("Longitude.:");
	private JTextField txtLngs = new JTextField("0");
	String lats , latn , lngs , lngn = null; 

	
	
	
	
	public TelaCliente() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(60, 100, 800, 600);
		contentPane = new JPanel();
		

		//final  Image backgroundImage = javax.imageio.ImageIO.read(new File("/Users/judney.costa/Downloads/Java/pictures/borgCube.jpeg"));
		
		//JImagePanel panelPrincipal = new JImagePanel(backgroundImage);

		
		
		
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		/*
		 TableFromMySqlDatabase frameJtable  = new TableFromMySqlDatabase();
         //frameJtable.setDefaultCloseOperation( EXIT_ON_CLOSE );
         frameJtable.pack();
         frameJtable.setVisible(true);
         frameJtable.setBounds(0,300,500,200); 
         frameJtable.setVisible(true);
         frameJtable.setVisible(true) ;
         contentPane.add(frameJtable); 
		 */ 
		
		
		
		//TableFromMySqlDatabase.
		
		//contentPane.add(TableFromMySqlDatabase.table);
		
		JLabel lblTitulo = new JLabel("Cadastro de Clientes");
		lblTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.add(lblTitulo);

		lblTitulo.setBounds(293, 20, 400, 16);

		JLabel lblCodigo = new JLabel("Código..: ");
		lblCodigo.setBounds(16, 59, 70, 20);
		contentPane.add(lblCodigo);

		String numero = null;

		JTextField txtCodigo = new JFormattedTextField(new MaskFormatter("#####"));
		txtCodigo.setBounds(76, 59, 70, 20);
	
		codCli = leNextCodigo();

		txtCodigo.setText("" + codCli);

		txtCodigo.setEnabled(false);
		contentPane.add(txtCodigo);

		JLabel lblNome = new JLabel("Nome..: ");
		lblNome.setBounds(175, 59, 70, 20);
		contentPane.add(lblNome);

	    txtNome = new JTextField("Nome Cliente ");
		txtNome.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
		        if (txtNome.getText().length() >= 50 ) // limit textfield to 50 characters
		            e.consume(); 
		    }  
			}); 
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String nome = txtNome.getText().trim();
				Integer var = txtNome.getText().trim().length();

				if (nome == null || var == 0) {
					JOptionPane.showMessageDialog(null, "Atenção !!! Nome não pode ser igual a nulo ");
					txtNome.requestFocus();
					txtNome.requestFocusInWindow(null);
				}
			}

		});


		txtNome.setBounds(228, 59, 566, 20);
		txtNome.setCaretPosition(0);

		contentPane.add(txtNome);

		JLabel lblCep = new JLabel("Cep.......: ");
		lblCep.setBounds(16, 103, 70, 20);
		contentPane.add(lblCep);

		JTextField txtCep = new JFormattedTextField(new MaskFormatter("#####-###"));
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				String cep = txtCep.getText();
				//Integer var = txtCep.getText().trim().length();
				String cepAux = cep.replaceAll("-", "");
				cep = cepAux.trim();
				Integer var=cep.length(); 
				if (cep.equals("") || var != 8 ) {
					JOptionPane.showMessageDialog(txtCep, "Cep digitado : ["+txtCep.getText() + "] inválido !!! ");
					txtCep.requestFocus();
				} else {
					Endereco endereco = null;
					try {
						endereco = veCep(cep);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					String ret = endereco.toString().substring(20, 24);

					
					//System.out.printf("Endereço..: %s \n ", endereco.toString() ) ;
					String varend = "null";

					if (ret.equals(varend)) {
						txtLogradouro.setText("");
						txtBairro.setText("");
						txtCidade.setText("");
						txtUf.setText("");
						String msg = "Cep : " + cep + " não encontrado , verifique !!!";
						JOptionPane.showMessageDialog(null, msg);
						txtCep.requestFocus();
					} else {
						txtLogradouro.setText(endereco.getLogradouro());
						txtBairro.setText(endereco.getBairro());
						txtCidade.setText(endereco.getLocalidade());
						txtUf.setText(endereco.getuf());
						
						
						String Url="https://maps.googleapis.com/maps/api/geocode/json?key=";
						String Key="AIzaSyA3ngioc-m44ggzMAHVzwgdX_hqJdI2Q6c";
						String Te1="&sensor=false&address=";
						
						String Tmp=endereco.getLogradouro();
						if ( Tmp != null ) { 
							String Log=Tmp.replaceAll("\\s+","%20");
					
							String Num ="0" ; 
							//String Num=txtNumero.getText(); 
							String CidAux=endereco.getLocalidade(); 
							String Cid=CidAux.replaceAll("\\s+","%20");
							String UF =endereco.getuf(); 
						
							String Req=Url+Key+Te1+Log+"%20"+Num+"%20"+Cid+"%20"+UF; 
							String[]  respGeo ; 
							respGeo=null ; 
							//try {
								try {
									respGeo=Utils.ConsultaGeocode.consultaUrl(Req );
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
								latn=respGeo[0];
								lngn=respGeo[1];
								lats=respGeo[2];
								lngs=respGeo[3];
							
							
							
								txtLatn.setText(respGeo[0] ) ; 
								txtLngn.setText(respGeo[1]); 
								txtLats.setText(respGeo[2]); 
								txtLngs.setText(respGeo[3]) ; 
							
							
							
								lblTitn.setVisible(true) ;
								lblLat.setVisible(true);
								txtLatn.setVisible(true);
								lblLng.setVisible(true);
								txtLngn.setVisible(true); 
								lblTits.setVisible(true);
								lblLats.setVisible(true); 
								txtLats.setVisible(true);
								lblLngs.setVisible(true); 
								txtLngs.setVisible(true); 
						} 
						else { 
							txtCep.requestFocus();
						}
							
						
		
		
					}
				}

			}
		});
		txtCep.setBounds(76, 103, 84, 20);
		txtCep.setCaretPosition(0);

		contentPane.add(txtCep);

		JLabel lblLogradouro = new JLabel("Logradouro..:");
		lblLogradouro.setBounds(175, 103, 89, 20);
		contentPane.add(lblLogradouro);

		txtLogradouro = new JTextField("Logradouro");
		txtLogradouro.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
		        if (txtLogradouro.getText().length() >= 100 ) // limit textfield to 100 characters
		            e.consume(); 
		    }  
			}); 
		txtLogradouro.setCaretPosition(0);
		txtLogradouro.setBounds(264, 103, 413, 20);
		contentPane.add(txtLogradouro);

		JLabel lblNum = new JLabel("Num : ");
		lblNum.setBounds(684, 103, 42, 20);
		contentPane.add(lblNum);

		JFormattedTextField txtNumero = new JFormattedTextField(new MaskFormatter("**********"));
		txtNumero.setText("N/A");
		txtNumero.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				String Numero = txtNumero.getText();
				Integer var = txtNumero.getText().trim().length();
				String comp = "";
				if (numero == comp || var == 0) {
					JOptionPane.showMessageDialog(txtCep, "Numero  Nao pode ser igual a Nulo ");

					txtNumero.requestFocus();
				}

			}

		});

		txtNumero.setCaretPosition(0);
		txtNumero.setBounds(724, 103, 70, 20);
		contentPane.add(txtNumero);

		JLabel lblComplemento = new JLabel("Comp....:");
		lblComplemento.setBounds(16, 147, 58, 20);
		contentPane.add(lblComplemento);

		txtComplemento = new JTextField("N/A");
		txtComplemento.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
		        if (txtComplemento.getText().length() >= 20 ) // limit textfield to 20 characters
		            e.consume(); 
		    }  
			}); 
		txtComplemento.setBounds(76, 147, 84, 20);
		txtComplemento.setCaretPosition(0);

		contentPane.add(txtComplemento);

		JLabel lblBairro = new JLabel("Bairro.:");
		lblBairro.setBounds(175, 147, 58, 20);
		contentPane.add(lblBairro);

		txtBairro = new JTextField("Bairro");
		txtBairro.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
		        if (txtBairro.getText().length() >= 50 ) // limit textfield to 50 characters
		            e.consume(); 
		    }  
			}); 
		
		txtBairro.setCaretPosition(0);
		txtBairro.setBounds(228, 147, 200, 20);
		contentPane.add(txtBairro);

		JLabel lblCidade = new JLabel("Cidade.:");
		
		lblCidade.setBounds(438, 147, 51, 20);
		contentPane.add(lblCidade);

		txtCidade = new JTextField("Cidade");
		txtCidade.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
		        if (txtCidade.getText().length() >= 50 ) // limit textfield to 50 characters
		            e.consume(); 
		    }  
			}); 
		
		txtCidade.setCaretPosition(0);
		txtCidade.setBounds(505, 147, 174, 20);
		contentPane.add(txtCidade);

		
		
		JLabel lblUf = new JLabel("UF.............:");
		lblUf.setBounds(684, 147, 100, 20);
		contentPane.add(lblUf);

		txtUf = new JTextField("UF");
		txtUf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
		        if (txtUf.getText().length() >= 2 ) // limit textfield to 2 characters
		            e.consume(); 
		    }  
			}); 
		
		
		txtUf.setCaretPosition(0);
		txtUf.setBounds(758, 147, 35, 20);
		contentPane.add(txtUf);

		
		//
		//JLabel lblTitn = new JLabel(" Norte ");
		lblTitn.setBounds(210, 173, 120, 20);
		contentPane.add(lblTitn);
		
		int Xpos=16;
		int Ypos=197; 
		int width=120 ; 
		int heigth = 20;
		int dist=150;
		
		//JLabel lblLat = new JLabel("Lat.....:");
		lblLat.setBounds(Xpos , Ypos , width , heigth );
		contentPane.add(lblLat);
		Xpos+= 60 ; 
		
		//JTextField txtLatn = new JTextField("0");
		txtLatn.setBounds(Xpos, Ypos, width , heigth  );
		contentPane.add(txtLatn) ; 
		Xpos+=130; 
		
		//JLabel lblLng = new JLabel("Lng:");
		lblLng.setBounds(Xpos , Ypos , width , heigth );
		contentPane.add(lblLng);
		Xpos+= 80;
		
		//JTextField txtLngn = new JTextField("0");
		txtLngn.setBounds(Xpos, Ypos, width , heigth  );
		contentPane.add(txtLngn) ; 
		

		//JLabel lblTits = new JLabel(" Sul ");
		lblTits.setBounds(620, 173, 120, 20);
		contentPane.add(lblTits);
		
		
		Xpos+=130; 
		//JLabel lblLats = new JLabel("Lat:");
		lblLats.setBounds(Xpos , Ypos , width , heigth );
		contentPane.add(lblLats);
		
		
		Xpos += 60; 
		//JTextField txtLats = new JTextField("0");
		txtLats.setBounds(Xpos, Ypos, width , heigth  );
		contentPane.add(txtLats) ; 
		
		Xpos+= 130; 
		
		//JLabel lblLngs = new JLabel("Lng:");
		lblLngs.setBounds(Xpos , Ypos , width , heigth );
		contentPane.add(lblLngs);
		Xpos+= 70 ;
		
		//JTextField txtLngs = new JTextField("0");
		txtLngs.setBounds(Xpos, Ypos, width , heigth  );
		contentPane.add(txtLngs) ; 
		
         /*
		lblTitn.setVisible(false) ;
		lblLat.setVisible(false);
		txtLatn.setVisible(false);
		lblLng.setVisible(false);
		txtLngn.setVisible(false); 
		lblTits.setVisible(false);
		lblLats.setVisible(false); 
		txtLats.setVisible(false);
		lblLngs.setVisible(false); 
		txtLngs.setVisible(false); 
        */
		
		txtLatn.setEditable(false);
		txtLngn.setEditable(false);
		txtLats.setEditable(false);
		txtLngs.setEditable(false);
		JLabel lblDoc = new JLabel("Tp Doc..: ");
		lblDoc.setBounds(16, 270, 62, 20);
		//lblDoc.setVisible(false);
		contentPane.add(lblDoc);

		JComboBox<TipoDoc> cmbDoc = new JComboBox<>();
		cmbDoc.setModel(new DefaultComboBoxModel<>(TipoDoc.values()));
		cmbDoc.setBounds(76, 270, 105, 20);

		JFormattedTextField txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCpf.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				cmbDoc.setVisible(false);
				vrCpf = txtCpf.getText();
				String vrCpfaux = vrCpf.replace(".", "");
				vrCpf = vrCpfaux.replace("-", "");
				boolean testeCpf = validaCPF.isCPF(vrCpf);
				if (!testeCpf) {
					JOptionPane.showMessageDialog(null, "Cpf :" + txtCpf.getText() + " Inválido !!! ");
					txtCpf.requestFocus();
				}

			}
		});

		txtCpf.setVisible(false);
		txtCpf.setCaretPosition(0);
		txtCpf.setBounds(76, 270, 120, 20);
		contentPane.add(txtCpf);

		JFormattedTextField txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		txtCnpj.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				cmbDoc.setVisible(false);
				vrCnpj = txtCnpj.getText();

				String vrCnpjaux = vrCnpj.replace(".", "");
				vrCnpj = vrCnpjaux.replace("-", "");
				vrCnpjaux = vrCnpj.replace("/", "");
				vrCnpj = vrCnpjaux;

				boolean testeCnpj = validaCNPJ.isCNPJ(vrCnpj);

				if (!testeCnpj) {
					JOptionPane.showMessageDialog(null, "Cnpj :" + txtCnpj.getText() + " Inválido !!! ");
					txtCnpj.requestFocus();
				}


			}
		});

		txtCnpj.setVisible(false);
		txtCnpj.setBounds(76, 270, 145, 20);
		txtCnpj.setCaretPosition(0);
		contentPane.add(txtCnpj);
	
		JLabel lblIdt = new JLabel("Identidade :");
		lblIdt.setBounds(228, 270, 84, 20);
		//lblIdt.setVisible(false ); 
		contentPane.add(lblIdt);
		
		txtIdt = new JTextField("N/A");
		txtIdt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
		        if (txtIdt.getText().length() >= 20 ) // limit textfield to 20 characters
		            e.consume(); 
		    }  
			}); 
		
		txtIdt.setCaretPosition(0);
		txtIdt.setBounds(303, 270, 129, 20);
		//txtIdt.setVisible(false); 
		contentPane.add(txtIdt);
		
		JLabel lblEmiss = new JLabel("Emissor : ");
		lblEmiss.setBounds(438, 270, 62, 20);
		//lblEmiss.setVisible(false); 
		contentPane.add(lblEmiss);
		
		txtEmiss = new JTextField("N/A");
		txtEmiss.setCaretPosition(0);
		txtEmiss.setBounds(504, 270, 120, 20);
		//txtEmiss.setVisible(false); 
		contentPane.add(txtEmiss);
		
		JLabel lblDtEmiss = new JLabel("Dt. Emiss:");
		lblDtEmiss.setBounds(626, 270, 90, 20);
		contentPane.add(lblDtEmiss);
		
		
		JFormattedTextField txtDtEmiss = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDtEmiss.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

				String dtEmiss = txtDtEmiss.getText();
		
				boolean valData = ValidaData.isDateValid(dtEmiss);

				if (!valData ) {
					JOptionPane.showMessageDialog(null, "Data de Emissão  :" + txtDtEmiss.getText() + " Inválida !!! ");

					txtDtEmiss.requestFocus();

				} else {

					boolean resu = ValidaData.ValidDtAtu(dtEmiss);

					if (!resu) {
						JOptionPane.showMessageDialog(null,
								"Data de Emissão  :" + txtDtEmiss.getText() + " maior que data de hoje  ");
						txtDtEmiss.requestFocus();
					}

				}

			}
		});
		txtDtEmiss.setCaretPosition(0);
		txtDtEmiss.setBounds(698, 270, 96, 20);
		contentPane.add(txtDtEmiss);
		
		JLabel lblDtNasc = new JLabel("Dt. Nasc : ");
		lblDtNasc.setBounds(16, 303, 70, 20);
		contentPane.add(lblDtNasc);
		
		JFormattedTextField txtDtNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDtNasc.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

				String dtNasc = txtDtNasc.getText();
				boolean valData = ValidaData.isDateValid(dtNasc);

				if (!valData ) {
					JOptionPane.showMessageDialog(null, "Data de Nascimento  :" + txtDtNasc.getText() + " Inválida !!! ");

					txtDtEmiss.requestFocus();

				}
			}
		}); 
		
		txtDtNasc.setCaretPosition(0);
		txtDtNasc.setBounds(76, 303, 96, 20);
		contentPane.add(txtDtNasc);
		
		JLabel lblEstcivil = new JLabel("Est.Civil :");
		lblEstcivil.setBounds(175, 303, 70, 20);
		contentPane.add(lblEstcivil);
		
		JComboBox<estCivil> cmbEstCivil = new JComboBox<>();
		cmbEstCivil.setModel(new DefaultComboBoxModel<>(estCivil.values()));
		cmbEstCivil.setBounds(235, 303, 150, 20);
		contentPane.add(cmbEstCivil); 
		
		txtEstCivil = new JTextField("Estado Civil");
		txtEstCivil.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
		        if (txtEstCivil.getText().length() >= 20 ) // limit textfield to 20 characters
		            e.consume(); 
		    }  
			}); 
		
		
		txtEstCivil.setCaretPosition(0);
		txtEstCivil.setBounds(238, 303, 120, 20);
		txtEstCivil.setVisible(false);
		contentPane.add(txtEstCivil);
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade: ");
		lblNacionalidade.setBounds(390, 303, 110, 20);
		contentPane.add(lblNacionalidade);
		
		txtNacionalidade = new JTextField("Brasileira");
		txtNacionalidade.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) { 
	        if (txtNacionalidade.getText().length() >= 50 ) // limit textfield to 50 characters
	            e.consume(); 
	    }  
		}); 
		txtNacionalidade.setCaretPosition(0);
		txtNacionalidade.setBounds(490, 303, 129, 20);
		contentPane.add(txtNacionalidade);
		
		JLabel lblNaturalidade = new JLabel("Natural :");
		lblNaturalidade.setBounds(630, 303, 97, 20);
		contentPane.add(lblNaturalidade);
		
		txtNaturalidade = new JTextField("Naturalidade");
		
		txtNaturalidade.setCaretPosition(0);
		txtNaturalidade.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (txtNaturalidade.getText().length() >= 50 ) // limit textfield to 50 characters
		            e.consume(); 
		    }  
		});
		
		
		
		txtNaturalidade.setBounds(689, 303, 105, 20);
		//txtNaturalidade.setVisible(false); 
		contentPane.add(txtNaturalidade);
		
		/*
		JLabel lblDiv = new JLabel(
				"-------------------------------------------------------------------------------------------------");
		lblDiv.setBounds(16, 350, 778, 20);
		contentPane.add(lblDiv);
         */ 

		JLabel lblObs = new JLabel("Obs..: ") ;
		lblObs.setBounds(16,340,50,20);
		contentPane.add(lblObs); 
		
		
		txtObs = new JTextField();
		
		
		
		txtObs.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (txtObs.getText().length() >= 200 ) // limit textfield to 200 characters
		            e.consume(); 
		    }  
		});
		
		
		
		txtObs.setBounds(76, 340, 720, 20);
		txtObs.setCaretPosition(0); 
		contentPane.add(txtObs); 
		

		
		
		cmbDoc.addItemListener((ItemListener) new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) // para evitar duplicações
					//System.out.printf("Você escolheu a opção [%s] \n ", e.getItem());
				opt = e.getItem().toString();
				if (opt == vrcompDoc[1]) {
					tipoDoc=1  ; 
					lblDoc.setText("Cpf ..: ");
					txtCpf.setVisible(true);
					txtCpf.requestFocus();
				} else if (opt == vrcompDoc[2]) {
					tipoDoc=2 ;
					lblDoc.setText("Cnpj..: ");
					txtCnpj.setVisible(true);
					txtCnpj.requestFocus();

				}
				
			}
		});

		contentPane.add(cmbDoc);
		
		
		cmbEstCivil.addItemListener((ItemListener) new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) // para evitar duplicações
				
				opt = e.getItem().toString();
				
				
				for ( int i = 0 ; i<  vtestCivil.length ; i++ )
				{ 
					String var = vtestCivil[i].toString(); 
					if ( var == opt )
						txtEstCivil.setText(var);
				}
				
				cmbEstCivil.setVisible(false);
				txtEstCivil.setVisible(true) ; 
				txtNacionalidade.requestFocus();
				
			}
		});

		
		
		
		
		JButton btnIncluir = new JButton("Incluir "); 
        	
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				Object[] options = { "Confirmar", "Cancelar" };

				int resposta = JOptionPane.showOptionDialog(null, "Confirma a inclusão ? ", "Informação",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

						if (resposta == 0) {
							CadastroPojo cadastro = new CadastroPojo();
                            //System.out.println("cod_cli :  "+ codCli );
							//cadastro.setCodigo(codCli);
							cadastro.setNome(txtNome.getText());
							String cep = txtCep.getText();
							String cepAux = cep.replaceAll("-", "");
							cep = cepAux;
							cadastro.setCep(cep);
							cadastro.setLogradouro(txtLogradouro.getText());
							cadastro.setNumero(txtNumero.getText());
							cadastro.setComplemento(txtComplemento.getText());
							cadastro.setBairro(txtBairro.getText());
							cadastro.setCidade(txtCidade.getText());
							cadastro.setUF(txtUf.getText());
							// 01/11
							cadastro.setTipoDoc(tipoDoc.toString() );
							cadastro.setObs(txtObs.getText());
						
							
							if ( tipoDoc == 1)
							   cadastro.setCpf(txtCpf.getText());
							else 
								cadastro.setCnpj(txtCnpj.getText());
							cadastro.setIdentidade(txtIdt.getText());
							cadastro.setEmissor(txtEmiss.getText());
							
							
							//Date dataEmissAux = null ; 
							
							
							cadastro.setDataEmiss(txtDtEmiss.getText()) ;
							cadastro.setDataNasc(txtDtNasc.getText());
							cadastro.setEstCivil(txtEstCivil.getText());
							cadastro.setNacionalidade(txtNacionalidade.getText());
							cadastro.setNaturalidade(txtNaturalidade.getText());
							
							
							ContatoDAO dao;
							try {
								dao = new ContatoDAO();
								dao.adiciona(cadastro);
								insereCod(codCli);
								codCli = leNextCodigo();
								//txtCodigo.setText(codCli.toString());
								lblDoc.setVisible(true);
								cmbDoc.setVisible(true);
				
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							/*
							TableFromMySqlDatabase frameJtable  = new TableFromMySqlDatabase();
					         //frameJtable.setDefaultCloseOperation( EXIT_ON_CLOSE );
					         frameJtable.pack();
					         frameJtable.setVisible(true);
					         frameJtable.setBounds(0,100,500,200); 
					         frameJtable.setVisible(true);
					         frameJtable.setVisible(true) ;
					         contentPane.add(frameJtable); 
							*/
						} 
			}
	    }); 
		btnIncluir.setForeground(Color.RED);
		Xpos=76 ;
		Ypos=370 ; 
		width=92 ; 
		heigth = 23;
		dist=100; 
		//btnIncluir.setBounds(xpos, 300, 92, 19);
		btnIncluir.setBounds(Xpos , Ypos , width , heigth ) ; 
		//btnIncluir.setVisible(false);
		contentPane.add(btnIncluir);
		
		txtObs.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

				btnIncluir.setVisible(true) ; 
				btnIncluir.requestFocus();
				
			}
		}); 	

		
		// Alterar 
		
		JButton btnAlterar = new JButton("Alterar"); 
    	
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				Object[] options = { "Confirmar", "Cancelar" };

				int resposta = JOptionPane.showOptionDialog(null, "Confirma a alteração  ? ", "Informação",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

						if (resposta == 0) {
							CadastroPojo cadastro = new CadastroPojo();

							cadastro.setCodigo(codCli);
							cadastro.setNome(txtNome.getText());
							String cep = txtCep.getText();
							String cepAux = cep.replaceAll("-", "");
							cep = cepAux;
							cadastro.setCep(cep);
							cadastro.setLogradouro(txtLogradouro.getText());
							cadastro.setNumero(txtNumero.getText());
							cadastro.setComplemento(txtComplemento.getText());
							cadastro.setBairro(txtBairro.getText());
							cadastro.setCidade(txtCidade.getText());
							cadastro.setUF(txtUf.getText());
							// 01/11
							cadastro.setTipoDoc(tipoDoc.toString() );
							cadastro.setObs(txtObs.getText());
						
							
							if ( tipoDoc == 1)
							   cadastro.setCpf(txtCpf.getText());
							else 
								cadastro.setCnpj(txtCnpj.getText());
							cadastro.setIdentidade(txtIdt.getText());
							cadastro.setEmissor(txtEmiss.getText());
							
							
							//Date dataEmissAux = null ; 
							
							
							cadastro.setDataEmiss(txtDtEmiss.getText()) ;
							cadastro.setDataNasc(txtDtNasc.getText());
							cadastro.setEstCivil(txtEstCivil.getText());
							cadastro.setNacionalidade(txtNacionalidade.getText());
							cadastro.setNaturalidade(txtNaturalidade.getText());
							
							
							ContatoDAO dao;
							try {
								dao = new ContatoDAO();
								dao.adiciona(cadastro);
								insereCod(codCli);
								codCli = leNextCodigo();
								txtCodigo.setText(codCli.toString());
								lblDoc.setVisible(true);
								cmbDoc.setVisible(true);
				
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						} 
			}
	    }); 
		btnAlterar.setForeground(Color.RED);
		Xpos+= dist  ; 
		btnAlterar.setBounds(Xpos , Ypos , width , heigth);
		//btnIncluir.setVisible(false);
		contentPane.add(btnAlterar);

		
		
		
		//Fim Alterar 
		
		
		// Inicio Excluir 
		
		
		JButton btnExcluir = new JButton("Excluir "); 
    	
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				Object[] options = { "Confirmar", "Cancelar" };

				int resposta = JOptionPane.showOptionDialog(null, "Confirma a exclusão ? ", "Informação",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

						if (resposta == 0) {
							CadastroPojo cadastro = new CadastroPojo();

							cadastro.setCodigo(codCli);
							cadastro.setNome(txtNome.getText());
							String cep = txtCep.getText();
							String cepAux = cep.replaceAll("-", "");
							cep = cepAux;
							cadastro.setCep(cep);
							cadastro.setLogradouro(txtLogradouro.getText());
							cadastro.setNumero(txtNumero.getText());
							cadastro.setComplemento(txtComplemento.getText());
							cadastro.setBairro(txtBairro.getText());
							cadastro.setCidade(txtCidade.getText());
							cadastro.setUF(txtUf.getText());
							// 01/11
							cadastro.setTipoDoc(tipoDoc.toString() );
							cadastro.setObs(txtObs.getText());
						
							
							if ( tipoDoc == 1)
							   cadastro.setCpf(txtCpf.getText());
							else 
								cadastro.setCnpj(txtCnpj.getText());
							cadastro.setIdentidade(txtIdt.getText());
							cadastro.setEmissor(txtEmiss.getText());
							
							
							//Date dataEmissAux = null ; 
							
							
							cadastro.setDataEmiss(txtDtEmiss.getText()) ;
							cadastro.setDataNasc(txtDtNasc.getText());
							cadastro.setEstCivil(txtEstCivil.getText());
							cadastro.setNacionalidade(txtNacionalidade.getText());
							cadastro.setNaturalidade(txtNaturalidade.getText());
							
							
							ContatoDAO dao;
							btnExcluir.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent e) {
									System.out.println("esta com o mouse sobre o botao");
								}

								@Override
								public void mouseExited(MouseEvent e) {
									System.out.println("saiu com o mouse de cima do botao");
								}

								@Override
								public void mouseClicked(MouseEvent e) {
									System.out.println("clicou no botao");
								}
							});			try {
								dao = new ContatoDAO();
								dao.adiciona(cadastro);
								insereCod(codCli);
								codCli = leNextCodigo();
								txtCodigo.setText(codCli.toString());
								lblDoc.setVisible(true);
								cmbDoc.setVisible(true);
				
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						} 
			}
	    }); 
		btnExcluir.setForeground(Color.RED);
		Xpos += dist ; 
		btnExcluir.setBounds(Xpos , Ypos , width , heigth);
		//btnIncluir.setVisible(false);
		contentPane.add(btnExcluir);

		
		
		//Fim Excluir 
		
		
		
		// Inicio Consultar 
		JButton btnConsultar = new JButton("Consultar"); 
    	

        //tableFromMySqlDatabase.ta
		
		
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			//int vrCons = TableFromMySqlDatabase. 	
				//db.TableFromMySqlDatabase.ta
				
				
				
				
							}
	    }); 
		
		
		btnConsultar.setForeground(Color.RED);
		Xpos+= dist ; 
		btnConsultar.setBounds(Xpos , Ypos , width , heigth);
		//btnIncluir.setVisible(false);
		contentPane.add(btnConsultar);

		
		
		// Fim Consultar 
		
	
		JButton btnSair = new JButton("Sair");
		/*
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("esta com o mouse sobre o botao");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("saiu com o mouse de cima do botao");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicou no botao");
			}
		});
		
		
		*/ 
		
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DB.closeConnection();

				System.exit(0);
			}
		});

		
		btnSair.setForeground(Color.RED);
		Xpos += dist ; 
		btnSair.setBounds(Xpos , Ypos , width , heigth);
		contentPane.add(btnSair);
		

}

	public static Endereco veCep(String cep) throws Exception {
		String cepAux = cep.replaceAll("-", "");
		cep = cepAux;
		Endereco endereco = null;
		if (cep.equals(""))
			JOptionPane.showMessageDialog(null, "Favor Digitar o Cep ");
		else if ( cep.length() != 8 )
			    JOptionPane.showMessageDialog(null, "Cep deve ter 8 digitos  ");
		        
		     else 
			     endereco = ServicoDeCep.buscaEnderecoPelo(cep);

		return (endereco);

	}

	public static Integer leNextCodigo() throws SQLException {

		ContatoDAO concod = new ContatoDAO();
		Integer codCli = concod.selCod();
		return codCli;

	}

	public void insereCod(Integer codCli) throws SQLException {
		ContatoDAO concod = new ContatoDAO();
		concod.insCod(codCli);

	}
}
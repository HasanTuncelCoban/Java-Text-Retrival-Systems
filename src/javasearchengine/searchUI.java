package javasearchengine;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
//import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
//import javax.swing.DropMode;
//import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class searchUI extends JFrame {

	private JPanel contentPane;
	private JTextField searchTxt;
	private JTextArea contentsTxt;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchUI frame = new searchUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public searchUI() {
		setTitle("Java Text Retrival System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel keywordtitle = new JLabel("Search (Deafult By Title)");
		keywordtitle.setFont(new Font("Calibri", Font.BOLD, 15));
		keywordtitle.setBounds(10, 43, 154, 31);
		contentPane.add(keywordtitle);

		searchTxt = new JTextField();
		searchTxt.setFont(new Font("Calibri", Font.BOLD, 13));
		searchTxt.setBounds(174, 48, 212, 19);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);

		contentsTxt = new JTextArea();
		contentsTxt.setBounds(137, 121, 312, 78);
		contentsTxt.setColumns(10);
		contentsTxt.setLineWrap(true);
		contentsTxt.setWrapStyleWord(true);

		scrollPane = new JScrollPane(contentsTxt);
		scrollPane.setBounds(136, 145, 347, 266);
		// scrollPane.add(contentsTxt);
		contentPane.add(scrollPane);
		// contentPane.add(contentsTxt);

		JButton srchBTN = new JButton("SEARCH");
		srchBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//String temp="<TITLE>"+searchTxt.getText()+ "</TITLE>";
				
				
               try {
            	   for(int i=0;i<22;i++) {
            			BufferedReader reader= new BufferedReader(new FileReader("src\\\\javasearchengine/read/reut2-"+i+".sgm"));
            			StringBuilder strB=new StringBuilder();
            			String str= "";
            			StringBuilder testB=new StringBuilder();
            			String temp="<TITLE>"+searchTxt.getText()+ "</TITLE>";
            			//String temp="<TITLE>AMERICAN EXCHANGE INTRODUCES INSTITUTIONAL INDEX</TITLE>";HELLO WORLD
            			String body= "";
            			
            			do {
            				str =reader.readLine();
            				if(str==null)break;
            						if(str.contentEquals(temp)) {
            							strB.append(str);
            							//contentsTxt.setText(strB.toString());
            								do {									
            								body=reader.readLine();
            								if(body==null)break;
            								testB.append(body);
            								testB.append("\n");	
            								ArrayList<String> bodyContents = new ArrayList<>();
            								bodyContents.add(testB.toString());
            												//System.out.println(body.toString());
            								contentsTxt.setText(bodyContents.toString());
            												if(body.contentEquals("&#3;</BODY></TEXT>")==true)break;	
            								}while(body!=null);	
            										
            						}
            			}while(str!=null);
            			
            	   }
					}catch(Exception e1) {
						JOptionPane.showInternalMessageDialog(null, "Hata"+e1.toString());
					}
			}
		});
		srchBTN.setFont(new Font("Calibri", Font.BOLD, 15));
		srchBTN.setBounds(396, 39, 108, 38);
		contentPane.add(srchBTN);

		JLabel lblNewLabel = new JLabel("Resault :");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(59, 146, 55, 19);
		contentPane.add(lblNewLabel);
		
		JRadioButton date = new JRadioButton("Date");
		date.setHorizontalAlignment(SwingConstants.RIGHT);
		date.setFont(new Font("Calibri", Font.BOLD, 14));
		date.setBounds(167, 90, 54, 25);
		contentPane.add(date);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Topics");
		rdbtnNewRadioButton.setFont(new Font("Calibri", Font.BOLD, 14));
		rdbtnNewRadioButton.setBounds(223, 92, 65, 21);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton body = new JRadioButton("Body");
		body.setFont(new Font("Calibri", Font.BOLD, 14));
		body.setBounds(290, 92, 65, 21);
		contentPane.add(body);
		
		JLabel lblNewLabel_1 = new JLabel("Search Filter :");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(74, 89, 87, 27);
		contentPane.add(lblNewLabel_1);

	}
}
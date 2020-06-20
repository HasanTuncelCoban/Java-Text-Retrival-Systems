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
	private static JTextField searchTxt;
	private static JTextArea contentsTxt;
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
		setResizable(false);
		setTitle("Document Retrieval System");
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
		scrollPane.setBounds(92, 175, 486, 266);
		// scrollPane.add(contentsTxt);
		contentPane.add(scrollPane);
		// contentPane.add(contentsTxt);
		
		JRadioButton date = new JRadioButton("Date");
		date.setHorizontalAlignment(SwingConstants.RIGHT);
		date.setFont(new Font("Calibri", Font.BOLD, 14));
		date.setBounds(167, 90, 54, 25);
		contentPane.add(date);
		
		JRadioButton topics = new JRadioButton("Topics");
		topics.setFont(new Font("Calibri", Font.BOLD, 14));
		topics.setBounds(223, 92, 65, 21);
		contentPane.add(topics);

		JButton srchBTN = new JButton("SEARCH");
		srchBTN.setBackground(Color.GREEN);
		srchBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Thread mt =new Thread(new searchUI());
				//mt.start();
				if(date.isSelected()) {
					date();
				}else if(topics.isSelected()) {
					topics();
				}
				else {
					title();
				}
			}
		});
		
		
		
		
		
		
		
		
		srchBTN.setFont(new Font("Calibri", Font.BOLD, 15));
		srchBTN.setBounds(396, 39, 108, 38);
		contentPane.add(srchBTN);

		
		JLabel lblNewLabel = new JLabel("Resault :");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(27, 176, 55, 19);
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Search Filter :");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(74, 89, 87, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton clean = new JButton("REFRESH");
		clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTxt.setText(" ");
				contentsTxt.setText(" ");
				
			}
		});
		clean.setBackground(Color.YELLOW);
		clean.setForeground(Color.BLACK);
		clean.setFont(new Font("Calibri", Font.BOLD, 15));
		clean.setBounds(514, 39, 108, 38);
		contentPane.add(clean);

	}
	public static void title() {
		String temp="<TITLE>"+searchTxt.getText()+ "</TITLE>";
		//System.out.println("Hello World");
		
		try {
	     	   for(int i=0;i<22;i++) {
	     			BufferedReader reader= new BufferedReader(new FileReader("src\\\\javasearchengine/read/reut2-"+i+".sgm"));
	     			StringBuilder strB=new StringBuilder();
	     			String str= "";
	     			StringBuilder testB=new StringBuilder();
	     			String body= null;
	     			
	     			do {
	     				str =reader.readLine();
	     				if(str==null)break;
	     						if(str.contentEquals(temp)) {
	     							strB.append(str);
	     							
	     								do {									
	     								body=reader.readLine();
	     								if(body==null)break;
	     								testB.append(body);
	     								testB.append("\n");	
	     								
	     								ArrayList<String> bodyContents = new ArrayList<>();
	     								
	     								bodyContents.add(testB.toString());
	     								for(String x: bodyContents) {
	     								x=x.replaceAll("<DATELINE>", "");
	     								x=x.replaceFirst("\\s", "");
	     								x=x.replaceAll("</DATELINE><BODY>","");
	     								x=x.replaceAll("&#3;</BODY></TEXT>", "");
	     								x=x.replaceAll("</REUTERS>", "");
	     								
	     									contentsTxt.setText(x.toString()+"\n");
	     										     									    									
	     									
	     								}
	     								
	     								if(body.contentEquals("</REUTERS>")==true)break;
	     									
	     								}while(body!=null);	
	     										
	     						}
      			}while(str!=null);
      			
     	   reader.close();
        }
        }
				catch(Exception e1) {
					JOptionPane.showInternalMessageDialog(null, "Error :"+e1.toString());
				}
		}
	

	public static void date() {
		String date="<DATE>"+searchTxt.getText()+ "</DATE>";//1;
		//System.out.println("Hello World");
		
        try {
     	   for(int i=0;i<22;i++) {
     			BufferedReader reader= new BufferedReader(new FileReader("src\\\\javasearchengine/read/reut2-"+i+".sgm"));
     			StringBuilder strB=new StringBuilder();
     			String str= "";
     			StringBuilder testB=new StringBuilder();
     			String body= "";
     			
     			do {
     				str =reader.readLine();
     				if(str==null)break;
     						if(str.contentEquals(date)) {
     							strB.append(str);
     							//contentsTxt.setText(strB.toString());
     								do {									
     								body=reader.readLine();
     								if(body==null)break;
     								testB.append(body);
     								testB.append("\n");	
     								
     								ArrayList<String> bodyContents = new ArrayList<>();
     								bodyContents.add(testB.toString());
     								
     								for(String x: bodyContents) {
     									x=x.replaceAll("<TOPICS></TOPICS>", "");
     									x=x.replaceAll("<TOPICS><D>", "");
     								    x=x.replaceAll("</D></TOPICS>", "");
     								    x=x.replaceAll("<PLACES></PLACES>", "");
     									x=x.replaceFirst("\\s", "");
     									x=x.replaceAll("<PLACES><D>usa</D></PLACES>", "");
     									x=x.replaceAll("<PEOPLE></PEOPLE>", "");
     									x=x.replaceAll("<ORGS></ORGS>", "");
     									x=x.replaceAll("<EXCHANGES></EXCHANGES>", "");
     									x=x.replaceAll("<COMPANIES></COMPANIES>", "");
     									x=x.replaceAll("<UNKNOWN>", "");
     									x=x.replaceAll("&#5;&#5;&#5;F Y\r\n" , "");
     									x=x.replaceAll("&#22;&#22;&#1;f0708&#31;reute" , "");	
     									x=x.replaceAll("</UNKNOWN>" , "");
     									x=x.replaceAll("<TEXT>&#2;" , "");	
     									x=x.replaceAll("<TITLE>" , "");
     									x=x.replaceAll("</TITLE>" , "");
     									x=x.replaceAll("<DATELINE>" , "");
     									x=x.replaceAll("</DATELINE><BODY>" , "");
     									x=x.replaceAll("&#3;</BODY></TEXT>" , "");
     									x=x.replaceAll("</REUTERS>" , "");
     									x=x.replaceAll("&#5;&#5;&#5;F" , "");
     									
     								contentsTxt.setText(x.toString().trim()+"\n");
     								
     								
     								}
     												if(body.contentEquals("</REUTERS>")==true)break;	
     								}while(body!=null);	
     										
     						}
     			}while(str!=null);
     			reader.close();
     	   }
				}catch(Exception e1) {
					JOptionPane.showInternalMessageDialog(null, "Error :"+e1.toString());
				}
		}
	
	public static void topics() {
		String topic="<TOPICS><D>"+searchTxt.getText()+ "</D></TOPICS>"; //<TOPICS><D>money-fx</D></TOPICS>
		try {
        	   for(int i=0;i<22;i++) {
        			BufferedReader reader= new BufferedReader(new FileReader("src\\\\javasearchengine/read/reut2-"+i+".sgm"));
        			StringBuilder strB=new StringBuilder();
        			String str= "";
        			StringBuilder testB=new StringBuilder();
        			String body= "";
        			
        			do {
        				str =reader.readLine();
        				if(str==null)break;
        						if(str.contentEquals(topic)) {
        							strB.append(str);
        							//contentsTxt.setText(strB.toString());
        								do {									
        								body=reader.readLine();
        								if(body==null)break;
        								testB.append(body);
        								testB.append("\n");	
        								ArrayList<String> bodyContents = new ArrayList<>();
        								bodyContents.add(testB.toString());
        								for(String x: bodyContents) {
         									//x=x.replaceAll("<TOPICS></TOPICS>", "");
         									//x=x.replaceAll("<TOPICS><D>", "");
         								   // x=x.replaceAll("</D></TOPICS>", "");
         									//x=x.replaceFirst("\\s", "");
         									x=x.replaceAll("</D></PLACES>", "");
         									x=x.replaceAll("<PLACES><D>", "");
         									x=x.replaceAll("<PLACES></PLACES>", "");
         									x=x.replaceAll("<PEOPLE></PEOPLE>", "");
         									x=x.replaceAll("<ORGS></ORGS>", "");
         									x=x.replaceAll("<EXCHANGES></EXCHANGES>", "");
         									x=x.replaceAll("<COMPANIES></COMPANIES>", "");
         									x=x.replaceAll("<UNKNOWN>", "");
         									x=x.replaceAll("&#5;&#5;&#5;F Y\r\n" , "");
         									x=x.replaceAll("&#22;&#22;&#1;f0708&#31;reute" , "");	
         									x=x.replaceAll("</UNKNOWN>" , "");
         									x=x.replaceAll("<TEXT>&#2;" , "");	
         									x=x.replaceAll("<TITLE>" , "");
         									x=x.replaceAll("</TITLE>" , "");
         									x=x.replaceAll("<DATELINE>" , "");
         									x=x.replaceAll("</DATELINE><BODY>" , "");
         									x=x.replaceAll("&#3;</BODY></TEXT>" , "");
         									x=x.replaceAll("</REUTERS>" , "");
         									x=x.replaceAll("&#5;&#5;&#5;F" , "");
         									
         								contentsTxt.setText(x.toString().trim()+"\n");
         								
         								
         								}
         												if(body.contentEquals("</REUTERS>")==true)break;		
        								}while(body!=null);	
        										
        						}
        			}while(str!=null);
        			reader.close();
        	   }
				}catch(Exception e1) {
					JOptionPane.showInternalMessageDialog(null, "Error :"+e1.toString());
				}
		
	}
	
	
	}

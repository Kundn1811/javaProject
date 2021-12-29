package vks_worker;
import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WorkerDetail {

	private JFrame frame;
	private JTextField txtwname;
	private JTextField txtfname;
	private JTextField textaccno;
	private JTextField textsalary;
	private JTextField textbank;
	private JTextField txtifsc;
	private JTable table;
	private JTextField txtwid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerDetail window = new WorkerDetail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WorkerDetail() {
		initialize();
		Connect();
	    table_load();
	}
	
	 Connection con;
		PreparedStatement pst;
		ResultSet rs;
		
		
		public void Connect()
	    {
	        try {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root","kundan@18");
	            String url = "jdbc:mysql://localhost:3306/student";
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	        
	  }
		
		
		public void table_load()
		{
			try 
			{
		    pst = con.prepareStatement("select * from workers");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
			catch (SQLException e) 
			 {
				e.printStackTrace();
		  } 
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Worker Detail");
		lblNewLabel.setBackground(new Color(30, 144, 255));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 17));
		lblNewLabel.setBounds(299, 11, 151, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(9, 51, 311, 155);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 23, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("F_Name");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 45, 46, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Acc_no.");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(10, 70, 46, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Salary");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_1_2.setBounds(10, 95, 46, 14);
		panel.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Bank");
		lblNewLabel_1_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_1_2_1.setBounds(10, 124, 46, 14);
		panel.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Ifsc");
		lblNewLabel_1_1_2_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_1_2_2.setBounds(172, 124, 32, 14);
		panel.add(lblNewLabel_1_1_2_2);
		
		txtwname = new JTextField();
		txtwname.setBounds(66, 20, 160, 20);
		panel.add(txtwname);
		txtwname.setColumns(10);
		
		txtfname = new JTextField();
		txtfname.setColumns(10);
		txtfname.setBounds(66, 42, 160, 20);
		panel.add(txtfname);
		
		textaccno = new JTextField();
		textaccno.setColumns(10);
		textaccno.setBounds(66, 67, 160, 20);
		panel.add(textaccno);
		
		textsalary = new JTextField();
		textsalary.setColumns(10);
		textsalary.setBounds(66, 92, 160, 20);
		panel.add(textsalary);
		
		textbank = new JTextField();
		textbank.setColumns(10);
		textbank.setBounds(49, 121, 101, 20);
		panel.add(textbank);
		
		txtifsc = new JTextField();
		txtifsc.setColumns(10);
		txtifsc.setBounds(200, 121, 101, 20);
		panel.add(txtifsc);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String wName,fName,accNo,salary,bank,ifsc_code;
			  
				wName = txtwname.getText();
				fName = txtfname.getText();
			    accNo = textaccno.getText();
                salary = textsalary.getText();
                bank = textbank.getText();
                ifsc_code = txtifsc.getText();
							
				 try {
					pst = con.prepareStatement("insert into workers(wName,fName,accNo,salary,bank,ifsc_code)values(?,?,?,?,?,?)");
					pst.setString(1, wName);
					pst.setString(2, fName );
					pst.setString(3, accNo );
					pst.setString(4, salary);
					pst.setString(5, bank);
					pst.setString(6,ifsc_code);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added ;)");
					table_load();
						           
					txtwname.setText("");
					txtfname.setText("");
					textaccno.setText("");
					textsalary.setText("");
					textbank.setText("");
					txtifsc.setText("");
					txtwname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				} 
			}
		});
		btnNewButton.setForeground(new Color(240, 248, 255));
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(19, 217, 89, 34);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(50, 205, 50));
		btnExit.setBackground(new Color(105, 105, 105));
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnExit.setBounds(122, 217, 89, 34);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtwname.setText("");
				txtfname.setText("");
				textaccno.setText("");
				textsalary.setText("");
				textbank.setText("");
				txtifsc.setText("");
				txtwname.requestFocus();
			}
		});
		btnClear.setForeground(new Color(240, 248, 255));
		btnClear.setBackground(new Color(105, 105, 105));
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnClear.setBounds(221, 217, 89, 34);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setToolTipText("");
		scrollPane.setBounds(330, 51, 424, 203);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(9, 262, 210, 57);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Worker Id");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 21, 70, 18);
		panel_1.add(lblNewLabel_2);
		
		txtwid = new JTextField();
		txtwid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String worker_Id = txtwid.getText();
					
					pst = con.prepareStatement("select wName,fName,accNo,salary,bank,ifsc_code from workers where  worker_Id = ?");
					pst.setString(1, worker_Id);
					rs =  pst.executeQuery(); 
					
					   if(rs.next()==true)
			            {
			              
			               
			                
			                String wName,fName,accNo,salary,bank,ifsc_code;
			  			  
							wName = rs.getString(1);
							fName =  rs.getString(2);
						    accNo =  rs.getString(3);
			                salary =  rs.getString(4);
			                bank =  rs.getString(5);
			                ifsc_code = rs.getString(6);
			                
			                txtwname.setText(wName);
			                txtfname.setText(fName);
							textaccno.setText( accNo );
							textsalary.setText(salary);
							textbank.setText( bank );
							txtifsc.setText(ifsc_code);
			                
			            }  
			            else
			            {
			            	txtwname.setText("");
							txtfname.setText("");
							textaccno.setText("");
							textsalary.setText("");
							textbank.setText("");
							txtifsc.setText("");
							
			                
			            }
				}catch (SQLException ex) {
			          
		        }
			}
		});
		txtwid.setBounds(90, 20, 86, 20);
		panel_1.add(txtwid);
		txtwid.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String wName,fName,accNo,salary,bank,ifsc_code,worker_Id;
				  
				wName = txtwname.getText();
				fName = txtfname.getText();
			    accNo = textaccno.getText();
                salary = textsalary.getText();
                bank = textbank.getText();
                ifsc_code = txtifsc.getText();
                worker_Id = txtwid.getText();			
				 try {
					
					 
					pst = con.prepareStatement("update workers set wName = ?,fName = ?,accNo = ?,salary = ?,bank = ?,ifsc_code = ? where worker_Id =?");
					pst.setString(1, wName);
					pst.setString(2, fName );
					pst.setString(3, accNo );
					pst.setString(4, salary);
					pst.setString(5, bank);
					pst.setString(6,ifsc_code);
					pst.setString(7, worker_Id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated ;)");
					table_load();
						           
					txtwname.setText("");
					txtfname.setText("");
					textaccno.setText("");
					textsalary.setText("");
					textbank.setText("");
					txtifsc.setText("");
					txtwname.requestFocus();
					txtwid.setText("");
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				} 
			}
		});
		btnUpdate.setForeground(new Color(240, 248, 255));
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnUpdate.setBackground(new Color(105, 105, 105));
		btnUpdate.setBounds(541, 262, 89, 34);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  
				String wid = txtwid.getText();
				try {
						
					 
						pst = con.prepareStatement("delete from workers where worker_Id=?");
						
						pst.setString(1, wid);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record deleted;)");
						table_load();
							           
						txtwname.setText("");
						txtfname.setText("");
						textaccno.setText("");
						textsalary.setText("");
						textbank.setText("");
						txtifsc.setText("");
						txtwname.requestFocus();
						txtwid.setText("");
					   }

					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					} 
			}
		});
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnDelete.setBackground(new Color(105, 105, 105));
		btnDelete.setBounds(653, 262, 89, 34);
		frame.getContentPane().add(btnDelete);
	}
}

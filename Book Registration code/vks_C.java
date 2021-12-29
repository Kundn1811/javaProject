import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vks_C {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vks_C window = new vks_C();
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
	public vks_C() {
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud", "root","kundan@18");
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
    pst = con.prepareStatement("select * from books");
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
		frame.setBounds(100, 100, 629, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 20));
		lblNewLabel.setBounds(63, 11, 161, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(8, 49, 299, 143);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 27, 70, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 52, 55, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(10, 77, 35, 14);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(105, 25, 134, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(89, 50, 134, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(66, 75, 134, 20);
		panel.add(txtprice);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
							
					String bname,edition,price;
					bname = txtbname.getText();
					edition = txtedition.getText();
					price = txtprice.getText();
								
					 try {
						pst = con.prepareStatement("insert into books(name,edition,price)values(?,?,?)");
						pst.setString(1, bname);
						pst.setString(2, edition);
						pst.setString(3, price);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Added ;)");
						table_load();
							           
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtbname.requestFocus();
					   }

					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSave.setBackground(Color.GRAY);
		btnSave.setBounds(8, 203, 89, 34);
		frame.getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.setBackground(Color.GRAY);
		btnExit.setBounds(107, 203, 89, 34);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				txtbid.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.setBackground(Color.GRAY);
		btnClear.setBounds(206, 203, 89, 34);
		frame.getContentPane().add(btnClear);
		
		JScrollPane table_1 = new JScrollPane();
		table_1.setBounds(313, 48, 290, 211);
		frame.getContentPane().add(table_1);
		
		table = new JTable();
		table_1.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 270, 247, 69);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Book id");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(10, 23, 58, 20);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String id = txtbid.getText();
					
					pst = con.prepareStatement("select name,edition,price from books where id = ?");
					pst.setString(1, id);
					rs =  pst.executeQuery(); 
					
					   if(rs.next()==true)
			            {
			              
			                String name = rs.getString(1);
			                String edition = rs.getString(2);
			                String price = rs.getString(3);
			                
			                txtbname.setText(name);
			                txtedition.setText(edition);
			                txtprice.setText(price);
			                
			                
			            }  
			            else
			            {
			             txtbname.setText("");
			             txtedition.setText("");
			                txtprice.setText("");
			                
			            }
				}catch (SQLException ex) {
			          
		        }
			}
		});
		txtbid.setBounds(79, 24, 98, 20);
		panel_1.add(txtbid);
		txtbid.setColumns(20);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,id;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				id = txtbid.getText();
							
				 try {
					pst = con.prepareStatement("update books set name= ?,edition=?,price=? where id =?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!;)");
					table_load();
						           
					  txtbname.setText("");
			            txtedition.setText("");
			            txtprice.setText("");
			            txtbname.requestFocus();
			            txtbid.setText("");
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBackground(Color.GRAY);
		btnUpdate.setBounds(326, 270, 89, 43);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String bid;
	bid  = txtbid.getText();
	
	 try {
			pst = con.prepareStatement("delete from books where id =?");
	
            pst.setString(1, bid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete");
            table_load();
           
            txtbname.setText("");
            txtedition.setText("");
            txtprice.setText("");
            txtbname.requestFocus();
            txtbid.setText("");
		}

        catch (SQLException e1) {
			
			e1.printStackTrace();
		}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBackground(Color.GRAY);
		btnDelete.setBounds(459, 270, 89, 43);
		frame.getContentPane().add(btnDelete);
	}
}

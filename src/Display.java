package proto2;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Display extends JDialog {

	private final JPanel contentPanel = new JPanel();
	double transAmount = 0;
	String accountType ="";
	private JTextField transactionAmount;
	private JTable accountTable;
	private JScrollPane jScrollPane1;
	Account custAct;



	public Display() {


		setTitle("G7 Bank");
		setBounds(100, 100, 404, 509);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);



		JLabel lblSelectAccountType = new JLabel("Select account type:");
			lblSelectAccountType.setBounds(10, 11, 135, 14);
			contentPanel.add(lblSelectAccountType);


			JLabel lblEnterInitialBalance = new JLabel("Transaction:");
			lblEnterInitialBalance.setBounds(10, 60, 97, 14);
			contentPanel.add(lblEnterInitialBalance);

			jScrollPane1 = new javax.swing.JScrollPane();





		JRadioButton rdbtnChecking = new JRadioButton("Checking");
		rdbtnChecking.setBounds(151, 7, 109, 23);
		contentPanel.add(rdbtnChecking);
		rdbtnChecking.setSelected(true);

		JRadioButton rdbtnSavings = new JRadioButton("Savings");
		rdbtnSavings.setBounds(151, 26, 109, 23);
		contentPanel.add(rdbtnSavings);

		ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(rdbtnChecking);
        bgroup.add(rdbtnSavings);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Deposit", "Withdraw"}));
        comboBox.setBounds(81, 56, 97, 22);
        contentPanel.add(comboBox);

        transactionAmount = new JTextField();
        transactionAmount.setBounds(188, 57, 104, 21);
        contentPanel.add(transactionAmount);
        transactionAmount.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 123, 368, 4);
        contentPanel.add(separator);

        JPanel detailsPannel = new JPanel();
        detailsPannel.setBounds(10, 138, 368, 321);
        contentPanel.add(detailsPannel);
        detailsPannel.setLayout(null);
        detailsPannel.setVisible(false);

        JLabel label = new JLabel("Checking Account Balance:");
        label.setBounds(20, 52, 129, 14);
        detailsPannel.add(label);

        JLabel lblCheckingAccountDetails = new JLabel("Checking Account Details");
        lblCheckingAccountDetails.setBounds(89, 11, 221, 20);
        lblCheckingAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailsPannel.add(lblCheckingAccountDetails);

        JLabel label_2 = new JLabel("Checking Interest Earned:");
        label_2.setBounds(20, 74, 126, 14);
        detailsPannel.add(label_2);

        JLabel label_3 = new JLabel("");
        label_3.setBounds(184, 41, 0, 0);
        detailsPannel.add(label_3);

        JButton button_1 = new JButton("Transaction History");
        button_1.setBounds(121, 246, 125, 23);
        detailsPannel.add(button_1);

        JLabel label_4 = new JLabel("Savings Account Balance:");
        label_4.setBounds(20, 188, 123, 14);
        detailsPannel.add(label_4);

        JLabel label_5 = new JLabel("Savings Interest Earned:");
        label_5.setBounds(20, 213, 120, 14);
        detailsPannel.add(label_5);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(310, 64, 0, 2);
        detailsPannel.add(separator_1);

        JButton button_2 = new JButton("Transaction History");
        button_2.setBounds(121, 105, 125, 23);
        detailsPannel.add(button_2);

        JLabel label_7 = new JLabel("");
        label_7.setBounds(157, 88, 0, 0);
        detailsPannel.add(label_7);

        JLabel label_8 = new JLabel("New label");
        label_8.setBounds(301, 52, 46, 14);
        detailsPannel.add(label_8);

        JLabel lblSavingsAccountDetails = new JLabel("Savings Account Details");
        lblSavingsAccountDetails.setBounds(78, 139, 195, 20);
        lblSavingsAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailsPannel.add(lblSavingsAccountDetails);

        JLabel label_9 = new JLabel("New label");
        label_9.setBounds(301, 74, 46, 14);
        detailsPannel.add(label_9);

        JLabel label_10 = new JLabel("New label");
        label_10.setBounds(312, 188, 46, 14);
        detailsPannel.add(label_10);

        JLabel label_1 = new JLabel("New label");
        label_1.setBounds(310, 213, 46, 14);
        detailsPannel.add(label_1);

        JButton button = new JButton("OK");
        button.setBounds(247, 89, 47, 23);
        contentPanel.add(button);
        button.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {


        		StringBuilder warnings = new StringBuilder();

    			if(transactionAmount.getText().isEmpty()) {
    				warnings.append("Transaction amount must not be empty\n");
    			} else {
    				try {
    					transAmount = Integer.parseInt(transactionAmount.getText());
    				}catch(NumberFormatException ex) {
    					warnings.append("Balance must be a number");
    				}

    			}
    			if (rdbtnChecking.isSelected()) {
    		    	accountType = "Checking";

    		    }
    		    else if (rdbtnSavings.isSelected()) {
    		    	accountType = "Savings";
    		    }
    			if(warnings.length()>0) {
    				JOptionPane.showMessageDialog(null, warnings.toString());
    			}
    			else {
    				if(comboBox.getSelectedItem().toString() == "Deposit") {
    				if(accountType != "Savings") {

    				Input.createNewAccount(Input.username,accountType, transAmount);
    				Transaction.depositFunds(transAmount,getAccountNumChecking(Input.username));
    				}else {
    					Input.createNewAccount(Input.username,accountType, transAmount);
        				Transaction.depositFunds(transAmount,getAccountNumSavings(Input.username));
    				}

    				}
    				else {
    					if(comboBox.getSelectedItem().toString() == "Withdraw") {
    	    				if(accountType != "Savings") {

    	    				Input.createNewAccount(Input.username,accountType, transAmount);
    	    				Transaction.withdrawFunds(transAmount,getAccountNumChecking(Input.username));
    	    				}else {
    	    					Input.createNewAccount(Input.username,accountType, transAmount);
    	        				Transaction.withdrawFunds(transAmount,getAccountNumSavings(Input.username));
    	    				}
    				}

    				}

    				transactionAmount.setText(null);

        	}
        	}
        });
        button.setActionCommand("OK");

        JButton btnNewButton = new JButton("View Account Details");
        btnNewButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		detailsPannel.setVisible(true);
        	}
        });
        btnNewButton.setBounds(67, 91, 135, 23);
        contentPanel.add(btnNewButton);
	}


       @SuppressWarnings("unchecked")
	public int getAccountNumChecking(String user) {
    	   ArrayList num = new ArrayList();

    	   Database database = new Database();
           Connection dbConn = database.getConnection();
           Statement stmt = null;

           int largest;

           try {
               String sql = "SELECT ID FROM ACCOUNTS WHERE USERNAME = ? AND ACCOUNTTYPE = ?";
               PreparedStatement pst = dbConn.prepareStatement(sql);
               pst.setString(1, user);
               pst.setString(2,"Checking");
               ResultSet rs = pst.executeQuery();

               while (rs.next()){
               	num.add(rs.getInt("ID"));
               }


           } catch (SQLException e) {
               System.out.println("------------------TableInsert-----------------");
               System.out.println("Cannot insert into table: " + e);
               System.out.println("--------------------------------------------------------");
           } finally {
               if (stmt != null) {
                   try {
   					stmt.close();
   					dbConn.close();
   				} catch (SQLException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
               }
           }

		return  largest = (int) Collections.max(num);




       }

       public int getAccountNumSavings(String user) {
    	   ArrayList num = new ArrayList();

    	   Database database = new Database();
           Connection dbConn = database.getConnection();
           Statement stmt = null;

           int largest;



           try {
               String sql = "SELECT ID FROM ACCOUNTS WHERE USERNAME = ? AND ACCOUNTTYPE = ?";
               PreparedStatement pst = dbConn.prepareStatement(sql);
               pst.setString(1, user);
               pst.setString(1, "Savings");
               ResultSet rs = pst.executeQuery();

               while (rs.next()){
               	num.add(rs.getInt("ID"));
               }


           } catch (SQLException e) {
               System.out.println("------------------TableInsert-----------------");
               System.out.println("Cannot insert into table: " + e);
               System.out.println("--------------------------------------------------------");
           } finally {
               if (stmt != null) {
                   try {
   					stmt.close();
   					dbConn.close();
   				} catch (SQLException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
               }
           }

		return  largest = (int) Collections.max(num);




       }

		public static DefaultTableModel buildTableModel(ResultSet rs)
		        throws SQLException {

		    ResultSetMetaData metaData = rs.getMetaData();

		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);

		}









	    public void displayBalance(int accountNum){
	        /*
	        TODO
	        pull balance from database and display in GUI;
	        */
	    }
	}



package proto2;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
	ResultSet rs =null;


	public Display() {


		setTitle("G7 Bank");
		setBounds(100, 100, 357, 509);
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

        JLabel lblChkAccountBalance = new JLabel("Checking Account Balance:");
        lblChkAccountBalance.setBounds(10, 169, 168, 14);
        contentPanel.add(lblChkAccountBalance);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 125, 321, 2);
        contentPanel.add(separator);

        JLabel lblNewLabel = new JLabel("Account Details");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(100, 138, 149, 14);
        contentPanel.add(lblNewLabel);
        {
        	JButton okButton = new JButton("OK");
        	okButton.setBounds(226, 89, 66, 23);
        	contentPanel.add(okButton);
        	okButton.addActionListener(new ActionListener() {
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


        				Input.createNewAccount(Input.username,accountType, transAmount);
        				transactionAmount.setText(null);      				

        			}

        		}
        	});
        	okButton.setActionCommand("OK");
        	getRootPane().setDefaultButton(okButton);
        }



        JLabel lblInterestEarned = new JLabel("Checking Interest Earned:");
        lblInterestEarned.setBounds(10, 182, 135, 14);
        contentPanel.add(lblInterestEarned);

        JLabel lblInterestEarned1 = new JLabel("");
        lblInterestEarned1.setBounds(208, 193, 52, 14);
        contentPanel.add(lblInterestEarned1);

        JButton btnNewButton = new JButton("Transaction History");
        btnNewButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		displayTransactionHistory();
        	}
        });
        btnNewButton.setBounds(100, 213, 149, 23);
        contentPanel.add(btnNewButton);

        JLabel lblSavingsAccountBalance = new JLabel("Savings Account Balance:");
        lblSavingsAccountBalance.setBounds(10, 314, 168, 14);
        contentPanel.add(lblSavingsAccountBalance);

        JLabel lblSavingsInterestEarned = new JLabel("Savings Interest Earned:");
        lblSavingsInterestEarned.setBounds(10, 329, 135, 14);
        contentPanel.add(lblSavingsInterestEarned);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 257, 321, 2);
        contentPanel.add(separator_1);

        JLabel label = new JLabel("Account Details");
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setBounds(100, 264, 149, 14);
        contentPanel.add(label);

        JButton button = new JButton("Transaction History");
        button.setBounds(100, 364, 149, 23);
        contentPanel.add(button);

        JLabel lblchkBalance = new JLabel("New label");
        lblchkBalance.setBounds(285, 169, 46, 14);
        contentPanel.add(lblchkBalance);

        JLabel lblChkInterestEarned = new JLabel("New label");
        lblChkInterestEarned.setBounds(285, 182, 46, 14);
        contentPanel.add(lblChkInterestEarned);

        JLabel lblSavActBal = new JLabel("New label");
        lblSavActBal.setBounds(285, 314, 46, 14);
        contentPanel.add(lblSavActBal);

        JLabel lblSavInt = new JLabel("New label");
        lblSavInt.setBounds(285, 329, 46, 14);
        contentPanel.add(lblSavInt);





		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}






		public void displayTransactionHistory() {
			Database database = new Database();
	        Connection dbConn = database.getConnection();

			try {
				String sql = "select * from Accounts Where USERNAME =?";
				PreparedStatement pst = dbConn.prepareStatement(sql);
				pst.setString(1,Input.username);
				rs=pst.executeQuery();
				JTable table = new JTable(buildTableModel(rs));
				JOptionPane.showMessageDialog(null, new JScrollPane(table));

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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



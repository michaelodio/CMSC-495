
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Display extends JDialog {

	private final JPanel contentPanel = new JPanel();
	double transAmount = 0;

	private JTextField transactionAmount;
	private JTable accountTable;
	private JScrollPane jScrollPane1;
	JLabel curChkActBal = new JLabel("");
	JLabel lblSavActBal = new JLabel("");
	Date d1 = new Date();
	private boolean hasBeenClicked = false;

	public Display() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("G7 Bank          " + d1.toString());
		setBounds(100, 100, 474, 509);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblEnterInitialBalance = new JLabel("Transaction:");
		lblEnterInitialBalance.setBounds(10, 60, 97, 14);
		contentPanel.add(lblEnterInitialBalance);
		jScrollPane1 = new javax.swing.JScrollPane();

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] {"Deposit", "Withdrawal"}));
		comboBox.setBounds(87, 56, 163, 22);
		contentPanel.add(comboBox);
		transactionAmount = new JTextField();
		transactionAmount.setBounds(274, 57, 104, 21);
		contentPanel.add(transactionAmount);
		transactionAmount.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 125, 438, 2);
		contentPanel.add(separator);

		JPanel detailsPannel = new JPanel();
		detailsPannel.setBounds(10, 138, 438, 321);
		contentPanel.add(detailsPannel);
		detailsPannel.setLayout(null);
		detailsPannel.setVisible(false);

		JLabel lblAccountBalance = new JLabel("Account Balance:");
		lblAccountBalance.setBounds(20, 52, 164, 14);
		detailsPannel.add(lblAccountBalance);

		JLabel lblCheckingAccountDetails = new JLabel("Account Details");
		lblCheckingAccountDetails.setBounds(126, 11, 221, 20);
		lblCheckingAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		detailsPannel.add(lblCheckingAccountDetails);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(184, 41, 0, 0);
		detailsPannel.add(label_3);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(310, 64, 0, 2);
		detailsPannel.add(separator_1);

		JButton chkTrans = new JButton("Transaction History");
		chkTrans.addActionListener(e -> {
			try {
				JTable table = new JTable(
						buildTableModel(Input.accountNum));
				UIManager.put("OptionPane.minimumSize",
						new Dimension(800, 800));
				JOptionPane.showMessageDialog(null, new JScrollPane(table),
						Input.username + "'s"
								+ " Checking Account Transaction History",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		chkTrans.setBounds(108, 99, 202, 23);
		detailsPannel.add(chkTrans);

		JLabel label_7 = new JLabel("");
		label_7.setBounds(157, 88, 0, 0);
		detailsPannel.add(label_7);
		lblSavActBal.setBounds(312, 188, 97, 14);
		detailsPannel.add(lblSavActBal);
		curChkActBal.setBounds(301, 52, 108, 14);
		detailsPannel.add(curChkActBal);

		JLabel savIntEarnedlbl = new JLabel("");
		savIntEarnedlbl.setBounds(310, 213, 46, 14);
		detailsPannel.add(savIntEarnedlbl);

		JButton button = new JButton("OK");
		button.setBounds(320, 89, 58, 23);
		contentPanel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				StringBuilder warnings = new StringBuilder();				

				if (transactionAmount.getText().isEmpty()) {
					warnings.append("Transaction amount must not be empty\n");
				} else {
					try {
						transAmount = Double
								.parseDouble(transactionAmount.getText());
					} catch (NumberFormatException ex) {
						warnings.append("Balance must be a number");
					}

				}

				if (warnings.length() > 0) {
					JOptionPane.showMessageDialog(null, warnings.toString());
				} else {

					String choice = comboBox.getSelectedItem().toString();


					switch (Input.accountType) {

						case "Checking" :
							try {
								if (Transaction.noFunds == false) {
									Input.createTransaction(Input.accountNum,
											transAmount, choice,
											Input.accountType);
									curChkActBal.setText("$"
											+ Double.toString(Input.getBalance(
													Input.accountNum)));
								}
								if (Transaction.noFunds == true) {
									JOptionPane.showMessageDialog(null,
											"Insufficient Funds");
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							break;
						case "Savings" :
							try {
								if (Transaction.noFunds == false) {
									Input.createTransaction(Input.accountNum,
											transAmount, choice,
											Input.accountType);
									curChkActBal.setText("$"
											+ Double.toString(Input.getBalance(
													Input.accountNum)));
								}
								if (Transaction.noFunds == true) {
									JOptionPane.showMessageDialog(null,
											"Insufficient Funds");
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							break;

						default :

					}
				}
				transactionAmount.setText(null);

			}
		});
		button.setActionCommand("OK");

		JButton btnNewButton = new JButton("View Account Details");
		btnNewButton.addActionListener(e -> {
			if(!hasBeenClicked) {
			detailsPannel.setVisible(true);
			displayBalance();
			}
			else {
				detailsPannel.setVisible(false);
			}

			 hasBeenClicked = ! hasBeenClicked;

		});
		btnNewButton.setBounds(127, 91, 163, 23);
		contentPanel.add(btnNewButton);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(e -> System.exit(0));
		btnLogOut.setBounds(20, 91, 89, 23);
		contentPanel.add(btnLogOut);
	}

	public DefaultTableModel buildTableModel(int actNum) throws SQLException {
		Database database = new Database();
		Connection dbConn = database.getConnection();
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		try {
			String sql = "SELECT * FROM TRANSACTIONS WHERE ACCOUNT_NUM = ?";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setInt(1, actNum);
			rs = pst.executeQuery();
			metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			for (int column = 1; column <= columnCount; column++) {
				columnNames.add(metaData.getColumnName(column));
			}

			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					vector.add(rs.getObject(columnIndex));
				}
				data.add(vector);
			}

		} finally {
			dbConn.close();
		}

		return new DefaultTableModel(data, columnNames);

	}

	// shows balances is no transactions have been made
	public void displayBalance() {
		try {
			curChkActBal.setText(
					"$" + Double.toString(Input.getBalance(Input.accountNum)));
		} catch (SQLException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}

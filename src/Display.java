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
import java.util.Date;
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
	String accountType = "";
	private JTextField transactionAmount;
	private JTable accountTable;
	private JScrollPane jScrollPane1;
	JLabel curChkActBal = new JLabel("");
	JLabel lblSavActBal = new JLabel("");
	Date d1 = new Date();

	public Display() {

		setTitle("G7 Bank          " + d1.toString());
		setBounds(100, 100, 474, 509);
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
		rdbtnChecking.setBounds(183, 7, 109, 23);
		contentPanel.add(rdbtnChecking);
		rdbtnChecking.setSelected(true);

		JRadioButton rdbtnSavings = new JRadioButton("Savings");
		rdbtnSavings.setBounds(318, 7, 109, 23);
		contentPanel.add(rdbtnSavings);

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdbtnChecking);
		bgroup.add(rdbtnSavings);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[]{"Deposit", "Withdraw"}));
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

		JLabel label = new JLabel("Checking Account Balance:");
		label.setBounds(20, 52, 164, 14);
		detailsPannel.add(label);

		JLabel lblCheckingAccountDetails = new JLabel(
				"Checking Account Details");
		lblCheckingAccountDetails.setBounds(126, 11, 221, 20);
		lblCheckingAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		detailsPannel.add(lblCheckingAccountDetails);

		JLabel label_2 = new JLabel("Checking Interest Earned:");
		label_2.setBounds(20, 74, 164, 14);
		detailsPannel.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(184, 41, 0, 0);
		detailsPannel.add(label_3);

		JButton savTrans = new JButton("Savings Transaction History");
		savTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JTable table = new JTable(buildTableModelSavings());
					JOptionPane.showMessageDialog(null, new JScrollPane(table),
							Input.username + "'s"
									+ " Savings Account Transaction History",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		savTrans.setBounds(108, 238, 202, 23);
		detailsPannel.add(savTrans);

		JLabel label_4 = new JLabel("Savings Account Balance:");
		label_4.setBounds(20, 188, 164, 14);
		detailsPannel.add(label_4);

		JLabel label_5 = new JLabel("Savings Interest Earned:");
		label_5.setBounds(20, 213, 153, 14);
		detailsPannel.add(label_5);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(310, 64, 0, 2);
		detailsPannel.add(separator_1);

		JButton chkTrans = new JButton("Checking Transaction History");
		chkTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JTable table = new JTable(buildTableModelChecking());
					JOptionPane.showMessageDialog(null, new JScrollPane(table),
							Input.username + "'s"
									+ " Checking Account Transaction History",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		chkTrans.setBounds(108, 99, 202, 23);
		detailsPannel.add(chkTrans);

		JLabel label_7 = new JLabel("");
		label_7.setBounds(157, 88, 0, 0);
		detailsPannel.add(label_7);

		lblSavActBal.setBounds(312, 188, 97, 14);
		detailsPannel.add(lblSavActBal);

		JLabel lblSavingsAccountDetails = new JLabel("Savings Account Details");
		lblSavingsAccountDetails.setBounds(131, 139, 195, 20);
		lblSavingsAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		detailsPannel.add(lblSavingsAccountDetails);

		curChkActBal.setBounds(301, 52, 108, 14);
		detailsPannel.add(curChkActBal);

		JLabel chkIntEarned = new JLabel("");
		chkIntEarned.setBounds(301, 74, 46, 14);
		detailsPannel.add(chkIntEarned);

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
				if (rdbtnChecking.isSelected()) {
					accountType = "Checking";

				} else if (rdbtnSavings.isSelected()) {
					accountType = "Savings";
				}
				if (warnings.length() > 0) {
					JOptionPane.showMessageDialog(null, warnings.toString());
				} else {
					if (comboBox.getSelectedItem().toString() == "Deposit") {

						if (accountType == "Checking") {

							try {
								Input.createTransaction(				getUserAccountNum(Input.username),
										transAmount, "Deposit", accountType);

								if (getAccountNumChecking(Input.username) <= 0) {
									Input.createNewAccount(Input.username,
											accountType, transAmount);
								} else {
									Transaction.depositFunds(transAmount,
											getAccountNumChecking(
													Input.username));
								}
								try {
									curChkActBal.setText("$"
											+ Double.toString(Input.getBalance(
													getAccountNumChecking(
															Input.username))));
								} catch (SQLException e1) { // TODO
															// Auto-generated
															// catch block
									e1.printStackTrace();
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (accountType == "Savings") {
							try {
								Input.createTransaction(
										getUserAccountNum(Input.username),
										transAmount, "Deposit", accountType);
								if (getAccountNumSavings(Input.username) <= 0) {
									Input.createNewAccount(Input.username,
											accountType, transAmount);
								} else {
									Transaction.depositFunds(transAmount,
											getAccountNumSavings(
													Input.username));
								}
								try {
									lblSavActBal.setText("$"
											+ Double.toString(Input.getBalance(
													getAccountNumSavings(
															Input.username))));
								} catch (SQLException e1) { // TODO
															// Auto-generated
															// catch block
									e1.printStackTrace();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}

					if (comboBox.getSelectedItem().toString() == "Withdraw") {
						if (accountType == "Checking") {
							try {
								Transaction.withdrawFunds(transAmount,
										getAccountNumChecking(Input.username));

								if (Transaction.noFunds == false) {

									Input.createTransaction(
											getUserAccountNum(Input.username),
											transAmount, "Withdraw",
											accountType);
								}
								if (Transaction.noFunds == true) {
									JOptionPane.showMessageDialog(null,
											"Insufficient Funds");
								}

								try {
									curChkActBal.setText("$"
											+ Double.toString(Input.getBalance(
													getAccountNumChecking(
															Input.username))));
								} catch (SQLException e1) { // TODO
															// Auto-generated
															// catch block
									e1.printStackTrace();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						if (accountType == "Savings") {
							try {
								Transaction.withdrawFunds(transAmount,
										getAccountNumSavings(Input.username));
								if (Transaction.noFunds == false) {
									Input.createTransaction(
											getUserAccountNum(Input.username),
											transAmount, "Withdraw",
											accountType);

								}
								if (Transaction.noFunds == true) {
									JOptionPane.showMessageDialog(null,
											"Insufficient Funds");
								}
								try {
									lblSavActBal.setText("$"
											+ Double.toString(Input.getBalance(
													getAccountNumSavings(
															Input.username))));
								} catch (SQLException e1) { // TODO
															// Auto-generated
															// catch block
									e1.printStackTrace();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}

				transactionAmount.setText(null);

			}
		});
		button.setActionCommand("OK");

		JButton btnNewButton = new JButton("View Account Details");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				detailsPannel.setVisible(true);

				displayBalance();

			}
		});
		btnNewButton.setBounds(87, 89, 163, 23);
		contentPanel.add(btnNewButton);
	}

	@SuppressWarnings("unchecked")
	public int getUserAccountNum(String user) throws SQLException {
		Database database = new Database();
		Connection dbConn = database.getConnection();
		Statement stmt = null;
		int iD = 0;

		try {
			String sql = "SELECT ID FROM USERS WHERE USERNAME = ?";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setString(1, user);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				iD = rs.getInt("ID");
			}

		} catch (SQLException e) {
			System.out
					.println("------------------TableInsert-----------------");
			System.out.println("Cannot insert into table: " + e);
			System.out.println(
					"--------------------------------------------------------");
		} finally {

			dbConn.close();

		}
		return iD;
	}

	public int getAccountNumSavings(String user) throws SQLException {
		ArrayList<Integer> num = new ArrayList<Integer>();
		Database database = new Database();
		Connection dbConn = database.getConnection();
		Statement stmt = null;
		int nothing = 0;

		int largest;

		try {
			String sql = "SELECT ID FROM ACCOUNTS WHERE USERNAME = ? AND ACCOUNTTYPE = ?";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setString(1, user);
			pst.setString(2, "Savings");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				num.add(rs.getInt("ID"));
			}

		} catch (SQLException e) {
			System.out
					.println("------------------TableInsert-----------------");
			System.out.println("Cannot insert into table: " + e);
			System.out.println(
					"--------------------------------------------------------");
		} finally {

			dbConn.close();

		}
		if (num.isEmpty()) {
			return nothing;
		} else {

			return largest = Collections.max(num);
		}

	}

	public int getAccountNumChecking(String user) throws SQLException {
		ArrayList<Integer> num = new ArrayList<Integer>();

		Database database = new Database();
		Connection dbConn = database.getConnection();

		int nothing = 0;

		int largest;

		try {
			String sql = "SELECT ID FROM ACCOUNTS WHERE USERNAME = ? AND ACCOUNTTYPE = ?";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setString(1, user);
			pst.setString(2, "Checking");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				num.add(rs.getInt("ID"));
			}

		} catch (SQLException e) {
			System.out
					.println("------------------TableInsert-----------------");
			System.out.println("Cannot insert into table: " + e);
			System.out.println(
					"--------------------------------------------------------");
		} finally {

			dbConn.close();
		}

		if (num.isEmpty()) {
			return nothing;
		} else {

			return largest = Collections.max(num);
		}

	}

	public DefaultTableModel buildTableModelChecking() throws SQLException {
		Database database = new Database();
		Connection dbConn = database.getConnection();
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		try {
			String sql = "SELECT * FROM TRANSACTIONS WHERE ACCOUNT_NUM = ? AND ACCOUNT_TYPE = ? ";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setInt(1, getUserAccountNum(Input.username));
			pst.setString(2, "Checking");
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


	public DefaultTableModel buildTableModelSavings() throws SQLException {
		Database database = new Database();
		Connection dbConn = database.getConnection();
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		try {
			String sql = "SELECT * FROM TRANSACTIONS WHERE ACCOUNT_NUM = ? AND ACCOUNT_TYPE = ? ";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setInt(1, getUserAccountNum(Input.username));
			pst.setString(2, "Savings");
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

	public void displayBalance() {
		try {
			curChkActBal.setText("$" + Double.toString(
					Input.getBalance(getAccountNumChecking(Input.username))));
		} catch (SQLException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			lblSavActBal.setText("$" + Double.toString(
					Input.getBalance(getAccountNumSavings(Input.username))));
		} catch (SQLException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

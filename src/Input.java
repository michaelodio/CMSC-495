import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Input extends JFrame {

	private static JPanel contentPane;
	private JTextField UserNameTextField;
	private JPasswordField passwordField;
	private JTextField userNametextField_1;
	private JTextField passwordtextField_2;
	private JTextField emailtextField_3;
	private JTextField firstnametextField_4;
	private JTextField lastnametextField_5;
	private JTextField phonetextField_6;

	static String username = "";
	static String accountType = "";
	static int accountNum = 0;


	String password = "";
	String emailAddress = "";
	String fName = "";
	String lName = "";
	long phoneNum = 0;

	public Input() {
		setTitle("G7 Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 559, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 24, 114, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(10, 77, 114, 14);
		contentPane.add(lblPassword);

		UserNameTextField = new JTextField();
		UserNameTextField.setBounds(113, 24, 147, 20);
		contentPane.add(UserNameTextField);
		UserNameTextField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(113, 77, 147, 20);
		contentPane.add(passwordField);

		JPanel registerPanel = new JPanel();
		registerPanel.setBounds(286, 0, 257, 250);
		contentPane.add(registerPanel);
		registerPanel.setLayout(null);
		registerPanel.setVisible(false);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 11, 64, 14);
		registerPanel.add(lblUsername);

		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(10, 36, 64, 14);
		registerPanel.add(lblPassword_1);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 61, 64, 14);
		registerPanel.add(lblEmail);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 89, 64, 14);
		registerPanel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 114, 64, 14);
		registerPanel.add(lblLastName);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 139, 92, 14);
		registerPanel.add(lblPhoneNumber);

		JLabel lblAccountType = new JLabel("Account Type:");
		lblAccountType.setBounds(10, 168, 80, 14);
		registerPanel.add(lblAccountType);

		userNametextField_1 = new JTextField();
		userNametextField_1.setBounds(103, 8, 144, 20);
		registerPanel.add(userNametextField_1);
		userNametextField_1.setColumns(10);

		passwordtextField_2 = new JTextField();
		passwordtextField_2.setColumns(10);
		passwordtextField_2.setBounds(103, 33, 144, 20);
		registerPanel.add(passwordtextField_2);

		emailtextField_3 = new JTextField();
		emailtextField_3.setColumns(10);
		emailtextField_3.setBounds(103, 58, 144, 20);
		registerPanel.add(emailtextField_3);

		firstnametextField_4 = new JTextField();
		firstnametextField_4.setColumns(10);
		firstnametextField_4.setBounds(103, 86, 144, 20);
		registerPanel.add(firstnametextField_4);

		lastnametextField_5 = new JTextField();
		lastnametextField_5.setColumns(10);
		lastnametextField_5.setBounds(103, 111, 144, 20);
		registerPanel.add(lastnametextField_5);

		phonetextField_6 = new JTextField();
		phonetextField_6.setColumns(10);
		phonetextField_6.setBounds(103, 136, 144, 20);
		registerPanel.add(phonetextField_6);

		JComboBox accountTypebox = new JComboBox();
		accountTypebox.setModel(
				new DefaultComboBoxModel(new String[]{"Checking", "Savings"}));
		accountTypebox.setBounds(103, 164, 144, 22);
		registerPanel.add(accountTypebox);

		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(e -> {

			StringBuilder warnings = new StringBuilder();

			if (userNametextField_1.getText().isEmpty()) {
				warnings.append("Username must not be empty\n");

			} else {

				username = userNametextField_1.getText();

			}

			if (passwordtextField_2.getText().isEmpty()) {
				warnings.append("Password must not be empty\n");
			} else {
				password = passwordtextField_2.getText();
			}
			if (emailtextField_3.getText().isEmpty()) {
				warnings.append("Email must not be empty\n");
			} else {
				emailAddress = emailtextField_3.getText();
			}
			if (firstnametextField_4.getText().isEmpty()) {
				warnings.append("First Name must not be empty\n");
			} else {
				fName = firstnametextField_4.getText();
			}
			if (lastnametextField_5.getText().isEmpty()) {
				warnings.append("Email must not be empty\n");
			} else {
				lName = lastnametextField_5.getText();
			}
			if (phonetextField_6.getText().isEmpty()) {
				warnings.append("Phone number must not be empty\n");
			} else {
				try {
					String temp = phonetextField_6.getText();
					String temp2 = temp.replaceAll("[()\\s-]+", "");
					phoneNum = Long.parseLong(temp2);

				} catch (NumberFormatException ex) {
					warnings.append("Phone Number must be a number");
				}
			}

			if (accountTypebox.getSelectedItem().toString() == "Checking") {
				accountType = "Checking";
			} else if (accountTypebox.getSelectedItem()
					.toString() == "Savings") {
				accountType = "Savings";
			}
			if (warnings.length() > 0) {
				JOptionPane.showMessageDialog(null, warnings.toString());
			} else {

				try {
					createUserAccount();
					createNewAccount(Input.username, accountType, 0);

					JOptionPane.showMessageDialog(null,
							"Account Created! Please Login with your Username and Password!");
					registerPanel.setVisible(false);

				} catch (SQLException ex) {
					System.out.println(
							"------------------AccountCreate-----------------");
					System.out.println("Cannot create new account: " + ex);
					System.out.println(
							"--------------------------------------------------------");
				}
			}

		});

		btnNewButton.setBounds(10, 216, 237, 23);
		registerPanel.add(btnNewButton);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {

			String pw = new String(passwordField.getPassword());

			if (login(UserNameTextField.getText(), pw)) {

				Input.username = UserNameTextField.getText();
				try {
					getAccountType();
					getAccountNum(username);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					close();
					Display dialog = new Display();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					// DEBUG for interest testing



					Date currentDate = new Date();
					Date lastTransaction = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(getLastInterestDate(accountNum));


					//if it has been more than 30 days since the last interest calculation
					if ((currentDate.getTime() - lastTransaction.getTime()) / 86400000 > 30 ){

						new Transaction(accountNum, 0d, "interest", accountType);
						System.out.println("calculated interest!");

					}


				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Incorrect username or password");
			}
		});

		btnLogin.setBounds(10, 150, 89, 23);
		contentPane.add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(e -> registerPanel.setVisible(true));
		btnRegister.setBounds(171, 150, 89, 23);
		contentPane.add(btnRegister);

	}

	public void createUserAccount() throws SQLException {
		new Account(username, password, emailAddress, fName, lName, phoneNum);
	}

	public static void createNewAccount(String user, String accountType,
			double balance) {
		new Account(user, accountType, balance);
	}

	boolean login(String username, String password) {
		Login login = new Login(username, password);
		try {
			if (login.checkCredentials()) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getAccountType() throws SQLException {
		Database database = new Database();
		Connection dbConn = database.getConnection();
		ResultSet rs = null;

		try {
			String sql = "SELECT ACCOUNTTYPE FROM ACCOUNTS WHERE USERNAME = ? ";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();

			while (rs.next()) {

				accountType = rs.getString("ACCOUNTTYPE");
			}
		} finally {
			dbConn.close();
		}

		return accountType;
	}

	public String getLastInterestDate(int accountNum) throws SQLException {

		Database database = new Database();
		Connection dbConn = database.getConnection();
		String transactionTime = "";

		try {
			String sql = "SELECT TRANSACTION_TIME FROM TRANSACTIONS WHERE TRANSACTION_TYPE = ? ORDER BY TRANSACTION_ID DESC LIMIT 1";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setString(1, "interest");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				transactionTime = rs.getString("TRANSACTION_TIME");
			}

			System.out.println("Last Transaction Time: " + transactionTime);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			dbConn.close();
		}

		return transactionTime;


	}

	public int getAccountNum(String user) throws SQLException {

		Database database = new Database();
		Connection dbConn = database.getConnection();

		try {
			String sql = "SELECT ID FROM ACCOUNTS WHERE USERNAME = ?";
			PreparedStatement pst = dbConn.prepareStatement(sql);
			pst.setString(1, user);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				accountNum = rs.getInt("ID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			dbConn.close();
		}

		return accountNum;
	}
	// searches the database for an account number and returns the balance
	public static double getBalance(int accountNumber) throws SQLException {
		Database database = new Database();
		Connection dbConn = database.getConnection();
		double balance = 0d;

		Statement stmt = null;

		try {
			stmt = dbConn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select BALANCE " + "FROM ACCOUNTS "
							+ " WHERE ID =" + String.valueOf(accountNumber));
			while (rs.next()) {
				balance = rs.getDouble("BALANCE");
			}
		} catch (SQLException e) {
			System.out
					.println("------------------TableInsert-----------------");
			System.out.println("Cannot insert into table: " + e);
			System.out.println(
					"--------------------------------------------------------");
		} finally {
			if (stmt != null) {
				stmt.close();
				dbConn.close();
			}
		}
		return balance;
	}

	// searches database for the desired account number and sets the balance to
	// the given amount
	public static void setBalance(double balance, int accountNumber)
			throws SQLException {
		Database database = new Database();
		Connection dbConn = database.getConnection();

		Statement insertStmt = null;

		try {
			insertStmt = dbConn.createStatement();
			insertStmt.executeUpdate("UPDATE Accounts " + "SET balance = "
					+ String.valueOf(balance) + " WHERE ID="
					+ String.valueOf(accountNumber));
		} catch (SQLException e) {
			System.out
					.println("------------------TableInsert-----------------");
			System.out.println(
					"Cannot insert into Accounts table (setBalance): Account Number: "
							+ accountNumber + ", Balance: " + balance + " "
							+ e);
			System.out.println(
					"--------------------------------------------------------");
		} finally {
			if (insertStmt != null) {
				insertStmt.close();
				dbConn.close();
			}
		}
	}

	public static void createTransaction(int accountNumber, double amount,
			String transactionType, String accountType) throws SQLException {

		Transaction transaction = new Transaction(accountNumber, amount,
				transactionType, accountType);

	}

	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

}

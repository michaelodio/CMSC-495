package proto2;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    String password = "";
    String emailAddress = "";
    String fName = "";
    String lName = "";
    int phoneNum = 0;
    String accountType = "";

    public Input() {
        setTitle("G7 Bank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        accountTypebox.setModel(new DefaultComboBoxModel(new String[]{"Checking", "Savings"}));
        accountTypebox.setBounds(103, 164, 144, 22);
        registerPanel.add(accountTypebox);

        JButton btnNewButton = new JButton("Create Account");
        btnNewButton.addActionListener(e -> {

            StringBuilder warnings = new StringBuilder();

            if (userNametextField_1.getText().isEmpty()) {
                warnings.append("Username must not be empty\n");

            } else
				try {
					if(uniqueUser(userNametextField_1.getText()) != false){

						warnings.append("Username already exists\n");
					}
					else {
					    username = userNametextField_1.getText();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
                    phoneNum = Integer.parseInt(phonetextField_6.getText());
                } catch (NumberFormatException ex) {
                    warnings.append("Phone Number must be a number");
                }
            }

            if (accountTypebox.getSelectedItem().toString() == "Checking") {
                accountType = "Checking";
            } else if (accountTypebox.getSelectedItem().toString() == "Savings") {
                accountType = "Savings";
            }
            if (warnings.length() > 0) {
                JOptionPane.showMessageDialog(null, warnings.toString());
            } else {

                try {
                    createUserAccount();
                    JOptionPane.showMessageDialog(null, "Account Created! Please Login with your Username and Password!");
                    registerPanel.setVisible(false);

                } catch (SQLException ex) {
                    System.out.println("------------------AccountCreate-----------------");
                    System.out.println("Cannot create new account: " + ex);
                    System.out.println("--------------------------------------------------------");
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

                    Display dialog = new Display();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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

    public static void createNewAccount(String user,String accountType, double balance) {
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

    public boolean uniqueUser(String user) throws SQLException {
    	Database database = new Database();
        Connection dbConn = database.getConnection();
        Statement stmt = null;
        boolean foundUser = false;
        ArrayList<String> temp = new ArrayList<String>();


        	try {
                String sql = "SELECT Username FROM Users WHERE USERNAME = ?";
                PreparedStatement pst = dbConn.prepareStatement(sql);
                pst.setString(1, user);
                ResultSet rs = pst.executeQuery();

                while (rs.next()){

                	temp.add(rs.getString("USERNAME"));
                	}

                if(temp.contains(user)) {
                	foundUser = true;
                }



        	}catch (SQLException e) {
                    System.out.println("------------------TableInsert-----------------");
                    System.out.println("Cannot insert into table2: " + e);
                    System.out.println("--------------------------------------------------------");
                } finally {
                    if (stmt != null) {
                        stmt.close();
                        dbConn.close();
                    }
                }
                return foundUser;

    }





  //searches the database for an account number and returns the balance
    public static double getBalance(int accountNumber) throws SQLException {
        Database database = new Database();
        Connection dbConn = database.getConnection();
        double balance = 0d;

        Statement stmt = null;

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select BALANCE " +
                            "FROM ACCOUNTS " +
                            " WHERE ID = " + accountNumber );
            while (rs.next()){
                balance = rs.getDouble("BALANCE");
            }
        } catch (SQLException e) {
            System.out.println("------------------TableInsert-----------------");
            System.out.println("Cannot insert into table2: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (stmt != null) {
                stmt.close();
                dbConn.close();
            }
        }
        return balance;
    }

    //searches database for the desired account number and sets the balance to the given amount
    public static void setBalance(double balance, int accountNumber) throws SQLException {
        Database database = new Database();
        Connection dbConn = database.getConnection();

        Statement insertStmt = null;

        try {

        	  String sql = "UPDATE Accounts SET Balance = ? WHERE ID = ?";
              PreparedStatement pst = dbConn.prepareStatement(sql);
              pst.setDouble(1, balance);
              pst.setInt(2, accountNumber);
              pst.executeUpdate();




        } catch (SQLException e) {
            System.out.println("------------------TableInsert-----------------");
            System.out.println("Cannot insert into table1: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }


}











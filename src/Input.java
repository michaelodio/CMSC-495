package proto2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
		accountTypebox.setModel(new DefaultComboBoxModel(new String[] {"Checking", "Savings"}));
		accountTypebox.setBounds(103, 164, 144, 22);
		registerPanel.add(accountTypebox);

		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				StringBuilder warnings = new StringBuilder();

				if(userNametextField_1.getText().isEmpty()) {
					warnings.append("Username must not be empty\n");

				} else {
					username = userNametextField_1.getText();
			        }

				if(passwordtextField_2.getText().isEmpty()) {
					warnings.append("Password must not be empty\n");
				} else {
			            password = passwordtextField_2.getText();
			        }
				if(emailtextField_3.getText().isEmpty()) {
					warnings.append("Email must not be empty\n");
				} else {
					emailAddress = emailtextField_3.getText();
			        }
				if(firstnametextField_4.getText().isEmpty()) {
					warnings.append("First Name must not be empty\n");
				} else {
			            fName = firstnametextField_4.getText();
			        }
				if(lastnametextField_5.getText().isEmpty()) {
					warnings.append("Email must not be empty\n");
				} else {
			            lName = lastnametextField_5.getText();
			        }
				if(phonetextField_6.getText().isEmpty()) {
					warnings.append("Phone number must not be empty\n");
				} else {
					try {
						phoneNum = Integer.parseInt(phonetextField_6.getText());
					}catch(NumberFormatException ex) {
						warnings.append("Phone Number must be a number");
					}

				}

			    if (accountTypebox.getSelectedItem().toString() == "Checking") {
			    	accountType = "Checking";
			    }
			    else if (accountTypebox.getSelectedItem().toString() == "Savings") {
			    	accountType = "Savings";
			    }
				if(warnings.length()>0) {
					JOptionPane.showMessageDialog(null, warnings.toString());
				}else {

			    try {
                    createUserAccount(accountType);
                    JOptionPane.showMessageDialog(null, "Account Created! Please Login with your Username and Password!");
                    registerPanel.setVisible(false);

                } catch (SQLException ex) {
                    System.out.println("------------------AccountCreate-----------------");
                    System.out.println("Cannot create new account: " + ex);
                    System.out.println("--------------------------------------------------------");
                }
				}

			}
		});

		btnNewButton.setBounds(10, 216, 237, 23);
		registerPanel.add(btnNewButton);


		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String pw = new String(passwordField.getPassword());

				if (login(UserNameTextField.getText(), pw)) {

					try {
						Display dialog = new Display();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}



                }

			}
		});


		btnLogin.setBounds(10, 150, 89, 23);
		contentPane.add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 registerPanel.setVisible(true);

			}
		});
		btnRegister.setBounds(171, 150, 89, 23);
		contentPane.add(btnRegister);
	}


	 void createUserAccount(String accountType) throws SQLException {
	        new Account(username, password, emailAddress, fName, lName, phoneNum);
	    }

	    static void createNewAccount(String accountType, double balance){
	        new Account(username, accountType, balance);
	    }

	    boolean login(String username, String password){
	        Login login = new Login(username, password);
	        try {
	            if (login.checkCredentials()){
	                return true;
	            }else{
	                return false;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}

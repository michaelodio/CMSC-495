/*
 * Michael Odio
 *
 * 9/27/2018
 *
 */

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Registration extends JFrame {

	Connection connection = null;

	private JPanel contentPane;
	private JTextField fNameTxtField;
	private JTextField lNameTxtField;
	private JTextField userNameTxtField;
	private JTextField passwordTxtField;
	private JTextField phoneNumberTxtField;
	private JTextField emailTxtField;
	private JLabel lblBankRegistration;
	private JButton btnRegister;
	private JButton btnReturn;

	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

	/**
	 * Create the frame.
	 */
	public Registration() {
		connection = sqliteConnection.dbConnector();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 811, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblfName = new JLabel("First Name:");
		lblfName.setBounds(10, 66, 73, 14);
		contentPane.add(lblfName);

		JLabel lName = new JLabel("Last Name:");
		lName.setBounds(10, 106, 91, 14);
		contentPane.add(lName);

		JLabel userName = new JLabel("Create Username:");
		userName.setBounds(10, 142, 104, 14);
		contentPane.add(userName);

		JLabel lblpassword = new JLabel("Create Password:");
		lblpassword.setBounds(10, 177, 104, 14);
		contentPane.add(lblpassword);

		JLabel lblPhoneNumber = new JLabel("Phone Number: ");
		lblPhoneNumber.setBounds(10, 215, 91, 14);
		contentPane.add(lblPhoneNumber);

		JLabel lblEmailAddress = new JLabel("Email: ");
		lblEmailAddress.setBounds(10, 252, 46, 14);
		contentPane.add(lblEmailAddress);

		fNameTxtField = new JTextField();
		fNameTxtField.setBounds(124, 63, 172, 20);
		contentPane.add(fNameTxtField);
		fNameTxtField.setColumns(10);

		lNameTxtField = new JTextField();
		lNameTxtField.setColumns(10);
		lNameTxtField.setBounds(124, 103, 172, 20);
		contentPane.add(lNameTxtField);

		userNameTxtField = new JTextField();
		userNameTxtField.setColumns(10);
		userNameTxtField.setBounds(124, 139, 172, 20);
		contentPane.add(userNameTxtField);

		passwordTxtField = new JTextField();
		passwordTxtField.setColumns(10);
		passwordTxtField.setBounds(124, 171, 172, 20);
		contentPane.add(passwordTxtField);

		phoneNumberTxtField = new JTextField();
		phoneNumberTxtField.setColumns(10);
		phoneNumberTxtField.setBounds(124, 212, 172, 20);
		contentPane.add(phoneNumberTxtField);

		emailTxtField = new JTextField();
		emailTxtField.setColumns(10);
		emailTxtField.setBounds(124, 249, 172, 20);
		contentPane.add(emailTxtField);

		lblBankRegistration = new JLabel("Bank Registration");
		lblBankRegistration.setBounds(366, 11, 115, 14);
		contentPane.add(lblBankRegistration);

		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into Users (FirstName,LastName,Username,Password,Email,Phone) values (?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,fNameTxtField.getText());
					pst.setString(2,lNameTxtField.getText());
					pst.setString(3,userNameTxtField.getText());
					pst.setString(4,passwordTxtField.getText());
					pst.setString(5,emailTxtField.getText());
					pst.setString(6,phoneNumberTxtField.getText());

					pst.execute();

					JOptionPane.showMessageDialog(null,"Account Created");

					pst.close();



				}catch(Exception p) {
					p.printStackTrace();
				}

			}
		});
		btnRegister.setBounds(45, 316, 231, 23);
		contentPane.add(btnRegister);

		btnReturn = new JButton("Return to Login");
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {

                    close();
					LoginPane frame = new LoginPane();
					frame.setVisible(true);
				} catch (Exception d) {
					d.printStackTrace();
				}
			}


		});
		btnReturn.setBounds(366, 316, 216, 23);
		contentPane.add(btnReturn);
	}

}

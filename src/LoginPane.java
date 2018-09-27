import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPane extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnRegister;

	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					LoginPane frame = new LoginPane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}



	/**
	 * Create the frame.
	 */
	public LoginPane() {
		connection = sqliteConnection.dbConnector();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel userName = new JLabel("UserName");
		userName.setBounds(208, 98, 76, 14);
		contentPane.add(userName);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(210, 197, 59, 14);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(294, 88, 326, 34);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(294, 187, 326, 34);
		contentPane.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {



				try {
					int count = 0;
					String query = "select * from Users where Username=? and Password=? ";
					PreparedStatement pst = connection.prepareStatement(query);

					String pw = new String(passwordField.getPassword());

					pst.setString(1,textField.getText());
					pst.setString(2,pw);

					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						count++;

					}
					if(count == 1) {
						JOptionPane.showMessageDialog(null, "Login success");
						try {
							close();
							GenDisplay frame = new GenDisplay();
							frame.setVisible(true);
						} catch (Exception d) {
							d.printStackTrace();
						}

					}
					else {
					JOptionPane.showMessageDialog(null, "Failed to Login");
					}
					rs.close();
					pst.close();

				}catch(Exception f) {
					JOptionPane.showMessageDialog(null,f);
				}
			}
		});
		btnLogin.setBounds(294, 283, 120, 23);
		contentPane.add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					close();
					Registration frame = new Registration();
					frame.setVisible(true);
				} catch (Exception f) {
					f.printStackTrace();
				}

			}
		});
		btnRegister.setBounds(490, 283, 106, 23);
		contentPane.add(btnRegister);
	}
}

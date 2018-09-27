import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GenDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField depositTextField;
	private JTextField withdrawTextField;


	/**
	 * Create the frame.
	 */
	public GenDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(287, 98, 89, 23);
		contentPane.add(btnDeposit);

		JButton btnWithdraw = new JButton("Withdraw ");
		btnWithdraw.setBounds(287, 132, 89, 23);
		contentPane.add(btnWithdraw);

		depositTextField = new JTextField();
		depositTextField.setBounds(10, 99, 267, 20);
		contentPane.add(depositTextField);
		depositTextField.setColumns(10);

		withdrawTextField = new JTextField();
		withdrawTextField.setColumns(10);
		withdrawTextField.setBounds(10, 133, 267, 20);
		contentPane.add(withdrawTextField);
	}

}

package proto2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Display extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField balanceTxtField;
	double balance = 0;
	String accountType ="";


	public Display() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSelectAccountType = new JLabel("Select account type:");
			lblSelectAccountType.setBounds(10, 11, 113, 14);
			contentPanel.add(lblSelectAccountType);
		}
		{
			JLabel lblEnterInitialBalance = new JLabel("Enter initial balance:");
			lblEnterInitialBalance.setBounds(10, 60, 97, 14);
			contentPanel.add(lblEnterInitialBalance);
		}
		{
			balanceTxtField = new JTextField();
			balanceTxtField.setBounds(122, 57, 195, 20);
			contentPanel.add(balanceTxtField);
			balanceTxtField.setColumns(10);
		}

		JRadioButton rdbtnChecking = new JRadioButton("Checking");
		rdbtnChecking.setBounds(122, 7, 109, 23);
		contentPanel.add(rdbtnChecking);

		JRadioButton rdbtnSavings = new JRadioButton("Savings");
		rdbtnSavings.setBounds(233, 7, 109, 23);
		contentPanel.add(rdbtnSavings);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						StringBuilder warnings = new StringBuilder();

						if(balanceTxtField.getText().isEmpty()) {
							warnings.append("Balance must not be empty\n");
						} else {
							try {
								balance = Integer.parseInt(balanceTxtField.getText());
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

							Input.createNewAccount(accountType, balance);
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	/*
	JButton btnNewButton = new JButton("Execute");
	btnNewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			StringBuilder warnings = new StringBuilder();

			if(balanceTxtField.getText().isEmpty()) {
				warnings.append("Balance must not be empty\n");
			} else {
				try {
					balance = Integer.parseInt(balanceTxtField.getText());
				}catch(NumberFormatException ex) {
					warnings.append("Balance must be a number");
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
				}
				else {
			 createNewAccount(accountType, balance);
				}

		});

	btnNewButton.setBounds(155, 128, 89, 23);
	contentPanel.add(btnNewButton);

*/

	 public void displayTransactionHist(){
	        /*
	        TODO
	        pull transactions from database and display in GUI;
	        */
	    }
	    public void displayBalance(int accountNum){
	        /*
	        TODO
	        pull balance from database and display in GUI;
	        */
	    }
	}



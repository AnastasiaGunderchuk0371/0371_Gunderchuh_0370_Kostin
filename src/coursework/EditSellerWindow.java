package coursework;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class EditSellerWindow extends JFrame{
	Container mainBox;
	JPanel panel1, panel2, panel3;
	JLabel FIOLabel, birthDateLabel, salaryLabel, startDataLabel;
	JTextField FIOField, birthDateField, salaryField;
	JButton editSeller;
	SpringLayout layout, layout1, layout2, layout3;
	int selectEditableRow;

	public EditSellerWindow(DefaultTableModel sellersModel, JFrame shop, int[] selectedRows) {
		this.setTitle("��������� ��������");
		this.setBounds(200, 100, 500, 270);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setResizable(false);

		//����� ������ ������ ��� ���������
		selectEditableRow = 0;
		
		// �������� ��������
		FIOLabel = new JLabel("������� ���:");
		birthDateLabel = new JLabel("������� ���� ��������:");
		salaryLabel = new JLabel("������� ��������:");
		startDataLabel = new JLabel("<html>���: " + sellersModel.getValueAt(selectedRows[selectEditableRow], 0) + 
				"<br>���� ��������: " + sellersModel.getValueAt(selectedRows[selectEditableRow], 1) + 
				"<br>��������: " + sellersModel.getValueAt(selectedRows[selectEditableRow], 2) + 
				"<html>");

		// �������� ����� ��� �����
		FIOField = new JTextField();
		birthDateField = new JTextField();
		salaryField = new JTextField();

		// �������� ������ ����������
		editSeller = new JButton("��������");
		
		

		// ���������� ���������
		mainBox = this.getContentPane();
		layout = new SpringLayout();
		layout1 = new SpringLayout();
		layout2 = new SpringLayout();
		layout3 = new SpringLayout();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		mainBox.setLayout(layout);
		panel1.setLayout(layout1);
		panel2.setLayout(layout2);
		panel3.setLayout(layout3);
		panel1.add(FIOLabel);
		panel1.add(FIOField);
		panel2.add(birthDateLabel);
		panel2.add(birthDateField);
		panel3.add(salaryLabel);
		panel3.add(salaryField);
		mainBox.add(startDataLabel);
		mainBox.add(panel1);
		mainBox.add(panel2);
		mainBox.add(panel3);
		mainBox.add(editSeller);
		//���������� stardDataLabel
		layout.putConstraint(SpringLayout.WEST, startDataLabel, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, startDataLabel, -5, SpringLayout.EAST, mainBox);
		layout.putConstraint(SpringLayout.NORTH, startDataLabel, 10, SpringLayout.NORTH, mainBox);
		layout.putConstraint(SpringLayout.SOUTH, startDataLabel, 50, SpringLayout.NORTH, startDataLabel);
		// ���������� panel1
		layout.putConstraint(SpringLayout.WEST, panel1, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, panel1, -5, SpringLayout.EAST, mainBox);
		layout.putConstraint(SpringLayout.NORTH, panel1, 10, SpringLayout.SOUTH, startDataLabel);
		layout.putConstraint(SpringLayout.SOUTH, panel1, 35, SpringLayout.NORTH, panel1);
		// ���������� panel2
		layout.putConstraint(SpringLayout.NORTH, panel2, 10, SpringLayout.SOUTH, panel1);
		layout.putConstraint(SpringLayout.SOUTH, panel2, 35, SpringLayout.NORTH, panel2);
		layout.putConstraint(SpringLayout.WEST, panel2, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, panel2, -5, SpringLayout.EAST, mainBox);
		// ���������� panel3
		layout.putConstraint(SpringLayout.NORTH, panel3, 10, SpringLayout.SOUTH, panel2);
		layout.putConstraint(SpringLayout.SOUTH, panel3, 35, SpringLayout.NORTH, panel3);
		layout.putConstraint(SpringLayout.WEST, panel3, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, panel3, -5, SpringLayout.EAST, mainBox);
		// ���������� ������ addSeller
		layout.putConstraint(SpringLayout.EAST, editSeller, -175, SpringLayout.EAST, mainBox);
		layout.putConstraint(SpringLayout.SOUTH, editSeller, -10, SpringLayout.SOUTH, mainBox);
		layout.putConstraint(SpringLayout.WEST, editSeller, 175, SpringLayout.WEST, mainBox);
		// ���������� ������ panel1
		layout1.putConstraint(SpringLayout.WEST, FIOLabel, 5, SpringLayout.WEST, panel1);
		layout1.putConstraint(SpringLayout.NORTH, FIOLabel, 5, SpringLayout.NORTH, panel1);
		layout1.putConstraint(SpringLayout.SOUTH, FIOLabel, -5, SpringLayout.SOUTH, panel1);
		layout1.putConstraint(SpringLayout.NORTH, FIOField, 5, SpringLayout.NORTH, panel1);
		layout1.putConstraint(SpringLayout.EAST, FIOField, -30, SpringLayout.EAST, panel1);
		layout1.putConstraint(SpringLayout.SOUTH, FIOField, -5, SpringLayout.SOUTH, panel1);
		layout1.putConstraint(SpringLayout.WEST, FIOField, 170, SpringLayout.WEST, panel1);
		// ���������� ������ panel2
		layout2.putConstraint(SpringLayout.WEST, birthDateLabel, 5, SpringLayout.WEST, panel2);
		layout2.putConstraint(SpringLayout.NORTH, birthDateLabel, 5, SpringLayout.NORTH, panel2);
		layout2.putConstraint(SpringLayout.SOUTH, birthDateLabel, -5, SpringLayout.SOUTH, panel2);
		layout2.putConstraint(SpringLayout.NORTH, birthDateField, 5, SpringLayout.NORTH, panel2);
		layout2.putConstraint(SpringLayout.EAST, birthDateField, -30, SpringLayout.EAST, panel2);
		layout2.putConstraint(SpringLayout.SOUTH, birthDateField, -5, SpringLayout.SOUTH, panel2);
		layout2.putConstraint(SpringLayout.WEST, birthDateField, 170, SpringLayout.WEST, panel2);
		// ���������� ������ panel3
		layout3.putConstraint(SpringLayout.WEST, salaryLabel, 5, SpringLayout.WEST, panel3);
		layout3.putConstraint(SpringLayout.NORTH, salaryLabel, 5, SpringLayout.NORTH, panel3);
		layout3.putConstraint(SpringLayout.SOUTH, salaryLabel, -5, SpringLayout.SOUTH, panel3);
		layout3.putConstraint(SpringLayout.NORTH, salaryField, 5, SpringLayout.NORTH, panel3);
		layout3.putConstraint(SpringLayout.EAST, salaryField, -30, SpringLayout.EAST, panel3);
		layout3.putConstraint(SpringLayout.SOUTH, salaryField, -5, SpringLayout.SOUTH, panel3);
		layout3.putConstraint(SpringLayout.WEST, salaryField, 170, SpringLayout.WEST, panel3);

		// ���������� ����������
		editSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					checkName(FIOField);
					checkBirthDate(birthDateField);
					checkSalary(salaryField);
					sellersModel.setValueAt(FIOField.getText(), selectedRows[selectEditableRow], 0);
					sellersModel.setValueAt(birthDateField.getText(), selectedRows[selectEditableRow], 1);
					sellersModel.setValueAt(salaryField.getText(), selectedRows[selectEditableRow], 2);
					FIOField.setText("");
					birthDateField.setText("");
					salaryField.setText("");
					if(selectEditableRow + 1 < selectedRows.length) {
						selectEditableRow += 1;
						startDataLabel.setText("<html>���: " + sellersModel.getValueAt(selectedRows[selectEditableRow], 0) + 
								"<br>���� ��������: " + sellersModel.getValueAt(selectedRows[selectEditableRow], 1) + 
								"<br>��������: " + sellersModel.getValueAt(selectedRows[selectEditableRow], 2) + 
								"<html>");
					}
					else {
						dispose();
					}
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(shop, ex.toString());
				} catch (HaveDigit ex) {
					JOptionPane.showMessageDialog(shop, ex.toString());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(shop, ex.toString());
				}
			}
		});
		
		this.setVisible(true);
	}

	// �������� ���
	private void checkName(JTextField textField) throws HaveDigit, NullPointerException {
		String name = textField.getText();
		if (name.length() == 0)
			throw new NullPointerException();
		for (int i = 0; i < name.length(); ++i) {
			if (0 <= name.charAt(i) - '0' && name.charAt(i) - '0' <= 9) {
				throw new HaveDigit();
			}
		}
	}

	// �������� ���� ��������
	private void checkBirthDate(JTextField textField) throws NullPointerException {
		String birthDate = textField.getText();
		if (birthDate.length() == 0)
			throw new NullPointerException();
	}

	// �������� ��������
	private void checkSalary(JTextField textField) throws NullPointerException, NumberFormatException {
		String salary = textField.getText();
		if (salary.length() == 0)
			throw new NullPointerException();
		for (int i = 0; i < salary.length(); ++i) {
			if (salary.charAt(i) - '0' < 0 || salary.charAt(i) - '0' > 9) {
				throw new NumberFormatException();
			}
		}
	}
}

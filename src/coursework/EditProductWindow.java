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

public class EditProductWindow extends JFrame {
	Container mainBox;
	JPanel panel1, panel2, panel3, panel4;
	JLabel productNameLabel, priceLabel, numLabel, soldLabel, startDataLabel;
	JTextField productNameField, priceField, numField, soldField;
	JButton addProduct;
	SpringLayout layout, layout1, layout2, layout3, layout4;
	int selectEditableRow;

	public EditProductWindow(DefaultTableModel productsModel, JFrame shop, int[] selectedRows) {
		this.setTitle("Изменение товара");
		this.setBounds(200, 100, 500, 345);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setResizable(false);
		
		//Выбор первой записи для изменения
		selectEditableRow = 0;

		// Создание надписей
		productNameLabel = new JLabel("Введите название:");
		priceLabel = new JLabel("Введите цену:");
		numLabel = new JLabel("<html>Введите кол-во<br>имеющихся товаров:</html>");
		soldLabel = new JLabel("<html>Введите кол-во<br>проданных товаров:</html>");
		startDataLabel = new JLabel("<html>Название: " + productsModel.getValueAt(selectedRows[selectEditableRow], 0) + 
				"<br>Цена: " + productsModel.getValueAt(selectedRows[selectEditableRow], 1) + 
				"<br>Кол-во имеющихся товаров: " + productsModel.getValueAt(selectedRows[selectEditableRow], 2) + 
				"<br>Кол-во проданных товаров: " + productsModel.getValueAt(selectedRows[selectEditableRow], 3) + 
				"<html>");

		// Создание полей для ввода
		productNameField = new JTextField();
		priceField = new JTextField();
		numField = new JTextField();
		soldField = new JTextField();

		// Создание кнопки добавления
		addProduct = new JButton("Изменить");

		// Размещение элементов
		mainBox = this.getContentPane();
		layout = new SpringLayout();
		layout1 = new SpringLayout();
		layout2 = new SpringLayout();
		layout3 = new SpringLayout();
		layout4 = new SpringLayout();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		mainBox.setLayout(layout);
		panel1.setLayout(layout1);
		panel2.setLayout(layout2);
		panel3.setLayout(layout3);
		panel4.setLayout(layout4);
		panel1.add(productNameLabel);
		panel1.add(productNameField);
		panel2.add(priceLabel);
		panel2.add(priceField);
		panel3.add(numLabel);
		panel3.add(numField);
		panel4.add(soldLabel);
		panel4.add(soldField);
		mainBox.add(startDataLabel);
		mainBox.add(panel1);
		mainBox.add(panel2);
		mainBox.add(panel3);
		mainBox.add(panel4);
		mainBox.add(addProduct);
		//Размещение startDataLabel
		layout.putConstraint(SpringLayout.WEST, startDataLabel, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, startDataLabel, -5, SpringLayout.EAST, mainBox);
		layout.putConstraint(SpringLayout.NORTH, startDataLabel, 10, SpringLayout.NORTH, mainBox);
		layout.putConstraint(SpringLayout.SOUTH, startDataLabel, 65, SpringLayout.NORTH, startDataLabel);
		// Размещение panel1
		layout.putConstraint(SpringLayout.WEST, panel1, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, panel1, -5, SpringLayout.EAST, mainBox);
		layout.putConstraint(SpringLayout.NORTH, panel1, 10, SpringLayout.SOUTH, startDataLabel);
		layout.putConstraint(SpringLayout.SOUTH, panel1, 35, SpringLayout.NORTH, panel1);
		// Размещение panel2
		layout.putConstraint(SpringLayout.NORTH, panel2, 10, SpringLayout.SOUTH, panel1);
		layout.putConstraint(SpringLayout.SOUTH, panel2, 35, SpringLayout.NORTH, panel2);
		layout.putConstraint(SpringLayout.WEST, panel2, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, panel2, -5, SpringLayout.EAST, mainBox);
		// Размещение panel3
		layout.putConstraint(SpringLayout.NORTH, panel3, 10, SpringLayout.SOUTH, panel2);
		layout.putConstraint(SpringLayout.SOUTH, panel3, 40, SpringLayout.NORTH, panel3);
		layout.putConstraint(SpringLayout.WEST, panel3, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, panel3, -5, SpringLayout.EAST, mainBox);
		//Размещение panel4
		layout.putConstraint(SpringLayout.NORTH, panel4, 10, SpringLayout.SOUTH, panel3);
		layout.putConstraint(SpringLayout.SOUTH, panel4, 40, SpringLayout.NORTH, panel4);
		layout.putConstraint(SpringLayout.WEST, panel4, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, panel4, -5, SpringLayout.EAST, mainBox);
		// Размещение кнопки addSeller
		layout.putConstraint(SpringLayout.EAST, addProduct, -175, SpringLayout.EAST, mainBox);
		layout.putConstraint(SpringLayout.SOUTH, addProduct, -10, SpringLayout.SOUTH, mainBox);
		layout.putConstraint(SpringLayout.WEST, addProduct, 175, SpringLayout.WEST, mainBox);
		// Размещение внутри panel1
		layout1.putConstraint(SpringLayout.WEST, productNameLabel, 5, SpringLayout.WEST, panel1);
		layout1.putConstraint(SpringLayout.NORTH, productNameLabel, 5, SpringLayout.NORTH, panel1);
		layout1.putConstraint(SpringLayout.SOUTH, productNameLabel, -5, SpringLayout.SOUTH, panel1);
		layout1.putConstraint(SpringLayout.NORTH, productNameField, 5, SpringLayout.NORTH, panel1);
		layout1.putConstraint(SpringLayout.EAST, productNameField, -30, SpringLayout.EAST, panel1);
		layout1.putConstraint(SpringLayout.SOUTH, productNameField, -5, SpringLayout.SOUTH, panel1);
		layout1.putConstraint(SpringLayout.WEST, productNameField, 170, SpringLayout.WEST, panel1);
		// Размещение внутри panel2
		layout2.putConstraint(SpringLayout.WEST, priceLabel, 5, SpringLayout.WEST, panel2);
		layout2.putConstraint(SpringLayout.NORTH, priceLabel, 5, SpringLayout.NORTH, panel2);
		layout2.putConstraint(SpringLayout.SOUTH, priceLabel, -5, SpringLayout.SOUTH, panel2);
		layout2.putConstraint(SpringLayout.NORTH, priceField, 5, SpringLayout.NORTH, panel2);
		layout2.putConstraint(SpringLayout.EAST, priceField, -30, SpringLayout.EAST, panel2);
		layout2.putConstraint(SpringLayout.SOUTH, priceField, -5, SpringLayout.SOUTH, panel2);
		layout2.putConstraint(SpringLayout.WEST, priceField, 170, SpringLayout.WEST, panel2);
		// Размещение внутри panel3
		layout3.putConstraint(SpringLayout.WEST, numLabel, 5, SpringLayout.WEST, panel3);
		layout3.putConstraint(SpringLayout.NORTH, numLabel, 5, SpringLayout.NORTH, panel3);
		layout3.putConstraint(SpringLayout.SOUTH, numLabel, -5, SpringLayout.SOUTH, panel3);
		layout3.putConstraint(SpringLayout.NORTH, numField, 5, SpringLayout.NORTH, panel3);
		layout3.putConstraint(SpringLayout.EAST, numField, -30, SpringLayout.EAST, panel3);
		layout3.putConstraint(SpringLayout.SOUTH, numField, -10, SpringLayout.SOUTH, panel3);
		layout3.putConstraint(SpringLayout.WEST, numField, 170, SpringLayout.WEST, panel3);
		//Размещение внутри panel4
		layout4.putConstraint(SpringLayout.WEST, soldLabel, 5, SpringLayout.WEST, panel4);
		layout4.putConstraint(SpringLayout.NORTH, soldLabel, 5, SpringLayout.NORTH, panel4);
		layout4.putConstraint(SpringLayout.SOUTH, soldLabel, -5, SpringLayout.SOUTH, panel4);
		layout4.putConstraint(SpringLayout.NORTH, soldField, 5, SpringLayout.NORTH, panel4);
		layout4.putConstraint(SpringLayout.EAST, soldField, -30, SpringLayout.EAST, panel4);
		layout4.putConstraint(SpringLayout.SOUTH, soldField, -10, SpringLayout.SOUTH, panel4);
		layout4.putConstraint(SpringLayout.WEST, soldField, 170, SpringLayout.WEST, panel4);

		// Добавление слушателей
		addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					checkProductName(productNameField);
					checkPrice(priceField);
					checkProductNum(numField);
					checkProductNum(soldField);
					productsModel.setValueAt(productNameField.getText(), selectedRows[selectEditableRow], 0);
					productsModel.setValueAt(priceField.getText(), selectedRows[selectEditableRow], 1);
					productsModel.setValueAt(numField.getText(), selectedRows[selectEditableRow], 2);
					productsModel.setValueAt(soldField.getText(), selectedRows[selectEditableRow], 3);
					productNameField.setText("");
					priceField.setText("");
					numField.setText("");
					soldField.setText("");
					if(selectEditableRow + 1 < selectedRows.length) {
						selectEditableRow += 1;
						startDataLabel.setText("<html>Название: " + productsModel.getValueAt(selectedRows[selectEditableRow], 0) + 
								"<br>Цена: " + productsModel.getValueAt(selectedRows[selectEditableRow], 1) + 
								"<br>Кол-во имеющихся товаров: " + productsModel.getValueAt(selectedRows[selectEditableRow], 2) + 
								"<br>Кол-во проданных товаров: " + productsModel.getValueAt(selectedRows[selectEditableRow], 3) + 
								"<html>");
					}
					else {
						dispose();
					}
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(shop, ex.toString());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(shop, ex.toString());
				}
			}
		});
		this.setVisible(true);
	}

	// Проверка названия товара
	private void checkProductName(JTextField textField) throws NullPointerException {
		String productName = textField.getText();
		if (productName.length() == 0)
			throw new NullPointerException();
	}

	// Проверка цены товара
	private void checkPrice(JTextField textField) throws NullPointerException, NumberFormatException {
		String price = textField.getText();
		if (price.length() == 0)
			throw new NullPointerException();
		for (int i = 0; i < price.length(); ++i) {
			if (price.charAt(i) - '0' < 0 || price.charAt(i) - '0' > 9) {
				throw new NumberFormatException();
			}
		}
	}

	// Проверка количества товара
	private void checkProductNum(JTextField textField) throws NullPointerException, NumberFormatException {
		String productNum = textField.getText();
		if (productNum.length() == 0)
			throw new NullPointerException();
		for (int i = 0; i < productNum.length(); ++i) {
			if (productNum.charAt(i) - '0' < 0 || productNum.charAt(i) - '0' > 9) {
				throw new NumberFormatException();
			}
		}
	}
}

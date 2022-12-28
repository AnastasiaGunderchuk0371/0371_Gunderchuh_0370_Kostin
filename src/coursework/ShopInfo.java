package coursework;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class ShopInfo extends JFrame {
	Container mainBox;
	JLabel info;
	SpringLayout layout;
	public ShopInfo() {
		this.setTitle("Справка о магазине");
		this.setBounds(200, 100, 500, 150);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setResizable(false);

		//Создание информации о магазине
		info = new JLabel("<html>Магазинчик человечков S<br>Специализация: все по 35<br>"
				+ "ФИО Директора: Костин Аркадий и Анастасия Гундерчук <br>Адрес: Санкт Петербург, ул. Профессора Попова 5");

		mainBox = this.getContentPane();
		layout = new SpringLayout();

		mainBox.setLayout(layout);
		mainBox.add(info);
		//Размещение info
		layout.putConstraint(SpringLayout.WEST, info, 5, SpringLayout.WEST, mainBox);
		layout.putConstraint(SpringLayout.EAST, info, -5, SpringLayout.EAST, mainBox);
		layout.putConstraint(SpringLayout.NORTH, info, 10, SpringLayout.NORTH, mainBox);
		layout.putConstraint(SpringLayout.SOUTH, info, 70, SpringLayout.NORTH, info);
		this.setVisible(true);
	}
}

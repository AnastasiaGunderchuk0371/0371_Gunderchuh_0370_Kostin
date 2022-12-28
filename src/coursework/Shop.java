package coursework;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Shop {
	private JFrame shop;
	private DefaultTableModel sellersModel, productsModel;
	private JButton save, // Сохранить
			open, // Открыть файл
			add, // Добавить запись
			delete, // Удалить запись
			edit, // Изменить запись
			info, // Информация о магазине
			report, // Формирование отчёта
			tableSearchButton; // Поиск записей
	private JToolBar toolBar; // Панель инструментов
	private JScrollPane sellersScroll, // Прокрутка таблицы продавцов
			productsScroll; // Прокрутка таблицы продуктов
	private JTable sellersTable, // Таблица продацов
			productsTable; // Таблица товаров
	private JLabel tableNameLabel, tableSearchLabel;
	private JComboBox tableNameBox, tableSearchSellersBox, tableSearchProductsBox;
	private JTextField tableSearchField; // Поисковое поле
	private Box tableContainer, // Контейнер для таблицы и прилегающих компонентов
			tableNameContainer, // Контейнер для выбора таблицы
			tableSearchContainer; // Контейнер для элементов поиска по таблице

	public void show() {
		// Создание окна
		shop = new JFrame("Магазин");
		shop.setSize(500, 300);
		shop.setLocation(100, 100);
		shop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Создание кнопок
		save = new JButton(new ImageIcon("./icons/save.png"));
		open = new JButton(new ImageIcon("./icons/open.png"));
		add = new JButton(new ImageIcon("./icons/add.png"));
		delete = new JButton(new ImageIcon("./icons/delete.png"));
		edit = new JButton(new ImageIcon("./icons/edit.png"));
		info = new JButton(new ImageIcon("./icons/info.png"));
		report = new JButton("Отчёт");
		report.setMaximumSize(new Dimension(50, 70));

		// Настройка подсказок для кнопок
		save.setToolTipText("Сохранить");
		open.setToolTipText("Открыть файл");
		add.setToolTipText("Добавить запись");
		delete.setToolTipText("Удалить запись");
		edit.setToolTipText("Изменить запись");
		info.setToolTipText("О магазине");
		report.setToolTipText("Сформировать pdf отчёт");

		//Удаление границ при нажатии на кнопки
		save.setFocusPainted(false);
		open.setFocusPainted(false);
		add.setFocusPainted(false);
		delete.setFocusPainted(false);
		edit.setFocusPainted(false);
		info.setFocusPainted(false);
		report.setFocusPainted(false);

		// Добавление кнопок на панель инструментов
		toolBar = new JToolBar("Панель инструментов");
		toolBar.add(save);
		toolBar.add(open);
		toolBar.add(add);
		toolBar.add(delete);
		toolBar.add(edit);
		toolBar.add(info);
		toolBar.add(report);

		// Размещение панели инструментов
		shop.setLayout(new BorderLayout());
		shop.add(toolBar, BorderLayout.NORTH);

		// Создание панели выбора таблицы
		tableNameContainer = Box.createHorizontalBox();
		tableNameLabel = new JLabel("Таблица:");
		tableNameBox = new JComboBox(new String[] { "Продавцы", "Товары" });
		tableNameLabel.setLabelFor(tableNameBox);
		tableNameBox.setMaximumSize(new Dimension(90, 25));
		tableNameContainer.add(tableNameLabel);
		tableNameContainer.add(Box.createHorizontalStrut(6));
		tableNameContainer.add(tableNameBox);
		tableNameContainer.add(Box.createHorizontalGlue());

		// Создание таблицы с данными продавцов
		String sellersColumn[] = { "ФИО", "Дата рождения", "Зарплата" };
		String sellersData[][] = {};
		sellersModel = new DefaultTableModel(sellersData, sellersColumn);
		sellersTable = new JTable(sellersModel);
		sellersTable.setAutoCreateRowSorter(true);
		sellersScroll = new JScrollPane(sellersTable);

		// Создание таблицы с данными товаров
		String productsColumn[] = { "Название товара", "Цена", "Количество", "Продано" };
		String productsData[][] = {};
		productsModel = new DefaultTableModel(productsData, productsColumn);
		productsTable = new JTable(productsModel);
		productsTable.setAutoCreateRowSorter(true);
		productsScroll = new JScrollPane(productsTable);
		productsScroll.setVisible(false);

		// Создание панели поиска по таблице
		tableSearchContainer = Box.createHorizontalBox();
		tableSearchLabel = new JLabel("Поиск по:");
		tableSearchSellersBox = new JComboBox(new String[] { "ФИО", "Дата рождения", "Зарплата" });
		tableSearchProductsBox = new JComboBox(new String[] { "Название товара", "Цена", "Количество", "Продано" });
		tableSearchProductsBox.setVisible(false);
		tableSearchField = new JTextField("Поиск");
		tableSearchButton = new JButton("Поиск");
		tableSearchButton.setFocusPainted(false);
		tableSearchContainer.add(tableSearchLabel);
		tableSearchContainer.add(Box.createHorizontalStrut(6));
		tableSearchContainer.add(tableSearchSellersBox);
		tableSearchContainer.add(tableSearchProductsBox);
		tableSearchContainer.add(Box.createHorizontalStrut(6));
		tableSearchContainer.add(tableSearchField);
		tableSearchContainer.add(Box.createHorizontalStrut(6));
		tableSearchContainer.add(tableSearchButton);

		// Размещение таблицы и прилегающих компонентов
		tableContainer = Box.createVerticalBox();
		tableContainer.add(tableNameContainer);
		tableContainer.add(sellersScroll);
		tableContainer.add(productsScroll);
		tableContainer.add(tableSearchContainer);
		shop.add(tableContainer, BorderLayout.CENTER);

		// Добавление слушателей
		addListeners();

		// Визуализация экранной формы
		shop.setVisible(true);
	}

	private void addListeners() {
		// Добавление слушателей
		save.addActionListener(new FirstAction());
		open.addActionListener(new FirstAction());
		add.addActionListener(new FirstAction());
		delete.addActionListener(new FirstAction());
		edit.addActionListener(new FirstAction());
		info.addActionListener(new FirstAction());
		report.addActionListener(new FirstAction());
		tableNameBox.addActionListener(new FirstAction());
		tableSearchSellersBox.addActionListener(new FirstAction());
		tableSearchButton.addActionListener(new FirstAction());
	}

	/**
	 * @param arr - массив для сортировки по убыванию
	 */
	private void sort(int[] arr) {
		int tmp;
		int len = arr.length;
		// Сортировка массива
		for (int i = 0; i < len; ++i) {
			for (int j = 0; j < len - i - 1; ++j) {
				if (arr[j] < arr[j + 1]) {
					tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
	}

	// Блоки прослушивания событий
	public class FirstAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == save) { // Сохранение в файл
				try {
					DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document doc = builder.newDocument();
					int currentTable = tableNameBox.getSelectedIndex();
					if (currentTable == 0) {
						// Создание корневого элемента sellerlist и добавление его в документ
						FileDialog saveFile = new FileDialog(shop, "Сохранение данных о продавцах", FileDialog.SAVE);
						saveFile.setFile("*.xml");
						saveFile.setVisible(true); // Отобразить запрос пользователю
						// Определить имя выбранного каталога и файла
						String fileName = saveFile.getDirectory() + saveFile.getFile();
						if (fileName == null)
							return; // Если пользователь нажал «отмена»
						Node sellerlist = doc.createElement("sellerlist");
						doc.appendChild(sellerlist);
						// Создание дочерних элементов seller и присвоение значений атрибутам
						for (int i = 0; i < sellersModel.getRowCount(); ++i) {
							Element seller = doc.createElement("seller");
							sellerlist.appendChild(seller);
							seller.setAttribute("fio", (String) sellersModel.getValueAt(i, 0));
							seller.setAttribute("birthDate", (String) sellersModel.getValueAt(i, 1));
							seller.setAttribute("salary", (String) sellersModel.getValueAt(i, 2));
						}
						try {
							// Создание преобразователя документа
							Transformer trans = TransformerFactory.newInstance().newTransformer();
							// Создание файла с именем sellers.xml для записи документа
							FileWriter fw = new FileWriter(fileName);
							// Запись документа в файл
							trans.transform(new DOMSource(doc), new StreamResult(fw));
						} catch (TransformerConfigurationException e) {
							e.printStackTrace();
						} catch (TransformerException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (currentTable == 1) {
						FileDialog saveFile = new FileDialog(shop, "Сохранение данных о товарах", FileDialog.SAVE);
						saveFile.setFile("*.xml");
						saveFile.setVisible(true); // Отобразить запрос пользователю
						// Определить имя выбранного каталога и файла
						String fileName = saveFile.getDirectory() + saveFile.getFile();
						if (fileName == null)
							return; // Если пользователь нажал «отмена»
						// Создание корневого элемента productlist и добавление его в документ
						Node productlist = doc.createElement("productlist");
						doc.appendChild(productlist);
						// Создание дочерних элементов product и присвоение значений атрибутам
						for (int i = 0; i < productsModel.getRowCount(); ++i) {
							Element product = doc.createElement("product");
							productlist.appendChild(product);
							product.setAttribute("name", (String) productsModel.getValueAt(i, 0));
							product.setAttribute("price", (String) productsModel.getValueAt(i, 1));
							product.setAttribute("num", (String) productsModel.getValueAt(i, 2));
							product.setAttribute("sold", (String) productsModel.getValueAt(i, 3));
						}
						try {
							// Создание преобразователя документа
							Transformer trans = TransformerFactory.newInstance().newTransformer();
							// Создание файла с именем sellers.xml для записи документа
							FileWriter fw = new FileWriter(fileName);
							// Запись документа в файл
							trans.transform(new DOMSource(doc), new StreamResult(fw));
						} catch (TransformerConfigurationException e) {
							e.printStackTrace();
						} catch (TransformerException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (ParserConfigurationException ex) {
					ex.printStackTrace();
				}
			} else if (event.getSource() == open) { // Загрузка из файла
				try {
					// Создание парсера документа
					DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					int currentTable = tableNameBox.getSelectedIndex();
					if (currentTable == 0) {
						// Чтение документа из файла
						FileDialog openFile = new FileDialog(shop, "Открытие файла с данными о продавцах",
								FileDialog.LOAD);
						openFile.setVisible(true); // Отобразить запрос пользователю
						// Определить имя выбранного каталога и файла
						String fileName = openFile.getDirectory() + openFile.getFile();
						if (fileName == null)
							return; // Если пользователь нажал «отмена»
						Document doc = dBuilder.parse(new File(fileName));
						// Нормализация документа
						doc.getDocumentElement().normalize();
						// Получение списка элементов с именем seller
						NodeList nlSellers = doc.getElementsByTagName("seller");
						int rows = sellersModel.getRowCount();
						for (int i = 0; i < rows; i++)
							sellersModel.removeRow(0); // Очистка таблицы
						// Цикл просмотра списка элементов и запись данных в таблицу
						for (int i = 0; i < nlSellers.getLength(); ++i) {
							// Выбор очередного элемента списка
							Node elem = nlSellers.item(i);
							// Получение списка атрибутов элемента
							NamedNodeMap attrs = elem.getAttributes();
							// Чтение атрибутов элемента
							String fio = attrs.getNamedItem("fio").getNodeValue();
							String birthDate = attrs.getNamedItem("birthDate").getNodeValue();
							String salary = attrs.getNamedItem("salary").getNodeValue();
							// Запись данных в таблицу
							sellersModel.addRow(new String[] { fio, birthDate, salary });
						}
					} else if (currentTable == 1) {
						// Чтение документа из файла
						FileDialog openFile = new FileDialog(shop, "Открытие файла с данными о товарах",
								FileDialog.LOAD);
						openFile.setVisible(true); // Отобразить запрос пользователю
						// Определить имя выбранного каталога и файла
						String fileName = openFile.getDirectory() + openFile.getFile();
						if (fileName == null)
							return; // Если пользователь нажал «отмена»
						Document doc = dBuilder.parse(new File(fileName));
						// Нормализация документа
						doc.getDocumentElement().normalize();
						// Получение списка элементов с именем seller
						NodeList nlProducts = doc.getElementsByTagName("product");
						int rows = productsModel.getRowCount();
						for (int i = 0; i < rows; i++)
							productsModel.removeRow(0); // Очистка таблицы
						// Цикл просмотра списка элементов и запись данных в таблицу
						for (int i = 0; i < nlProducts.getLength(); ++i) {
							// Выбор очередного элемента списка
							Node elem = nlProducts.item(i);
							// Получение списка атрибутов элемента
							NamedNodeMap attrs = elem.getAttributes();
							// Чтение атрибутов элемента
							String name = attrs.getNamedItem("name").getNodeValue();
							String price = attrs.getNamedItem("price").getNodeValue();
							String num = attrs.getNamedItem("num").getNodeValue();
							String sold = attrs.getNamedItem("sold").getNodeValue();
							// Запись данных в таблицу
							productsModel.addRow(new String[] { name, price, num, sold });
						}
					}
				} catch (ParserConfigurationException ex) {
					ex.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (event.getSource() == add) { // Добавление в таблицу
				int currentTable = tableNameBox.getSelectedIndex();
				if (currentTable == 0) {
					SellerWindow seller = new SellerWindow(sellersModel, shop);
				} else if (currentTable == 1) {
					ProductWindow product = new ProductWindow(productsModel, shop);
				}
			} else if (event.getSource() == delete) { // Удаление из таблицы
				int currentTable = tableNameBox.getSelectedIndex();
				if (currentTable == 0) {
					int[] selectedRows = sellersTable.getSelectedRows();
					if (selectedRows.length != 0) {
						for (int i : selectedRows) {
							sellersModel.removeRow(i);
						}
					} else {
						JOptionPane.showMessageDialog(shop, "Вы не выбрали строку для удаления!");
					}
				} else if (currentTable == 1) {
					int[] selectedRows = productsTable.getSelectedRows();
					if (selectedRows.length != 0) {
						sort(selectedRows);
						for (int i : selectedRows) {
							productsModel.removeRow(i);
						}
					} else {
						JOptionPane.showMessageDialog(shop, "Вы не выбрали строку для удаления!");
					}
				}
			} else if (event.getSource() == edit) { // Изменение записи
				int currentTable = tableNameBox.getSelectedIndex();
				if (currentTable == 0) {
					int[] selectedRows = sellersTable.getSelectedRows();
					if (selectedRows.length != 0) {
						EditSellerWindow editSeller = new EditSellerWindow(sellersModel, shop, selectedRows);
					} else {
						JOptionPane.showMessageDialog(shop, "Вы не выбрали строк для изменения!");
					}
				} else if (currentTable == 1) {
					int[] selectedRows = productsTable.getSelectedRows();
					if (selectedRows.length != 0) {
						EditProductWindow editProduct = new EditProductWindow(productsModel, shop, selectedRows);
					} else {
						JOptionPane.showMessageDialog(shop, "Вы не выбрали строк для изменения!");
					}
				}
			} else if (event.getSource() == info) { // Вывод информации о магазине
				ShopInfo shopInfo = new ShopInfo();
			} else if (event.getSource() == tableNameBox) { // Смена таблиц
				int selectedIndex = tableNameBox.getSelectedIndex(); // 0 - Продавцы, 1 - Товары
				if (selectedIndex == 0) {
					sellersScroll.setVisible(true);
					productsScroll.setVisible(false);
					tableSearchSellersBox.setVisible(true);
					tableSearchProductsBox.setVisible(false);
				} else if (selectedIndex == 1) {
					sellersScroll.setVisible(false);
					productsScroll.setVisible(true);
					tableSearchSellersBox.setVisible(false);
					tableSearchProductsBox.setVisible(true);
				}
				shop.setVisible(true);
			} else if (event.getSource() == report) {
				com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 50, 50, 50, 50);

				PdfPTable t = new PdfPTable(4);
				try {
					FileDialog saveFile = new FileDialog(shop, "Формирование pdf отчёта", FileDialog.SAVE);
					saveFile.setFile("*.pdf");
					saveFile.setVisible(true); // Отобразить запрос пользователю
					// Определить имя выбранного каталога и файла
					String fileName = saveFile.getDirectory() + saveFile.getFile();
					if (fileName == null)
						return; // Если пользователь нажал «отмена»
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (DocumentException e) {
					e.printStackTrace();
				}

				BaseFont bfComic = null;

				try {
					bfComic = BaseFont.createFont("/Windows/Fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

				} catch (DocumentException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Font font1 = new Font(bfComic, 12);
				t.addCell(new PdfPCell(new Phrase("Название товара", font1)));
				t.addCell(new PdfPCell(new Phrase("Цена", font1)));
				t.addCell(new PdfPCell(new Phrase("Кол-во товара в наличии", font1)));
				t.addCell(new PdfPCell(new Phrase("Кол-во проданного товара", font1)));
				for (int i = 0; i < productsModel.getRowCount(); i++) {
					t.addCell(new Phrase((String) productsModel.getValueAt(i, 0), font1));
					t.addCell(new Phrase((String) productsModel.getValueAt(i, 1), font1));
					t.addCell(new Phrase((String) productsModel.getValueAt(i, 2), font1));
					t.addCell(new Phrase((String) productsModel.getValueAt(i, 3), font1));
				}
				document.open();
				try {
					document.add(t);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				document.close();
			}
			else if(event.getSource() == tableSearchButton) {
				int currentTable = tableNameBox.getSelectedIndex();
				if (currentTable == 0) {
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(sellersModel);
				    sellersTable.setRowSorter(sorter);
					int currentSearchColumn = tableSearchSellersBox.getSelectedIndex();
					if(currentSearchColumn == 0) {
						String text = tableSearchField.getText();
				        if (text.length() == 0) {
				          sorter.setRowFilter(null);
				        } else {
				          sorter.setRowFilter(RowFilter.regexFilter(text, 0));
				        }
					}
					else if(currentSearchColumn == 1) {
						String text = tableSearchField.getText();
				        if (text.length() == 0) {
				          sorter.setRowFilter(null);
				        } else {
				          sorter.setRowFilter(RowFilter.regexFilter(text, 1));
				        }
					}
					else if(currentSearchColumn == 2) {
						String text = tableSearchField.getText();
				        if (text.length() == 0) {
				          sorter.setRowFilter(null);
				        } else {
				          sorter.setRowFilter(RowFilter.regexFilter(text, 2));
				        }
					}
				} else if (currentTable == 1) {
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(productsModel);
				    productsTable.setRowSorter(sorter);
					int currentSearchColumn = tableSearchProductsBox.getSelectedIndex();
					if(currentSearchColumn == 0) {
						String text = tableSearchField.getText();
				        if (text.length() == 0) {
				          sorter.setRowFilter(null);
				        } else {
				          sorter.setRowFilter(RowFilter.regexFilter(text, 0));
				        }
					}
					else if(currentSearchColumn == 1) {
						String text = tableSearchField.getText();
				        if (text.length() == 0) {
				          sorter.setRowFilter(null);
				        } else {
				          sorter.setRowFilter(RowFilter.regexFilter(text, 1));
				        }
					}
					else if(currentSearchColumn == 2) {
						String text = tableSearchField.getText();
				        if (text.length() == 0) {
				          sorter.setRowFilter(null);
				        } else {
				          sorter.setRowFilter(RowFilter.regexFilter(text, 2));
				        }
					}
					else if(currentSearchColumn == 3) {
						String text = tableSearchField.getText();
				        if (text.length() == 0) {
				          sorter.setRowFilter(null);
				        } else {
				          sorter.setRowFilter(RowFilter.regexFilter(text, 3));
				        }
					}
				}

			}
		}
	}
	public static void main(String[] args) {
		Shop shop = new Shop();
		shop.show();
	}
}
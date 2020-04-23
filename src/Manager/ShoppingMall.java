package Manager;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import DB.GoodsDAO;

public class ShoppingMall extends JFrame {
	Scanner in = new Scanner(System.in);
	String header[] = { "상품코드", "상품이름", "수량", "가격", "체크" };
	JTabbedPane tabpane = new JTabbedPane();
	DefaultTableModel tablemodel = new DefaultTableModel(header, 0);
	JTable table = new JTable(tablemodel);
	JScrollPane tableScroll = new JScrollPane(table);

	// center panel
	JPanel tab_center = new JPanel();
	JPanel tab_south = new JPanel();
	JPanel south_north = new JPanel();

	// JTextField[] txtfield = new JTextField[5];
	JTextField tfield = null;

	int modIntRow = -1;

	private GoodsDAO dao = GoodsDAO.getInstance();
	ArrayList<String[]> initList = new ArrayList<>();

	public ShoppingMall() {
		super("쇼핑몰");// super의 생성자 호출
		Dimension size = new Dimension(600, 400);
		createpanel();
		createtb();
		tablesetting();

		init();
		createchkbox();
		// createcombox();
		changeCellEditor(table, null);

		this.setLocation(300, 300);
		this.setSize(size);
		this.add(tabpane);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

	public void changeCellEditor(JTable table, TableColumn column) {
		TableColumn cntcombo = table.getColumnModel().getColumn(2);
		// JComboBox<Integer> comboBox = new JComboBox<Integer>();
		int num = 0;
		// int[] aa = new int[];
		JComboBox comboBox = new JComboBox();
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < initList.size(); i++) {
			num = Integer.parseInt(initList.get(i)[2]);
			arr.add(num);
			int[] aa = new int[num];
			for (int j = 1; j < aa.length; j++) {
				comboBox.addItem(j);
				cntcombo.setCellEditor(new DefaultCellEditor(comboBox));
			}
		}
	}

//			System.out.println(num);
//			for (int j = num; j >=num-1; j--) {
//				comboBox.addItem(j);
//				break;
//
//			}

	private Object aa(int j) {
		// TODO Auto-generated method stub
		return null;
	}

	private int parseInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void createchkbox() {

//		JCheckBox box = new JCheckBox();
//		table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));
//		box.setHorizontalAlignment(JLabel.CENTER);

		Container c = getContentPane();
		DefaultTableCellRenderer renderer = new MyCheckbox();
		table.getColumn("체크").setCellRenderer(renderer);
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));

//		for (int i = 0; i < initList.size(); i++) {
//			if (initList.get(i)[4].equals("false")) {
//				Boolean ch = Boolean.valueOf(initList.get(i)[4]);
//				box.setSelected(((Boolean) ch).booleanValue());
//				System.out.println("?????");
//			} else if (initList.get(i)[4].equals("true")) {
//				Boolean ch = Boolean.valueOf(initList.get(i)[4]);
//				box.setSelected(((Boolean) ch).booleanValue());
//				System.out.println("!!!!!");
//			}
//		}
		c.add(box);
	}

	private void init() {
		String[] torf = null;
		String real = null;
		String[] real2 = new String[12];
		String real3 = null;
		initList = dao.getList();
		for (int i = 0; i < initList.size(); i++) {
			tablemodel.addRow(initList.get(i));
//			for (int j = 0; j < real2.length; j++) {
//				real2[j] = initList.get(i)[4];
//			}
		}

		int colNum = tablemodel.getColumnCount();
		int rowNum = tablemodel.getRowCount();
	}

	public void tablesetting() {
		table.setRowMargin(0);
		table.getColumnModel().setColumnMargin(0);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {// 마우스 왼쪽 클릭
					modIntRow = table.getSelectedRow();
					// for (int i = 0; i < txtfield.length; i++) {
					// txtfield[i].setText((String) table.getValueAt(modIntRow,
					// i));
					// }
					// modIntRow = table.getSelectedRow();
					// tfield.setText((String) table.getValueAt(modIntRow, 5));
					// }
					if (e.getClickCount() == 2) {// 왼쪽 더블 클릭

					}
					if (e.getClickCount() == 3) {

						modIntRow = table.getSelectedRow();
					}
				}
			}
		});
		DefaultTableCellRenderer ts = new DefaultTableCellRenderer();// 테이블의 셀
																		// 값을 정렬
		ts.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc = table.getColumnModel();
		for (int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(ts);
		}

	}

	private void createtb() {
		// south_north.setLayout(new BoxLayout(south_north, BoxLayout.X_AXIS));
		// for (int i = 0; i < txtfield.length; i++) {
		// south_north.add(txtfield[i] = new JTextField());
		// }
		JLabel all = new JLabel("총 금액");
		south_north.add(all);

		JTextField total = new JTextField(10);
		south_north.add(total);

		JButton addB = new JButton("장바구니에 담기");
		south_north.add(addB);

		addB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				// for (int i = 0; i < txtfield.length; i++) {
				// in[i] = txtfield[i].getText();
				// txtfield[i].setText("");
				// }
				// int sum = Integer.parseInt(in[3]) * Integer.parseInt(in[4]);
				// in[5] = Integer.toString(sum);
				// dto = new DTO();
				// dto.setTotal(in[5]);
				tablemodel.addRow(in);
				// saveToDB(in);
			}

		});

		JButton modB = new JButton("바로 주문하기");
		south_north.add(modB);
		modB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				// for (int i = 0; i < txtfield.length; i++) {
				// in[i] = txtfield[i].getText();
				// txtfield[i].setText("");
				// }
				// int sum = Integer.parseInt(in[3]) * Integer.parseInt(in[4]);
				// in[5] = Integer.toString(sum);
				// delTableRow(modIntRow);
				// tablemodel.insertRow(modIntRow, in);
				// dto = new DTO();
				// editToDB(in);
				modIntRow = -1;
			}
		});

	}

	private void createpanel() {
		this.add(tab_center, "Center");
		this.add(tab_south, "South");

		tab_center.setLayout(new BorderLayout());
		tab_center.add(tableScroll, "Center");
		tab_center.add(tab_south, "South");
		tabpane.add("shopping", tab_center);

		tab_south.setLayout(new BorderLayout());
		tab_south.add(south_north, "North");
		// tab_south.add(south_south, "South");

	}

}

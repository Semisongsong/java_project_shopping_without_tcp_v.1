package Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class test extends JFrame {
	private final Object[] colNames = { "ChkBox", "ÀÌ¸§", "³ªÀÌ", "¼ºº°" };
	private Object[][] datas = { { false, "È«±æµ¿", "20", "³²" }, { true, "±è¸»ÀÚ", "18", "¿©" } };

	public test() {
		super("JTable Tester");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new BorderLayout());
		DefaultTableModel dtm = new DefaultTableModel(datas, colNames);
		JTable table = new JTable(dtm);
		table.getColumn("ChkBox").setCellRenderer(dcr);
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("ChkBox").setCellEditor(new DefaultCellEditor(box));
		JScrollPane jsp = new JScrollPane(table);
		panel.add(jsp, "Center");
		setContentPane(panel);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new test();
	}

	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent // ¼¿·»´õ·¯
		(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JCheckBox box = new JCheckBox();
			box.setSelected(((Boolean) value).booleanValue());
			box.setHorizontalAlignment(JLabel.CENTER);
			return box;
		}
	};
}

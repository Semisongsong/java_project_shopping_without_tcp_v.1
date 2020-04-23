package Manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.DefaultListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class test22 extends JPanel {

	private static final int CHECK_COL = 1;
	private static final Object[][] DATA = { { "One", Boolean.TRUE }, { "Two", Boolean.FALSE },
			{ "Three", Boolean.TRUE }, { "Four", Boolean.FALSE }, { "Five", Boolean.TRUE }, { "Six", Boolean.FALSE },
			{ "Seven", Boolean.TRUE }, { "Eight", Boolean.FALSE }, { "Nine", Boolean.TRUE }, { "Ten", Boolean.FALSE } };
	private static final String[] COLUMNS = { "Number", "CheckBox" };
	private DataModel dataModel = new DataModel(DATA, COLUMNS);
	private JTable table = new JTable(dataModel);
	private DefaultListSelectionModel selectionModel;

	public test22() {
		super(new BorderLayout());
		this.add(new JScrollPane(table));
		this.add(new ControlPanel(), BorderLayout.SOUTH);
		table.setPreferredScrollableViewportSize(new Dimension(250, 175));
		selectionModel = (DefaultListSelectionModel) table.getSelectionModel();
	}

	private class DataModel extends DefaultTableModel {

		public DataModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == CHECK_COL) {
				return getValueAt(0, CHECK_COL).getClass();
			}
			return super.getColumnClass(columnIndex);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return column == CHECK_COL;
		}
	}

	private class ControlPanel extends JPanel {

		public ControlPanel() {
			this.add(new JLabel("Selection:"));
			this.add(new JButton(new SelectionAction("Clear", false)));
			this.add(new JButton(new SelectionAction("Check", true)));
		}
	}

	private class SelectionAction extends AbstractAction {

		boolean value;

		public SelectionAction(String name, boolean value) {
			super(name);
			this.value = value;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < dataModel.getRowCount(); i++) {
				if (selectionModel.isSelectedIndex(i)) {
					dataModel.setValueAt(value, i, CHECK_COL);
				}
			}
		}
	}

	private static void createAndShowUI() {
		JFrame frame = new JFrame("CheckABunch");
		frame.add(new test22());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				createAndShowUI();
			}
		});
	}
}
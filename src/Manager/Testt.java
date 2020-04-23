package Manager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyFrame extends JFrame implements ActionListener {

	private JButton buttonOK;
	private JCheckBox onion, chese, tomato;

	public MyFrame() {
		// TODO Auto-generated constructor stub
		setTitle("체크박스 테스트");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("햄버거에 무엇을 추가하겠습니까?");
		JPanel toPanel = new JPanel();
		toPanel.add(label);
		add(toPanel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		onion = new JCheckBox("양파");
		panel.add(onion);
		chese = new JCheckBox("치즈");
		panel.add(chese);
		tomato = new JCheckBox("토마토");
		panel.add(tomato);
		add(panel, BorderLayout.CENTER);

		buttonOK = new JButton("OK");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(buttonOK);
		add(buttonPanel, BorderLayout.SOUTH);
		buttonOK.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonOK) {
			String msg = "";
			if (onion.isSelected())
				msg += "양파\n";
			if (chese.isSelected())
				msg += "치즈\n";
			if (tomato.isSelected())
				msg += "토마토\n";
			msg = "선택한 옵션은 다움과같습니다\n" + msg;
			System.out.println(msg);
			onion.setSelected(false);
			chese.setSelected(false);
			tomato.setSelected(false);
		}
	}

}

public class Testt {
	public static void main(String[] args) {
		new MyFrame();
	}
}

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Okno extends JFrame {
	Main main = new Main();

	public Okno() {
		initComponents();
	}

	public void initComponents() {

		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button5 = new JButton();

		JLabel Witaj;
		JLabel Witaj2;
		// ======== this ========
		setTitle("Licznik Zu¿ycia Pr¹du");
		setSize(618, 347);// 247
		setLayout(null);

		// ---- button2 ----
		button2.setText("Dodaj urz¹dzenie");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.dodajUrzadzenie();
			}
		});

		// ---- button3 ----
		button3.setText("Lista urz¹dzeñ");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Main.odczytWyniku();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// ---- button4 ----
		button4.setText("Oblicz koszt");
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					Main.obliczKoszt();
			}
		});
		// ---- button5 ----
		button5.setText("Reset");
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Main.reset();
			JOptionPane.showMessageDialog(null, "Dane zosta³y zresetowane", "Reset",
					JOptionPane.INFORMATION_MESSAGE);
		}
		});
		// ---- wynik Label ----
		Witaj = new JLabel("Kalkulator zu¿ycia pr¹du");
		Witaj.setBounds(130, 0, 600, 100);
		Witaj.setFont(new Font("SansSerif", Font.BOLD, 30));
		add(Witaj);
		
		Witaj2 = new JLabel("Dodaj urz¹dzenia, a nastêpnie kliknij 'Oblicz koszt', aby sprawdziæ dzienny koszt zu¿ycia pr¹du");
		Witaj2.setBounds(10,30,600,100);
		Witaj2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		add(Witaj2);

		//JPanel przyciski = new JPanel();
		//przyciski.setLayout(new GridLayout(1, 3, 0, 0));
		button2.setBounds(100, 100, 400, 50);
		button3.setBounds(100, 150, 400, 50);
		button4.setBounds(100, 200, 400, 50);
		button5.setBounds(100, 250, 400, 50);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
	}
	public JButton button2;
	public JButton button3;
	public JButton button4;
	public JButton button5;
}
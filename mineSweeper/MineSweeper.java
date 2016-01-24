package mineSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MineSweeper extends JFrame implements ActionListener {

	private JPanel p1 = new JPanel();
	// creem pentru joc 10 linii si 10 butoane
	private JPanel p2 = new JPanel(new GridLayout(10, 10));

	private JButton s = new JButton();
	// creez 100 de butoane
	private JButton[][] b = new JButton[10][10];
	// fiecare buton b corespunde fiecarui element din matricea m
	private int[][] m = new int[10][10];
	// creez 2 iconite
	private ImageIcon icon1 = new ImageIcon("D:/eclipseWorkspace/mineSweeper/src/mineSweeper/face.png");
	private ImageIcon icon2 = new ImageIcon("D:/eclipseWorkspace/mineSweeper/src/mines.png");

	public MineSweeper() {
		// dau un nume ferestrei
		super("Minesweeper");
		// adaug panoul p1 la nord
		add(p1, BorderLayout.NORTH);
		// in p1 adaug butonul s ce va contine smiley face
		p1.add(s);
		// setez iconita smiley face pe butonul s
		s.setIcon(icon1);
		s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				newGame();
			}
		});

		// adaug panoul 2
		add(p2);
		// parcurg matricea
		generare(20);
		count();
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				// creez butoanele
				b[i][j] = new JButton();
				// if (m[i][j] == -1)
				// b[i][j].setBackground(Color.RED);
				// adaug butoanele
				p2.add(b[i][j]);
				b[i][j].addActionListener(this);
			}
		}
		setSize(500, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void initializare() {
		// metoda va fi folosita de reincepere joc pentru a initializa toate
		// butoanele cu 0
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = 0;
			}
		}
	}

	public void generare(int n) {
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			int x = r.nextInt(10);
			int y = r.nextInt(10);

			// daca nu exista deja pe acesta pozitie -1 seteaza -1
			if (m[x][y] != -1) {
				m[x][y] = -1;
			} else {
				i--;
			}
		}
	}

	/**
	 * metoda are rolul de a numara cate bombe sunt in imediata apropiere a
	 * butonului smile face(valoarea 0)
	 */
	private void count() {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] != -1) {
					try {
						if (m[i - 1][j - 1] == -1)
							m[i][j]++;
					} catch (Exception e) {
					}
					try {
						if (m[i - 1][j] == -1)
							m[i][j]++;
					} catch (Exception e) {
					}
					try {
						if (m[i - 1][j + 1] == -1)
							m[i][j]++;
					} catch (Exception e) {
					}
					try {
						if (m[i][j - 1] == -1)
							m[i][j]++;
					} catch (Exception e) {
					}

					try {
						if (m[i][j + 1] == -1)
							m[i][j]++;
					} catch (Exception e) {
					}
					try {
						if (m[i + 1][j - 1] == -1)
							m[i][j]++;
					} catch (Exception e) {
					}
					try {
						if (m[i + 1][j] == -1)
							m[i][j]++;
					} catch (Exception e) {
					}
					try {
						if (m[i + 1][j + 1] == -1)
							m[i][j]++;
					} catch (Exception e) {
					}

				}
			}
		}

	}

	public void actionPerformed(ActionEvent ev) {
		JButton sursa = (JButton) ev.getSource();
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (b[i][j] == sursa) {
					if (m[i][j] == -1) {
						// Game over
						gameOver();
					} else {
						b[i][j].setText(String.valueOf(m[i][j]));
						b[i][j].setEnabled(false);
					}
				}
			}
		}

	}

	/**
	 * in functie de valoarea lui x metoda activeaza sau dezactiveaza butoanele
	 * 
	 * @param x
	 */
	private void activareButoane(boolean x) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {

				b[i][j].setEnabled(x);

			}
		}
	}

	/**
	 * metodele afiseaza toate minele
	 */
	private void afisareMine() {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (m[i][j] == -1) {
					b[i][j].setIcon(icon2);
				}
			}
		}
	}

	private void clearButtons() {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				b[i][j].setText(null);
				b[i][j].setIcon(null);
			}
		}
	}

	private void newGame() {
		initializare();
		generare(20);
		count();
		clearButtons();
		activareButoane(true);
		JOptionPane.showMessageDialog(null, "Succes!!!");

	}

	public void gameOver() {
		afisareMine();
		activareButoane(false);
		JOptionPane.showMessageDialog(null, "Game Over!!!");
	}

	public static void main(String[] args) {
		new MineSweeper();
	}
}

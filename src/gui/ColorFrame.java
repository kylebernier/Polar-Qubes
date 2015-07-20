package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorFrame extends JInternalFrame {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3128703280848457717L;

	/** The find. */
	private final JButton find;

	/** The btext. */
	private JTextField atext, rtext, gtext, btext;

	/** The alpha. */
	private JLabel red, green, blue, alpha;

	/** The bslide. */
	private JSlider aslide, rslide, gslide, bslide;

	/** The display. */
	private JTextPane display;

	/** The more. */
	private JButton more;

	/** The color. */
	private Color color;

	/** The b. */
	private int a = 255, r = 0, g = 0, b = 0;

	/** The finder. */
	private boolean finder = false;

	/**
	 * Instantiates a new color chooser.
	 */
	public ColorFrame() {
		setTitle("Colors");
		setSize(433, 126);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		
		setBackground(Color.DARK_GRAY);

		color = Color.black;
		getContentPane().setLayout(null);
		setPreferredSize(new Dimension(405, 85));

		alpha = new JLabel("Alpha");
		alpha.setForeground(Color.BLACK);
		alpha.setBounds(6, 6, 36, 16);
		getContentPane().add(alpha);

		aslide = new JSlider();
		aslide.setFocusable(false);
		aslide.setOpaque(false);
		aslide.setBounds(42, 6, 180, 16);
		aslide.setMaximum(255);
		aslide.setValue(255);
		getContentPane().add(aslide);

		atext = new JTextField();
		atext.setText("255");
		atext.setBounds(222, 6, 50, 16);
		getContentPane().add(atext);

		red = new JLabel("Red");
		red.setForeground(Color.RED);
		red.setBounds(6, 25, 36, 16);
		getContentPane().add(red);

		rslide = new JSlider();
		rslide.setFocusable(false);
		rslide.setOpaque(false);
		rslide.setValue(0);
		rslide.setBounds(42, 25, 180, 16);
		rslide.setMaximum(255);
		getContentPane().add(rslide);

		rtext = new JTextField();
		rtext.setText("0");
		rtext.setBounds(222, 25, 50, 16);
		getContentPane().add(rtext);

		green = new JLabel("Green");
		green.setForeground(Color.GREEN);
		green.setBounds(6, 44, 36, 16);
		getContentPane().add(green);

		gslide = new JSlider();
		gslide.setFocusable(false);
		gslide.setOpaque(false);
		gslide.setValue(0);
		gslide.setBounds(42, 44, 180, 16);
		gslide.setMaximum(255);
		getContentPane().add(gslide);

		gtext = new JTextField();
		gtext.setText("0");
		gtext.setBounds(222, 44, 50, 16);
		getContentPane().add(gtext);

		blue = new JLabel("Blue");
		blue.setForeground(Color.BLUE);
		blue.setBounds(6, 63, 36, 16);
		getContentPane().add(blue);

		bslide = new JSlider();
		bslide.setOpaque(false);
		bslide.setValue(0);
		bslide.setBounds(42, 63, 180, 16);
		bslide.setMaximum(255);
		getContentPane().add(bslide);

		btext = new JTextField();
		btext.setText("0");
		btext.setBounds(222, 63, 50, 16);
		getContentPane().add(btext);

		display = new JTextPane();
		display.setFocusable(false);
		display.setBounds(275, 6, 62, 73);
		display.setBackground(color);
		getContentPane().add(display);

		more = new JButton("More");
		more.setFocusable(false);
		more.setFont(new Font("Tahoma", Font.PLAIN, 11));
		more.setOpaque(false);
		more.setBackground(null);
		more.setForeground(color);
		more.setBounds(339, 6, 58, 16);
		getContentPane().add(more);

		find = new JButton(new ImageIcon(getClass().getResource("/toolbar/Find.png")));
		find.setFocusable(false);
		find.setOpaque(false);
		find.setBorder(null);
		find.setBackground(null);
		find.setBounds(344, 25, 48, 48);
		getContentPane().add(find);

		find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				find.setIcon(new ImageIcon(getClass().getResource("/toolbar/Find2.png")));
				find.setBorder(null);
				finder = true;
			}
		});

		aslide.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				a = aslide.getValue();
				refresh();
			}
		});

		rslide.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				r = rslide.getValue();
				refresh();
			}
		});

		gslide.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				g = gslide.getValue();
				refresh();
			}
		});

		bslide.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				b = bslide.getValue();
				refresh();
			}
		});

		atext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = Integer.parseInt(atext.getText());
				if (i <= 255 && i >= 0)
					a = i;
				refresh();
			}
		});

		rtext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = Integer.parseInt(rtext.getText());
				if (i <= 255 && i >= 0)
					r = i;
				refresh();
			}
		});

		gtext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = Integer.parseInt(gtext.getText());
				if (i <= 255 && i >= 0)
					g = i;
				refresh();
			}
		});

		btext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = Integer.parseInt(btext.getText());
				if (i <= 255 && i >= 0)
					b = i;
				refresh();
			}
		});

		more.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("More Colors");
				final JColorChooser jcc = new JColorChooser();
				jcc.setColor(Main.currentColor);
				frame.getContentPane().add(jcc);
				frame.setSize(new Dimension(625, 400));
				frame.setVisible(true);
				frame.addWindowListener(new WindowListener() {
					@Override
					public void windowClosing(WindowEvent arg0) {
						a = jcc.getColor().getAlpha();
						r = jcc.getColor().getRed();
						g = jcc.getColor().getGreen();
						b = jcc.getColor().getBlue();
						aslide.setValue(a);
						rslide.setValue(r);
						gslide.setValue(g);
						bslide.setValue(b);
						refresh();
					}

					// Unused Methods
					@Override
					public void windowClosed(WindowEvent arg0) {
					}

					@Override
					public void windowActivated(WindowEvent arg0) {
					}

					@Override
					public void windowDeactivated(WindowEvent arg0) {
					}

					@Override
					public void windowDeiconified(WindowEvent arg0) {
					}

					@Override
					public void windowIconified(WindowEvent arg0) {
					}

					@Override
					public void windowOpened(WindowEvent arg0) {
					}
				});
			}
		});
	}

	/**
	 * Gets the find.
	 *
	 * @return the find
	 */
	public boolean getFind() {
		return finder;
	}

	/**
	 * Found.
	 *
	 * @param c
	 *            the c
	 */
	public void found(Color c) {
		r = c.getRed();
		g = c.getGreen();
		b = c.getBlue();
		a = c.getAlpha();
		find.setIcon(new ImageIcon(getClass().getResource("toolbar/Find.png")));
		find.setBorder(null);
		finder = false;
		refresh();
	}

	/**
	 * Refresh.
	 */
	private void refresh() {
		color = new Color(r, g, b, a);
		Main.currentColor = color;
		atext.setText("" + a);
		aslide.setValue(a);
		rtext.setText("" + r);
		rslide.setValue(r);
		gtext.setText("" + g);
		gslide.setValue(g);
		btext.setText("" + b);
		bslide.setValue(b);
		display.setBackground(new Color(r, g, b));
		more.setForeground(new Color(r, g, b));
	}
}

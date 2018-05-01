package mousedrawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MouseDrawing extends JFrame {
	/**
	 * @author zacharycs;
	 * @version 1.0;
	 * @param none;
	 * This is the stack of varibles that set up the GUI
	 */
	JMenuBar mainMenuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenu canvasColor = new JMenu("Canvas Color");
	JMenuItem newMenuItem = new JMenuItem("new");
	JMenuItem exitMenuItem = new JMenuItem("exit");
	JMenuItem blackColor = new JMenuItem("black");
	JMenuItem whiteColor = new JMenuItem("white");
	JMenuItem greenColor = new JMenuItem("green");
	JMenuItem grayColor = new JMenuItem("gray");
	JMenuItem blueColor = new JMenuItem("blue");
	JPanel drawPanel = new JPanel();
	JLabel leftColorLabel = new JLabel();
	JLabel rightColorLabel = new JLabel();
	JButton smPenlabel = new JButton("Sm");
	JButton midPenlabel = new JButton("Med");
	JButton lgPenlabel = new JButton("Lg");
	JPanel colorPanel = new JPanel();
	JLabel[] colorLabel = new JLabel[16];
	Graphics2D g2D;
	double xPrevious, yPrevious;
	Color drawColor, leftColor, rightColor;
	Stroke smPen, midPen, lgPen;
	
	
	
	public static void main(String[] args) {
		new MouseDrawing().setVisible(true);

	}
	public MouseDrawing() {
		setTitle("Kyle Dixon 3/14/18 Drawing");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitForm(e);
			}
		});
		getContentPane().setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.DARK_GRAY);
		
		setJMenuBar(mainMenuBar);
		fileMenu.setMnemonic('F');
		mainMenuBar.add(fileMenu);
		fileMenu.add(newMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMenuItemActionPerformed(e);
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitMenuItemActionPerformed(e);
			}
		});
		
		setJMenuBar(mainMenuBar);
		canvasColor.setMnemonic('C');
		mainMenuBar.add(canvasColor);
		canvasColor.add(blackColor);
		canvasColor.addSeparator();
		canvasColor.add(whiteColor);
		canvasColor.addSeparator();
		canvasColor.add(greenColor);
		canvasColor.addSeparator();
		canvasColor.add(blueColor);
		canvasColor.addSeparator();
		canvasColor.add(grayColor);
		blackColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blackColorActionPerformed(e);
			}
		});
		whiteColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whiteColorActionPerformed(e);
			}
		});
		greenColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				greenColorActionPerformed(e);
			}
		});
		grayColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grayColorActionPerformed(e);
			}
		});
		blueColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blueColorActionPerformed(e);
			}
		});
		
		drawPanel.setPreferredSize(new Dimension(800, 700));
		drawPanel.setBackground(Color.BLACK);
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.gridheight = 5;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(drawPanel, gridConstraints);
		drawPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				drawPanelMousePressed(e);
			}
		});
		drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				drawPanelMouseDragged(e);
			}
		});
		drawPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e){
				drawPanelMouseReleased(e);
			}
		});
		
		
		leftColorLabel.setPreferredSize(new Dimension(70,70));
		leftColorLabel.setOpaque(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,5,10,10);
		getContentPane().add(leftColorLabel, gridConstraints);
		
		rightColorLabel.setPreferredSize(new Dimension(70,70));
		rightColorLabel.setOpaque(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10, 5, 10, 10);
		getContentPane().add(rightColorLabel, gridConstraints);
		
		smPenlabel.setPreferredSize(new Dimension(80,40));
		smPenlabel.setOpaque(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		gridConstraints.gridwidth = 2;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,5,10,10);
		getContentPane().add(smPenlabel, gridConstraints);
		smPenlabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smPenlabelActionPerformed(e);
			}
		});
		
		midPenlabel.setPreferredSize(new Dimension(80,40));
		midPenlabel.setOpaque(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 3;
		gridConstraints.gridwidth = 2;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,5,10,10);
		getContentPane().add(midPenlabel, gridConstraints);
		midPenlabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				midPenlabelActionPerfomred(e);
			}
		});
		
		lgPenlabel.setPreferredSize(new Dimension(80,40));
		lgPenlabel.setOpaque(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 4;
		gridConstraints.gridwidth = 2;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,5,10,10);
		getContentPane().add(lgPenlabel, gridConstraints);
		lgPenlabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lgPenlabelActionPerformed(e);
			}
		});
		
		colorPanel.setPreferredSize(new Dimension(140,160));
		colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.gridwidth = 4;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,10,10,10);
		getContentPane().add(colorPanel, gridConstraints);
		
		
		colorPanel.setLayout(new GridBagLayout());
		int j = 0;
		for (int i = 0; i< 16; i++) {
			colorLabel[i] = new JLabel();
			colorLabel[i].setPreferredSize(new Dimension(30,30));
			colorLabel[i].setOpaque(true);
			gridConstraints = new GridBagConstraints();
			gridConstraints.gridx = j;
			gridConstraints.gridy = i - j * 4;
			colorPanel.add(colorLabel[i], gridConstraints);
			if (i == 3) {
				j++;
			}
			else if (i == 7) {
				j++;
			}
			else if (i == 11) {
				j++;
			}
			colorLabel[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					colorMousePressed(e);
				}
			});
		}
		
		colorLabel[0].setBackground(Color.BLACK);
		colorLabel[1].setBackground(Color.WHITE);
		colorLabel[7].setBackground(Color.BLUE.darker());
		colorLabel[3].setBackground(Color.BLUE);
		colorLabel[2].setBackground(Color.YELLOW);
		colorLabel[8].setBackground(Color.GREEN);
		colorLabel[9].setBackground(Color.PINK);
		colorLabel[4].setBackground(Color.GRAY);
		colorLabel[5].setBackground(Color.WHITE.darker());
		colorLabel[6].setBackground(Color.YELLOW.darker());
		colorLabel[10].setBackground(Color.ORANGE);
		colorLabel[11].setBackground(Color.MAGENTA);
		colorLabel[12].setBackground(Color.GREEN.darker());
		colorLabel[13].setBackground(Color.PINK.darker());
		colorLabel[14].setBackground(Color.ORANGE.darker());
		colorLabel[15].setBackground(Color.MAGENTA.darker());
		leftColor = colorLabel[0].getBackground();
		leftColorLabel.setBackground(leftColor);
		rightColor = colorLabel[1].getBackground();
		rightColorLabel.setBackground(rightColor);
		
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int) (0.5 * (screenSize.width = getWidth())),
				(int) (0.5 * (screenSize.height = getHeight())),
				getWidth(), getHeight());
		g2D = (Graphics2D) drawPanel.getGraphics();
	}
	
	protected void lgPenlabelActionPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(16));
		
	}
	protected void midPenlabelActionPerfomred(ActionEvent e) {
		g2D.setStroke(new BasicStroke(8));
		
	}
	protected void smPenlabelActionPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(2));
	}
	protected void blueColorActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new project?");
		if (response == JOptionPane.YES_OPTION) {
			g2D.setPaint(Color.blue);
			g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
			drawPanel.setBackground(Color.BLUE);
		}
	}
	protected void grayColorActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new project?");
		if (response == JOptionPane.YES_OPTION) {
			g2D.setPaint(Color.GRAY);
			g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
			drawPanel.setBackground(Color.GRAY);
		}
	}
	protected void greenColorActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new project?");
		if (response == JOptionPane.YES_OPTION) {
			g2D.setPaint(Color.GREEN);
			g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
			drawPanel.setBackground(Color.GREEN);
		}
	}
	protected void whiteColorActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new project?");
		if (response == JOptionPane.YES_OPTION) {
			g2D.setPaint(Color.WHITE);
			g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
			drawPanel.setBackground(Color.WHITE);
		}
	}
	protected void blackColorActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new project?");
		if (response == JOptionPane.YES_OPTION) {
			g2D.setPaint(Color.BLACK);
			g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
			drawPanel.setBackground(Color.BLACK);
		}
		
	}
	private void drawPanelMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void drawPanelMouseDragged(MouseEvent e) {
		Line2D.Double myline = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
		g2D.setPaint(drawColor);
		g2D.draw(myline);
		xPrevious = e.getX();
		yPrevious = e.getY();
		
	}
	private void drawPanelMousePressed(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON1||e.getButton()==MouseEvent.BUTTON3||e.getButton()==MouseEvent.BUTTON2) {
			xPrevious = e.getX();
			yPrevious = e.getY();
			if(e.getButton()== MouseEvent.BUTTON1) {
				drawColor = leftColor;
			}
			else if (e.getButton() == MouseEvent.BUTTON3) {
				drawColor = rightColor;
			}
			else if (e.getButton() == MouseEvent.BUTTON2) {
				drawColor = drawPanel.getBackground();
			}
		}
		
	}
	private void exitMenuItemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
				"Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);
			if (response == JOptionPane.NO_OPTION) {
				return;
			} else {
				exitForm(null);
			}
		
	}
	private void newMenuItemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new project?");
		if (response == JOptionPane.YES_OPTION) {
			g2D.setPaint(drawPanel.getBackground());
			g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
		}
	}
	private void colorMousePressed(MouseEvent e) {
		Component clickedColor = e.getComponent();
		Toolkit.getDefaultToolkit().beep();
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftColor = clickedColor.getBackground();
			leftColorLabel.setBackground(leftColor);
		} 
		else if (e.getButton() == MouseEvent.BUTTON3) {
			rightColor = clickedColor.getBackground();
			rightColorLabel.setBackground(rightColor);
		}
	}
	
	private void exitForm(WindowEvent e) {
		g2D.dispose();
		System.exit(0);
	}
}

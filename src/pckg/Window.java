package pckg;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class Window {
	private JLayeredPane pane;
	private HashMap<Integer, Color> colors = new HashMap<Integer, Color>();
	{
		int i;
		for (i = 0; i < 50; i++) {
			colors.put(i, new Color(255, i * 255 / 50, 0));
		}
		for (int j = 51; j > 0; j--) {
			colors.put(i, new Color(j * 255 / 51, 255, 0));
			++i;
		}
	}
	public Window(int planesNumber) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				InitializeFrame();
				VisualizeAirports();
				new Thread(){
					public void run() {
						Plane[] planes = new Plane[planesNumber];
						JLabel[] labels = new JLabel[planesNumber];
						for (int i = 0; i < planesNumber; ++i) {
							planes[i] = new Plane();
							labels[i] = new JLabel(Integer.toString(i));
							pane.add(labels[i],0);
						}

						while (true) {
							for (int i = 0; i < planesNumber; ++i) {
								if (!planes[i].isAlive()) {
									addCrashSite((int) planes[i].getX(), (int) planes[i].getY());
									planes[i] = new Plane();
									labels[i].setText(planes[i].getName());
								}
								labels[i].setBounds((int) planes[i].getX(), (int) planes[i].getY(), 32, 32);
								labels[i].setForeground(colors.get(planes[i].getFuelPercentage()));
							}
						}
					}
				}.start();
			}
		});

	}

	private void VisualizeAirports() {
		for (Airport port : AirportManager.getList()) {
            JLabel portLabel = new JLabel(new ImageIcon("src//img//airport.png"));
            portLabel.setText(port.getName());
            portLabel.setForeground(Color.BLUE);
            portLabel.setHorizontalTextPosition(JLabel.CENTER);
            portLabel.setVerticalTextPosition(JLabel.BOTTOM);
            portLabel.setBounds((int) port.getX(), (int) port.getY(), 74, 86);
            pane.add(portLabel,1);
        }
	}

	private void InitializeFrame() {
		JFrame frame = new JFrame("Airplanes simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.white);
		frame.setSize(640, 480);
		frame.setLayout(null);
		frame.setVisible(true);
		pane = new JLayeredPane();
		frame.setContentPane(pane);
	}

	public void addCrashSite(int x, int y) {
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(x, y, 16, 16);
		pane.add(label,2);
	}

}

package pckg;

import pckg.plane.Plane;

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
					private Plane[] oldPlanes = new Plane[planesNumber];
					private JLabel[] labels = new JLabel[planesNumber];
					public void run() {
						for (int i = 0; i < planesNumber; ++i) {
							oldPlanes[i] = new Plane();
							labels[i] = new JLabel(Integer.toString(i));
							pane.add(labels[i],0);
						}

						while (true) {
							for (int i = 0; i < planesNumber; ++i) {
								replenishCrashed(i);
								updatePlaneLabel(i);
							}
						}
					}

					private void updatePlaneLabel(int i) {
						labels[i].setBounds((int) oldPlanes[i].getPosition().getX(), (int) oldPlanes[i].getPosition().getY(), 32, 32);
						labels[i].setForeground(colors.get(oldPlanes[i].getFuelPercentage()));
					}

					private void replenishCrashed(int i) {
						if (!oldPlanes[i].isAlive()) {
                            addCrashSite((int) oldPlanes[i].getPosition().getX(), (int) oldPlanes[i].getPosition().getY());
                            oldPlanes[i] = new Plane();
                            labels[i].setText(oldPlanes[i].getName());
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

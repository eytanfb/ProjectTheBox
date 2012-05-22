package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.sound.sampled.ReverbType;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MainWindow extends JFrame
{
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainWindow()
	{
		setTitle("The Box");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//contentPane.add(new JButton("Deneme"));
	}
	
	public void setContentPaneFromOutside(JPanel panel)
	{
		getContentPane().removeAll();
		contentPane.add(panel);
		//contentPane.setSize(new Dimension(panel.getWidth(), panel.getHeight()));
		setBounds(getX(), getY(), panel.getWidth(), panel.getHeight());
		validate();
	}
}

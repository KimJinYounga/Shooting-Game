

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {
	public Main() {
		JFrame frame=new JFrame("Shooting Game");
		JButton bt1;
		JLabel label=new JLabel("Shooting Game");
		frame.setPreferredSize(new Dimension(500,630));
		frame.setLocation(500,100);
		Container contentPane=frame.getContentPane();
		
		//contentPane.setLayout(null);
		
		ImagePanel imagePanel=new ImagePanel();
		label.setFont(new Font("PLAIN",Font.BOLD | Font.ITALIC,60));
		label.setForeground(Color.lightGray);
		bt1=new JButton("start!!");
		bt1.setLocation(100,200);
		
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==bt1) {
					new MyFrame();
					frame.dispose();
					
				}
			}
		});
		contentPane.add(imagePanel);
		imagePanel.add(bt1);
		imagePanel.add(label);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
	
class ImagePanel extends JPanel {
	
	Toolkit toolkit=getToolkit();
	Image image=toolkit.getImage("Space.png");
	public void paintComponent(Graphics g) {
	g.drawImage(image,0,0,this);
	}
	
}
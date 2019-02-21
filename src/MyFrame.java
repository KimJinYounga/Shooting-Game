import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	public MyFrame() {
		Container contentPane=this.getContentPane();
		MyPanel mypanel=new MyPanel();
		contentPane.add(mypanel);
		setTitle("jy Shooting Game");
		setLocation(500,100);
		setSize(500,630);
		setVisible(true);
//			System.out.println("dispose......");
//			dispose();
//			new Result();
	}
	public static void main(String args[]) {
		new Main();
	}
}   
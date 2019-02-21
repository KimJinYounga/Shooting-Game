import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Enemy extends ImgGet {
	double dy,dx;
	Dimension res=Toolkit.getDefaultToolkit().getScreenSize();
	int wid=res.width;
	int height=res.height;
	
	public Enemy (String name) {		
		super(name);
		y=0;
		
	}
	@Override
	public void update() {
		y+=1;
//		if(MyPanel.level_missile==1)
//			y+=1;
//		else if(MyPanel.level_missile==2) {
//			y+=2;
//		}
			
	}
	public void update( int dx, int dy) {
		y+=dy;
		x+=dx;
		
	}
	
}
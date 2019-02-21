import java.awt.event.KeyEvent;

public class Missile extends ImgGet {
	int put=2;
	int i;
	public Missile(String name) {
		super(name);
		y=-200;
	}
	public void update() {
		if(put==1 || put==2) {
			if(MyPanel.level_missile==1)
				y-=9;
			if(MyPanel.level_missile==2)
				y-=6;
			if(MyPanel.level_missile==3)
				y-=4;
		}
		if(y<-100) put=0;
		put=2;
	}
	public void update(int y1) {
		if(put==1 || put==2) {
			
			y-=y1;
		}
		if(y<-100) put=0;
		put=2;
	}
	public void keyPressed(KeyEvent event, int x1, int y1) {
		if(event.getKeyCode() == KeyEvent.VK_SPACE) {
			put=1;
			x=x1;
			y=y1;
		}
	}
	public void keyReleased(KeyEvent event, int x1, int y1) {
		if(event.getKeyCode() ==KeyEvent.VK_SPACE||event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() ==KeyEvent.VK_LEFT || event.getKeyCode() ==KeyEvent.VK_DOWN
				|| event.getKeyCode() ==KeyEvent.VK_UP ) {
		
		x=x1;
		y=y1;
	}
}
}

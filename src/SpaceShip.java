import java.awt.event.KeyEvent;

class SpaceShip extends ImgGet{
	int size=0;
	boolean Up=false;
	boolean Down=false;
	boolean Right=false;
	boolean Left=false;
	boolean space=false;
	public SpaceShip(String name) {
		super(name);
		x=150;
		y=500;
		
	}
	public void keyPressed(KeyEvent event) {
		
		if(event.getKeyCode()==KeyEvent.VK_LEFT) 
			this.Left=true;
		
		else if(event.getKeyCode()==KeyEvent.VK_RIGHT) 
			this.Right=true;
	
		else if(event.getKeyCode()==KeyEvent.VK_UP) 
			this.Up=true;
		
		else if(event.getKeyCode()==KeyEvent.VK_DOWN) 
			this.Down=true;
			
		
	}
	
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.VK_LEFT) {
			this.Left=false;
		}
		else if(event.getKeyCode()==KeyEvent.VK_RIGHT) {
			this.Right=false;
		}
		else if(event.getKeyCode()==KeyEvent.VK_UP) {
			this.Up=false;
		}
		else if(event.getKeyCode()==KeyEvent.VK_DOWN) {
			this.Down=false;
		}
		else if(event.getKeyCode()==KeyEvent.VK_SPACE)
			this.space=false;
	}
	public void Process() {
		if (MyPanel.level_missile==1) size=4;
		else if(MyPanel.level_missile==2 ) size=3;
		else if (MyPanel.level_missile==1) size=2;
		if(this.x<-34 )
			x=-34;
		if(this.x>400)
			x=400;

		if(this.y>500)
			y=500;
		if(Down && Left) {
			x-=size;
			y+=size;
		}
		else if(Down && Right) {
			x+=2;
			y+=2;
		}
		else if(Up && Right) {
			x+=2;
			y-=2;
		}
		else if(Up && Left) {
			x-=2;
			y-=2;
		}
		else if(Down) y+=size;
		else if(Left) x-=size;
		else if(Right) x+=size;
		else if(Up) y-=size;
	}
}
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel implements KeyListener, ActionListener{
	private Timer timer;
	public static int score=0;
	Main main;
	//Result result=new Result();
	Missile [] missile1=new Missile[100]; //1단계 미사일
	Missile [] missile2=new Missile[200];
	Missile [] missile3=new Missile[300];
	SpaceShip spaceship;
	ImgGet img=new ImgGet("bomb.png"); 
	int bombcount=0;
	private Enemy [] enemy=new Enemy[1000]; //적1는 500명으로 설정
	private Enemy [] enemy2=new Enemy[200]; //적2는 200명으로 설정
	private Enemy [] enemy3=new Enemy[100]; //적2는 100명으로 설정
//	BackgroundImage bgimg=new BackgroundImage("bg.png");
	Image back=Toolkit.getDefaultToolkit().createImage("gameover.gif");
	Image image=Toolkit.getDefaultToolkit().createImage("background3.gif");
	Image image2=Toolkit.getDefaultToolkit().createImage("background2.gif");
	Image image3=Toolkit.getDefaultToolkit().createImage("background.gif");
	ArrayList<Enemy> enemies=new ArrayList<Enemy>();
	ArrayList<ImgGet> bomb=new ArrayList<ImgGet>();
	ArrayList<Missile> missiles=new ArrayList<Missile>();
	JLabel label=new JLabel("Total : "+score);
	JLabel label1=new JLabel("+10!!");
	JButton bt1=new JButton("점수 저장");
	boolean over=false;
	int i;
	int index=0;
	int index2=0;
	int index3=0;
	public static int level_missile=1;
	int count=0;
	int count2=0;
	int count3=0;
	int j=0;
	int num=0;
	int width;
	int life=3;
	boolean islife=false;
	int gameover=0;
	boolean pause=false;
	//private MyFrame win;
	// 레벨 up할 떄 마다 왜 미사일이 하나두개씩 화면에 그대로 없어지지도 않고 
	// 떠있을까.....
	
	public MyPanel() {
		super();
		//this.win=win;
		//setLayout(null);
		label.setFont(new Font("Serif",Font.BOLD | Font.ITALIC,30));
		label1.setFont(new Font("Serif", Font.BOLD, 50) );
		addKeyListener(this);
		add(label);
		if(score>=100)
			add(bt1);
		bt1.addActionListener(this);
		this.requestFocus();
		setFocusable(true);
		for(i=0;i<300;i++) 
		{
			if(i<100)
				missile1[i]=new Missile("Missile1.png");
			if(i<200)
				missile2[i]=new Missile("Missile2.png");	
			missile3[i]=new Missile("Missile3.png");
		}
		
			timer=new Timer(200,this); 
			timer.start(); // ActionListener -> 타이머가 끝나면 actionPerformed함수 호출
		if(pause==true)
		{	
			timer.stop();
		}
	
		
		spaceship=new SpaceShip("Ship.png");
		 
		
		// 게임 실행
		
		class MyThread extends Thread {
			int i;
			public void run() {
				try {
					while(true) {		
						for(i=0;i<count;i++)
							enemy[i].update();
						for(i=0;i<count2;i++)
							enemy2[i].update(1,1);
						for(i=0;i<count3;i++) 
							enemy3[i].update(-1,2);
						KeyProcess();
						//if(spaceship.x>=0 || spaceship.x<450)
							spaceship.update();
//						if(level_missile==1) {
//							for(i=0;i<index;i++) {		
//								missile1[i].update(); ////////////////원래는 missile1[i].update();
////							if(level_missile==3)
////								missile1[i].update(4);
//						}
//						}
						
							for(i=0;i<index;i++) 
								missile1[i].update(); 
							for(i=0;i<index2;i++)
								missile2[i].update();
							for(i=0;i<index3;i++)
								missile3[i].update();
						
//						if(level_missile==3) {
//							for(i=0;i<index3;i++)
//								missile3[i].update();
//						}
						repaint();
						if(level_missile==1)
							Thread.sleep(6);
						else if(level_missile==2)
							Thread.sleep(4);
						else if(level_missile==3)
							Thread.sleep(3);
						
						if(islife==true) 
						{
							islife=false;
						}
						if(gameover==1){
							timer.stop();
							for(int i=0;i<enemies.size();i++)
								enemies.remove(i);
						}
						if(pause==true) {
							Thread.sleep(2000);
							pause=false;
							timer.restart();
							
						}
					}
					
						
			}catch(InterruptedException e) {}
			}
		}
		
		Thread t=new MyThread();		
			t.start();
			
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//bgimg.draw(g);
		if(level_missile==1 ||level_missile==4) {
		 if(image!=null) {
			 g.drawImage(image,0,0,this);
			 
		 }
		}
		
		if(level_missile==2||level_missile==4) {
			 if(image!=null) {
				 g.drawImage(image2,0,0,this);
				 
			 }
			}
		if(level_missile==3||level_missile==4) {
			 if(image!=null) {
				 g.drawImage(image3,0,0,this);
			 }
			}
	

		 
		//for(int i=0;i<count;i++) 
			//enemy[i].draw(g);
		spaceship.draw(g);
		Missile M;
	//	if(missiles.size()==0) M=missiles.get(0);
		//첫번째 미사일이 우연히 적에 맞으면 또 안보여... 
		if(life==0) {
		if(back!=null) {
			 g.drawImage(back,23,150,this);
			 gameover=1;
			 
		 }
		}
				
				
		
		for (int i = 0; i < enemies.size(); i++) {
			Enemy E = enemies.get(i);
			
			E.draw(g);
			for(int j=0;j<missiles.size();j++) {  //여기서 missile.size()가 0이기 때문에 초반 화면이 안뜨는 것 같음.......
				
				M=missiles.get(j);
				M.draw(g);
			//System.out.println("E.x="+E.x);
			//System.out.println("missile1[index].x="+missile1[index].x); 
			//System.out.println("missile1[index].y="+missile1[index].y);
			//
			// 화살표를 안누르면 아예 실행 X,,,,,,,,,
//			if(E.x-10<missile1[index].x && E.x+missile1[index].width+10 > missile1[index].x  ) {   // 여기 다시하자!!!!!!!!!!!!!!! shipspace.x...
			
			int long1=0;
			if(level_missile==1) long1=10;
			else if(level_missile==2) long1=19;
			else if(level_missile==3) long1=35;
			//부딪히는거 다시하자....?
			if(islife==false) {
			if(E.x -70 <spaceship.x && spaceship.x < E.x+10 ) {
				if(E.y+20 >= spaceship.y && E.y-60 <spaceship.y) {
					life--;
					score-=5;
					pause=true;
					label.setText("Total : "+score);
					if(life==0) { 
						//label.setVisible(false);
						over=true;
						this.add(bt1);
						timer.stop();
						
						
					} //종료시킴과 동시에 창을 하나 더 띄우고 점수를 파라미터 형식으로 넘겨보자!! 해결 ! 아싸~~~
					System.out.println("생명이 줄었습니다"+life);
					islife=true;
					enemies.remove(i);
					
			}
			}
			}
			if(E.x-long1<M.x && E.x+M.width+long1 > M.x  ) {
//			for(int k=0;k<index;k++) {
					if( E.y+50>M.y && E.y < M.y) {
						//System.out.println("missile.x="+missile1[k].x);
						//System.out.println("missile.y="+missile1[k].y);
						//System.out.println("E.x="+E.x);
						//System.out.println("E.y="+E.y);
						enemies.remove(i);
//						bomb.add(img);
//						ImgGet get=bomb.get(0);
//						get.draw(g);
//						bombcount++;
//						System.out.println("bombcountn "+bombcount );
//						if(bombcount==100) bomb.remove(0);
						missiles.remove(j);
//						System.out.println("미사일 없어짐."+j);						
						score+=10;
						label.setText("Total : "+score);
						if(score>=200 ) {
							this.level_missile=2;
						//	System.out.println("level 2!! ");
						}
						if(score>=650) {
							this.level_missile=3;
						//	System.out.println("level 3!! ");
						}
						
					//	System.out.println("score = "+score);
						
						//System.out.println("index="+k);
						}
				}
			
			//}
			if(E.y> 550) {	
				enemies.remove(E);
			}
		}
		}
	}
	
	public void keyPressed(KeyEvent event) {
		spaceship.keyPressed(event); 
		if(level_missile==1) {
			missile1[index].keyPressed(event);
		//System.out.println("index="+index);
		//if(level_missile==1)  {
			missile1[index].keyPressed(event, spaceship.x+40,spaceship.y); // 
		}
		if(level_missile==2) {
			missile2[index2].keyPressed(event);
			missile2[index2].keyPressed(event, spaceship.x+40, spaceship.y);
		}
		if(level_missile==3) {
			missile3[index3].keyPressed(event);
			missile3[index3].keyPressed(event, spaceship.x+40, spaceship.y);
		}
			//미사일 막 발사했을 때 레벨업 될때 안없어지는 미사일이 적과 닿으면 사라진다...? 왜지?
		//}
		//System.out.println("missile1[index].y="+missile1[index].y);  
		//System.out.println("missile1[index].x="+missile1[index].x); 
		
		if(event.getKeyCode()==KeyEvent.VK_SPACE) {
			if(level_missile==1) {
			missiles.add(missile1[index]);
			index++;
			}
			if(level_missile==2) {
				missiles.add(missile2[index2]);
				index2++;
			}
			if(level_missile==3) {
				missiles.add(missile3[index3]);
				index3++;
			}
			//System.out.println("missile1[index].x="+missile1[index].x); 
		}
		
	}
	
	public void keyReleased(KeyEvent event) {
		spaceship.keyReleased(event);
		//System.out.println("spaceship.x="+spaceship.x);
		missile1[index].keyReleased(event); //spaceship.process처럼 바꿔야할까?.. NO
		missile1[index].x=spaceship.x+40;
		missile1[index].y=spaceship.y;
		missile2[index2].keyReleased(event); //spaceship.process처럼 바꿔야할까?.. NO
		missile2[index2].x=spaceship.x+40;
		missile2[index2].y=spaceship.y;
		missile3[index3].keyReleased(event); //spaceship.process처럼 바꿔야할까?.. NO
		missile3[index3].x=spaceship.x+40;
		missile3[index3].y=spaceship.y;
		
	}
	public void keyTyped(KeyEvent event) {
	}
	public void KeyProcess() {
		spaceship.Process();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt1) {
			//this.level_missile=4;
			System.out.println("랭킹Ranking");
			new Result(score);
			
			
		
		}
		num++;
	//	System.out.println(num);
		if(pause!=true) {
		if(count<1000 ) {
			if(num%2==0) {
				enemy[count]=new Enemy("enemy.png");  
				enemy[count].x=(int)(Math.random()*450);
				enemies.add(enemy[count]);			//
				//System.out.println("enemy add"+count); 
				//System.out.println();
				count++;
				
			}
		}
		if(level_missile>1 && count2<200) {
			if(num%2==0) {
				enemy2[count2]=new Enemy("enemy2-1.png");  
				enemy2[count2].x=(int)(Math.random()*350);
				enemies.add(enemy2[count2]);				//
				count2++;
				}
		}
		if(level_missile>2 &&count3<100) {
			if(num%2==0) {
			enemy3[count3]=new Enemy("enemy3.png");  
			enemy3[count3].x=(int)(Math.random()*500);
			enemies.add(enemy3[count3]);			//
			count3++;
			}
		}
		
	}
}

}
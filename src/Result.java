import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Result implements ActionListener {
	JLabel label1;
	ImageIcon icon;
	int score;
	int size=0;
	int[] scores=new int[size]; //text
	int[] rank=new int[size];
	Scanner s=null;
	String name;
	JLabel user = new JLabel("UserName:\t");
	JButton but1 = new JButton("Submit");
	TextField userField = new TextField(30);
	public Result(int s) {
	
	//파일
	
	icon=new ImageIcon("background.png");
	JFrame frame = new JFrame();
	frame.setLayout(null);
	this.score=s;
	label1 = new JLabel("Your score is "+
			score, JLabel.CENTER);
	label1.setBounds(80,10,220,20);
	
	user.setBounds(90,50,100,50);
	
	userField.setBounds(190,65,80,20);
	
	but1.addActionListener(this);
		
		
	but1.setBounds(140,100,100,20);
	frame.getContentPane().add(label1);
	frame.getContentPane().add(user);
	frame.getContentPane().add(userField);
	frame.getContentPane().add(but1);
	frame.setVisible(true);
	frame.setSize(400,200);
	frame.setLocation(530,150);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			size++;
			FileWriter fw=new FileWriter("save.txt", true);
			Scanner s=new Scanner(new BufferedReader(new FileReader("save.txt")));
			BufferedWriter bf=new BufferedWriter(fw);
			bf.write(userField.getText()+" ");
			bf.write(score+" , ");
			bf.close();
			userField.setText("");
			FileReader fr=new FileReader("save.txt");
			BufferedReader br=new BufferedReader(fr);    //한줄씩읽기위해
            String str=null;   
//            while(s.hasNext()) {
//            	System.out.println(s.next());
//            }
            while((str=br.readLine())!=null)
            {
                System.out.println(str);        //null이 될때까지 한줄씩
            }
            
            br.close();        //읽어온 자원들을 해제한다.
        }catch(Exception n){
            System.out.println(n);
        }
			
		System.exit(0);
		
	}
}
 

 


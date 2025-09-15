import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class Demo01 implements ActionListener{

	private final String[]seri= {"Kupa","Sinek","Karo","Maça"};
	private final String[]name= {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

	private JFrame frame;
	private AllCards deck;
	private AllCards hand1,hand2;
	private JLabel label1,label2,label3;
	private JButton bttnkartver1,bttnkartver2,bttnplay;
	
	
	public Demo01() {
		frame= new JFrame("POKER");
		frame.getContentPane().setLayout(null);
		frame.setSize(1300,800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//deste olusturma
		deck= new AllCards();
		int id=0;
		for(int s=0; s<seri.length;s++) {
			for(int i=0; i<name.length;i++) {
				int value= i>9 ? 10: i+1;
				deck.kartEkle(new Card(id,value,seri[s],name[i],"resimler"));
				id++;
			}
		}
		deck.karistir();
		for(int k=0;k<52;k++) {
			deck.kartGoster(k).setLocation(k*15, 0);
			//deck.kartGoster(k).kartAc();
			frame.getContentPane().add(deck.kartGoster(k));
		}
		hand1= new AllCards();
		bttnkartver1= new JButton("Give card player1");
		bttnkartver1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bttnkartver1.setBounds(0, 200, 141, 38);
		bttnkartver1.addActionListener(this);
		frame.getContentPane().add(bttnkartver1);
		
		hand2= new AllCards();
		bttnkartver2= new JButton("Give card player2");
		bttnkartver2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bttnkartver2.setBounds(151, 200, 141, 38);
		bttnkartver2.addActionListener(this);
		frame.getContentPane().add(bttnkartver2);
		
		bttnplay= new JButton("Play");
		bttnplay.setFont(new Font("Source Sans Pro Black", Font.BOLD | Font.ITALIC, 16));
		bttnplay.setBounds(320, 200, 100, 38);
		bttnplay.addActionListener(this);
		frame.getContentPane().add(bttnplay);
		
		label1= new JLabel("Player 1");
		label1.setFont(new Font("Oswald", Font.BOLD, 13));
		label1.setBounds(10, 250, 100, 50);
		frame.getContentPane().add(label1);
		
		label2= new JLabel("Player 2");
		label2.setFont(new Font("Oswald", Font.BOLD, 13));
		label2.setBounds(10, 501, 100, 50);
		frame.getContentPane().add(label2);
		
		label3= new JLabel("Winner:");
		label3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		label3.setBounds(1078, 65, 113, 57);
		frame.getContentPane().add(label3);
		
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bttnkartver1)) {
			if(hand1.kartSayısı()<5) {
				hand1.kartEkle(deck.kartAl());
				for(int id=0;id<hand1.kartSayısı();id++) {
					hand1.kartGoster(id).setLocation(id*67, 300);
					hand1.kartGoster(id).kartAc();
				}
			}else {
				bttnkartver1.setEnabled(true);
			}
			
		}else if(e.getSource().equals(bttnkartver2)) {
			if(hand2.kartSayısı()<5) {
				hand2.kartEkle(deck.kartAl());
				for(int id=0;id<hand2.kartSayısı();id++) {
					hand2.kartGoster(id).setLocation(id*67, 550);
					hand2.kartGoster(id).kartAc();
				}
			}else {
				bttnkartver2.setEnabled(true);
			}
			
	}else if(e.getSource().equals(bttnplay)) {
		
		 if(hand1.royalFlush()) {
			label3.setText("royal flush Player 1 wins");
		}
		else if(hand2.royalFlush()) {
			label3.setText("royal flush Player 2 wins");
		}
		else if(hand1.straightFlush()) {
			label3.setText("staright flush Player 1 wins");
		}
		else if(hand2.straightFlush()) {
			label3.setText("staright flush Player 2 wins");
		}
		else if(hand1.fullHouse() || hand2.fullHouse()) {
			if(hand1.f>hand1.f) {
				label3.setText("fullhouse Player 1 wins");
			}else if(hand2.f>hand1.f) {
				label3.setText("fullhouse Player 2 wins");
			}
		}
		else if(hand1.flush() || hand2.flush()) {
			if(hand1.flush()) {
				label3.setText("flush Player 1 wins");
			}else {
				label3.setText("flush Player 2 wins");
			}
			
		}
		else if(hand1.straight() || hand2.straight()) {
			if(hand1.straight()) {
				label3.setText("staright Player 1 wins");
			}else {
				label3.setText("staright Player 2 wins");
			}
		}
		else if(hand1.triple() || hand2.triple()) {
			if(hand1.triple()) {
				label3.setText("triple Player 1 wins");
			}else {
				label3.setText("triple Player 2 wins");
			}
		}
		else if(hand1.twoPairs() || hand2.twoPairs()) {
			if(hand1.twoPairs()) {
				label3.setText("two pairs Player 1 wins");
			}else {
				label3.setText("two pairs Player 2 wins");
			}
		}  
		if(hand1.pair() || hand2.pair()) {
			if(hand1.pair()) {
				label3.setText("pairs Player 1 wins");
			}else if(hand2.pair()) {
				label3.setText("pairs Player 2 wins");
			}else if(hand1.pair() && hand2.pair()) {
				if(hand1.x > hand2.x) {
					label3.setText("big pairs Player 1 wins");
				}else if(hand2.x > hand1.x) {
					label3.setText("big pairs Player 2 wins");
				}
			}
		}
		else if((hand1.findBiggestCardNo()>hand2.findBiggestCardNo())) {
			label3.setText("Player 1 wins");
		}
		else if((hand2.findBiggestCardNo()>hand1.findBiggestCardNo())) {
			label3.setText("Player 2 wins");
		} 
	}
	
	}

	public static void main(String[]args) {
		new Demo01();
	}
}

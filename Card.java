import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton{ 
	private final int no;
	private final int value;
	private final String seri; //kupa,maça,karo,sinek
	private final String name; //A,1,2,3,4,5,6,7,8,9,10,J,Q,K
	private final String klasör;
	private ImageIcon resim;
	
	public Card(int no, int value,String seri,String name, String klasör){
		this.no=no;
		this.value=value;
		this.name=name;
		this.resim=new ImageIcon(getClass().getResource("/"+klasör+"/card-back.png"));
		this.klasör=klasör;
		this.seri=seri;
		super.setIcon(resim);
		super.setBounds(0, 0, resim.getIconWidth(), resim.getIconHeight());
	}

	public int getNo() {
		return no;
	}

	public int getValue() {
		return value;
	}

	public String getSeri() {
		return seri;
	}

	public String getName() {
		return name;
	}

	public ImageIcon getResim() {
		return resim;
	}
    public void kartAc() {
    	this.resim= new ImageIcon(getClass().getResource("/"+klasör+"/"+seri+"-"+name+".png"));
        this.setIcon(resim);
    }
	
	public String toString() {
		return seri+ ""+name+ "Value: "+ value;
	}

}

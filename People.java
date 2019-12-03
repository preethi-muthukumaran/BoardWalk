import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class People {
	private int pplx, pply, ppw, pph, pplvy, pplvx;
	private Image pplImage;
	private AffineTransform tx = AffineTransform.getTranslateInstance(pplx, pply);
	
	Rocks [] rocks = new Rocks [10];

//indicates if projectile is live
	public boolean activePeople = false;

//default seagull constructor
	public People(String fileName) {
		pplx = 400;
		pply = 300;
		ppw = 100;
		pph = 100;
		pplvy = 0;
		pplvx = -10;

		pplImage = getImage(fileName);
		updatePeople();

//move?
//movePeople();
	}

//seagull constructor to specify position and file name
	public People(String fileName, int paramSx, int paramSy) {
		pplx = paramSx;
		pply = paramSy;
		ppw = 175;
		pph = 75;
		pplvy = 0;
		pplvx = -10;
		pplImage = getImage(fileName);
		updatePeople();

//movePeople();
	}

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(pplImage, tx, null); // draws GIF
		pplx += pplvx;
		updatePeople();

	}

	public void updatePeople() {
		tx.setToTranslation(pplx, pply); // updates position of Seagull x and y
	}

// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = People.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	//inside Enemy class
			public boolean collided(Seagull s) {
				
				//from parameter -> pass information regarding the "Seagull" object
				Rectangle theSeagull = new Rectangle(s.getSx(), s.getSy(), s.getSw(),s.getSh());
				
				Rectangle me = new Rectangle (pplx, pply, 200, 200); //this class (object) 
				boolean col = me.intersects(theSeagull);
				
				//part 2 of collision is to check enemy projectile collision
				//traverse - visit all projectile objects in  the array 
				for (int i = 0; i < rocks.length; i++) {
					if (rocks[i].active) {
						//active projectile-- must check collision
						
						if (rocks[i].collided(s)) {
							col = true; //found one collision exit loop -- no need to check the rest
							break;
						}
					}
				}
				
				return col; //two ways of becoming true: collision with ship, ship to projectil collision
			
			}
			
	
	
	
//getters and setters for position --> track seagull + mouse/pointer
	public int getPplx() {
		return pplx;
	}

	public void setPplx(int pplx) {
		this.pplx = pplx;
	}

	public int getPply() {
		return pply;
	}

	public void setPpl(int pply) {
		this.pply = pply;
	}

//getters and setters for size --> mouse/pointer
	public int getPpw() {
		return ppw;
	}

	public void setPplw(int pplw) {
		this.ppw = pplw;
	}

	public int getPplh() {
		return pph;
	}

	public void setSh(int pplh) {
		this.pph = pplh;
	}

	public int getPplvy() {
		return pplvy;
	}

	public void setPplvy(int pplvy) {
		this.pplvy = pplvy;
	}

	public int getPplvx() {
		return pplvx;
	}

	public void setPplvx(int pplvx) {
		this.pplvx = pplvx;
	}

	public void movePeople() {
		pplx -= 10;

	}

	public void tilePeople() {
		if (pplx <= -100) {
			pplx = (int) (Math.random() * (800) + 800);
		}

	}

// public void tilePeople() {
// if(pplx== -800) {
// pplx = 800;
// }

}

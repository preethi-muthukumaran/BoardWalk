import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Clouds {
	private int cx, cy, cw, ch, cvy, cvx;
	private Image cloudImage;
	private AffineTransform tx = AffineTransform.getTranslateInstance(cx, cy);
	


//indicates if projectile is live
	public boolean activePeople = false;

//default seagull constructor
	public Clouds(String fileName) {
		cx = 400;
		cy = 300;
		cw = 100;
		ch = 100;
		cvy = 0;
		cvx = -10;

		cloudImage = getImage(fileName);
		updateClouds();

	}

//seagull constructor to specify position and file name
	public Clouds(String fileName, int paramCx, int paramCy) {
		cx = paramCx;
		cy = paramCy;
		cw = 175;
		ch = 75;
		cvy = 0;
		cvx = -10;
		cloudImage = getImage(fileName);
		updateClouds();

//movePeople();
	}

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(cloudImage, tx, null); // draws GIF
		cx += cvx;
		updateClouds();

	}

	public void updateClouds() {
		tx.setToTranslation(cx, cy); // updates position of cloud's x and y
	}

// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Clouds.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	/*//inside Enemy class
			public boolean collided(Seagull s) {
				
				//from parameter -> pass information regarding the "Seagull" object
				Rectangle theSeagull = new Rectangle(s.getSx(), s.getSy(), s.getSw(),s.getSh());
				
				Rectangle me = new Rectangle (cx, cy, 200, 200); //this class (object) 
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
			
			}*/
			
	
	
	
//getters and setters for position --> track seagull + mouse/pointer
	public int getCx() {
		return cx;
	}

	public void setCx(int cx) {
		this.cx = cx;
	}
	
	
	public void moveClouds() {
		cx -= 2;

	}
	

	public void tileClouds() {
		if (cx <= -300) {
			cx = (int) (Math.random() * (800) + 800);
		}

	}


}

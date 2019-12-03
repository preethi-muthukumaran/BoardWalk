import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Rocks {
	private int rx, ry, rw, rh, rvy, rvx;
	private Image sImage;
	private AffineTransform tx = AffineTransform.getTranslateInstance(rx, ry);

//indicates if projectile is live
	public boolean active = false;

//default seagull constructor
	public Rocks(String fileName) {
		rx = 400;
		ry = 300;
		rw = 100;
		rh = 100;
		rvy = 0;

		sImage = getImage(fileName);
		updateProjectile();
	}

//seagull constructor to specify position and file name
	public Rocks(String fileName, int paramSx, int paramSy) {
		rx = paramSx;
		ry = paramSy;
		rw = 175;
		rh = 75;
		rvy = 0;
		sImage = getImage(fileName);
		updateProjectile();
	}

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(sImage, tx, null); // draws GIF
		ry += rvy;
		updateProjectile();

	}

	public void updateProjectile() {
		tx.setToTranslation(rx, ry); // updates position of Seagull x and y
	}

// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Rocks.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	//inside Projectile Class
	public boolean collided (Seagull s) {
		Rectangle pRect = new Rectangle (rx,ry, 50, 50);//projectile
		Rectangle sRect = new Rectangle (s.getSx(), s. getSy(), 200, 299); //ship
		return pRect.intersects(sRect);
	}

//getters and setters for position --> track seagull + mouse/pointer
	public int getRx() {
		return rx;
	}

	public void setSx(int rx) {
		this.rx = rx;
	}

	public int getRy() {
		return ry;
	}

	public void setSy(int ry) {
		this.ry = ry;
	}

//getters and setters for size --> mouse/pointer
	public int getRw() {
		return rw;
	}

	public void setRw(int rw) {
		this.rw = rw;
	}

	public int getRh() {
		return rh;
	}

	public void setSh(int rh) {
		this.rh = rh;
	}

	public int getRvy() {
		return rvy;
	}

	public void setRvy(int rvy) {
		this.rvy = rvy;
	}

	public int getRvx() {
		return rvx;
	}

	public void setRvx(int rvx) {
		this.rvx = rvx;
	}

}
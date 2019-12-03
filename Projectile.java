import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Projectile {
	private int sx, sy, sw, sh, svy, svx;
	private Image sImage;
	private AffineTransform tx = AffineTransform.getTranslateInstance(sx, sy);

//indicates if projectile is live
	public boolean active = false;

//default seagull constructor
	public Projectile(String fileName) {
		sx = 400;
		sy = 300;
		sw = 100;
		sh = 100;
		svy = 0;

		sImage = getImage(fileName);
		updateProjectile();
	}

//seagull constructor to specify position and file name
	public Projectile(String fileName, int paramSx, int paramSy) {
		sx = paramSx;
		sy = paramSy;
		sw = 175;
		sh = 75;
		svy = 0;
		sImage = getImage(fileName);
		updateProjectile();
	}

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(sImage, tx, null); // draws GIF
		sy += svy;
		updateProjectile();

	}

	public void updateProjectile() {
		tx.setToTranslation(sx, sy); // updates position of Seagull x and y
	}

// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Projectile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

//getters and setters for position --> track seagull + mouse/pointer
	public int getSx() {
		return sx;
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

	public int getSy() {
		return sy;
	}

	public void setSy(int sy) {
		this.sy = sy;
	}

//getters and setters for size --> mouse/pointer
	public int getSw() {
		return sw;
	}

	public void setSw(int sw) {
		this.sw = sw;
	}

	public int getSh() {
		return sh;
	}

	public void setSh(int sh) {
		this.sh = sh;
	}

	public int getSvy() {
		return svy;
	}

	public void setSvy(int svy) {
		this.svy = svy;
	}

	public int getSvx() {
		return svx;
	}

	public void setSvx(int svx) {
		this.svx = svx;
	}

}

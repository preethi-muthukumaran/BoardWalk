import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Lives {
	private int lx, ly;
	private Image pplImage;
	private AffineTransform tx = AffineTransform.getTranslateInstance(lx, ly);

//indicates if projectile is live
	public boolean activeLives = false;

//default seagull constructor
	public Lives(String fileName) {
		lx = 760;
		ly = 50;

		pplImage = getImage(fileName);
		updateLives();

//move?
//movePeople();
	}

//seagull constructor to specify position and file name
	public Lives(String fileName, int paramlx, int paramly) {
		lx = paramlx;
		ly = paramly;
		pplImage = getImage(fileName);
		updateLives();

//movePeople();
	}

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(pplImage, tx, null); // draws GIF
		//lx += pplvx;
		updateLives();

	}

	public void updateLives() {
		tx.setToTranslation(lx,ly); // updates position of Seagull x and y
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

//getters and setters for position --> track seagull + mouse/pointer
	public int getLx() {
		return lx;
	}

	public void setLx(int newLx) {
		this.lx = newLx;
	}

	public int getLy() {
		return ly;
	}

	public void setPpl(int newLy) {
		this.ly = newLy;
	}

/*//getters and setters for size --> mouse/pointer
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
// }*/

}

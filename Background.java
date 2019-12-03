import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
	private int bx, by, bw, bh, bvx;
	private Image backgroundImg;
	private AffineTransform tx = AffineTransform.getTranslateInstance(bx, by);

//default constructor: background
	public Background(String backFileName) {
		bx = 0;
		by = 0;
		bw = 800;
		bh = 600;
		bvx = -10;
		backgroundImg = getImage(backFileName);
		updateBackground();
	}

//constructor to be used - specify location + file name
	public Background(String backFileName, int paramBx, int paramBy) {
		bx = paramBx;
		by = paramBy;
		bw = 800;
		bh = 600;
		bvx = -10;
		backgroundImg = getImage(backFileName);
		updateBackground();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(backgroundImg, tx, null);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public int getBx() {
		return bx;
	}

	public void setBx(int bx) {
		this.bx = bx;
	}

	public int getBy() {
		return by;
	}

	public void setBy(int by) {
		this.by = by;
	}

	public void updateBackground() {
		tx.setToTranslation(bx, by);

	}

	public int getBw() {
		return bw;
	}

	public void setBw(int bw) {
		this.bw = bw;
	}

	public int getBvx() {
		return bvx;
	}

	public void setBvx(int bvx) {
		this.bvx = bvx;
	}

	public Image getBack_img() {

		return backgroundImg;
	}

	public void setBack_img(Image back_img) {
		this.backgroundImg = back_img;
	}

//move

	public void backMove() {
		bx -= 5;

	}

	public void tileBackground() {
		if (bx == -800) {
			bx = 800;
		}

	}

}
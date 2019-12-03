import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Seagull {
	private int sx, sy, sw, sh, svy, svx;
	private Image sImage;
	private AffineTransform tx = AffineTransform.getTranslateInstance(sx, sy);

	Projectile[] projectiles = new Projectile[10]; // creates container for projectiles
// People[] people = new People [4];
	Image[] image_people = new Image[4];

//default seagull constructor
	public Seagull(String fileName) {
		sx = 400;
		sy = 300;
		sw = 100;
		sh = 100;
		svy = 0;

//call the load projectile method
		loadProjectiles();

//call load People method
//loadPeople();

		sImage = getImage(fileName);
		updateSeagull();
	}

//seagull constructor to specify position and file name
	public Seagull(String fileName, int paramSx, int paramSy) {
		sx = paramSx;
		sy = paramSy;
		sw = 175;
		sh = 75;
		svy = 0;
		sImage = getImage(fileName);
		updateSeagull();

//call the load projectile method
		loadProjectiles();
//call load people method
//loadPeople();
	}

//method to setup projectiles
	public void loadProjectiles() {
//traverse each spot in the array
//fill it with a Projectile object
		for (int i = 0; i < projectiles.length; i++) {
			projectiles[i] = new Projectile("poop.gif");
		}
	}

//method for firing our projectiles
	public void fire() {

//visit each projectile in array

//each time, check if it is active
//if it is not, activate it
//finally, call break immediately after activating
		for (int i = 0; i < projectiles.length; i++) {
			if (!projectiles[i].active) {
//activate it - the firing
				projectiles[i].active = true;

//projectiles should appear by the ship
//move them wherever ship is
				projectiles[i].setSx(sx + 50);
				projectiles[i].setSy(sy + 110);

//specify velocity for projectile!
				projectiles[i].setSvx(10); // sets projectiles vx, vy?? make sure
				projectiles[i].setSvy(10);
				break; // break will stop searching
//for more projectiles to activate
			}

		}

	}

//method to setup People
// public void loadPeople() {
// //traverse each spot in people array
// //fill it with images + location
// int ranX1 = (int)(Math.random()*(800)+0); //random initial x within screen_width
// int ranX2 = (int)(Math.random()*(800)+800); //random initial x inside screen_width + 800
//
// people[0] = new People("people1", 50, 550);
// people[1] = new People("banana.gif", 100, 550);
// people[2] = new People("banana.gif", 150, 550);
// people[3] = new People("banana.gif", 200, 550);
// }

//method activate people checks if people are in frame, then
//changes boolean active people to true

// public void activatePeople() {
//
// //visit each people in array
// //check if it is in frame --> if yes --> activate
// //if out of frame --> deactivate it
// //call break after activating
// for(int i = 0; i< projectiles.length; i++) {
// if(people[i].getPplx() >=0 || people[i].getPplx() <= 800) {
// people[i].activePeople = true;
// break;
// }
// else {
// people[i].activePeople = false;
// }
//
// }
// }

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(sImage, tx, null); // draws GIF

//ship will handle telling active projectiles to draw themselves
//write code to visit all projectiles
		for (int i = 0; i < projectiles.length; i++) {
//only active projectiles are being drawn
			if (projectiles[i].active) {
				projectiles[i].paint(g);
			}

//later - you should figure out when
//to deactivate an active missle?
//aka when is it necessary to stop
//painting it? --> if it's off the screen

		}

//need to draw people if they are in the frame
//and undraw them if they are not

// for(int i = 0; i<people.length; i++) {
// if(people[i].activePeople) {
// people[i].paint(g);
// }
//
//figure out how to deactivate
	}

	public void updateSeagull() {
		tx.setToTranslation(sx, sy); // updates position of Seagull x and y
	}

// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Seagull.class.getResource(path);
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
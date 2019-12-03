
//star imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
//end imports

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	public static int life = 3; // don't know what this does yet

	int screen_width = 800;
	int screen_height = 600;
	
	//scores
	int score = 0;

	String src = new File("").getAbsolutePath() + "/src/"; // path to image
	Clip hop;

	Background background1 = new Background("background.jpg", 0, 0);
	Background background2 = new Background("background.jpg", screen_width, 0);
	Seagull s = new Seagull("seagull.gif", 400, 300);
	People[] people = new People[2];
	Lives[] heart = new Lives[3];
	Rocks[] rocks = new Rocks [4];
	Clouds[] cloud = new Clouds [2];


// clip.open(audioInputStream);
	Sequencer sequencer;
// Background bg;
	int my_variable = 0; // example
	String lost = "";

// method to instantiate people objects
	public void loadPeople() {
// traverse each spot in people array
// fill it with images + location
// int ranX1 = (int) (Math.random() * (800) + 0); // random initial x within
// screen_width
// int ranX2 = (int) (Math.random() * (800) + 800); // random initial x inside
// screen_width + 800

		people[0] = new People("people.gif", 50, 300);
		people[1] = new People("people.gif", 100, 350);

	}

	public void loadLives() {
		heart[0] = new Lives("heart.png", 700, 5);
		heart[1] = new Lives("heart.png", 730, 5);
		heart[2] = new Lives("heart.png", 760, 5);

	}
	
	public void loadClouds() {
		cloud[0] = new Clouds("cloud.png", 600, 100);
		cloud[1] = new Clouds("cloud.png", 500, 300);
	}
	

	public void paint(Graphics g) {

		super.paintComponent(g);

		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString(("my_variable:") + Integer.toString(my_variable), 0, 870);
		g.setFont(font2);
		g.setColor(Color.CYAN);
		background1.paint(g);
		background2.paint(g);
		s.paint(g);
		
		//scoring stuff
		g.setColor(Color.WHITE);
		g.setFont(new Font("CourierNew", 0, 20));
		g.drawString("Score: " + score, 705, 50);


// paint people object
// future objective: potentially, only paint them when they are active

		for (int i = 0; i < people.length; i++) {
			people[i].paint(g);
		}

		for (int i = 0; i < heart.length; i++) {
			heart[i].paint(g);
		}

		
		for (int i = 0; i < rocks.length; i++) {
			rocks[i].paint(g);
		}
		
		for (int i = 0; i < cloud.length; i++) {
			cloud[i].paint(g);
		}


	}

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);

// method to tile background
	public void tileBackground() {
		background1.backMove();
		background2.backMove();

		if (background1.getBx() <= -800) {
			background1.setBx(screen_width);
		}
		if (background2.getBx() <= -800) {
			background2.setBx(screen_width);
		}

	}

// method to tile people
	public void tilePeople() {
		for (int i = 0; i < people.length; i++) {
			people[i].movePeople();
		}
// if people go off screen, make them reaapea in a random
// loc in second pane
		for (int i = 0; i < people.length; i++) {
			if (people[i].getPplx() == -50) {
				people[i].setPplx((int) (Math.random() * (800) + 800));
			}
		}

	}
	
	public void tileClouds() {
		for (int i = 0; i < cloud.length; i++) {
			cloud[i].moveClouds();
		}
		for (int i = 0; i < cloud.length; i++) {
			if (cloud[i].getCx() == -50) {
				cloud[i].setCx((int) (Math.random() * (800) + 800));
			}
		}

	}
	

	

// move

	public void update() {
		s.updateSeagull();
		background1.updateBackground();
		background2.updateBackground();
		background1.backMove();
		background2.backMove();
		background1.tileBackground();
		background2.tileBackground();

		for (int i = 0; i < people.length; i++) {

			people[i].movePeople();
			people[i].tilePeople();
			people[i].updatePeople();
		}
		
		for (int i = 0; i < cloud.length; i++) {

			cloud[i].moveClouds();
			cloud[i].tileClouds();
			cloud[i].updateClouds();
		}

		for (int i = 0; i < heart.length; i++) {
			heart[i].updateLives();
		}
		
		//send Seagull object to one of the enemies
		//through a method call (collided method)
		
	for (int i = 0; i < rocks.length; i++) {
			if(rocks[i].collided(s)) {
				//right now -> exits if collided with projectiles
				System.out.println("ouch");
				score += 1; 
			}
		} 
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	public Driver() {

		JFrame f = new JFrame();
		f.setTitle("Space Fighter");
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		loadPeople();
		loadLives();
		loadClouds();


		 for (int i = 0; i < rocks.length; i++) { 
			 rocks[i] = new Rocks ("rocks.png",
			 (int) (Math.random() * (600)), (int) (Math.random() * (600))); 
			 }
		 

// Obtains the default Sequencer connected to a default device.
		try {
			sequencer = MidiSystem.getSequencer();
// Opens the device, indicating that it should now acquire any
// system resources it requires and become operational.
			sequencer.open();

// create a stream from a file

			InputStream is = new BufferedInputStream(
					new FileInputStream(new File("Thelazysong.mid").getAbsoluteFile()));

// Sets the current sequence on which the sequencer operates.
// The stream must point to MIDI file data.
			sequencer.setSequence(is);

// Starts playback of the MIDI data in the currently loaded
// sequence.

		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}

// player.addMouseListener(this);
// bg = new Background("background.png");
// do not add to frame, call paint on
// these objects in paint method

		f.add(this);
		t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 87){
			s.setSx(s.getSx()+100);
			s.setSy(s.getSy()+100);
			}
		if (e.getKeyCode() == 83){
			s.setSx(s.getSx()+100);
			s.setSy(s.getSy()+100);
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void reset() {

	}

	boolean on = false;

	@Override
	public void mousePressed(MouseEvent e) {

		s.fire();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent m) {

// this method is triggered anytime there is
// mouse movement on the frame
		s.setSx(m.getX() - s.getSw() / 2);
		s.setSy(m.getY() - s.getSh() * 2);

	}

	public Background getBackground1() {
		return background1;
	}

	public void setBackground1(Background background1) {
		this.background1 = background1;
	}

	public Background getBackground2() {
		return background2;
	}

	public void setBackground2(Background background2) {
		this.background2 = background2;
	}

}
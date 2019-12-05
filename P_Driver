
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
	
	//screen attributes
	int screen_width = 800;
	int screen_height = 600;

	String src = new File("").getAbsolutePath() + "/src/"; // path to image
	Clip hop;
	
	//Object creation + instantiation
	Background background1 = new Background("background.jpg", 0, 0);
    Background background2 = new Background("background.jpg", screen_width, 0);
	Seagull s = new Seagull("seagull.gif", 400, 300);
	People[] people = new People [4];
	
	//clip.open(audioInputStream); //what is this
	Sequencer sequencer;
	Background bg; //what is this?
	int my_variable = 0; // example

	String lost = "";
	int coll_cntr = 0;
	boolean coll_heart3 = false; //does this automate to public?
	boolean coll_heart2 = false; //does this automate to public?
	boolean coll_heart1 = false; //does this automate to public?

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

		//paint all people
		for (int i = 0; i < people.length; i++) {
			people[i].paint(g);
		}
	}

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);

	// loadPeople method - instantiate people objects with image + loc
	public void loadPeople() {
		int person_start = 100;
		for (int i = 0; i < people.length; i++) {
			people[i] = new People("people3.gif", person_start, 440);
			person_start += 200;
		}
	}

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

		//Collision
		//Send seagull object to one of the enemies
		//through a method call (collided method)
		
		//new - might need to delete 12/4
		//create public boolean var that checks if collided, if so,
		//in background, make third  heart active false
		//which will stop painting heart in background
		if(people[0].collided(s)) { //how to do for all people??
			System.out.println("ouch"); //now make score increase
			coll_cntr++;
		}
		
		if(coll_cntr == 1) {
			coll_heart3 = true; //now need to stop painting third heart in background
		}
		
		if(coll_cntr == 2) {
			coll_heart2 = true; //now need to stop painting second heart in background
		}
		
		if(coll_cntr == 3) {
			coll_heart1 = true; //now need to stop painting first heart in background
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
		
		//call loadPeople here to instantiate the people array
		loadPeople();
		

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

		s.fireProjectile();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public int getColl_cntr() {
		return coll_cntr;
	}

	public void setColl_cntr(int coll_cntr) {
		this.coll_cntr = coll_cntr;
	}

	public boolean isColl_heart3() {
		return coll_heart3;
	}

	public void setColl_heart3(boolean coll_heart3) {
		this.coll_heart3 = coll_heart3;
	}

	public boolean isColl_heart2() {
		return coll_heart2;
	}

	public void setColl_heart2(boolean coll_heart2) {
		this.coll_heart2 = coll_heart2;
	}

	public boolean isColl_heart1() {
		return coll_heart1;
	}

	public void setColl_heart1(boolean coll_heart1) {
		this.coll_heart1 = coll_heart1;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent m) {

		// this method is triggered anytime there is
		// mouse movement on the frame
		s.setX(m.getX() - s.getW() / 2);
		s.setY(m.getY() - s.getH() * 2);

	}

	

	
	

}

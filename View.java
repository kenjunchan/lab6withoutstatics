package lab4;
//Anna Bortle

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class View extends JPanel{
	
	final int frameCount = 10;
	int picNum = 0;
	BufferedImage[][] forwardpics;
	BufferedImage[][] idlepics;
	final static int frameWidth = 500;
	final static int frameHeight = 300;
	final static int imgWidth = 165;
	final static int imgHeight = 165;
	
	JFrame frame;
	boolean flag = true;
	
	public View() {
	
		Direction[]	directions = new Direction[]{Direction.NORTH, Direction.NORTHEAST, Direction.EAST, Direction.SOUTHEAST
				, Direction.SOUTH, Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST};
		Direction[]	idle1 = new Direction[]{Direction.EAST,Direction.WEST, Direction.NORTH, Direction.SOUTH};
		Direction[]	idle2 = new Direction[]{Direction.NORTHWEST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.SOUTHEAST};
			
		//load in buffered images into 2d array
		forwardpics = new BufferedImage[directions.length][frameCount];
		for (Direction d: directions) {
			BufferedImage img = createForwardImage(d.getName());
			for(int i = 0; i < frameCount; i++) {
				forwardpics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, 0, getImageWidth(), getImageHeight());
			}
		}
		idlepics = new BufferedImage[directions.length][4];
		int j = 0;
		for (Direction d: idle1) {
			BufferedImage img = createIdleImage("ewns");
			for(int i = 0; i < 4; i++) {
				idlepics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, getImageHeight()*j, getImageWidth(), getImageHeight());
			}
			j++;
		}
		j = 0;
		
		for (Direction d: idle2) {
			BufferedImage img = createIdleImage("nwneswse");
			for(int i = 0; i < 4; i++) {
				idlepics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, getImageHeight()*j, getImageWidth(), getImageHeight());
			}
			j++;
		}
		j = 0;
	}
		
	//Read image from file and return
	private BufferedImage createForwardImage(String direction){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/orc/orc_forward_"+direction+".png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private BufferedImage createIdleImage(String direction){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/orc/orc_idle_"+direction+".png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageWidth() {
		return imgWidth;
	}
	public int getImageHeight() {
		return imgHeight;
	}
	
	//draw the new updated view
	public void update(int xloc, int yloc, Direction dir) {
		
		//create a newjframe only once
		if(flag) {
			frame = new JFrame();
			frame.getContentPane().add(new View());
			frame.setBackground(Color.gray);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(frameWidth, frameHeight);
			frame.setVisible(true);
			flag = false;
		}
		
		//paint the view
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		g.drawImage(forwardpics[Model.getDirect().ordinal()][picNum], Model.getX(), Model.getY(), Color.gray, this);
	}
		
	
		

		
	
	
	
		
}

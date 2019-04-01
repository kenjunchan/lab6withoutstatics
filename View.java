package lab4;
//TEAM 11-12
//B.Azueta, A.Bortle, H.Bridge, K.Chan, C.Lemus

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



	public class View extends JFrame {
		
		Model model;
		int frameCount = 10;
		int picNum = 0;
		BufferedImage[][] forwardpics;
		BufferedImage[][] idlepics;
		final static int frameWidth = 800;
		final static int frameHeight = 800;
		final static int drawDelay = 60; //msec
		final static int imgWidth = 165;
		final static int imgHeight = 165;
		
		
		DrawPanel drawPanel = new DrawPanel();
		Action drawAction;
		Button b;
		
		
		
		public View() {
			//arrays of Directions, used in images read-in
			Direction[]	directions = new Direction[]{Direction.NORTH, Direction.NORTHEAST, Direction.EAST, Direction.SOUTHEAST
					, Direction.SOUTH, Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST};
			Direction[] idle1 = new Direction[] {Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH};
			Direction[] idle2 = new Direction[] {Direction.NORTHWEST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.SOUTHEAST};
				
			//load in buffered images into 2D-arrays
			//forward images
			forwardpics = new BufferedImage[directions.length][frameCount];
			for (Direction d: directions) {
				BufferedImage img = createForwardImage(d.getName());
				for(int i = 0; i < frameCount; i++) {
					forwardpics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, 0, getImageWidth(), getImageHeight());
				}
			}
			//idle images
			idlepics = new BufferedImage[directions.length][4];
			int j=0;
			for (Direction d: idle1) {
				BufferedImage img = createIdleImage("ewns");
				for(int i=0; i < 4; i++) {
					idlepics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, getImageHeight()*j, getImageWidth(), getImageHeight());
				}
				j++;
			}
			j=0;
			for (Direction d: idle2) {
				BufferedImage img = createIdleImage("nwneswse");
				for(int i=0; i < 4; i++) {
					idlepics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, getImageHeight()*j, getImageWidth(), getImageHeight());
				}
				j++;
			}
			
			
			drawAction = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					drawPanel.repaint();
					
				}
				
			};
			
			add(drawPanel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(getPreferredSize());
			drawPanel.setBackground(Color.gray);
			
			//add button to control movement
			b = new Button("click to idle/move");
			b.setLocation(50, 50);
			drawPanel.add(b);
			
			setVisible(true);
			pack();
		}
			
		//Functions for reading in files and creating buffered images
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

		public void bringModeltoView(Model m) {
			this.model = m;
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
			


		@SuppressWarnings("serial")
		private class DrawPanel extends JPanel{
			int picNum = 0;
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.gray);
				
				if (model.getMovementFlag()) { //if moving
					picNum = (picNum + 1) % frameCount;
					g.drawImage(forwardpics[model.getDirect().ordinal()][picNum], model.getX(), model.getY(), Color.gray, this);
				}
				if (!model.getMovementFlag()) { //if idle
					picNum = (picNum + 1) % 4;
					g.drawImage(idlepics[model.getDirect().ordinal()][picNum], model.getX(), model.getY(), Color.gray, this);
				}
				
			}
			public Dimension getPreferredSize() {
					return new Dimension(frameWidth, frameHeight);
			}
		}
		
		//function to add the controller as the ActionListener of the Button
		public void addControllerToButton(Controller c) {
			b.addActionListener(c);
		}
	}



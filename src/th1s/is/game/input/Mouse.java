package th1s.is.game.input;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener{

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	
	public static int getX(){
		return mouseX;
	}
	
	
	public static int getY(){
		return mouseY;
	}
	
	
	public static int getButton(){
		return mouseB;
	}
	public static void resetButton(){
		mouseB = -1;
	}
	
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		mouseB = 4;
	}

	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

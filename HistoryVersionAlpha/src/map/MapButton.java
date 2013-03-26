package map;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MapButton extends JButton implements MapTile
{
	public BufferedImage associatedTile;
	private int xpos;
	private int ypos;
	
	public MapButton(BufferedImage img, int x, int y)
	{
		Dimension size = new Dimension(40, 40);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		
		xpos = x;
		ypos = y;
		
		associatedTile=img;
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(associatedTile, 0, 0, null);
	}
	
	public void ChangeImage(BufferedImage newpict)
	{
		if (newpict!=null)
		{
			associatedTile=newpict;
			repaint();
		}
	}
	
	public int getXPos()
	{
		return xpos;
	}
	
	public int getYPos()
	{
		return ypos;
	}
	
}

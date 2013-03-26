package map;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class MapLabel extends JLabel implements MapTile
{
	public BufferedImage associatedTile;
	private int xpos;
	private int ypos;
	
	MapLabel(BufferedImage img, int x, int y)
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

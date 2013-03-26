package map;

import java.awt.image.BufferedImage;

public interface MapTile
{
	public void ChangeImage(BufferedImage newpict);
	
	public int getXPos();
	
	public int getYPos();
}

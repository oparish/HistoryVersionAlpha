package ui;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import ui.main.MainScreen;


public class Cursors
{
	private static final String[][] CURSORDATA = {{"MoveCursor", "MoveCursor1.png", "MoveCursor2.png"}}; 
	private static HashMap<String, Cursor> staticCursorMap;
	private static HashMap<String, BufferedImage[]> animatedCursorImages;
	private static CursorAnimation runningAnimation = null;
	
	static
	{
		staticCursorMap = new HashMap<String, Cursor>();
		animatedCursorImages = new HashMap<String, BufferedImage[]> ();
		Toolkit toolkit = MainScreen.getInstance().getToolkit();
		for (String[] cursorData : CURSORDATA)
		{
			if (cursorData.length > 2)
			{
				BufferedImage[] cursorImages = new BufferedImage[cursorData.length - 1];
				for (int i = 0; i < cursorImages.length; i++)
				{
					cursorImages[i] = MainScreen.LoadImage(cursorData[i + 1]);
				}
				animatedCursorImages.put(cursorData[0], cursorImages);
			}
			else
			{
				Cursor newCursor = toolkit.createCustomCursor(MainScreen.LoadImage(cursorData[1]), new Point(0, 0), cursorData[0]);
				staticCursorMap.put(cursorData[0], newCursor);
			}
		}
	}
	
	public static void setCursor(String key, JComponent component)
	{
		if (Cursors.runningAnimation != null && Cursors.runningAnimation.isRunning())
			Cursors.runningAnimation.stop();
		if (staticCursorMap.containsKey(key))
		{
			component.setCursor(staticCursorMap.get(key));
		}
		else if (animatedCursorImages.containsKey(key))
		{	
			setAnimatedCursor(key, component);
		}
	}
	
	private static void setAnimatedCursor(String name, JComponent component)
	{
		BufferedImage[] images = animatedCursorImages.get(name);
		Toolkit toolkit = MainScreen.getInstance().getToolkit();
		Cursors.runningAnimation = new CursorAnimation(name, images, component);
	}
}

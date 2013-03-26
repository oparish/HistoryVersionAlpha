package ui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import ui.main.MainScreen;


public class CursorAnimation implements Runnable
{
	private Cursor[] cursors;
	private JComponent component;
	private boolean running = false;
	private int counter = 0;
	
	CursorAnimation(String name, BufferedImage[] images, JComponent component)
	{
		this.cursors = new Cursor[images.length];
		this.component = component;
		Toolkit toolkit = MainScreen.getInstance().getToolkit();
		for (int i = 0; i< images.length; i++)
		{
			cursors[i] = toolkit.createCustomCursor(images[i], new Point(0, 0), name);
		}
		(new Thread(this)).start();
	}

	public void stop()
	{
		running = false;
	}
	
	public boolean isRunning()
	{
		return this.running;
	}
	
	@Override
	public void run()
	{
		running = true;
		while (running)
		{
			if (counter == this.cursors.length)
			{
				counter = 0;
			}
			component.setCursor(cursors[counter]);
			counter++;
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}

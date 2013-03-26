package map;

import java.awt.image.BufferedImage;

import data.Province;


@SuppressWarnings("serial")
public class ProvinceButton extends MapButton
{
	Province province;
	
	public ProvinceButton(BufferedImage img, int x, int y, Province province)
	{
		super(img, x, y);
		this.province = province;
	}
	
	public Province getProvince()
	{
		return this.province;
	}
}

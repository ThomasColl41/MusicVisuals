package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import example.CubeVisual;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new CubeVisual());		
	}

	public void startCubeVisual()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new CubeVisual());
		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startCubeVisual();			
	}
}
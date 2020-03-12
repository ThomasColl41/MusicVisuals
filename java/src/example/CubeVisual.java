package example;

import ie.tudublin.Visual;

public class CubeVisual extends Visual
{
    public void settings()
    {
        size(500, 500, P3D);
    }

    public void draw()
    {
        background(0);
        noFill();
        stroke(255);
        box(100);
    }
}
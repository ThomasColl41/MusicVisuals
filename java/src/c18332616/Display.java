package c18332616;

import java.util.ArrayList;

import ie.tudublin.Visual;

public class Display extends Visual
{
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    public void settings()
    {
        size(800, 600);
    }

    public void setup()
    {
        Shape s1 = new Shape();
        shapes.add(s1);
        colorMode(HSB);
        background(0);
        fill(255);
        stroke(255);
    }


    public void keyPressed()
    {
        if(key == ' ')
        {
            for(Shape s : shapes)
            {
                s.changeShape();
            }
        }

        if(key == 'q')
        {
            shapes.add(new Shape());
        }

        if(key == 'e')
        {
            if(shapes.size() >= 1)
            {
                shapes.remove(0);
            }
        }
    }

    public void instructions()
    {
        fill(255);
        text("\'q\' for new shape, \'e\' to delete a shape, spacebar to change shapes.", 5, height - 5);
    }

    public void drawShapes()
    {
        int i = 0;

        translate(250, 250);

        for(Shape s : shapes)
        {
            float theta = map(i, 0, 10, 0, TWO_PI);
            float x = sin(theta) * 200;
            float y = cos(theta) * 200;
            pushMatrix();
            translate(x, y);
            //rotateY(theta);
            s.render(this);
            popMatrix();
            i++;
        }

    }

    public void draw()
    {
        background(0);
        instructions();
        drawShapes();
    }
}
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
        for(int i = 0; i < 5; i++)
        {
            newShape();
        }
        colorMode(HSB);
        background(0);
        fill(255);
        stroke(255);
    }

    public void newShape()
    {
        switch((int)random(1,6))
        {
            case 1:
            {
                shapes.add(new Ellipse());
                break;
            }

            case 2:
            {
                shapes.add(new Rectangle());
                break;
            }

            case 3:
            {
                shapes.add(new Triangle());
                break;
            }

            case 4:
            {
                shapes.add(new Circle());
                break;
            }

            case 5:
            {
                shapes.add(new Square());
                break;
            }

            default:
            {
                shapes.add(new Ellipse());
                break;
            }
        }
    }


    public void keyPressed()
    {
        if(key == ' ')
        {
            for(Shape s : shapes)
            {
                s.changeShape(s);
            }
        }

        if(key == 'q')
        {
            newShape();
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
            float theta = map(i, 0, shapes.size(), 0, TWO_PI);
            float x = sin(theta) * 200;
            float y = cos(theta) * 200;
            pushMatrix();
            translate(x, y);
            //rotateY(theta);
            s.render(this);
            //print(s.getClass());
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
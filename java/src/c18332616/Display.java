package c18332616;

import java.util.ArrayList;

import ie.tudublin.Visual;

public class Display extends Visual
{
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    float hueOffset = 0;

    enum strokeVal
    {
        INCREASING,
        DECREASING
    }

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
        startMinim();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().play();
        fill(255);
        stroke(255);
    }

    int numShapes = 5;
    public void newShape()
    {
        switch((int)random(1,numShapes + 1))
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

    public void changeShapes()
    {
        int size = shapes.size();

            for(int i = 0; i < size; i++)
            {
                shapes.remove(0);
                newShape();
            }
    }


    public void keyPressed()
    {
        if(key == ' ')
        {
            changeShapes();
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

    float strokeOffset = 0;
    strokeVal fader = strokeVal.INCREASING;
    public void fadeStroke()
    {
        stroke(strokeOffset);

        if(strokeOffset <= 0)
        {
            fader = strokeVal.INCREASING;
        }
        else if (strokeOffset >= 255)
        {
            fader = strokeVal.DECREASING;
        }

        if(fader == strokeVal.INCREASING)
        {
            strokeOffset += getSmoothedAmplitude() * 10;
        }
        else
        {
            strokeOffset -= getSmoothedAmplitude() * 10;
        }
    }

    public void drawLines(int shapeCount)
    {
        int lines[] = new int[shapeCount];

        translate(width / 2, height / 2);

        for(int i = 0; i < lines.length; i++)
        {
            float theta = map(i, 0, lines.length, 0, TWO_PI);
            float x = sin(theta) * 100;
            float y = cos(theta) * 100;
            float outX = x * 4;
            float outY = y * 4;

            push();
            pushMatrix();
            translate(x, y);
            rotate(angle);
            stroke(map(getSmoothedAmplitude(), 0, 0.3f, 255/ 4, 255));
            line(
                x * map(getSmoothedAmplitude(), 0, 0.3f, 0, 1), 
                y * map(getSmoothedAmplitude(), 0, 0.3f, 0, 1),
                -x, 
                -y
            );
            popMatrix();
            pushMatrix();
            translate(outX, outY);
            line(
                outX, 
                outY,
                -outX * map(getSmoothedAmplitude(), 0, 0.3f, 0, 0.25f), 
                -outY * map(getSmoothedAmplitude(), 0, 0.3f, 0, 0.25f)
            );
            popMatrix();
            pop();
        }
    }

    float angle = 0.01f;
    public void drawShapes()
    {
        int i = 0;

        push();
        pushMatrix();
        translate(width / 2, height / 2);

        fadeStroke();
        for(Shape s : shapes)
        {
            float theta = map(i, 0, shapes.size(), 0, TWO_PI);
            float x = sin(theta) * 250;
            float y = cos(theta) * 250;

            //fill();
            pushMatrix();
            translate(x, y);
            // rotate(angle);
            strokeWeight(2);
            fill(((map(getSmoothedAmplitude(), 0, 1, 0, 255) + hueOffset) + (i * 5)) % 255, 
            255,
            255);
            s.render(this);
            popMatrix();
            i++;
            hueOffset += 0.1f;
        }
        popMatrix();
        pop();
        angle += 0.01f;

        drawLines(shapes.size());

    }

    public void draw()
    {
        background(0);
        calculateAverageAmplitude();
        instructions();
        drawShapes();

        //print(getSmoothedAmplitude() + "\n");

        // line(width / 2, height / 2, width / 2 + map(getSmoothedAmplitude(), 0, 1, 10, 500), height / 2);
        // ellipse(width / 2, height / 2, 100, map(getSmoothedAmplitude(), 0, 0.4f, 10, 100));

    }
}
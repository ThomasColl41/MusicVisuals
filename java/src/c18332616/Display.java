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

    int initSize = 10;
    public void setup()
    {
        for(int i = 0; i < initSize; i++)
        {
            shapes.add(newShape());
        }
        colorMode(HSB);
        startMinim();
        loadAudio("disconnected.mp3");
        getAudioPlayer().play();
        fill(255);
        stroke(255);
    }

    int numShapes = 5;
    public Shape newShape()
    {
        switch((int)random(1,numShapes + 1))
        {
            case 1:
            {
                return new Ellipse();
            }

            case 2:
            {
                return new Rectangle();
            }

            case 3:
            {
                return new Triangle();
            }

            case 4:
            {
                return new Circle();
            }

            case 5:
            {
                return new Square();
            }

            default:
            {
                return new Ellipse();
            }
        }
    }

    public void changeShapes()
    {
        int size = shapes.size();

            for(int i = 0; i < size; i++)
            {
                shapes.set(i, newShape());
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
            shapes.add(newShape());
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

    float angle = 0.01f;
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
            stroke(map(getSmoothedAmplitude(), 0, 0.5f, 255/ 4, 255));
            line(
                x * map(getSmoothedAmplitude(), 0, 0.5f, 0, 1), 
                y * map(getSmoothedAmplitude(), 0, 0.5f, 0, 1),
                -x, 
                -y
            );
            popMatrix();
            pushMatrix();
            translate(outX, outY);
            line(
                outX, 
                outY,
                -outX * map(getSmoothedAmplitude(), 0, 0.5f, 0, 0.25f), 
                -outY * map(getSmoothedAmplitude(), 0, 0.5f, 0, 0.25f)
            );
            popMatrix();
            pop();
        }
        angle += 0.01f;
    }

    public void drawShapes()
    {
        push();
        pushMatrix();
        translate(width / 2, height / 2);

        fadeStroke();
        for(int i = 0; i < shapes.size(); i++)
        {
            float theta = map(i, 0, shapes.size(), 0, TWO_PI);
            float x = sin(theta) * 250;
            float y = cos(theta) * 250;
            float colourGap = map(i, 0, shapes.size(), 0, 255);

            pushMatrix();
            translate(x, y);
            strokeWeight(2);
            fill((colourGap + hueOffset) % 255, 255, 255);
            shapes.get(i).render(this);
            popMatrix();
            hueOffset += 0.01f;
        }
        popMatrix();
        pop();
        hueOffset += 0.5f;

        drawLines(shapes.size());

    }

    public void draw()
    {
        background(0);
        calculateAverageAmplitude();
        instructions();
        drawShapes();

        // print(getSmoothedAmplitude() + "\n");

        // line(width / 2, height / 2, width / 2 + map(getSmoothedAmplitude(), 0, 1, 10, 500), height / 2);
        // ellipse(width / 2, height / 2, 100, map(getSmoothedAmplitude(), 0, 0.4f, 10, 100));

    }
}
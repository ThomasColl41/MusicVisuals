package c18332616;

import java.util.ArrayList;

import c18332616.Control.Mode;
import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PFont;

public class Display extends Visual
{
    public void settings()
    {
        size(800, 600);
    }

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    Control c;
    int fontSize = 13;
    public void setup()
    {
        int initShapes = 10;
        for(int i = 0; i < initShapes; i++)
        {
            shapes.add(newShape());
        }
        colorMode(HSB);
        startMinim();
        loadAudio("disconnected.mp3");
        getAudioPlayer().play();
        fill(255);
        stroke(255);
        PFont instruct = createFont("CONSOLA.TTF", fontSize);
        textFont(instruct);

        c = new Control();
    }

    public Shape newShape()
    {
        int numShapes = 5;
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

    public void deleteShape()
    {
        if(shapes.size() >= 1)
        {
            shapes.remove(0);
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

        if(key == 'a')
        {
            if(c.auto == Mode.OFF)
            {
                c.auto = Mode.ON;
            }
            else
            {
                c.auto = Mode.OFF;
            }
        }

        if(key == 'q')
        {
            shapes.add(newShape());
        }

        if(key == 'e')
        {
            deleteShape();
        }
    }

    public void instructions()
    {
        String autoMessage;
        float autoLength = 80;
        float textGap = 5;

        if(c.auto == Mode.OFF)
        {
            autoMessage = "OFF";
        }
        else if(c.auto == Mode.ON)
        {
            autoMessage = "ON";
        }
        else
        {
            autoMessage = "???";
        }

        push();
        stroke(255);
        fill(0);
        rect(-1, height - textGap - fontSize, width + 1, textGap + fontSize);
        fill(255);
        text("\'q\' for new shape, \'e\' to delete a shape, spacebar to change shapes, 'a' to toggle auto on/off.", textGap, height - textGap);
        text("AUTO: " + autoMessage, width - autoLength, height - textGap);
        pop();
    }

    enum strokeVal
    {
        INCREASING,
        DECREASING
    }
    strokeVal fader = strokeVal.INCREASING;
    float strokeOffset = 0;
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

    float angle = 0;
    float ampMin = 0;
    float ampMax = 0.5f;
    public void drawLines(int shapeCount)
    {
        int lines[] = new int[shapeCount];

        push();

        translate(width / 2, height / 2);

        for(int i = 0; i < lines.length; i++)
        {
            float theta = map(i, 0, lines.length, 0, TWO_PI);
            float x = sin(theta) * 100;
            float y = cos(theta) * 100;
            float outX = x * 4;
            float outY = y * 4;

            push();
            translate(x, y);
            rotate(angle);
            stroke(map(getSmoothedAmplitude(), ampMin, ampMax, 255/ 4, 255));
            line(
                x * map(getSmoothedAmplitude(), ampMin, ampMax, 0, 1), 
                y * map(getSmoothedAmplitude(), ampMin, ampMax, 0, 1),
                -x, 
                -y
            );
            pop();
            push();
            translate(outX, outY);
            line(
                outX, 
                outY,
                -outX * map(getSmoothedAmplitude(), ampMin, ampMax, 0, 0.25f), 
                -outY * map(getSmoothedAmplitude(), ampMin, ampMax, 0, 0.25f)
            );
            pop();
        }
        pop();
        angle += 0.01f;
    }

    float hueOffset = 0;
    public void drawShapes()
    {
        push();
        translate(width / 2, height / 2);

        fadeStroke();
        for(int i = 0; i < shapes.size(); i++)
        {
            float theta = map(i, 0, shapes.size(), 0, TWO_PI);
            float x = sin(theta) * 250;
            float y = cos(theta) * 250;
            float colourGap = map(i, 0, shapes.size(), 0, 255);

            push();
            translate(x, y);
            strokeWeight(2);
            fill((colourGap + hueOffset) % 255, 255, 255);
            shapes.get(i).render(this);
            pop();
            hueOffset += 0.01f;
        }
        pop();
        hueOffset += 0.5f;

        drawLines(shapes.size());

    }

    public void draw()
    {
        background(0);
        calculateAverageAmplitude();
        drawShapes();
        instructions();

        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();

        if(c.auto == Mode.ON)
        {
            c.automate(this);
        }
    }
}
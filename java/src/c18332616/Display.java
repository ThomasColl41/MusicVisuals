package c18332616;

import java.util.ArrayList;

import c18332616.Control.Mode;
import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PFont;

public class Display extends Visual
{
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    float hueOffset = 0;
    Control c;

    enum strokeVal
    {
        INCREASING,
        DECREASING
    }

    public void settings()
    {
        size(800, 600);
    }

    int initShapes = 10;
    int fontSize = 13;
    public void setup()
    {
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

    public void deleteShape()
    {
        if(shapes.size() >= 1)
        {
            shapes.remove(0);
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

        if(key == 'm')
        {
            print("0: " + max0 + "count: " + count0 + "\n");
            print("1: " + max1 + "count: " + count1 + "\n");
            print("2: " + max2 + "count: " + count2 + "\n");
            print("3: " + max3 + "count: " + count3 + "\n");
            print("4: " + max4 + "count: " + count4 + "\n");
            print("5: " + max5 + "count: " + count5 + "\n");
            print("6: " + max6 + "count: " + count6 + "\n");
            print("7: " + max7 + "count: " + count7 + "\n");
            print("8: " + max8 + "count: " + count8 + "\n");
        }

        if(key == 'n')
        {
            float[] bandz = getSmoothedBands();
            for(int i = 0; i < bandz.length; i++)
            {
                print(i + ": " + bandz[i] + "\n\n");
            } 
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
            stroke(map(getSmoothedAmplitude(), 0, 0.5f, 255/ 4, 255));
            line(
                x * map(getSmoothedAmplitude(), 0, 0.5f, 0, 1), 
                y * map(getSmoothedAmplitude(), 0, 0.5f, 0, 1),
                -x, 
                -y
            );
            pop();
            push();
            translate(outX, outY);
            line(
                outX, 
                outY,
                -outX * map(getSmoothedAmplitude(), 0, 0.5f, 0, 0.25f), 
                -outY * map(getSmoothedAmplitude(), 0, 0.5f, 0, 0.25f)
            );
            pop();
        }
        pop();
        angle += 0.01f;
    }

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

    float max0 = 0;
    float max1 = 0;
    float max2 = 0;
    float max3 = 0;
    float max4 = 0;
    float max5 = 0;
    float max6 = 0;
    float max7 = 0;
    float max8 = 0;
    public void maxBands()
    {
        float [] bands = getSmoothedBands();
        if(max0 < bands[0])
        {
            max0 = bands[0];
        }
        else if(max1 < bands[1])
        {
            max1 = bands[1];
        }
        else if(max2 < bands[2])
        {
            max2 = bands[2];
        }
        else if(max3 < bands[3])
        {
            max3 = bands[3];
        }
        else if(max4 < bands[4])
        {
            max4 = bands[4];
        }
        else if(max5 < bands[5])
        {
            max5 = bands[5];
        }
        else if(max6 < bands[6])
        {
            max6 = bands[6];
        }
        else if(max7 < bands[7])
        {
            max7 = bands[7];
        }
        else if(max8 < bands[8])
        {
            max8 = bands[8];
        }
    }

    float count0 = 0;
    float count1 = 0;
    float count2 = 0;
    float count3 = 0;
    float count4 = 0;
    float count5 = 0;
    float count6 = 0;
    float count7 = 0;
    float count8 = 0;
    public void countBands()
    {
        float [] bands = getSmoothedBands();

        if(bands[0] > 390)
        {
            count0++;
        }
        else if(bands[1] > 666)
        {
            count1++;
        }
        else if(bands[2] > 485)
        {
            count2++;
        }
        else if(bands[3] > 843)
        {
            count3++;
        }
        else if(bands[4] > 715)
        {
            count4++;
        }
        else if(bands[5] > 1129)
        {
            count5++;
        }
        else if(bands[6] > 1154)
        {
            count6++;
        }
        else if(bands[7] > 415)
        {
            count7++;
        }
        else if(bands[8] > 62)
        {
            count8++;
        }
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

        maxBands();
        countBands();

        // c.check(this);

        if(c.auto == Mode.ON)
        {
            c.automate(this);
        }


        // print(getSmoothedAmplitude() + "\n");

        // line(width / 2, height / 2, width / 2 + map(getSmoothedAmplitude(), 0, 1, 10, 500), height / 2);
        // ellipse(width / 2, height / 2, 100, map(getSmoothedAmplitude(), 0, 0.4f, 10, 100));

    }
}
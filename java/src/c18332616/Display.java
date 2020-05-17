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
        noCursor();
        colorMode(HSB);
        startMinim();
        loadAudio("disconnected.mp3");
        getAudioPlayer().play();
        PFont instruct = createFont("CONSOLA.TTF", getFontSize());
        textFont(instruct);
        c = new Control();
    }

    public Shape newShape()
    {
        int numShapes = 5;
        switch((int)random(1, numShapes + 1))
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
            if(c.getAuto() == Mode.OFF)
            {
                c.setAuto(Mode.ON);
            }
            else
            {
                c.setAuto(Mode.OFF);
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

    float maxHsb = 255;
    public void instructions()
    {
        String autoMessage;
        float autoLength = 80;
        float textGap = 5;

        if(c.getAuto() == Mode.OFF)
        {
            autoMessage = "OFF";
        }
        else if(c.getAuto() == Mode.ON)
        {
            autoMessage = "ON";
        }
        else
        {
            autoMessage = "???";
        }

        push();
        stroke(getMaxHsb());
        fill(0);
        rect(-1, height - textGap - getFontSize(), width + 1, textGap + getFontSize());
        fill(getMaxHsb());
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
        stroke(getStrokeOffset());

        if(getStrokeOffset() <= 0)
        {
            setFader(strokeVal.INCREASING);
        }
        else if (getStrokeOffset() >= maxHsb)
        {
            setFader(strokeVal.DECREASING);
        }

        if(getFader() == strokeVal.INCREASING)
        {
            setStrokeOffset(getStrokeOffset() + getSmoothedAmplitude() * 10);
        }
        else
        {
            setStrokeOffset(getStrokeOffset() - getSmoothedAmplitude() * 10);
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
            rotate(getAngle());
            stroke(map(getSmoothedAmplitude(), getAmpMin(), getAmpMax(), getMaxHsb() / 4, getMaxHsb()));
            line(
                x * map(getSmoothedAmplitude(), getAmpMin(), getAmpMax(), 0, 1), 
                y * map(getSmoothedAmplitude(), getAmpMin(), getAmpMax(), 0, 1),
                -x, 
                -y
            );
            pop();
            push();
            translate(outX, outY);
            stroke(getMaxHsb());
            line(
                outX, 
                outY,
                -outX * map(getSmoothedAmplitude(), getAmpMin(), getAmpMax(), 0, 0.25f), 
                -outY * map(getSmoothedAmplitude(), getAmpMin(), getAmpMax(), 0, 0.25f)
            );
            pop();
        }
        pop();
        setAngle(getAngle() + 0.01f);
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
            float colourGap = map(i, 0, shapes.size(), 0, getMaxHsb());

            push();
            translate(x, y);
            strokeWeight(2);
            fill((colourGap + getHueOffset()) % getMaxHsb(), getMaxHsb(), getMaxHsb());
            shapes.get(i).render(this);
            pop();
            setHueOffset(getHueOffset() + 0.01f);
        }
        pop();
        setHueOffset(getHueOffset() + 0.5f);
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

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public Control getC() {
        return c;
    }

    public void setC(Control c) {
        this.c = c;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public float getMaxHsb() {
        return maxHsb;
    }

    public void setMaxHsb(float maxHsb) {
        this.maxHsb = maxHsb;
    }

    public strokeVal getFader() {
        return fader;
    }

    public void setFader(strokeVal fader) {
        this.fader = fader;
    }

    public float getStrokeOffset() {
        return strokeOffset;
    }

    public void setStrokeOffset(float strokeOffset) {
        this.strokeOffset = strokeOffset;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAmpMin() {
        return ampMin;
    }

    public void setAmpMin(float ampMin) {
        this.ampMin = ampMin;
    }

    public float getAmpMax() {
        return ampMax;
    }

    public void setAmpMax(float ampMax) {
        this.ampMax = ampMax;
    }

    public float getHueOffset() {
        return hueOffset;
    }

    public void setHueOffset(float hueOffset) {
        this.hueOffset = hueOffset;
    }

    @Override
    public String toString() {
        return "Display [ampMax=" + ampMax + ", ampMin=" + ampMin + ", angle=" + angle + ", c=" + c + ", fader=" + fader
                + ", fontSize=" + fontSize + ", hueOffset=" + hueOffset + ", maxHsb=" + maxHsb + ", shapes=" + shapes
                + ", strokeOffset=" + strokeOffset + "]";
    }
}
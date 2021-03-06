package c18332616;

public class Shape extends Display
{
    float x1, y1;

    public Shape()
    {
        this.x1 = 0;
        this.y1 = 0;
    }

    public void render(Display d)
    {
       d.text("SHAPE", getX1(), getY1());
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    @Override
    public String toString() {
        return "Shape [x1=" + x1 + ", y1=" + y1 + "]";
    }

}
package c18332616;

public class Square extends Shape {
    float l;

    public Square()
    {
        this.x1 = -25;
        this.y1 = -25;
        this.l = 50;
    }

    public void render(Display d)
    {
        d.square(getX1(), getY1(), map(d.getSmoothedAmplitude(), getAmpMin(), getAmpMax(), getL() / 2, getL()));
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    @Override
    public String toString() {
        return "Square [l=" + l + "]";
    }
}
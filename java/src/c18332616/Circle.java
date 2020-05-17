package c18332616;

public class Circle extends Shape {
    float r;

    public Circle()
    {
        super();
        this.r = 50;
    }

    public void render(Display d)
    {
        d.circle(getX1(), getY1(), map(d.getSmoothedAmplitude(), getAmpMin(), getAmpMax(), getR() / 2, getR()));
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "Circle [r=" + r + "]";
    }
}
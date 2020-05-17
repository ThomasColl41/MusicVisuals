package c18332616;

public class Ellipse extends Shape {
    float l, w;

    public Ellipse()
    {
        super();
        this.l = 50;
        this.w = 25;
    }

    public void render(Display d)
    {
        d.ellipse(getX1(), getY1(), getL(), map(d.getSmoothedAmplitude(), getAmpMin(), getAmpMax(), getW() / 2, getW()));
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "Ellipse [l=" + l + ", w=" + w + "]";
    }
}
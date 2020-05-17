package c18332616;

public class Rectangle extends Shape {
    float l, w;

    public Rectangle()
    {
        this.x1 = -25;
        this.y1 = -12.5f;
        this.l = 50;
        this.w = 25;
    }

    public void render(Display d)
    {
        d.rect(getX1(), getY1(), getL(), map(d.getSmoothedAmplitude(), getAmpMin(), getAmpMax(), getW() / 2, getW() * 2));
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
        return "Rectangle [l=" + l + ", w=" + w + "]";
    }
}
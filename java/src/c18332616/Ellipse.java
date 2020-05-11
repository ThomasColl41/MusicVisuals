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
        d.ellipse(x1, y1, l, map(d.getSmoothedAmplitude(), 0, 0.3f, w / 2, w));
    }
}
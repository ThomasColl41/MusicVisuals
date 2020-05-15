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
        d.circle(x1, y1, map(d.getSmoothedAmplitude(), 0, 0.5f, r / 2, r));
    }
}
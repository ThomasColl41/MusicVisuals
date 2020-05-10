package c18332616;

public class Ellipse extends Shape {
    float l, w;

    public Ellipse()
    {
        super();
        this.l = 100;
        this.w = 50;
    }

    public void render(Display d)
    {
        d.ellipse(x1, y1, l, w);
    }
}
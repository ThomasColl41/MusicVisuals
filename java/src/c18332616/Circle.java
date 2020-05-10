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
        d.circle(x1, y1, r);
    }
}
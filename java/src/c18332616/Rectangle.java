package c18332616;

public class Rectangle extends Shape {
    float l, w;

    public Rectangle()
    {
        super();
        this.l = 100;
        this.w = 50;
    }

    public void render(Display d)
    {
        d.rect(x1, y1, l, w);
    }
}
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
        d.square(x1, y1, l);
    }
}
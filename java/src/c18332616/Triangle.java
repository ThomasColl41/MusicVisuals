package c18332616;

public class Triangle extends Shape {
    float x2, y2;
    float x3, y3;

    public Triangle()
    {
        this.x1 = 0;
        this.y1 = -25;
        this.x2 = -25;
        this.y2 = 25;
        this.x3 = 25;
        this.y3 = 25;
    }

    public void render(Display d)
    {
        d.triangle(x1, y1, x2, y2, x3, y3);
    }
}
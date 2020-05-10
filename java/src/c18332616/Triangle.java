package c18332616;

public class Triangle extends Shape {
    float x2, y2;
    float x3, y3;

    public Triangle()
    {
        this.x1 = -50;
        this.y1 = -50;
        this.x2 = 50;
        this.y2 = 50;
        this.x3 = 0;
        this.y3 = 50;
    }

    public void render(Display d)
    {
        d.triangle(x1, y1, x2, y2, x3, y3);
    }
}
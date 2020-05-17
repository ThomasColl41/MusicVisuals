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
        d.rect(x1, y1, l, map(d.getSmoothedAmplitude(), ampMin, ampMax, w / 2, w * 2));
    }
}
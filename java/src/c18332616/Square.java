package c18332616;

public class Square extends Shape {
    float l;

    public Square()
    {
        super();
        this.l = 100;
    }

    public void render(Display d)
    {
        d.square(x1, y1, l);
    }
}
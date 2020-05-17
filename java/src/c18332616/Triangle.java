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
        d.triangle(
            getX1(), map(d.getSmoothedAmplitude(), getAmpMin(), getAmpMax(), getY1() / 2, getY1() * 2), 
            getX2(), getY2(), 
            getX3(), getY3()
        );
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public float getX3() {
        return x3;
    }

    public void setX3(float x3) {
        this.x3 = x3;
    }

    public float getY3() {
        return y3;
    }

    public void setY3(float y3) {
        this.y3 = y3;
    }

    @Override
    public String toString() {
        return "Triangle [x2=" + x2 + ", x3=" + x3 + ", y2=" + y2 + ", y3=" + y3 + "]";
    }
}
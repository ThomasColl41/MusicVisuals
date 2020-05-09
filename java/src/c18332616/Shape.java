package c18332616;

import ie.tudublin.Visual;

public class Shape extends Display
{
    float x1, x2, x3;
    float y1, y2, y3;
    float l, w, r;

    int shape;

    public Shape()
    {
        this.x1 = 0;
        this.y1 = 50;
        this.x2 = -50;
        this.y2 = -50;
        this.x3 = 50;
        this.y3 = 50;
        this.l = 100;
        this.w = 50;
        this.r = 50;

        this.shape = (int)random(1,3);
    }

    public void changeShape()
    {
        setShape((int)random(1, 3));
    }

    public void render(Display s)
    {
        switch(getShape())
        {
            case 1:
            {
                s.ellipse(getX1(), getY1(), getR(), getR());
                break;
            }

            case 2:
            {
                s.rect(getX1(), getY1(), getL(), getW());
                break;
            }

            case 3:
            {   
                s.triangle(getX1(), getY1(), getX2(), getY2(), getX3(), getY3());
                break;
            }

            default:
            {
                break;
            }
        }
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getX3() {
        return x3;
    }

    public void setX3(float x3) {
        this.x3 = x3;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public float getY3() {
        return y3;
    }

    public void setY3(float y3) {
        this.y3 = y3;
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }
}
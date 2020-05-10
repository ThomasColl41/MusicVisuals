package c18332616;

import ie.tudublin.Visual;

public class Shape extends Display
{
    float x1, y1;

    public Shape()
    {
        this.x1 = 0;
        this.y1 = 0;
    }

    public void changeShape(Shape s)
    {
        switch((int)random(1,numShapes + 1))
        {
            case 1:
            {
                s = new Ellipse();
                break;
            }

            case 2:
            {
                s = new Rectangle();
                break;
            }

            case 3:
            {
                s = new Triangle();
                break;
            }

            case 4:
            {
                s = new Circle();
                break;
            }

            case 5:
            {
                s = new Square();
                break;
            }

            default:
            {
                s = new Ellipse();
                break;
            }
        }
    }

    public void render(Display d)
    {
       d.text("SHAPE", x1, y1);
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

}
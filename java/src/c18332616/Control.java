package c18332616;

public class Control extends Display {
    
    enum Mode
    {
        OFF,
        ON
    }

    Mode auto;

    public Control()
    {
        auto = Mode.OFF;
    }

    public void check()
    {
        if(auto == Mode.ON)
        {
            print("YES");
        }
        else if(auto == Mode.OFF)
        {
            print("NO");
        }
    }

    boolean cooleddown = true;
    public void automate(Display d)
    {
        float[] bands = d.getSmoothedBands();
        int changeBand = 0;
        int newBand = 5;
        int deleteBand = 8;
        int second = 60;
        float changeThresh = 340;
        float newThresh = 100;
        float deleteThresh = 100;

        if(bands[changeBand] > changeThresh && cooleddown == true)
        {
            d.changeShapes();
            cooleddown = false;
        }
        else if(bands[newBand] > newThresh && cooleddown == true)
        {
            d.shapes.add(newShape());
            cooleddown = false;
        }
        else if(bands[deleteBand] > deleteThresh && cooleddown == true)
        {
            d.deleteShape();
            cooleddown = false;
        }

        if(d.frameCount % second == 0)
        {
            cooleddown = true;
        }
    }
}
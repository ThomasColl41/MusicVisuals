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

    public void check(Display d)
    {
        float[] bands = d.getSmoothedBands();

        for(int i = 0; i < bands.length; i++)
        {
            float gap = map(i, 0, bands.length, 0, d.width);
            d.line(gap, d.height - 100, gap, d.height - 100 - bands[i]);
            d.text(bands[i], gap, d.height - 50);
            d.text(i, gap, d.height - 25);
        }
    }

    boolean changeCooled = true;
    boolean newCooled = true;
    boolean deleteCooled = true;
    public void automate(Display d)
    {
        float[] bands = d.getSmoothedBands();
        int changeBand = 1;
        int newBand = 7;
        int deleteBand = 8;
        int second = 60;
        float changeThresh = 212;
        float newThresh = 250;
        float deleteThresh = 40;

        if(bands[changeBand] > changeThresh && changeCooled == true)
        {
            d.changeShapes();
            changeCooled = false;
        }
        
        if(bands[newBand] > newThresh && newCooled == true)
        {
            d.shapes.add(newShape());
            newCooled = false;
        }
        
        if(bands[deleteBand] > deleteThresh && deleteCooled == true)
        {
            d.deleteShape();
            deleteCooled = false;
        }

        if(d.frameCount % second == 0)
        {
            changeCooled = true;
        }

        if(d.frameCount % (second * 1.75) == 0)
        {
            newCooled = true;
        }

        if(d.frameCount % (second * 1.75) == 0)
        {
            deleteCooled = true;
        }
    }
}
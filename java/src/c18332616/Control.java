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

    boolean changeCooled = true;
    boolean newCooled = true;
    boolean deleteCooled = true;
    public void automate(Display d)
    {
        float[] bands = d.getSmoothedBands();
        int changeBand = 1;
        float changeThresh = 212;
        int newBand = 7;
        float newThresh = 250;
        int deleteBand = 8;
        float deleteThresh = 40;
        int second = 60;

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
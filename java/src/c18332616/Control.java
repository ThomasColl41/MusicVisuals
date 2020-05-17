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

        if(bands[changeBand] > changeThresh && isChangeCooled() == true)
        {
            d.changeShapes();
            setChangeCooled(false);
        }
        
        if(bands[newBand] > newThresh && isNewCooled() == true)
        {
            d.shapes.add(newShape());
            setNewCooled(false);
        }
        
        if(bands[deleteBand] > deleteThresh && isDeleteCooled() == true)
        {
            d.deleteShape();
            setDeleteCooled(false);
        }

        if(d.frameCount % second == 0)
        {
            setChangeCooled(true);
        }

        if(d.frameCount % (second * 1.75) == 0)
        {
            setNewCooled(true);
        }

        if(d.frameCount % (second * 1.75) == 0)
        {
            setDeleteCooled(true);
        }
    }

    public Mode getAuto() {
        return auto;
    }

    public void setAuto(Mode auto) {
        this.auto = auto;
    }

    public boolean isChangeCooled() {
        return changeCooled;
    }

    public void setChangeCooled(boolean changeCooled) {
        this.changeCooled = changeCooled;
    }

    public boolean isNewCooled() {
        return newCooled;
    }

    public void setNewCooled(boolean newCooled) {
        this.newCooled = newCooled;
    }

    public boolean isDeleteCooled() {
        return deleteCooled;
    }

    public void setDeleteCooled(boolean deleteCooled) {
        this.deleteCooled = deleteCooled;
    }

    @Override
    public String toString() {
        return "Control [auto=" + auto + ", changeCooled=" + changeCooled + ", deleteCooled=" + deleteCooled
                + ", newCooled=" + newCooled + "]";
    }
}
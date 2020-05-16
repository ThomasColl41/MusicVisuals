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
}
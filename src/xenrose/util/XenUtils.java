package xenrose.util;

import arc.util.Time;

public class XenUtils {

    //Only numbers greater than 0 (In seconds)
    public boolean Timer(float amount){
        amount *= 100;
        amount -= Time.delta;
        if(amount <= 0) return true;
        else return false;
    }
}

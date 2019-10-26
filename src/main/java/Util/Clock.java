package Util;

import TowerDefense.Enemy;
import jdk.management.resource.internal.TotalResourceContext;
import org.lwjgl.Sys;

public class Clock {
    private static boolean paused = false;
    public static long lastFrame, totalTime;
    public static float d = 0, multiplier = 1;
    public static Enemy e;
    public static long getTime(){
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }

    public static float getDelta(){
        long currentTime  = getTime();
        int delta = (int) (currentTime-lastFrame);
        lastFrame = getTime();
        if(delta*0.001f > 0.01f ) return  0.01f;
        return (float) (delta*0.001f);
    }


    public static float Delta(){
        if(paused) {
            return 0;
        }
        else{
            return d * multiplier;
        }
    }

    public static float TotalTime(){
        return totalTime;
    }

    public static float Multiplier(){
        return multiplier;
    }

    public static void Update(){
        d = getDelta();
        totalTime += d;
    }

    public static void ChangeMultiplier(int change){
        if(multiplier+change >= 1 || multiplier+change <7 ) multiplier += change;
    }

    public static void Pause(){
        if(paused) paused = false;
        else paused = true;
    }



}

package com.threecubed.world;

public class Camera {
    
    public static int x = 5;
    public static int y = 5;

    public static int clamp(int current, int min, int max){
        if(current < min){
            current = min;
        }

        if(current > max) {
            current = max;
        }

        return current;
    }
}

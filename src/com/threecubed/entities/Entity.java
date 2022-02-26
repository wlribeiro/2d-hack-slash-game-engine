package com.threecubed.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.threecubed.main.Game;

public class Entity {
    
    public static BufferedImage LIFEPACK = Game.sprinteSheet.getSprite(15*16, 15*16, 16, 16);
    public static BufferedImage WEAPON = Game.sprinteSheet.getSprite(18*16, 9*16, 16, 32);
    public static BufferedImage BULLET = Game.sprinteSheet.getSprite(15*16, 15*16, 16, 16);
    public static BufferedImage ENEMY = Game.sprinteSheet.getSprite(23*16, 2*16, 16, 16);

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private BufferedImage sprite;

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void render(Graphics g){
        g.drawImage(sprite, this.getX(), this.getY(), null);
    }
    
    public void tick(){
        
    }
}

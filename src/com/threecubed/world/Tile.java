package com.threecubed.world;

import com.threecubed.main.Game;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile {
    
    public static BufferedImage TILE_FLOOR = Game.sprinteSheet.getSprite(16, 4*16, 16, 16);
    public static BufferedImage TILE_WALL = Game.sprinteSheet.getSprite(16, 16, 16, 16);

    private BufferedImage sprite;
    private int x,y;

    public Tile(BufferedImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g){
        g.drawImage(sprite, x, y, null);
    }
}

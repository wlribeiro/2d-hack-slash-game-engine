package com.threecubed.world;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;

import com.threecubed.entities.Enemy;
import com.threecubed.entities.Entity;
import com.threecubed.main.Game;


public class World {

    private Tile[] tiles;
    public static int WIDTH, HEIGHT;
    
    public World(String path){
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[map.getWidth() * map.getHeight()];
            WIDTH = map.getWidth();
            HEIGHT = map.getHeight();
            tiles = new Tile[map.getWidth() * map.getHeight()];
            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

            for(int xx = 0; xx < map.getWidth(); xx++){
                for(int yy = 0; yy < map.getHeight(); yy++){
                    int pixelAtual = pixels[xx + (yy * map.getWidth())];
                    
                    tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx*16, yy*16);

                    if(pixelAtual == 0xFF000000){
                        //  floor
                        tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx*16, yy*16);

                    } else if(pixelAtual == 0xFFFFFFFF){
                        // wall
                        tiles[xx + (yy * WIDTH)] = new WallTile(Tile.TILE_WALL, xx*16, yy*16);

                    } else if(pixelAtual == 0xFF0000ff){
                        // player
                        Game.player.setX(xx*16);
                        Game.player.setY(yy*16);
                        tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx*16, yy*16);
                        System.out.println("player");

                    } else if(pixelAtual == 0xFFff0000) {
                        // lifepack
                        
                    } else if (pixelAtual == 0xFF09ff00) {
                        // enemy
                        Game.entities.add(new Enemy(xx*16, yy*16, 16, 16, Entity.ENEMY));

                    } else if (pixelAtual == 0xFFf2ff00) {
                        // bullet 
                        System.out.println("enemy");


                    } else if (pixelAtual == 0xFF00d5ff) {
                        // weapon
                        Game.entities.add(new Enemy(xx*16, yy*16, 16, 16, Entity.WEAPON));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g){
        for(int xx = 0; xx < WIDTH; xx++){
            for(int yy = 0; yy < HEIGHT; yy++){
                Tile tile = tiles[xx + (yy * WIDTH)];
                tile.render(g);
            }
        }
    }
}

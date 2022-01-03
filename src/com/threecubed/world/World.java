package com.threecubed.world;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;


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

                    if(pixelAtual == 0xFF000000){
                        //  floor
                        tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx*16, yy*16);
                    } else if(pixelAtual == 0xFFFFFFFF){
                        // wall
                        tiles[xx + (yy * WIDTH)] = new WallTile(Tile.TILE_WALL, xx*16, yy*16);
                    } else if(pixelAtual == 0xFF0000ff){
                        // player
                        tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx*16, yy*16);
                    } else {
                        // floor
                        tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx*16, yy*16);
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

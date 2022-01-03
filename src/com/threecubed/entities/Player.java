package com.threecubed.entities;

import java.awt.image.BufferedImage;

import com.threecubed.main.Game;

import java.awt.Graphics;

public class Player extends Entity {

    public boolean right, up, left, down;
    public int speed = 2;
    public int right_dir = 0, left_dir = 1;
    public int dir = right_dir;

    private int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;
    public boolean moved;
    private BufferedImage[] rightPlayer;
    private BufferedImage[] leftPlayer;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        
        rightPlayer = new BufferedImage[4];
        leftPlayer = new BufferedImage[4];

        for(int i = 0; i < 4; i ++ ){
            rightPlayer[i] = Game.sprinteSheet.getSprite((9 + i) * 16, 16, 16, 16);
            leftPlayer[i] = Game.sprinteSheet.getSprite((9 + i) * 16, 16, 16, 16);
        }
        

    }
    
    public void tick(){
        if(right){
            dir = right_dir;
            moved = true;
            x += speed;
        } else if (left){
            dir = left_dir;
            moved = true;
            x -= speed;
        }

        if(up){
            moved = true;
            y -= speed;
        } else if(down){
            moved = true;
            y += speed;
        }

        if(moved){
            frames ++;
            if(frames == maxFrames){
                frames = 0;
                index ++;
                if(index > maxIndex){
                    index = 0;
                }
            }
        }
    }

    public void render(Graphics g){
        if(dir == right_dir){
            g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
        } else if (dir == left_dir){
            g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);

        }

        if(up){
            
        } else if(down){
            
        }
    }
}

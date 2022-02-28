package com.threecubed.entities;

import java.awt.image.BufferedImage;

import com.threecubed.main.Game;
import com.threecubed.world.Camera;
import com.threecubed.world.World;

import java.awt.Graphics;

public class Player extends Entity {

    public boolean right, up, left, down;
    public double speed = 1; // resolver bug do 1.x // quando a velocidade do personagem está em valor double, ele acaba travando na pareder
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
            leftPlayer[i] = mirrorImage(Game.sprinteSheet.getSprite((9 + i) * 16, 16, 16, 16));
        }
        

    }

    public BufferedImage mirrorImage(BufferedImage image){
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int imageType = image.getType();

        BufferedImage invertedImage = new BufferedImage(imageWidth, imageHeight, imageType);

        for(int y = 0; y < imageHeight; y++){
            for(int lx = 0, rx = imageWidth - 1; lx < imageWidth; lx++, rx--){
                int pixel = image.getRGB(lx, y);
                invertedImage.setRGB(rx, y, pixel);
            }
        }

        return invertedImage;
    }
    
    public void tick(){
        if(right && World.isFree((int)(x + speed), this.getY())){
            dir = right_dir;
            moved = true;
            x += speed;
        } else if (left && World.isFree((int)(x - speed), this.getY())){
            dir = left_dir;
            moved = true;
            x -= speed;
        }

        if(up  && World.isFree(this.getX(), (int)(y - speed))){
            moved = true;
            y -= speed;
        } else if(down  && World.isFree(this.getX(), (int)(y + speed))){
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

        Camera.x = Camera.clamp(this.getX() - (Game.WIDTH - x), 0, World.WIDTH*16 - Game.WIDTH);
        Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT - y), 0, World.HEIGHT*16 - Game.HEIGHT);
    }

    public void render(Graphics g){
        if(dir == right_dir){
            g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        } else if (dir == left_dir){
            g.drawImage(leftPlayer[index],this.getX() - Camera.x, this.getY() - Camera.y, null);

        }

        if(up){
            
        } else if(down){
            
        }
    }
}

package com.threecubed.entities;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import com.threecubed.main.Game;
import com.threecubed.world.Camera;
import com.threecubed.world.World;

public class Enemy extends Entity {

    private int speed =  1;

    private int maskx = 8;
    private int masky = 8;
    private int maskw = 10;
    private int maskh = 10;

    private boolean DEBUG = false;

    private int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;
    
    public boolean moved;

    public int right_dir = 0, left_dir = 1;
    public int dir = right_dir;

    private BufferedImage[] rightEnemy;
    private BufferedImage[] leftEnemy;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        
        rightEnemy = new BufferedImage[4];
        leftEnemy = new BufferedImage[4];

        for(int i = 0; i < 4; i ++ ){
            rightEnemy[i] = Game.sprinteSheet.getSprite((23 + i)*16, 2*16, 16, 16);
            leftEnemy[i] = Game.sprinteSheet.getSprite((23 + i)*16, 2*16, 16, 16);
        }
    }
    
// bug da movimentação. Os inimigos travam nas paredes ou colidem e se proprios 
    public void tick(){
        int positionX = Game.player.getX();
        int positionY = Game.player.getY();

        int xNextPositionRight = (int)(x + speed);
        int xNextPositionLeft = (int)(x - speed);
        int yNextPositionUp = (int)(y + speed);
        int yNextPositionDown = (int)(y - speed);

        boolean xRightIsFree = World.isFree(xNextPositionRight, this.getY());
        boolean xLeftIsFree = World.isFree(xNextPositionLeft, this.getY());
        boolean yUpIsFree = World.isFree(this.getX(), yNextPositionUp);
        boolean yDownIsFree = World.isFree(this.getX(), yNextPositionDown);

        boolean xRightIsColliding = isColiding(xNextPositionRight, this.getY());
        boolean xLeftIsColliding = isColiding(xNextPositionLeft, this.getY());
        boolean yUpIsColliding = isColiding(this.getX(), yNextPositionUp);
        boolean yDownIsColliding = isColiding(this.getX(), yNextPositionDown);

        if(((int)x < positionX) && xRightIsFree && !xRightIsColliding){
            x += speed;
            dir = right_dir;
            moved = true;
        } else if(((int)x > positionX) && xLeftIsFree && !xLeftIsColliding){
            x -= speed;
            dir = left_dir;
            moved = true;
        }

        if(((int)(y) < positionY) && yUpIsFree && !yUpIsColliding){
            y += speed;
            moved = true;
        } else if(((int)(y) > positionY) && yDownIsFree && !yDownIsColliding){
            y -= speed;
            moved = true;
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

    public boolean isColiding(int xnext, int ynext){
        Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh);
        for(int i = 0; i < Game.enemies.size(); i++){
            Enemy enemy = Game.enemies.get(i);
            if(enemy == this){
                continue;
            }
            Rectangle targetEnemy = new Rectangle(enemy.getX() + maskx, enemy.getY() + masky,maskw, maskh);
            if(enemyCurrent.intersects(targetEnemy)){
                return true;
            }
        }

        return false;
    }

    public void render(Graphics g){
        if(dir == right_dir){
            g.drawImage(rightEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        } else if (dir == left_dir){
            g.drawImage(leftEnemy[index],this.getX() - Camera.x, this.getY() - Camera.y, null);
        }

        if(DEBUG) {
            g.setColor(Color.BLUE);
            g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);
        }
    
    }
}

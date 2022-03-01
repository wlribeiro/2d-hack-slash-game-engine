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

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
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

        } else if(((int)x > positionX) && xLeftIsFree && !xLeftIsColliding){
            x -= speed;

        }

        if(((int)(y) < positionY) && yUpIsFree && !yUpIsColliding){
            y += speed;

        } else if(((int)(y) > positionY) && yDownIsFree && !yDownIsColliding){
            y -= speed;

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
        super.render(g);
        if(DEBUG) {
            g.setColor(Color.BLUE);
            g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);
        }
            
    }
}

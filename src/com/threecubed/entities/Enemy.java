package com.threecubed.entities;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import com.threecubed.main.Game;
import com.threecubed.world.Camera;
import com.threecubed.world.World;

public class Enemy extends Entity {

    private double speed =  1;

    private int maskx = 8;
    private int masky = 8;
    private int maskw = 10;
    private int maskh = 10;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }
    
// bug da movimentação. Os inimigos travam nas paredes ou colidem e se proprios 
    public void tick(){
        int positionX = Game.player.getX();
        int positionY = Game.player.getY();
        boolean xRightIsFree = World.isFree((int)(x + speed), positionY);
        boolean xLeftIsFree = World.isFree((int)(x - speed), positionY);
        boolean yUpIsFree = World.isFree(positionX, (int)(y + speed));
        boolean yDownIsFree = World.isFree(positionX, (int)(y - speed));

        if(((int)x < positionX) && xRightIsFree && !isColiding((int)(x + speed), positionY)){
            x += speed;

        } else if(((int)x > positionX) && xLeftIsFree && !isColiding((int)(x - speed), positionY)){
            x -= speed;

        }

        if(((int)(y) < positionY) && yUpIsFree && !isColiding(positionX, (int)(y + speed))){
            y += speed;

        } else if(((int)(y) > positionY) && yDownIsFree && !isColiding(positionX, (int)(y - speed))){
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
        g.setColor(Color.BLUE);
        g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);
    }
}

package com.threecubed.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SprinteSheet {
    
    private BufferedImage spritesheet;

    public SprinteSheet(String path){
        try {
            spritesheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int x, int y, int width, int height){
        return spritesheet.getSubimage(x, y, width, height);
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
}

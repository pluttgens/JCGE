/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.components.Renderer;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.Script;
import com.gamefactory.services.ServiceLocator;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author scalpa
 */
public class AnimatorFourDirections extends Script {

    private ArrayList<BufferedImage> animationsLeft;
    private ArrayList<BufferedImage> animationsRight;
    private ArrayList<BufferedImage> animationsUp;
    private ArrayList<BufferedImage> animationsDown;

    private Position position;
    private Position previousPosition;
    private Renderer renderer;

    @Override
    public void init(ComponentManager owner) {
        this.owner = owner;
        this.position = (Position) this.owner.getComponent(Position.class);
        this.previousPosition = position;
        this.renderer = (Renderer) this.owner.getComponent(Renderer.class);
    }

    public void loadAnimations(Image animations) {
        //Ouest
        animationsLeft.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "ouest/1O.png"));
        animationsLeft.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "ouest/2O.png"));
        animationsLeft.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "ouest/3O.png"));
        animationsLeft.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "ouest/4O.png"));
        
        //Est
        animationsRight.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "ouest/1E.png"));
        animationsRight.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "ouest/2E.png"));
        animationsRight.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "ouest/3E.png"));
        animationsRight.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "ouest/4E.png"));
        
        //Nord
        animationsUp.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "nord/1N.png"));
        animationsUp.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "nord/2N.png"));
        animationsUp.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "nord/3N.png"));
        animationsUp.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "nord/4N.png"));
        
        //Sud
        animationsDown.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "sud/1S.png"));
        animationsDown.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "sud/2S.png"));
        animationsDown.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "sud/3S.png"));
        animationsDown.add((BufferedImage) ServiceLocator.getAssetManager().getAsset("image", "sud/4S.png"));
    }

    @Override
    public void update() {
        if (this.previousPosition.distanceWith(position) > 50) {
            Position.Orientation orientation = position.getOrientation();
            BufferedImage current = this.renderer.getImage();
            switch (orientation) {
                case DOWN:
                    this.renderer.setImage(getNextImage(current, this.animationsDown));
                    break;
                case UP:
                    this.renderer.setImage(getNextImage(current, this.animationsUp));
                    break;
                case LEFT:
                    this.renderer.setImage(getNextImage(current, this.animationsLeft));
                    break;
                case RIGHT:
                    this.renderer.setImage(getNextImage(current, this.animationsRight));
                    break;
            }
        }
    }

    public BufferedImage getNextImage(Image img, ArrayList<BufferedImage> images) {
        BufferedImage image;
        for (int i = 0; i < images.size(); i++) {
            if (img.equals(images.get(i))) {
                image = images.get((i + 1) % images.size());
                return image;
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        }
        return images.get(1);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gamefactory.scripts;

import com.gamefactory.components.Position;
import com.gamefactory.displayable.ComponentManager;
import com.gamefactory.displayable.ScriptManager;
import com.gamefactory.game.Game;
import com.gamefactory.services.ServiceLocator;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Adrien
 */
public class PlayerClikScript extends UpdateScript<ComponentManager> implements MouseListener{

    private int velocity;
    private Position position;
    private List<Point> line1;
    private Point point;
    private int i = 0;
    
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
        
    @Override
    public void init(ScriptManager script) {
        super.init(script);
        this.setVelocity(1);
        ServiceLocator.getGameWindow().getFrame().addMouseListener(this);
        ServiceLocator.getGameWindow().getCanvas().addMouseListener(this);
    }

    @Override
    public void load() {
        this.position = (Position) owner.getOwner().getComponent(Position.class);
        this.position.setX(Game.WIDTH / 2 - 20);
        this.position.setY(Game.HEIGHT / 2 - 20);
    }

    @Override
    public void mouseClicked(MouseEvent e) {  
        //line1 = null;
        i=0;
        int buttonDown = e.getButton();
        if(buttonDown == MouseEvent.BUTTON3) {
            this.line1 = line(position.getX(),position.getY(),e.getX(),e.getY());
        
        // a gauche - a gauche en haut - a gauche en bas
        if(e.getX() < position.getX() && e.getY() == position.getY() || e.getX() < position.getX() && e.getY() < position.getY() || e.getX() < position.getX() && e.getY() > position.getY()){
            position.setOrientation(Position.Orientation.LEFT);
        }
        
        // a droite - a droite en haut - a droite en bas
        if(e.getX() > position.getX() && e.getY() == position.getX() || e.getX() > position.getX() && e.getY() < position.getY() || e.getX() > position.getX() && e.getY() > position.getY()){
            position.setOrientation(Position.Orientation.RIGHT);
        }
               
        //en haut 
        if(e.getX() == position.getX() && e.getY() > position.getY()){
            position.setOrientation(Position.Orientation.DOWN);
        }
                        
        //en bas
        if(e.getX() == position.getX() && e.getY() < position.getY()){
            position.setOrientation(Position.Orientation.UP);
        }
            
    }
    }
    
    @Override
    public void execute() {
        if(line1 == null){
            //rien
        }
        else {
            if(i < line1.size()){
                position.setX((int) line1.get(i).getX());
                position.setY((int) line1.get(i).getY());
                i = i + velocity;
            }
            else{
                i =0;
                line1=null;
            }
        }
    }

    
    public List<Point> line(int x, int y, int x2, int y2) {
        ArrayList<Point> line = new ArrayList<>();
        int w;
        int h;
        System.out.println("Position click x=" + x2 + " y=" + y2);

        do{
            w = x2 - x;
            h = y2 - y;
            if(w < 0) x = x - 1;      
            if(w > 0) x = x + 1;
            if(w == 0){
                if(h < 0) y = y - 1;
                if(h > 0) y = y + 1;
            }
            
            
            line.add(new Point(x, y)); 
        }
        while(w != 0 || h != 0);
        System.out.println("Position finale héro x=" + x + " y=" + y);        
        return line;
    }
    
    
    /*
    public List<Point> line(int x, int y, int x2, int y2) {
        ArrayList<Point> line = new ArrayList<>();
        int w = x2 - x;
        int h = y2 - y;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w < 0) {
            dx1 = -1;
        } else if (w > 0) {
            dx1 = 1;
        }
        if (h < 0) {
            dy1 = -1;
        } else if (h > 0) {
            dy1 = 1;
        }
        if (w < 0) {
            dx2 = -1;
        } else if (w > 0) {
            dx2 = 1;
        }
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if (!(longest > shortest)) {
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if (h < 0) {
                dy2 = -1;
            } else if (h > 0) {
                dy2 = 1;
            }
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++) {
            line.add(new Point(x, y));
            numerator += shortest;
            if (!(numerator < longest)) {
                numerator -= longest;
                x += dx1;
                y += dy1;
            } else {
                x += dx2;
                y += dy2;
            }
        }
        return line;
    }
    */

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}

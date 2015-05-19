/**
 * 
 */
package com.Gamesareme.TCO.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.input.MouseInput;

/**
 * @author Oliver
 *
 */
@SuppressWarnings("serial")
public class Button extends Rectangle {
    
    private String text;
    private int yOff;

    /**
     * Creates a button from a specified top-left corner and a specified width and height
     * @param x The far-left x-value of the button
     * @param y The highest y-value of the button
     * @param width The width of the button
     * @param height The height of the button
     */
    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);

    }
    
    /**
     * Sets the text to be displayed on the button
     * @param text the text displayed on the button
     * @return this
     */
    public Button setText(String text){
        this.text = text;
        return this;
    }
    
    public Button setYOff(int offset){
    	this.yOff = offset;
    	return this;
    }
    
    
    /**
     * Utility method to facilitate the drawing of rectangles for our buttons
     * @param g the Graphics context of our <strong> <code> Game class </strong> </code>
     * @param offset  The horizontal offset determines how to far or near to the left of the button to start drawing the rectangle
     */
    public void drawButton(Graphics g, int offset){
        int xx = x + offset;
        int yy = y + yOff;// + 38;
        
        
        if(MouseInput.MOUSE.intersects(this)){  //show our buttons in yellow when our mouse hovers over them
            g.setColor(Color.YELLOW);
        }else  //other wise, show the button in white
            g.setColor(Color.WHITE);
        
        if(!MouseInput.pressed && MouseInput.MOUSE.intersects(this))
            g.drawRect(x, y, width, height);
        else if(MouseInput.pressed && MouseInput.MOUSE.intersects(this)) //fills in the button when we press it
            g.fillRect(x, y, width, height);
        else
            g.drawRect(x,y,width,height);
        
        g.setColor(Color.RED);
        g.drawString(text, xx, yy);
    }
}

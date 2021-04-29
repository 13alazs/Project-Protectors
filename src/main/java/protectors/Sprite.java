package protectors;

import java.awt.Graphics;
import java.awt.Image;

/*
    Project Name: Project Protectors
    Members: Árvai Balázs, Bíró Benjámin, Fodor Ádám, Zászlós Dorottya Beáta
    @author Bíró Benjámin
*/

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    /**
     * Constructor for the Sprite class.
     * 
     * @param x
     *            The x coord for the image.
     * @param y
     *            The y coord for the image.
     * @param width
     *            The width of the image.
     * @param height
     *            The height of the image.
     * @param image
     *            The sprite for the image.
     */
    public Sprite(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    /**
     * Function to draw the image on the screen.
     * 
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    /**
     * Returns the x coord of the image.
     * 
     * @return X coord of the image.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coord of the image.
     * 
     * @return Y coord of the image.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the height of the image.
     * 
     * @return Height of the image.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the image.
     * 
     * @return Width of the image.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the sprite of the image.
     * 
     * @return Sprite of the image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the x coord of the image.
     * 
     * @param x
     *            X coord of the image.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coord of the image.
     * 
     * @param y
     *            Y coord of the image.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the height of the image.
     * 
     * @param height
     *            Height of the image.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the width of the image.
     * 
     * @param width
     *            Width of the image.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the sprite for the image.
     * 
     * @param image
     *            Sprite of the image.
     */
    public void setImage(Image image) {
        this.image = image;
    }
}

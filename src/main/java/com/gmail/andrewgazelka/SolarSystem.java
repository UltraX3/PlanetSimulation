package com.gmail.andrewgazelka;

import processing.core.PApplet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andrewgazelka on 6/6/17.
 */
public class SolarSystem {

    private double x;
    private double y;
    private double dt;
    private PApplet pApplet;

    private Set<Body> bodies;

    /** A solar system is represented by a coordinate system with the origin at the center, spanning x to the left, x
     *  to the right; y to the top, y to the bottom.
     *
     * @param x
     * @param y
     * @param dt time increment between physics frames. A smaller dt yields more accurate calculations
     * @param pApplet The applet we are drawing on
     */
    public SolarSystem(final double x, final double y, double dt, PApplet pApplet){

        /*
        TODO — QUESTION: Is requiring a pApplet input bad here? I feel this limits versatility of the program, especially if
        someone _didn't_ want to use a pApplet. ex: What if someone wanted to not have a graphics display at all or
        use another library besides processing?
         */

        this.x = x;
        this.y = y;
        this.dt = dt;
        bodies = new HashSet<Body>();
        this.pApplet = pApplet;
    }

    public double getX() {
        return x;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Set<Body> getBodies() {
        return bodies;
    }

    /**
     *
     * @param solarMagnitude
     * @return The length in solar system coordinates (what we defined in the constructor) to screen coordinates)
     */
    double solarLengthToScreenLengthX(double solarMagnitude){
        return (((float) solarMagnitude / x)*pApplet.width);
    }

    double solarLengthToScreenLengthY(double solarMagnitude){
        return (((float) solarMagnitude / y)*pApplet.height);
    }

    /**
     *
     * @param solarPoint
     * @return Similar to solarLengthToScreenLength, but returns actual screen coordinates, not just magnitudes.
     */
    Point solarPointToScreenPoint(Point solarPoint){

        double pointX = (((float) solarPoint.getX() / x)*pApplet.width+pApplet.width/2);
        double pointY = (((float) solarPoint.getY() / y)*pApplet.height+pApplet.height/2);

        return new Point(pointX,pointY);
    }

    /**
     * Updates the bodies
     * @param draw
     */
    void update(boolean draw){
        /*
            TODO — QUESTION: I feel this "boolean draw" makes the program less clean. What if we implemented a solar system
            that printed out logs of coordinates but didn't draw? Be annoying to constantly put false.
         */

        for(Body body : bodies){
            for(Body externalBody : bodies){
                if(body != externalBody){ // We are eliminating internal forces
                    Point fog = PhysicsUtil.forceGravMagnitude(body,externalBody);
                    if(draw){
                        /*
                        TODO — QUESTION: This is why I used draw—to be able to draw lines from one coordinate to another.
                        boolean draw would probably be better as an interface, but still, is repeatedly putting a
                        blank interface a good practice?
                         */
                        Point screenPoint1 = solarPointToScreenPoint(body.getSolarPos());
                        Point screenPoint2 = solarPointToScreenPoint(externalBody.getSolarPos());
                        float sw = (float) Math.log10(fog.magnitudeSquared())/50;
                        if(sw > 0)
                            pApplet.strokeWeight(sw);
                        pApplet.line((int)screenPoint1.getX(),(int)screenPoint1.getY(),(int)screenPoint2.getX(),(int)screenPoint2.getY());
                    }
                    body.addTempForce(fog,dt);
                }
            }
        }
        for(Body body : bodies){
            body.updatePosition(dt);
        }
    }




}

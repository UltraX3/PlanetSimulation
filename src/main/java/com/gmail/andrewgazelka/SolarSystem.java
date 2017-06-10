package com.gmail.andrewgazelka;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolarSystem implements CoordTransformable2D {

    private double x;
    private double y;
    private double dt;
    private List<Coupler<Point,Point>> forcesSinceLastUpdate;

    private Set<Body> bodies;

    /** A solar system is represented by a coordinate system with the origin at the center, spanning x to the left, x
     *  to the right; y to the top, y to the bottom.
     *
     * @param x
     * @param y
     * @param dt time increment between physics frames. A smaller dt yields more accurate calculations
     */
    public SolarSystem(final double x, final double y, double dt){

        this.x = x;
        this.y = y;
        this.dt = dt;
        bodies = new HashSet<Body>();
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


    public double currentLengthToScreenLengthX(double solarMagnitude, int screenWidth){
        return (((float) solarMagnitude/ x)*screenWidth);
    }

    public double currentLengthToScreenLengthY(double solarMagnitude, int screenHeight){
        return (((float) solarMagnitude / y)*screenHeight);
    }

    /**
     *
     * @param solarPoint
     * @return Similar to solarLengthToScreenLength, but returns actual screen coordinates, not just magnitudes.
     */
    public Point currentPointToScreenPoint(Point solarPoint, int screenWidth, int screenHeight){
        double pointX = (((float) solarPoint.getX() / x)*screenWidth+screenWidth/2);
        double pointY = (((float) solarPoint.getY() / y)*screenHeight+screenHeight/2);
        return new Point(pointX,pointY);
    }

    public List<Coupler<Point, Point>> getForcesSinceLastUpdate() {
        return forcesSinceLastUpdate; // TODO — QUESTION: Is this a good practice to grab intermediate data from a method?
    }

    /**
     * Updates the bodies
     * @param logInformation — Whether to log information to getForces
     */
    void update(boolean logInformation){
        if(logInformation)
            forcesSinceLastUpdate = new ArrayList<>();
        else
            forcesSinceLastUpdate = null; // There was no information last update
        for(Body body : bodies){
            for(Body externalBody : bodies){
                if(body != externalBody){ // We are eliminating internal forces
                    Point fog = PhysicsUtil.forceGravMagnitude(body,externalBody);
                    if(logInformation){
                        //Point screenPoint1 = body.getSolarPos();
                        //Point screenPoint2 = externalBody.getSolarPos();
                        forcesSinceLastUpdate.add(new Coupler<>(body.getSolarPos(),externalBody.getSolarPos()));
                        //Point screenPoint1 = solarPointToScreenPoint(body.getSolarPos());
                        //Point screenPoint2 = solarPointToScreenPoint(externalBody.getSolarPos());
                        //float sw = (float) Math.log10(fog.magnitudeSquared())/50;
                        //if(sw > 0)
                        //    pApplet.strokeWeight(sw);
                        //pApplet.line((int)screenPoint1.getX(),(int)screenPoint1.getY(),(int)screenPoint2.getX(),(int)screenPoint2.getY());
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

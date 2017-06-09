package com.gmail.andrewgazelka;

import processing.core.PApplet;

/**
 * A single object behaving like a point-particle that has position, mass, and velocity.
 */
public class Body {

    private double radius;
    private Point solarPos;
    private double mass;
    private Point solarVel;
    private DrawBehavior drawBehavior;
    private SolarSystem solarSystem;

    public Body(double radius, Point solarPos, double mass, Point solarVel,DrawBehavior drawBehavior, SolarSystem solarSystem) {
        this.radius = radius;
        this.solarPos = solarPos;
        this.mass = mass;
        this.solarVel = solarVel;
        this.drawBehavior = drawBehavior;
        this.solarSystem = solarSystem;

        /*
        TODO — QUESTION: Is it bad that I included solarSystem in the constructor? What if someone wanted to make a body
        that didn't belong to a solarSystem? The sole reason I included solarSystem (a possible parent of this class)
        was so that performDraw could access it
         */
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public double getRadius() {
        return radius;
    }

    public Point getSolarPos() {
        return solarPos;
    }

    public double getMass() {
        return mass;
    }

    public Point getSolarVel() {
        return solarVel;
    }

    public void setRadius(long radius) {
        this.radius = radius;
    }

    public void setSolarPos(Point solarPos) {
        this.solarPos = solarPos;
    }

    public void setMass(long mass) {
        this.mass = mass;
    }

    public void setSolarVel(Point solarVel) {
        this.solarVel = solarVel;
    }

    void addTempForce(Point force, double dt){
        // F = ma
        // a = dv/dt
        // F = m * dv/dt
        // F * dt / m = dv
        double dv_x = force.getX()*dt / mass;
        double dv_y = force.getY()*dt / mass;
        solarVel.setX(solarVel.getX()+dv_x);
        solarVel.setY(solarVel.getY()+dv_y);
    }

    /*
    TODO — QUESTION: Is it bad to reference pApplet here as well? This goes back to the issue if someone doesn't want
    to use a pApplet. Additionally, Is it bad to use the Strategy Pattern when passing in the current object
    (as seen here) into the performXYZ(...) method?
     */
    void performDraw(PApplet pApplet){
        drawBehavior.performDraw(this, pApplet);
    }

    void updatePosition(double dt){
        solarPos.setX((solarPos.getX()+solarVel.getX()*dt));
        solarPos.setY((solarPos.getY()+solarVel.getY()*dt));
    }
}

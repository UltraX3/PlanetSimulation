package com.gmail.andrewgazelka;

/**
 * A single object behaving like a point-particle that has position, mass, and velocity.
 */
public class Body {

    private double radius;
    private Point solarPos;
    private double mass;
    private Point solarVel;
    private BodyDrawBehavior drawBehavior;

    public Body(double radius, Point solarPos, double mass, Point solarVel /*, BodyDrawBehavior drawBehavior*/) {
        this.radius = radius;
        this.solarPos = solarPos;
        this.mass = mass;
        this.solarVel = solarVel;
        //this.drawBehavior = drawBehavior;
        /*
        TODO : QUESTION — Should drawBehavior be a parameter of Body? I have moved it
         */
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

    void updatePosition(double dt){
        solarPos.setX((solarPos.getX()+solarVel.getX()*dt));
        solarPos.setY((solarPos.getY()+solarVel.getY()*dt));
    }
}

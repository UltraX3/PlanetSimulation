package com.gmail.andrewgazelka;

/**
 * A single object behaving like a point-particle that has position, mass, and velocity.
 */
public class Body {

    private double radius;
    private Point position;
    private double mass;
    private Point velocity;

    public Body(double radius, Point position, double mass, Point velocity /*, BodyDrawBehavior drawBehavior*/) {
        this.radius = radius;
        this.position = position;
        this.mass = mass;
        this.velocity = velocity;
        //this.drawBehavior = drawBehavior;
        /*
        TODO : QUESTION — Should drawBehavior be a parameter of Body? I have moved it
         */
    }

    public double getRadius() {
        return radius;
    }

    public Point getPosition() {
        return position;
    }

    public double getMass() {
        return mass;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setRadius(long radius) {
        this.radius = radius;
    }

    public void setPosition(Point Pos) {
        this.position = Pos;
    }

    public void setMass(long mass) {
        this.mass = mass;
    }

    public void setVelocity(Point Vel) {
        this.velocity = Vel;
    }

    void addTempForce(Point force, double dt){
        // F = ma
        // a = dv/dt
        // F = m * dv/dt
        // F * dt / m = dv
        double dv_x = force.getX()*dt / mass;
        double dv_y = force.getY()*dt / mass;
        velocity.setX(velocity.getX()+dv_x);
        velocity.setY(velocity.getY()+dv_y);
    }

    void updatePosition(double dt){
        position.setX((position.getX()+ velocity.getX()*dt));
        position.setY((position.getY()+ velocity.getY()*dt));
    }
}

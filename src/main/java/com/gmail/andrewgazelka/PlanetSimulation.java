package com.gmail.andrewgazelka;

import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.Random;
import java.util.Set;

public class PlanetSimulation extends PApplet {

    // Screen dimensions
    final static private int screenX = 900;
    final static private int screenY = 900;

    // Calculation (physics) frames per visual frame
    final static private int calcPerFrame = 1000;

    // How to draw the object
    private DrawBehavior drawBehavior;

    // The object we are storing bodies in
    private SolarSystem solarSystem;

    public PlanetSimulation(){
        this.solarSystem = new SolarSystem(3*141e10,3*141e10,1e2,this);

        // The behavior as to how to draw the objects
        this.drawBehavior = new ConcreteCircleDrawBehavior();

        // Think this is good... TODO — QUESTION: would it be better if I implement solarSystem.setBodies(...) and
        // instantiate the HashMap in the PlanetSimulator constructor?
        Set<Body> bodySet = solarSystem.getBodies();
        // Populate with random the bodies. Modifies bodySet
        populatePsuedoRandomBodies(bodySet,drawBehavior);
    }

    /**
     * This method populates bodySet with a number of random objects. Most of the stuff here is just crazy numbers and
     * calcs that aren't really important.
     * @param bodySet
     * @param drawBehavior
     */
    void populatePsuedoRandomBodies(Set<Body> bodySet, DrawBehavior drawBehavior){
        Body sun = new Body(1e3, new Point(0,0), 1.989e30,new Point(0,0),drawBehavior,solarSystem);
        Random random = new Random();
        for(int i = 0; i < 5; i ++){
            Point position = new Point((1+random.nextFloat())*141e8,(1+random.nextFloat())*141e8);
            double mass = Math.pow(random.nextFloat(),2)*1.989e28;
            Point velocity = new Point((1+random.nextFloat())*2e4,-(1+random.nextFloat())*2e4);
            Body body = new Body(1,position,mass,velocity,drawBehavior,solarSystem);
            bodySet.add(body);
        }
        bodySet.add(sun);
    }

    // The method that starts it all
    public static void main(String[] args) {
        PApplet.main("com.gmail.andrewgazelka.PlanetSimulation");
        new PlanetSimulation();
    }

    public void settings(){
        size(screenX,displayWidth);
    }

    public void setup(){
        settings();
        fill(0,0,0);
        frameRate(30);
    }

    // This method draws to the screen
    public void draw() {
        background(0, 0, 0);
        stroke(255,0,0);
        for(int i = 0; i < calcPerFrame-1; i++){
            solarSystem.update(false);
        }
        solarSystem.update(true);
        for (Body body : solarSystem.getBodies()) {
            stroke(0,0,255);
            float stroke = (float)Math.log(body.getMass()/100000);
            strokeWeight(stroke);
            fill(100, 100, 100);
            body.performDraw(this);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKey()){
            case 'w':
                solarSystem.setX(solarSystem.getX()/2);
                solarSystem.setY(solarSystem.getY()/2);
                break;
            case 's':
                solarSystem.setX(solarSystem.getX()*2);
                solarSystem.setY(solarSystem.getY()*2);
                break;
            case 'd':
                solarSystem.setDt(solarSystem.getDt()*2);
                break;
            case 'a':
                solarSystem.setDt(solarSystem.getDt()/2);
                break;
            case 'r':
                Set<Body> bodySet = solarSystem.getBodies();
                bodySet.clear();
                populatePsuedoRandomBodies(bodySet,drawBehavior);
        }
    }
}

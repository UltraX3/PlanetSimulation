package com.gmail.andrewgazelka;

import processing.core.PApplet;

public class MainPAppletSolarSystemDrawer implements SolarSystemDrawer {

    private BodyDrawBehavior drawBehavior;
    private PApplet pApplet;
    private int calcPerFrame;

    public MainPAppletSolarSystemDrawer(BodyDrawBehavior drawBehavior, PApplet pApplet, int calcPerFrame){
        this.drawBehavior = drawBehavior;
        this.pApplet = pApplet;
        this.calcPerFrame = calcPerFrame;
    }

    public void draw(SolarSystem solarSystem){
        pApplet.background(0, 0, 0);
        pApplet.stroke(255,0,0);
        for(int i = 0; i < calcPerFrame-1; i++){
            solarSystem.update(false);
        }
        solarSystem.update(true);
        for(Coupler<Point,Point> coupler : solarSystem.getForcesSinceLastUpdate()){
            Point solarPoint1 = coupler.getFirst();
            Point solarPoint2 = coupler.getSecond();
            Point screenPoint1 = solarSystem.currentPointToScreenPoint(solarPoint1,pApplet.width,pApplet.height);
            Point screenPoint2 = solarSystem.currentPointToScreenPoint(solarPoint2,pApplet.width,pApplet.height);
            pApplet.strokeWeight(1);
            pApplet.line((int)screenPoint1.getX(),(int)screenPoint1.getY(),(int)screenPoint2.getX(),(int)screenPoint2.getY());
        }
        for (Body body : solarSystem.getBodies()) {
            pApplet.stroke(0,0,255);
            float stroke = (float)Math.log(body.getMass()/100000);
            pApplet.strokeWeight(stroke);
            pApplet.fill(100, 100, 100);
            drawBehavior.performDraw(body,solarSystem);
        }
    }
}

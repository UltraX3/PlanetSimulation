package com.gmail.andrewgazelka;

import processing.core.PApplet;

public class PAppletCircleBodyDrawBehavior implements BodyDrawBehavior {

    private PApplet applet;

    public PAppletCircleBodyDrawBehavior(PApplet applet){
        this.applet = applet;
    }

    public void performDraw(Body body, CoordTransformable2D coordTransformable2D) {
        Point screenPoint = coordTransformable2D.currentPointToScreenPoint(body.getSolarPos(),applet.width,applet.height);
        applet.ellipse((int) screenPoint.getX(), (int) screenPoint.getY(), 10,10);
    }
}

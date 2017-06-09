package com.gmail.andrewgazelka;

import processing.core.PApplet;

public class ConcreteCircleDrawBehavior implements DrawBehavior {

    public void performDraw(Body body, PApplet pApplet) {
        Point screenPoint = body.getSolarSystem().solarPointToScreenPoint(body.getSolarPos());
        double radiusX = body.getSolarSystem().solarLengthToScreenLengthX(body.getRadius());
        double radiusY = body.getSolarSystem().solarLengthToScreenLengthY(body.getRadius());

        // I am temporarily making the radius of the ellipse 10 because if it weren't, since objects in space are so
        // small, it would be impossible to see (get that 8K Dell monitor quick!!!!). Hypothetically, we would use
        // radiusX and radiusY
        pApplet.ellipse((int) screenPoint.getX(), (int) screenPoint.getY(), 10,10);
    }
}

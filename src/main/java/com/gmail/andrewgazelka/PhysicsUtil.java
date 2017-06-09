package com.gmail.andrewgazelka;

public class PhysicsUtil {

    final private static double E_D = 10e9;
    final public static double G = 6.67e-11;

    /**
     * Newton's Law of Gravity. Has an error distance of E_D because we don't want to "divide by 0" when objects are on
     * top of eachother, resulting an an infinite force. (not good because dt, the time interval is not infinitely small)
     * @param body1
     * @param body2
     * @return
     */
    public static Point forceGravMagnitude(Body body1, Body body2){

        double distanceX = -body1.getSolarPos().getX()+body2.getSolarPos().getX();
        double distanceY = -(body1.getSolarPos().getY()-body2.getSolarPos().getY());

        double distance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);

        double numerator = (G * (body1.getMass() * body2.getMass()));

        double magnitude = numerator/(distanceX*distanceX + distanceY*distanceY);

        Point fog = new Point(distanceX/(distance+ E_D)*magnitude,distanceY/(distance+ E_D)*magnitude);
        return fog;

    }
}

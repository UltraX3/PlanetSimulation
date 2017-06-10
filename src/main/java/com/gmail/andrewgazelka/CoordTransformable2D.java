package com.gmail.andrewgazelka;

/**
 *  This is mainly used to transform coordinates in a certain 2D space to 2D screen coordinates
 */
public interface CoordTransformable2D {

    double currentLengthToScreenLengthX(double currentMagnitude, int screenWidth);

    double currentLengthToScreenLengthY(double currentMagnitude, int screenHeight);

    /**
     *
     * @param solarPoint
     * @return Similar to solarLengthToScreenLength, but returns actual screen coordinates, not just magnitudes.
     */

    /**
     * Transforms coordinates into standard Processing coordinates. If using another library that has a different
     * coordinate system, a double transformation can take place.
     * @param solarPoint
     * @param screenWidth
     * @param screenHeight
     * @return
     */
    Point currentPointToScreenPoint(Point solarPoint, int screenWidth, int screenHeight);
}

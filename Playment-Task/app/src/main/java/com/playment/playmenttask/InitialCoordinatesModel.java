package com.playment.playmenttask;

final class InitialCoordinatesModel {
     float x1;
     float y1;
    static String coordinates1;

    public static void setCoordinates1(String coordinates1) {
        InitialCoordinatesModel.coordinates1 = coordinates1;
    }

    public static String getCoordinates1() {
        return coordinates1;
    }

    public void setX(float x) {
        x1 = x;
    }

    public void setY(float y) {
        y1 = y;
    }

    public float getX() {
        return x1;
    }

    public float getY() {
        return y1;
    }
}

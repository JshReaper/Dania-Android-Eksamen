package com.jshch.androidgameeksamen;

public class Vector2 {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2 Subtract ( Vector2 v2)
    {
        return new Vector2(this.x - v2.getX(), this.y - v2.getY());
    }

    public Vector2 Add ( Vector2 v2)
    {
        return new Vector2(this.x + v2.getX(), this.y + v2.getY());
    }

    public Vector2 Scale (Vector2 v1, float f)
    {
        return new Vector2(v1.getX() * f, v1.getY() * f);
    }

    // Normalisere en vektor og giver den normaliserede vektor uden at overskrive den originale
    public Vector2 Normalized()
    {
        float length = (float)Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()));

        float newX = this.getX() / length;
        float newY = this.getY() / length;

        return new Vector2(newX, newY);

    }

    // Giver en vinkel mellem 2 vektorer som tit bruges i matematik
    public float AngleToVector2D(Vector2 V1)
    {
        float length = (float)Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()));
        float lengthV2 = (float)Math.sqrt((V1.getX() * V1.getX()) + (V1.getY() * V1.getY()));
        float total = ((this.getX() * V1.getY()) + (this.getY() * V1.getY())) / (length * lengthV2);
        float toReturn = (float)Math.acos(total);
        toReturn = (float)(toReturn * (180 / Math.PI));
        return toReturn;

    }

    // Denne funktion giver distancen mellem 2 vektor punkter
    public float Dist(Vector2 vec)
    {
        float length = (float)Math.sqrt((this.getX() - vec.getX()) * (this.getX() - vec.getX()) + (this.getY() - vec.getY()) * (this.getY() - vec.getY()));

        return length;
    }

    // retunere skalar produktet af en vektors koordinater
    public float Scalar()
    {
        float scalar = this.getX() * this.getX() + this.getY() * this.getY();
        return scalar;
    }
    // retunere skalar produktet af to vektorers koordinater
    public float Scalar(Vector2 vec)
    {
        float scalar = this.getX() * vec.getX() + this.getY() * vec.getY();
        return scalar;
    }

    // retunere en nul vektor
    public Vector2 Zero()
    {
        return new Vector2(0, 0);
    }

    // Hatter en vektor (drejer den 90 grader med uret)
    public void Hat()
    {
        float tempX = this.x;
        this.x = -this.y;
        this.y = tempX;
    }

}

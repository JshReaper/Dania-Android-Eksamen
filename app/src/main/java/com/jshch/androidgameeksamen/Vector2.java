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


    // Constructors
    public Vector2(float x, float y) {
        setX(x);
        setY(y);
    }

    public Vector2(){
        setX(0);
        setY(0);
    }


    //subtract two vectors from another
    public Vector2 Subtract ( Vector2 v2) {
        return new Vector2(this.x - v2.getX(), this.y - v2.getY());
    }

    // add two vectors together
    public Vector2 Add ( Vector2 v2) {
        return new Vector2(this.x + v2.getX(), this.y + v2.getY());
    }

    // scale the vector with a given float
    public static Vector2 Scale (Vector2 v1, float scale) {
        return new Vector2(v1.getX() * scale, v1.getY() * scale);
    }

    // returns a normalised version of the given vector without replacing the given vector.
    public Vector2 Normalized() {
        float length = (float)Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()));

        float newX = this.getX() / length;
        float newY = this.getY() / length;

        return new Vector2(newX, newY);
    }

    // Returns the angle between 2 vectors
    public float AngleToVector2D(Vector2 V1) {
        float length = (float)Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()));
        float lengthV2 = (float)Math.sqrt((V1.getX() * V1.getX()) + (V1.getY() * V1.getY()));
        float total = ((this.getX() * V1.getY()) + (this.getY() * V1.getY())) / (length * lengthV2);
        float toReturn = (float)Math.acos(total);
        return ((float)(toReturn * (180 / Math.PI)));
    }

    // returns the length of the given Vector
    public float Distance(Vector2 vec) {
        float length = (float)Math.sqrt((this.getX() - vec.getX()) * (this.getX() - vec.getX()) + (this.getY() - vec.getY()) * (this.getY() - vec.getY()));
        return length;
    }

    // returns the scalar of a vectors coordinates
    public float Scalar() {
        float scalar = this.getX() * this.getX() + this.getY() * this.getY();
        return scalar;
    }
    // returns the scalar of a vector
    public float Scalar(Vector2 vec) {
        float scalar = this.getX() * vec.getX() + this.getY() * vec.getY();
        return scalar;
    }

    // returns a zero vector
    public static Vector2 Zero() {
        return new Vector2(0, 0);
    }

    // hats a vector (turns it 90 against the clock)
    public void Hat() {
        float tempX = this.x;
        this.x = -this.y;
        this.y = tempX;
    }

    public static Vector2 VectorFromAngle(float angle){

        float x = 1;
        float y = 0;

        x = (1 * (float)Math.cos(angle) - 0 * (float)Math.sin(angle));
        y = (1 * (float)Math.sin(angle) + 0 * (float) Math.cos(angle));

        return new Vector2(x,y);
    }

}

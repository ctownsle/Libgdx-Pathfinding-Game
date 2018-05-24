package com.mygdx.game;

public class Location {

    private int x;
    private int y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }

    @Override
    public boolean equals(Object obj) {
        final Location other = (Location) obj;
        if(other.getY() == this.y && other.getX() == this.x){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return x;
    }
}

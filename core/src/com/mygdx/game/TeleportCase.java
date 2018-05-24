package com.mygdx.game;

import java.util.HashMap;

public class TeleportCase {

    private Location entrancea;
    private Location entranceb;
    private static HashMap<Location, Location> necessary = new HashMap<>();

    public TeleportCase(Location a, Location b){
        this.entrancea = a;
        this.entranceb = b;
        necessary.put(a, b);
    }

    public Location getBuddyA(){
        return entranceb;
    }

    public Location getBuddyB() {
        return entrancea;
    }

    public static HashMap<Location, Location> getPairs() {
        return necessary;
    }

    @Override
    public boolean equals(Object obj) {
        final TeleportCase other = (TeleportCase) obj;
        if(this.entrancea.equals(other.entrancea) && this.entranceb.equals(other.entranceb) ||
                (this.entrancea.equals(other.entranceb) || this.entranceb.equals(other.entrancea))){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return entrancea.getY();
    }
}

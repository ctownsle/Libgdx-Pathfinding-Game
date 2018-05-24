package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TiledMapActor extends Actor {

    private TiledMap tmap;

    private TiledMapTileLayer tileLayer;

    TiledMapTileLayer.Cell cell;

    public TiledMapActor (TiledMap tmap, TiledMapTileLayer tileLayer, TiledMapTileLayer.Cell cell){
        this.tmap = tmap;
        this.tileLayer = tileLayer;
        this.cell = cell;
    }
}

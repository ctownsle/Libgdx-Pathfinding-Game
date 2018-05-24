package com.mygdx.game;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TiledMapClickListener extends ClickListener {

    private TiledMapActor actor;
    private static int i = 0;
    private boolean astar = false;
    private static boolean firstClick = true;
    private static boolean secondClick = false;
    static Location startPos = null;
    static Location endPos = null;

    public TiledMapClickListener(TiledMapActor actor){
        this.actor = actor;
    }

    @Override
    public void clicked(InputEvent event, float x, float y){
        int xPix = 95;
        int yPix = 88;

       final  SearchAlgorithm sa = new SearchAlgorithm();
       // getTapCount();

        int adjustedX =(int) event.getStageX()/ xPix;
        int adjustedY = (int) event.getStageY()/ yPix;
        //if()
        //System.out.println(firstClick);
        if(firstClick == true){
           System.out.println("Source Set");
            startPos = new Location(adjustedX, adjustedY);
            firstClick = false;
            secondClick = true;
          // System.out.println(firstClick);
        } else if(secondClick == true) {
            System.out.println("Goal set");
            endPos = new Location(adjustedX, adjustedY);
            firstClick = false;
            secondClick = false;
        }
        i++;//System.out.println(actor.cell + " " + adjustedX + " " + adjustedY + " has been clicked");
        //index = index + 1;
        //System.out.println(i);
        if(i % 2 == 0) {

            List<Location> path = sa.dijkstras(GameClass.grid, startPos.getX(), startPos.getY(),
                    endPos.getX(), endPos.getY(), astar);


    		changeTiles(sa, path);
    		firstClick = true;
        }

    }

    private void changeTiles(SearchAlgorithm sa, List<Location> path){
        for(Location n: sa.visited){
            //map.getCell(i,j).setTile(tiles.getCell(grid[i][j] + 30, 0).getTile());
            if(GameClass.grid[n.getX()][n.getY()] == 0){
                GameClass.map.getCell(n.getX(), n.getY()).setTile
                        (GameClass.tiles.getCell(GameClass.grid[n.getX()][n.getY()]  + 31, 0).getTile());
            } else {
                GameClass.map.getCell(n.getX(), n.getY()).setTile
                        (GameClass.tiles.getCell(GameClass.grid[n.getX()][n.getY()] - 1 + 10, 0).getTile());
            }
        }

        for(Location n: path){
            if(GameClass.grid[n.getX()][n.getY()] != 0){
                GameClass.map.getCell(n.getX(), n.getY()).setTile
                        (GameClass.tiles.getCell(GameClass.grid[n.getX()][n.getY()] - 1 + 20, 0).getTile());
            } else {
                GameClass.map.getCell(n.getX(), n.getY()).setTile
                        (GameClass.tiles.getCell(GameClass.grid[n.getX()][n.getY()] + 32 , 0).getTile());
            }
        }
    }
}

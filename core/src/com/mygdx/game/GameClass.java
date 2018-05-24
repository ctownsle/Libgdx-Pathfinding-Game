package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GameClass extends ApplicationAdapter{

	static TiledMapTileLayer tiles;// = (TiledMapTileLayer)(new TmxMapLoader().load("mejff.tmx")).getLayers().get(0);
	static TiledMapTileLayer map;
	OrthogonalTiledMapRenderer mr;
	static TiledMap renderMap;
	public static OrthographicCamera camera;

	static byte[][] grid;


	Stage stage;
	@Override
	public void create () {
		tiles = (TiledMapTileLayer) (new TmxMapLoader().load("mejff.tmx")).getLayers().get(0);
		try {
			ParseText p = new ParseText(30);
			grid = p.getFinalmap();

		} catch (IOException e) {
			e.printStackTrace();
		}
		map = new TiledMapTileLayer(grid.length, grid[0].length, 95, 88);
		//System.out.println(map.setCell());
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				map.setCell(i, j, new TiledMapTileLayer.Cell());
				if(grid[i][j] == 0){
				    map.getCell(i,j).setTile(tiles.getCell(grid[i][j] + 30, 0).getTile());
                } else {
                    map.getCell(i, j).setTile(tiles.getCell(grid[i][j] - 1, 0).getTile());
                }
			}
		}
		renderMap = new TiledMap();

		renderMap.getLayers().add(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mr = new OrthogonalTiledMapRenderer(renderMap);

		stage = new TiledMapStage(renderMap);
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		mr.setView(camera);
		stage.getViewport().setCamera(camera);
		mr.render();
		stage.act();
		keyUp();


	}
	
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
        mr.dispose();
        stage.dispose();
	}

	public void keyUp() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			camera.translate(-30, 0);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			camera.translate(30, 0);
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			camera.translate(0, 30);
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			camera.translate(0, -30);
		if(Gdx.input.isKeyPressed(Input.Keys.EQUALS))
		    camera.zoom += 0.20;
		if(Gdx.input.isKeyPressed(Input.Keys.MINUS))
		    camera.zoom -= 0.20;

	}

}

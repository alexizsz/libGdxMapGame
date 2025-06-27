package io.libGdx.mapGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Main extends ApplicationAdapter {

    OrthographicCamera camera;
    TiledMap map;
    OrthogonalTiledMapRenderer mapRenderer;
    SpriteBatch batch;
    Texture playerTexture;

    float playerX = 100;
    float playerY = 100;
    float playerSpeed = 100f;

    @Override
    public void create() {
        map = new TmxMapLoader().load("assets/firstMap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        batch = new SpriteBatch();
        playerTexture = new Texture("assets/TX Player.png"); // put your player sprite in assets folder

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 640);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        // Basic player movement
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W)) playerY += playerSpeed * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.S)) playerY -= playerSpeed * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) playerX -= playerSpeed * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) playerX += playerSpeed * delta;

        // Render map
        mapRenderer.setView(camera);
        mapRenderer.render();

        // Render player sprite on top of map
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(playerTexture, playerX, playerY);
        batch.end();
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        batch.dispose();
        playerTexture.dispose();
    }
}

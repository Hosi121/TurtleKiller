package org.lwjglb.engine;

import org.lwjgl.openal.AL11;
import org.lwjglb.engine.graph.Model;
import org.lwjglb.engine.scene.ModelLoader;
import org.lwjglb.engine.graph.Render;
import org.lwjglb.engine.scene.SkyBox;
import org.lwjglb.engine.scene.lights.AmbientLight;
import org.lwjglb.engine.scene.lights.DirLight;
import org.lwjglb.engine.scene.lights.SceneLights;
import org.lwjglb.engine.scene.Camera;
import org.lwjglb.engine.scene.Entity;
import org.lwjglb.engine.scene.Scene;
import org.lwjglb.engine.scene.GameMap;
import org.lwjglb.engine.sound.*;
import org.joml.Vector3f;

public class Initializer implements IInitializer{

    private Scene scene;
    private Render render;
    private Window window;
    private SoundSource playerSoundSource;
    private SoundManager soundMgr;


    public Initializer(Window window, Scene scene, Render render) {
        this.window = window;
        this.scene = scene;
        this.render = render;
        initTerrain(scene);
        initMap(scene);
        initLighting(scene);
        initSkybox(scene);
        initCamera(scene, window);
    }
    @Override
    public void initMap(Scene scene) {
        GameMap gameMap = new GameMap("resources/Maps.json", scene);
        for (Entity cubeEntity : gameMap.getEntities()) {
            scene.addEntity(cubeEntity);
        }
    }
    @Override
    public void initTerrain(Scene scene) {
        String terrainModelId = "terrain";
        Model terrainModel = ModelLoader.loadModel(terrainModelId, "resources/models/terrain/terrain.obj",
                scene.getTextureCache(), scene.getMaterialCache(), false);
        scene.addModel(terrainModel);
        Entity terrainEntityClass = new Entity("terrainEntity", terrainModelId);
        terrainEntityClass.setScale(100.0f);
        terrainEntityClass.updateModelMatrix();
        scene.addEntity(terrainEntityClass);
    }

    @Override
    public void initLighting(Scene scene) {
        SceneLights sceneLights = new SceneLights();
        AmbientLight ambientLight = sceneLights.getAmbientLight();
        ambientLight.setIntensity(0.5f);
        ambientLight.setColor(0.3f, 0.3f, 0.3f);

        DirLight dirLight = sceneLights.getDirLight();
        dirLight.setPosition(0, 1, 0);
        dirLight.setIntensity(1.0f);
        scene.setSceneLights(sceneLights);
    }


    @Override
    public void initSkybox(Scene scene) {
        SkyBox skyBox = new SkyBox("resources/models/skybox/skybox.obj", scene.getTextureCache(),
                scene.getMaterialCache());
        skyBox.getSkyBoxEntity().setScale(100);
        skyBox.getSkyBoxEntity().updateModelMatrix();
        scene.setSkyBox(skyBox);
    }

    @Override
    public void initCamera(Scene scene, Window window) {
        Camera camera = scene.getCamera();
        camera.setPosition(-1.5f, 3.0f, 4.5f);
        camera.addRotation((float) Math.toRadians(15.0f), (float) Math.toRadians(390.f));
    }

//    @Override
//    public void initSounds(Vector3f position, Camera camera) {
//        soundMgr = new SoundManager();
//        soundMgr.setAttenuationModel(AL11.AL_EXPONENT_DISTANCE);
//        soundMgr.setListener(new SoundListener(camera.getPosition()));
//
//        SoundBuffer buffer = new SoundBuffer("resources/sounds/creak1.ogg");
//        soundMgr.addSoundBuffer(buffer);
//        playerSoundSource = new SoundSource(false, false);
//        playerSoundSource.setPosition(position);
//        playerSoundSource.setBuffer(buffer.getBufferId());
//        soundMgr.addSoundSource("CREAK", playerSoundSource);
//
//        buffer = new SoundBuffer("resources/sounds/bgm.ogg");
//        soundMgr.addSoundBuffer(buffer);
//        SoundSource source = new SoundSource(true, true);
//        source.setBuffer(buffer.getBufferId());
//        soundMgr.addSoundSource("MUSIC", source);
//        source.play();
//    }
}

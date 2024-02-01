package org.lwjglb.game;

import org.joml.*;
import org.lwjgl.openal.AL11;
import org.lwjglb.engine.*;
import org.lwjglb.engine.graph.*;
import org.lwjglb.engine.scene.*;
import org.lwjglb.engine.scene.lights.*;
import org.lwjglb.engine.sound.*;

import java.lang.Math;

import static org.lwjgl.glfw.GLFW.*;

public class Main implements IAppLogic {

    private static final float MOUSE_SENSITIVITY = 0.1f;
    private static final float MOVEMENT_SPEED = 0.005f;

    private Entity cubeEntity1Class;
    private Entity cubeEntity2Class;
    private float lightAngle;
    private float rotation;
    private Initializer initializer;
    private InputHandler inputHandler = new InputHandler();
    private SoundSource playerSoundSource;
    private SoundManager soundMgr;

    public static void main(String[] args) {
        Main main = new Main();
        Window.WindowOptions opts = new Window.WindowOptions();
        opts.width = 1200;
        opts.height = 700;
        opts.antiAliasing = true;
        Engine gameEng = new Engine("TurtleKiller", opts, main);
        gameEng.start();
    }

    @Override
    public void cleanup() {
        // Nothing to be done yet
    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        initializer = new Initializer(window, scene, render);
        Vector3f position = scene.getCamera().getPosition();
        initSounds(position, scene.getCamera());
        render.setupData(scene);

        lightAngle = 45.001f;
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis, boolean inputConsumed) {
        Camera camera = new Camera();
        inputHandler.handleInput(window, scene, diffTimeMillis, inputConsumed);
        soundMgr.updateListenerPosition(camera);
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
    }

    public void initSounds(Vector3f position, Camera camera) {
        soundMgr = new SoundManager();
        soundMgr.setAttenuationModel(AL11.AL_EXPONENT_DISTANCE);
        soundMgr.setListener(new SoundListener(camera.getPosition()));

//        SoundBuffer buffer = new SoundBuffer("resources/sounds/bgm.ogg");
//        soundMgr.addSoundBuffer(buffer);
//        playerSoundSource = new SoundSource(false, false);
//        playerSoundSource.setPosition(position);
//        playerSoundSource.setBuffer(buffer.getBufferId());
//        soundMgr.addSoundSource("CREAK", playerSoundSource);

        SoundBuffer buffer = new SoundBuffer("resources/sounds/bgm.ogg");
        soundMgr.addSoundBuffer(buffer);
        SoundSource source = new SoundSource(true, true);
        source.setBuffer(buffer.getBufferId());
        soundMgr.addSoundSource("MUSIC", source);
        source.play();
    }
}
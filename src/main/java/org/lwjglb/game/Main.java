package org.lwjglb.game;

import org.joml.*;
import java.util.List;
import org.lwjglb.engine.*;
import org.lwjglb.engine.graph.*;
import org.lwjglb.engine.scene.*;
import org.lwjglb.engine.scene.lights.*;

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

    public static void main(String[] args) {
        Main main = new Main();
        Window.WindowOptions opts = new Window.WindowOptions();
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
        render.setupData(scene);
        lightAngle = 45.001f;
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis, boolean inputConsumed) {
        inputHandler.handleInput(window, scene, diffTimeMillis, inputConsumed);
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis){
    }
}
package org.lwjglb.engine;

import org.joml.Vector2f;
import org.lwjglb.engine.scene.*;
import org.lwjglb.engine.scene.lights.DirLight;
import org.lwjglb.engine.scene.lights.SceneLights;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler implements IInputHandler {
    private static final float MOUSE_SENSITIVITY = 0.1f;
    private static final float MOVEMENT_SPEED = 0.005f;
    private float lightAngle;
    private float initializer;

    public InputHandler() {
        this.initializer = initializer;
        this.lightAngle = 45.001f;
    }
    @Override
    public void handleInput(Window window, Scene scene, long diffTimeMillis, boolean inputConsumed) {
        if (inputConsumed) {
            return;
        }
        float move = diffTimeMillis * MOVEMENT_SPEED;
        Camera camera = scene.getCamera();
        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.moveForward(move);
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(move);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(move);
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(move);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            lightAngle -= 2.5f;
            if(lightAngle < -90){
                lightAngle = -90;
            }
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            lightAngle += 2.5f;
            if(lightAngle > 90){
                lightAngle = 90;
            }
        }
        MouseInput mouseInput = window.getMouseInput();
        if (mouseInput.isRightButtonPressed()) {
            Vector2f displVec = mouseInput.getDisplVec();
            camera.addRotation((float) Math.toRadians(-displVec.x * MOUSE_SENSITIVITY), (float) Math.toRadians(-displVec.y * MOUSE_SENSITIVITY));
        }

        SceneLights sceneLights = scene.getSceneLights();
        DirLight dirLight = sceneLights.getDirLight();
        double angRad = Math.toRadians(lightAngle);
        dirLight.getDirection().z = (float) Math.sin(angRad);
        dirLight.getDirection().y = (float) Math.cos(angRad);
    }
}

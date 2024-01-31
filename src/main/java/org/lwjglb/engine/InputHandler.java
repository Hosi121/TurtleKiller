package org.lwjglb.engine;

import org.joml.Vector2f;
import org.joml.Vector3f;
import java.util.Iterator;
import org.lwjglb.engine.scene.*;
import org.lwjglb.engine.graph.*;
import org.lwjglb.engine.scene.lights.DirLight;
import org.lwjglb.engine.scene.lights.SceneLights;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler{
    private static final float MOUSE_SENSITIVITY = 0.1f;
    private static final float MOVEMENT_SPEED = 0.005f;
    private static final int PLAYER_AREA = 1;
    private static final int PLAYER_LIMIT = 4;
    private float lightAngle;
    private float initializer;

    public InputHandler() {
        this.initializer = initializer;
        this.lightAngle = 45.001f;
    }
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
        } else if (mouseInput.isLeftButtonPressed()){
            Vector3f cameraPosition = camera.getPosition();

            int x = Math.round(cameraPosition.x);
            int y = Math.round(cameraPosition.y);
            int z = Math.round(cameraPosition.z);

            cameraPosition.set(x, y, z);
            Vector3f cameraDirection = camera.getDirection().normalize();

            System.out.println("Clicked");
            System.out.println(cameraPosition);
            System.out.println(cameraDirection);

            for (int i = PLAYER_AREA; i < PLAYER_LIMIT; i++) {
                Vector3f point = new Vector3f(cameraDirection).mul(i).add(cameraPosition);

                // このpointに近いEntityをSceneから探す
                for (Model model : scene.getModelMap().values()) {
                    Iterator<Entity> it = model.getEntitiesList().iterator();
                    while (it.hasNext()) {
                        Entity entity = it.next();
                        if (isPointInsideEntityBoundingBox(point, entity.getPosition())) {
                            scene.removeEntity(entity); // 一致するEntityを削除
                            System.out.println("Deleted");
                            break; // 最初に見つかったEntityを削除したらループを抜ける
                        }
                    }
                }
            }
        }


        SceneLights sceneLights = scene.getSceneLights();
        DirLight dirLight = sceneLights.getDirLight();
        double angRad = Math.toRadians(lightAngle);
        dirLight.getDirection().z = (float) Math.sin(angRad);
        dirLight.getDirection().y = (float) Math.cos(angRad);
    }
    private boolean isPointInsideEntityBoundingBox(Vector3f point, Vector3f entityPosition) {
        // Entityの中心から半径1の立方体内にpointが存在するかチェック
        float dx = Math.abs(point.x - entityPosition.x);
        float dy = Math.abs(point.y - entityPosition.y);
        float dz = Math.abs(point.z - entityPosition.z);
        return dx <= 1.0f && dy <= 1.0f && dz <= 1.0f;
    }
}

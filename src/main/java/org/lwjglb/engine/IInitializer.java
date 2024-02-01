package org.lwjglb.engine;

import org.joml.Vector3f;
import org.lwjglb.engine.scene.*;

public interface IInitializer {
    void initTerrain(Scene scene);
    void initMap(Scene scene);
    void initLighting(Scene scene);
    void initSkybox(Scene scene);
//    void initSounds(Vector3f position, Camera camera);
    void initCamera(Scene scene, Window window);
}
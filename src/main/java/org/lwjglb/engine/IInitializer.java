package org.lwjglb.engine;

import org.lwjglb.engine.*;
import org.lwjglb.engine.graph.*;
import org.lwjglb.engine.scene.*;

public interface IInitializer {
    void initTerrain(Scene scene);
    void initMap(Scene scene);
    void initCubes(Scene scene);
    void initLighting(Scene scene);
    void initSkybox(Scene scene);
    void initCamera(Scene scene, Window window);
}
package org.lwjglb.engine;

import org.lwjglb.engine.scene.Scene;
public interface IInputHandler {
    void handleInput(Window window, Scene scene, long diffTimeMillis, boolean inputConsumed);
}

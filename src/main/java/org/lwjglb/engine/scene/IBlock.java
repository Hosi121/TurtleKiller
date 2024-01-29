package org.lwjglb.engine.scene;

public interface IBlock {
    String getId();
    String getModelId();
    float getBlockScale();
    void setBlockScale(float scale);
    void breakBlock();
}
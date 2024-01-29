package org.lwjglb.engine.scene;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public interface IEntity {
    String getId();
    String getModelId();
    Matrix4f getModelMatrix();
    Vector3f getPosition();
    float getScale();
    void setPosition(float x,float y, float z);
}
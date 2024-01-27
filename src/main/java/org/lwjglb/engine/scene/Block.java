package org.lwjglb.engine.scene;

import org.lwjglb.engine.graph.Model;
import org.lwjglb.engine.scene.Scene;

public class Block extends Entity implements IBlock{
    private Model cubeModel;
    private Scene scene;
    private String id;
    private String modelId;
    private float scale;

    public Block(String id, String modelId) {
        super(id, modelId);
        this.id = id;
        this.modelId = modelId;
        this.scale = 1;
    }

    public float getBlockScale() {
        return scale;
    }
    public void setBlockScale(float scale) {
        this.scale = scale;
    }
    @Override
    public void breakBlock() {
        // ブロックを壊した際の動作
    }
}

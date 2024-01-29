package org.lwjglb.engine.scene;

import org.lwjglb.engine.graph.Mesh;

public class GrassBlock extends Block{
    private String grassType;
    private String Texture;

    public GrassBlock(String id, String modelId, String grassType, String textureFile) {
        super(id, modelId);
        this.grassType = grassType;
    }
    public void GrassBlockMethod(){

    }
    public float getGrassBlockScale(){
        return getBlockScale();
    }
}

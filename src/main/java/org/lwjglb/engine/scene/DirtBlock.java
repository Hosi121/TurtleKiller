package org.lwjglb.engine.scene;

public class DirtBlock extends Block{
    private String dirtType;

    public DirtBlock(String id, String modelId, String dirtType) {
        super(id, modelId);
        this.dirtType = dirtType;
    }
    public void DirtBlockMethod(){

    }
    public float getDirtBlockScale(){
        return getBlockScale();
    }
}
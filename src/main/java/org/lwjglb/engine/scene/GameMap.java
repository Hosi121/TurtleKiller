package org.lwjglb.engine.scene;

import org.lwjglb.engine.Utils;
import org.lwjglb.engine.graph.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private List<Entity> entities;
    private List<List<List<Float>>> position;

    public GameMap(String filePath, Scene scene) {
        this.entities = new ArrayList<>();
        this.position = new ArrayList<>();
        try{
            String jsonContent = Utils.readFile(filePath);
            setMap(jsonContent, scene);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setMap(String jsonContent, Scene scene) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonContent);

        Model cubeModel = ModelLoader.loadModel("cube-model", "resources/models/cube/cube.obj",
                scene.getTextureCache(), scene.getMaterialCache(), false);
        scene.addModel(cubeModel);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String cubeID = jsonObject.getString("id");
            JSONArray posArray = jsonObject.getJSONArray("position");
            float x = (float) posArray.getDouble(0);
            float y = (float) posArray.getDouble(1);
            float z = (float) posArray.getDouble(2);

            Entity cubeEntity = new Entity(cubeID, cubeModel.getId());
            cubeEntity.setPosition(x, y, z);
            cubeEntity.updateModelMatrix();

            entities.add(cubeEntity);
            position.add(setPositionList(x, y, z));
        }
    }

    private List<List<Float>> setPositionList(float x, float y, float z) {
        List<List<Float>> position = new ArrayList<>();
        List<Float> xList = new ArrayList<>();
        xList.add(x);
        List<Float> yList = new ArrayList<>();
        yList.add(y);
        List<Float> zList = new ArrayList<>();
        zList.add(z);

        position.add(xList);
        position.add(yList);
        position.add(zList);

        return position;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<List<List<Float>>> getposition() {
        return position;
    }
}


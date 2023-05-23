package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.TargetType;

public class Target extends Tile {

    private TargetType type;

    public Target(Vector2 coordinate, String color) {
        super(coordinate);

        switch (color) {
            case "green":
                setType(TargetType.WIN);
                setAtlasRegion(Assets.greenTarget);
                break;
            case "red":
                setType(TargetType.FAIL);
                setAtlasRegion(Assets.redTarget);
                break;
        }
    }

    public TargetType getType() {
        return type;
    }

    public void setType(TargetType type) {
        this.type = type;
    }

    public void pinchPositionAction(Vector2 position) {
        setCoordinate(position);

        // perform in and out action with movement
        addAction(Actions.sequence(
                Actions.scaleTo(1.5f, 1.5f, .3f),
                Actions.scaleTo(0, 0, .3f),
                Actions.alpha(0, .1f),
                Actions.moveTo(position.x, position.y, .3f),
                Actions.alpha(1, .1f),
                Actions.scaleTo(1f, 1f, .3f)));
    }
}

package com.galing.codecube.objects;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.galing.codecube.board.Board;

public class Tile extends Actor {

    final float SIZE = Board.TILE_SIZE * Board.UNIT_SCALE;

    private AtlasRegion atlasRegion;
    private Vector2 coordinate;

    public Tile(Vector2 coordinate) {
        setSize(SIZE, SIZE);
        setOrigin(Align.center);
        setCoordinate(coordinate);
        setPosition(coordinate.x, coordinate.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(atlasRegion, getX(), getY(), getOriginX(), getOriginY(), SIZE, SIZE, getScaleX(), getScaleY(),
                getRotation());
    }


    public Vector2 getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Vector2 coordinate) {
        this.coordinate = new Vector2(coordinate);
    }

    public void setAtlasRegion(AtlasRegion atlasRegion) {
        this.atlasRegion = atlasRegion;
    }

    public void addMovePositionAction(Vector2 position) {
        setCoordinate(position);

        // perform move action
        addAction(moveTo(position.x, position.y, .3f));
    }

    public void addResetPositionAction() {
        addAction(moveTo(coordinate.x, coordinate.y, .3f));
    }

    public void addInOutAction() {
        // perform in and out action
        addAction(Actions.sequence(
                Actions.scaleTo(1.25f, 1.25f, .15f),
                Actions.scaleTo(1f, 1f, .2f)));
    }

    public void addInOutPositionAction(Vector2 position) {
        setCoordinate(position);

        // perform in and out action with movement
        addAction(Actions.sequence(
                Actions.scaleTo(1.5f, 1.5f, .3f),
                Actions.scaleTo(.0f, .0f, .3f),
                Actions.alpha(0, .1f),
                Actions.moveTo(position.x, position.y, .3f),
                Actions.alpha(1, .1f),
                Actions.scaleTo(1f, 1f, .3f)));
    }

    public boolean isEqualCoordinate(Vector2 position) {
        return position.x == getCoordinate().x
                && position.y == getCoordinate().y;
    }
}

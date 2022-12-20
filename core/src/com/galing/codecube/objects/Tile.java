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
    private int position;
    private Vector2 positionVector;

    public Tile(int position) {
        setPosition(position);
        setSize(SIZE, SIZE);
        setOrigin(Align.center);
        setPosition(positionVector.x, positionVector.y);
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
        this.positionVector = new Vector2(Board.positionHashMap.get(position).x,
                Board.positionHashMap.get(position).y);
    }

    public void setAtlasRegion(AtlasRegion atlasRegion) {
        this.atlasRegion = atlasRegion;
    }

    public void addMovePositionAction(int position) {
        setPosition(position);

        // perform move action
        addAction(moveTo(this.positionVector.x,
                this.positionVector.y, .3f));
    }

    public void addInOutAction() {
        // perform in and out action
        addAction(Actions.sequence(
                Actions.scaleTo(1.25f, 1.25f, .15f),
                Actions.scaleTo(1f, 1f, .2f)));
    }

    public void addInOutPositionAction(int position) {
        setPosition(position);

        // perform in and out action with movement
        addAction(Actions.sequence(
                Actions.scaleTo(1.5f, 1.5f, .3f),
                Actions.scaleTo(.05f, .05f, .3f),
                Actions.alpha(0, .1f),
                Actions.moveTo(this.positionVector.x,
                        this.positionVector.y, .3f),
                Actions.alpha(1, .1f),
                Actions.scaleTo(1f, 1f, .3f)));
    }

    public void addResetPositionAction() {
        addAction(moveTo(positionVector.x,
                positionVector.y, .35f));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(atlasRegion, getX(), getY(), getOriginX(), getOriginY(), SIZE, SIZE, getScaleX(), getScaleY(),
                getRotation());
    }
}

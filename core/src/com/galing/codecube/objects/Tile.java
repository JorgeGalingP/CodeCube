package com.galing.codecube.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.galing.codecube.board.Board;

public class Tile extends Actor {

    public static final float SIZE = Board.TILE_SIZE * Board.UNIT_SCALE;

    private AtlasRegion atlasRegion;
    private Vector2 coordinate;

    public Tile(Vector2 coordinate) {
        setSize(SIZE, SIZE);
        setOrigin(Align.center);
        setCoordinate(coordinate);
        setPosition(coordinate.x, coordinate.y);
        setVisible(true);
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

    public void showAction(Group group) {
        group.addActor(this);
        addAction(Actions.sequence(
                Actions.parallel(
                        Actions.scaleTo(0, 0),
                        Actions.alpha(0)),
                Actions.sequence(Actions.parallel(
                        Actions.scaleTo(1, 1, .3f),
                        Actions.alpha(1, .3f)))
        ));
    }

    public void removeAction() {
        addAction(Actions.sequence(Actions.parallel(
                        Actions.scaleTo(0, 0, .3f),
                        Actions.alpha(0, .3f)),
                Actions.removeActor()
        ));
    }

    public void pinchAction() {
        addAction(Actions.sequence(
                Actions.scaleTo(1.25f, 1.25f, .15f),
                Actions.scaleTo(1f, 1f, .2f)));
    }

    public boolean isEqualCoordinate(Vector2 position) {
        return position.x == getCoordinate().x
                && position.y == getCoordinate().y;
    }
}

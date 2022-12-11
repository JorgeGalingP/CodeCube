package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.galing.codecube.board.Board;

public class Tile extends Actor {

    final float SIZE = Board.TILE_SIZE * Board.UNIT_SCALE;

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
}

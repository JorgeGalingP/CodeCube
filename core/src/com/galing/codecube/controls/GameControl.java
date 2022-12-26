package com.galing.codecube.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

public abstract class GameControl {
    public final Collection<Box> programList;
    public final Collection<Box> functionList;

    public final Vector2 programButtonPosition;
    public final int programSize;
    public final int programInitial;

    public final Vector2 functionButtonPosition;
    public final int functionSize;
    public final int functionInitial;

    public GameControl(String type, Button programButton, Button functionButton, Array<Control> programControls,
                       Array<Control> functionControls) {
        if (type.equals("stack")) {
            this.programList = new Stack<>();
            this.functionList = new Stack<>();
        } else if (type.equals("queue")) {
            this.programList = new LinkedList<>();
            this.functionList = new LinkedList<>();
        } else {
            this.programList = new ArrayList<>();
            this.functionList = new ArrayList<>();
        }
        this.programButtonPosition = programButton.getCoordinate();
        this.programSize = programControls.size;
        this.programInitial = (int) programControls.first().getCoordinate().y;
        this.functionButtonPosition = functionButton.getCoordinate();
        this.functionSize = functionControls.size;
        this.functionInitial = (int) functionControls.first().getCoordinate().y;
    }

    public int getProgramSize() {
        return this.programList.size();
    }

    public int getFunctionSize() {
        return this.functionList.size();
    }

    public abstract Box getNextBox();
}

package com.galing.codecube.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.board.SpawnManager;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.Collection;

public abstract class Control<T extends Collection<Box>> implements Controllable {
    private T program;
    private T function;
    private T holder;

    private final SpawnManager spawnManager;

    private final Vector2 programButtonPosition;
    private final int programSize;
    private final Array<Container> programControls;

    private Vector2 functionButtonPosition;
    private int functionSize;
    private Array<Container> functionControls;

    public Control(SpawnManager spawnManager,
                   Button programButton, Button functionButton,
                   Array<Container> programControls,
                   Array<Container> functionControls) {
        this.spawnManager = spawnManager;
        this.programButtonPosition = programButton.getCoordinate();
        this.programSize = programControls.size;
        this.programControls = programControls;
        this.functionButtonPosition = null;
        this.functionSize = 0;
        this.functionControls = null;

        if (functionControls != null
                && !functionControls.isEmpty()) {
            this.functionButtonPosition = functionButton.getCoordinate();
            this.functionSize = functionControls.size;
            this.functionControls = functionControls;
        }
    }

    public Array<Container> getProgramControls() {
        return programControls;
    }

    public Array<Container> getFunctionControls() {
        return functionControls;
    }

    public T getProgram() {
        return program;
    }

    public void setProgram(T program) {
        this.program = program;
    }

    public T getFunction() {
        return function;
    }

    public void setFunction(T function) {
        this.function = function;
    }

    public T getHolder() {
        return holder;
    }

    public void setHolder(T holder) {
        this.holder = holder;
    }

    public int getProgramSize() {
        return program.size();
    }

    public int getFunctionSize() {
        return function.size();
    }

    public boolean isProgramEmpty() {
        return program.isEmpty();
    }

    public boolean isFunctionEmpty() {
        return function.isEmpty();
    }

    public boolean isHolderEmpty() {
        return holder.isEmpty();
    }

    public int countFunction() {
        return (int) getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();
    }

    public void pushToProgram(Box box) {
        if (!isProgramEmpty())
            getProgram().forEach(b -> b.setIsTouchable(false));

        box.setIsTouchable(true);
        box.setControlType(ContainerType.PROGRAM);
        box.setPushedIdle();
    }

    public void pushToFunction(Box box) {
        if (!isFunctionEmpty())
            getFunction().forEach(b -> b.setIsTouchable(false));

        box.setIsTouchable(true);
        box.setControlType(ContainerType.FUNCTION);
        box.setPushedIdle();
    }

    public abstract Box getNextBox();

    public abstract void addToProgram(Box box);

    public abstract void addToFunction(Box box);

    public abstract void remove(Box box);

    public abstract Box removeFromProgram();

    public abstract Box removeFromFunction();

    public abstract void handleProgramTouchable();

    public abstract void handleFunctionTouchable();

    public abstract void copyFunction();

    public void attachDragListener(Box box) {
        box.addListener(new DragListener() {
            private final Vector2 lastTouch = new Vector2();

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                // only can be moved if is not in the control
                // or is the peek of it
                if (box.getIsTouchable() == null
                        || (box.getIsTouchable()
                        && (box.getControlType().equals(ContainerType.PROGRAM) || box.getControlType().equals(ContainerType.FUNCTION)))) {
                    box.toFront();
                    box.setDraggedIdle();
                    box.moveBy(x - box.getWidth() / 2, y - box.getHeight() / 2);
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lastTouch.set(event.getStageX(), event.getStageY());

                // only can be added if box is in button's bounds,
                // can be touched
                // and control has remain space
                if (isBoxInButtonBounds(lastTouch, programButtonPosition, box)
                        && box.getIsTouchable() == null
                        && getProgramSize() != programSize) {
                    addToProgram(box);                          // push to program control
                    spawnManager.spawn(box.getType());          // spawn a new box of same type
                } else if (functionButtonPosition != null
                        && (isBoxInButtonBounds(lastTouch, functionButtonPosition, box)
                        && box.getIsTouchable() == null
                        && !box.getType().equals(BoxType.FUNCTION)
                        && getFunctionSize() != functionSize)) {
                    addToFunction(box);                         // push to function control
                    spawnManager.spawn(box.getType());          // spawn a new box of same type
                } else if (box.getIsTouchable() != null
                        && box.getControlType() != null) {
                    if (box.getIsTouchable())
                        remove(box);                            // remove box only if is the first
                } else
                    box.addResetPositionAction();               // back to start
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }

    private boolean isBoxInButtonBounds(Vector2 lastTouch, Vector2 buttonPosition, Box box) {
        return (lastTouch.x >= buttonPosition.x - box.getWidth() / 2)
                && (lastTouch.x <= buttonPosition.x + 1 + box.getWidth() / 2)
                && (lastTouch.y >= buttonPosition.y - box.getHeight() / 2)
                && (lastTouch.y <= buttonPosition.y + 1 + box.getHeight() / 2);
    }
}

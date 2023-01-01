package com.galing.codecube.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.Collection;

public abstract class Control<T extends Collection<Box>> implements Controllable {
    private T program;
    private T function;

    private final Vector2 programButtonPosition;
    private final int programSize;
    private final Array<Container> programControls;

    private final Vector2 functionButtonPosition;
    private final int functionSize;
    private final Array<Container> functionControls;

    public Control(Button programButton, Button functionButton,
                   Array<Container> programControls,
                   Array<Container> functionControls) {
        this.programButtonPosition = programButton.getCoordinate();
        this.programSize = programControls.size;
        this.functionButtonPosition = functionButton.getCoordinate();
        this.functionSize = functionControls.size;
        this.programControls = programControls;
        this.functionControls = functionControls;
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

    public Array<Container> getProgramControls() {
        return programControls;
    }

    public Array<Container> getFunctionControls() {
        return functionControls;
    }

    public boolean isProgramEmpty() {
        return program.isEmpty();
    }

    public boolean isFunctionEmpty() {
        return function.isEmpty();
    }

    public int getProgramSize() {
        return program.size();
    }

    public int getFunctionSize() {
        return function.size();
    }

    public abstract Box getNextBox();

    public abstract void addToProgram(Box box);

    public abstract void addToFunction(Box box);

    public abstract void remove(Box box);

    public abstract Box removeFromProgram();

    public abstract Box removeFromFunction();

    public void attachDragListener(Box box) {
        box.addListener(new DragListener() {
            private final Vector2 lastTouch = new Vector2();

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                // only can be moved if is not in the stack or is the peek of it
                if (box.isNext() == null
                        || (box.isNext()
                        && (box.getControlType().equals(ContainerType.PROGRAM) || box.getControlType().equals(ContainerType.FUNCTION))))
                    box.moveBy(x - box.getWidth() / 2, y - box.getHeight() / 2);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lastTouch.set(event.getStageX(), event.getStageY());

                // Gdx.app.log("!", box.isTouchable());this.bounds.overlaps(obstacle.getBounds())

                // is in target and is not in the stack
                if ((lastTouch.x >= programButtonPosition.x - box.getWidth() / 2)
                        && (lastTouch.x <= programButtonPosition.x + 1 + box.getWidth() / 2)
                        && (lastTouch.y >= programButtonPosition.y - box.getHeight() / 2)
                        && (lastTouch.y <= programButtonPosition.y + 1 + box.getHeight() / 2)
                        && box.isNext() == null
                        && getProgramSize() != programSize) {
                    // push box to the stack
                    addToProgram(box);
                    box.setControlType(ContainerType.PROGRAM);
                    box.setPushedIdle();

                    // move box action
                    Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
                    box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));

                } else if ((lastTouch.x >= functionButtonPosition.x - box.getWidth() / 2)
                        && (lastTouch.x <= functionButtonPosition.x + 1 + box.getWidth() / 2)
                        && (lastTouch.y >= functionButtonPosition.y - box.getHeight() / 2)
                        && (lastTouch.y <= functionButtonPosition.y + 1 + box.getHeight() / 2)
                        && box.isNext() == null
                        && getFunctionSize() != functionSize) {
                    // push box to the stack
                    addToFunction(box);
                    box.setControlType(ContainerType.FUNCTION);
                    box.setPushedIdle();

                    // move box action
                    Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();
                    box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
                } else if (box.isNext() != null
                        && box.getControlType() != null) {
                    if (box.isNext()) {
                        // pop peek element out of the stack and set back to original position
                        remove(box);

                        // back to start
                        box.clearControl();
                        box.addResetPositionAction();
                    }
                } else {
                    // back to start
                    box.clearControl();
                    box.addResetPositionAction();
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }
}

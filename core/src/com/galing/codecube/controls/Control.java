package com.galing.codecube.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.Collection;
import java.util.List;

public abstract class Control<T extends Collection<Box>> implements Controllable {
    private T program;
    private List<T> function;

    private final Vector2 programButtonPosition;
    private final int programSize;
    private final Array<Container> programControls;

    private Vector2 functionButtonPosition;
    private int functionSize;
    private Array<Container> functionControls;

    public Control(Button programButton, Button functionButton,
                   Array<Container> programControls,
                   Array<Container> functionControls) {
        this.programButtonPosition = programButton.getCoordinate();
        this.programSize = programControls.size;
        this.programControls = programControls;
        this.functionButtonPosition = null;
        this.functionSize = 0;
        this.functionControls = null;

        if (functionControls != null) {
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

    public List<T> getFunction() {
        return function;
    }

    public void setFunction(List<T> function) {
        this.function = function;
    }

    public int getProgramSize() {
        return program.size();
    }

    public int getFunctionSize() {
        int count = numberOfFunctions();

        if (count > 0)
            return function.get(count - 1).size();

        return function.get(0).size();
    }

    public boolean isProgramEmpty() {
        return program.isEmpty();
    }

    public boolean isFunctionEmpty() {
        int count = numberOfFunctions();

        if (count > 0)
            return function.get(count - 1).isEmpty();

        return function.get(0).isEmpty();
    }

    public boolean hasSeveralFunctions() {
        return (int) this.program.stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count() > 1;
    }

    public int numberOfFunctions() {
        return (int) this.getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();
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
            getFunction().forEach(f -> f.forEach(b -> b.setIsTouchable(false)));

        box.setIsTouchable(true);
        box.setControlType(ContainerType.FUNCTION);
        box.setPushedIdle();
    }

    public void removeFunctionMethod(Box box) {
        if (box.getType().equals(BoxType.FUNCTION)
                && getFunction().size() > 1) {
            getFunction().remove(getFunction().size() - 1);
        }
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
                if (box.getIsTouchable() == null
                        || (box.getIsTouchable()
                        && (box.getControlType().equals(ContainerType.PROGRAM) || box.getControlType().equals(ContainerType.FUNCTION)))) {
                    box.toFront();
                    box.moveBy(x - box.getWidth() / 2, y - box.getHeight() / 2);
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lastTouch.set(event.getStageX(), event.getStageY());

                // is in target and is not in the stack
                if ((lastTouch.x >= programButtonPosition.x - box.getWidth() / 2)
                        && (lastTouch.x <= programButtonPosition.x + 1 + box.getWidth() / 2)
                        && (lastTouch.y >= programButtonPosition.y - box.getHeight() / 2)
                        && (lastTouch.y <= programButtonPosition.y + 1 + box.getHeight() / 2)
                        && box.getIsTouchable() == null
                        && getProgramSize() != programSize) {
                    // push box to the stack
                    addToProgram(box);
                } else if (functionButtonPosition != null
                        && ((lastTouch.x >= functionButtonPosition.x - box.getWidth() / 2)
                        && (lastTouch.x <= functionButtonPosition.x + 1 + box.getWidth() / 2)
                        && (lastTouch.y >= functionButtonPosition.y - box.getHeight() / 2)
                        && (lastTouch.y <= functionButtonPosition.y + 1 + box.getHeight() / 2)
                        && box.getIsTouchable() == null
                        && getFunctionSize() != functionSize)) {
                    // push box to the stack
                    addToFunction(box);
                } else if (box.getIsTouchable() != null
                        && box.getControlType() != null) {
                    if (box.getIsTouchable()) {
                        // pop peek element out of the stack and set back to original position
                        remove(box);
                    }
                } else {
                    // back to start
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

package com.galing.codecube.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Control;

import java.util.Stack;

public class StackControl extends GameControl {

    public StackControl(Button programButton, Button functionButton, Array<Control> programControls,
                        Array<Control> functionControls) {
        super("stack", programButton, functionButton, programControls, functionControls);
    }

    public int getProgramSize() {
        return this.programList.size();
    }

    public int getFunctionSize() {
        return this.programList.size();
    }

    @Override
    public Box getNextBox() {
        return (Box) ((Stack<Box>) this.programList).peek();
    }

    public void pushToProgram(Box box) {
        if (((Stack<Box>) this.programList).empty())
            box.stackPosition = programInitial;
        else
            box.stackPosition = ((Stack<Box>) this.programList).peek().stackPosition + 1;

        box.stackType = 1;
        ((Stack<Box>) this.programList).push(box);

        box.setPushedIdle();
    }

    public void pushToFunction(Box box) {
        if (((Stack<Box>) this.functionList).empty())
            box.stackPosition = programInitial;
        else
            box.stackPosition = ((Stack<Box>) this.functionList).peek().stackPosition + 1;

        box.stackType = 2;
        ((Stack<Box>) this.functionList).push(box);

        box.setPushedIdle();
    }

    public Box popOutProgram() {
        Box box = ((Stack<Box>) this.programList).pop();
        box.stackPosition = null;
        box.stackType = null;

        box.setRandomIdle();

        return box;
    }

    public Box popOutFunction() {
        Box box = ((Stack<Box>) this.functionList).pop();
        box.stackPosition = null;
        box.stackType = null;

        box.setRandomIdle();

        return box;
    }

    public boolean isProgramEmpty() {
        return ((Stack<Box>) this.programList).empty();
    }

    public boolean isFunctionEmpty() {
        return ((Stack<Box>) this.functionList).empty();
    }

    public void attachDragListener(Box box) {
        box.addListener(new DragListener() {
            private final Vector2 lastTouch = new Vector2();

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                // only can be moved if is not in the stack or is the peek of it
                if (box.stackPosition == null
                        || (box.stackPosition == getProgramSize() && box.stackType == 1)
                        || (box.stackPosition == getFunctionSize() && box.stackType == 2))
                    box.moveBy(x - box.getWidth() / 2, y - box.getHeight() / 2);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lastTouch.set(event.getStageX(), event.getStageY());

                // is in target and is not in the stack
                if ((lastTouch.x >= programButtonPosition.x - box.getWidth() / 2)
                        && (lastTouch.x <= programButtonPosition.x + 1 + box.getWidth() / 2)
                        && (lastTouch.y >= programButtonPosition.y - box.getHeight() / 2)
                        && (lastTouch.y <= programButtonPosition.y + 1 + box.getHeight() / 2)
                        && box.stackPosition == null
                        && getProgramSize() != programSize) {
                    // push box to the stack
                    pushToProgram(box);

                    box.addAction(Actions.sequence(Actions.moveTo(programButtonPosition.x,
                            box.stackPosition + programInitial - 1, 0.15f)));
                } else if ((lastTouch.x >= functionButtonPosition.x - box.getWidth() / 2)
                        && (lastTouch.x <= functionButtonPosition.x + 1 + box.getWidth() / 2)
                        && (lastTouch.y >= functionButtonPosition.y - box.getHeight() / 2)
                        && (lastTouch.y <= functionButtonPosition.y + 1 + box.getHeight() / 2)
                        && box.stackPosition == null
                        && getFunctionSize() != functionSize) {
                    // push box to the stack
                    pushToFunction(box);

                    box.addAction(Actions.moveTo(functionButtonPosition.x,
                            box.stackPosition + functionInitial - 1, 0.15f));
                } else if (box.stackPosition != null
                        && box.stackType != null) {
                    // element is already in the stack
                    if (box.stackPosition == getProgramSize()
                            && box.stackType == 1) {
                        // pop peek element out of the stack and set back to original position
                        popOutProgram();

                        // back to start
                        box.addResetPositionAction();
                    } else if (box.stackPosition == getFunctionSize()
                            && box.stackType == 2) {
                        // pop peek element out of the stack and set back to original position
                        popOutFunction();

                        // back to start
                        box.addResetPositionAction();
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

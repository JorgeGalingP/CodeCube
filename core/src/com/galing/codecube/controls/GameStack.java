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

public class GameStack {

    private final Stack<Box> programStack;
    private final Stack<Box> functionStack;

    private final Vector2 programButtonPosition;
    private final int programSize;
    private final int programInitial;

    private final Vector2 functionButtonPosition;
    private final int functionSize;
    private final int functionInitial;

    public GameStack(Button programButton, Button functionButton, Array<Control> programControls,
                     Array<Control> functionControls) {
        this.programStack = new Stack<>();
        this.functionStack = new Stack<>();

        this.programButtonPosition = programButton.getCoordinate();
        this.programSize = programControls.size;
        this.programInitial = (int) programControls.first().getCoordinate().y;
        this.functionButtonPosition = functionButton.getCoordinate();
        this.functionSize = functionControls.size;
        this.functionInitial = (int) functionControls.first().getCoordinate().y;
    }

    public int getProgramSize() {
        return this.programStack.size();
    }

    public int getFunctionSize() {
        return this.functionStack.size();
    }

    public Box getProgramPeek() {
        return this.programStack.peek();
    }

    public void pushToProgram(Box box) {
        if (programStack.empty())
            box.stackPosition = programInitial;
        else
            box.stackPosition = programStack.peek().stackPosition + 1;

        box.stackType = 1;
        programStack.push(box);

        box.setPushedIdle();
    }

    public void pushToFunction(Box box) {
        if (functionStack.empty())
            box.stackPosition = programInitial;
        else
            box.stackPosition = functionStack.peek().stackPosition + 1;

        box.stackType = 2;
        functionStack.push(box);

        box.setPushedIdle();
    }

    public Box popOutProgram() {
        Box box = programStack.pop();
        box.stackPosition = null;
        box.stackType = null;

        box.setRandomIdle();

        return box;
    }

    public Box popOutFunction() {
        Box box = functionStack.pop();
        box.stackPosition = null;
        box.stackType = null;

        box.setRandomIdle();

        return box;
    }

    public boolean isProgramEmpty() {
        return this.programStack.empty();
    }

    public boolean isFunctionEmpty() {
        return this.functionStack.empty();
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

package com.galing.codecube.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.galing.codecube.objects.Box;

import java.util.Stack;

public class GameStack {

    private final Stack<Box> programStack;
    private final Stack<Box> functionStack;

    private final Vector2 programPosition;
    private final int programSize;
    private final int programInitial;

    private final Vector2 functionPosition;
    private final int functionSize;
    private final int functionInitial;

    public int controlSize;

    public GameStack(Vector2 programPosition, int programSize, int programInitial,
                     Vector2 functionPosition, int functionSize, int functionInitial) {
        this.programStack = new Stack<>();
        this.functionStack = new Stack<>();
        this.programPosition = programPosition;
        this.programSize = programSize;
        this.programInitial = programInitial;
        this.functionPosition = functionPosition;
        this.functionSize = functionSize;
        this.functionInitial = functionInitial;

        this.controlSize = 1;
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
                        || (box.stackPosition == getFunctionSize() && box.stackType == 2)) {
                    // put box in front of others actors and perform moveBy action
                    box.toFront();
                    box.moveBy(x - box.getWidth() / 2, y - box.getHeight() / 2);
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lastTouch.set(event.getStageX(), event.getStageY());

                // is in target and is not in the stack
                if ((lastTouch.x >= programPosition.x - box.getWidth() / 2)
                        && (lastTouch.x <= programPosition.x + controlSize + box.getWidth() / 2)
                        && (lastTouch.y >= programPosition.y - box.getHeight() / 2)
                        && (lastTouch.y <= programPosition.y + controlSize + box.getHeight() / 2)
                        && box.stackPosition == null
                        && getProgramSize() != programSize) {
                    // push box to the stack
                    pushToProgram(box);

                    box.addAction(Actions.sequence(Actions.moveTo(programPosition.x,
                            box.stackPosition + programInitial - 1, 0.15f)));
                } else if ((lastTouch.x >= functionPosition.x - box.getWidth() / 2)
                        && (lastTouch.x <= functionPosition.x + controlSize + box.getWidth() / 2)
                        && (lastTouch.y >= functionPosition.y - box.getHeight() / 2)
                        && (lastTouch.y <= functionPosition.y + controlSize + box.getHeight() / 2)
                        && box.stackPosition == null
                        && getFunctionSize() != functionSize) {
                    // push box to the stack
                    pushToFunction(box);

                    box.addAction(Actions.moveTo(functionPosition.x,
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

                Gdx.app.log("PROGRAM", programStack.toString());
                Gdx.app.log("FUNCTION", functionStack.toString());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }
}

package com.galing.codecube.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sequence extends Control<List<Box>> {
    public Sequence(Button programButton, Button functionButton, Array<Container> programControls,
                    Array<Container> functionControls,
                    int numberOfFunctionTiles) {
        super(programButton, functionButton, programControls, functionControls, numberOfFunctionTiles);
        setProgram(new ArrayList<>());
        setFunction(IntStream.range(0, numberOfFunctionTiles)
                .mapToObj(i -> new ArrayList<Box>())
                .collect(Collectors.toList()));
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().get(0);
    }

    @Override
    public void addToProgram(Box box) {
        if (!isProgramEmpty())
            for (Box programBox : this.getProgram())
                programBox.setIsTouchable(false);

        box.setIsTouchable(true);
        getProgram().add(box);

        box.setControlType(ContainerType.PROGRAM);
        box.setPushedIdle();

        Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void addToFunction(Box box) {
        if (!isFunctionEmpty())
            getFunction().forEach(f -> f.forEach(b -> b.setIsTouchable(false)));

        box.setIsTouchable(true);
        getFunction().forEach(f -> f.add(box));

        box.setControlType(ContainerType.FUNCTION);
        box.setPushedIdle();

        Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);
            if (getProgramSize() > 0)
                getProgram().get(getProgramSize() - 1).setIsTouchable(true);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().forEach(function -> function.remove(box));

            if (getFunctionSize() > 0)
                getFunction().get(0).get(getFunctionSize() - 1).setIsTouchable(true);
        }

        // back to start
        box.clearControl();
        box.addResetPositionAction();
    }

    @Override
    public Box removeFromProgram() {
        return getProgram().remove(0);
    }

    @Override
    public Box removeFromFunction() {
        int count =
                (int) this.getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();
        return getFunction().get(count - 1).remove(0);
    }
}

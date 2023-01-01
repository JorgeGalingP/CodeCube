package com.galing.codecube.controls;

import com.galing.codecube.objects.Box;

public interface Controllable {

    boolean isProgramEmpty();

    boolean isFunctionEmpty();

    Box getNextBox();

    void addToProgram(Box box);

    void addToFunction(Box box);

    void remove(Box box);

    Box removeFromProgram();

    Box removeFromFunction();

    void attachDragListener(Box box);

}

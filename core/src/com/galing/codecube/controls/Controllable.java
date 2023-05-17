package com.galing.codecube.controls;

import com.galing.codecube.objects.Box;

public interface Controllable {

    boolean isProgramEmpty();

    boolean isFunctionEmpty();

    boolean isHolderEmpty();

    Box getNextBox();

    void reset();

    void addToProgram(Box box);

    void addToFunction(Box box);

    void copyFunction();

    void resetFunction();

    int countFunction();

    void kill(Box box);

    Box removeFromProgram();

    Box removeFromFunction();

    void attachDragListener(Box box);
}

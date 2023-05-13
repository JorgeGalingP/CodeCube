package com.galing.codecube.controls;

import com.galing.codecube.objects.Box;

public interface Controllable {

    void show();

    boolean isProgramEmpty();

    boolean isFunctionEmpty();

    boolean isHolderEmpty();

    Box getNextBox();

    void addToProgram(Box box);

    void addToFunction(Box box);

    void remove(Box box);

    Box removeFromProgram();

    Box removeFromFunction();

    void attachDragListener(Box box);

    int numberOfFunctionsLeft();

    void generateHolder();
}

package com.galing.codecube.controls;

import com.galing.codecube.objects.Box;

public interface Controllable {
    Box getNextBox();

    void addToProgram(Box box);

    void addToFunction(Box box);

    Box removeFromProgram();

    Box removeFromFunction();
}

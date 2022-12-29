package com.galing.codecube.controls;

import com.galing.codecube.objects.Box;

public interface BoxManageable {
    Box getNextBox();

    void addToProgram(Box box);

    void addToFunction(Box box);

    Box removeFromProgram();

    Box removeFromFunction();
}

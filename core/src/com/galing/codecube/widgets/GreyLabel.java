package com.galing.codecube.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.galing.codecube.Assets;

public class GreyLabel extends Label {

    public GreyLabel(String text) {
        super(text, new LabelStyle(Assets.vagaRoundBoldGray25, Assets.vagaRoundBoldGray25.getColor()));

        LabelStyle labelStyle = getStyle();
        setStyle(labelStyle);

        setAlignment(Align.center);
    }

    public GreyLabel(String text, boolean withBackground) {
        super(text, new LabelStyle(Assets.vagaRoundBoldGray25, Assets.vagaRoundBoldGray25.getColor()));

        LabelStyle labelStyle = getStyle();
        if (withBackground)
            labelStyle.background = Assets.greyPanelNinePatch;
        setStyle(labelStyle);

        setAlignment(Align.center);
    }
}

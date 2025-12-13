package Narration;

import Hero.*;
import DesignRelated.*;

public class EndingPlot {

    SwordsmanPlot swordsmanPlotHandler = new SwordsmanPlot();
    GunnerPlot gunnerPlotHander = new GunnerPlot();
    MagePlot magePlotHandler = new MagePlot();
    IntroTitle loadHandler = new IntroTitle();
    IntroTitle outroHandler = new IntroTitle();
    Narration promptSeparatorHandler = new Narration();

    public void generalEndingPlot(Hero hero) {

        if (hero.getSwordmanCharacterChosen()) {
            swordsmanPlotHandler.swordsManEndingPlot();
        } else if (hero.getGunnerCharacterChosen()) {
            gunnerPlotHander.gunnerEndingPlot();
        } else if (hero.getMageCharacterChosen()) {
            magePlotHandler.mageEndingPlot();
        }

        loadHandler.startGame();
        promptSeparatorHandler.promptSeparator();
        outroHandler.mystvaleOutroTitle();

        System.exit(0);

    }

}

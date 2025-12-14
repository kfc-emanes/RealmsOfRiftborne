package Narration;

import Hero.*;
import DesignRelated.*;

public class EndingPlot {

    private SwordsmanPlot swordsmanPlotHandler = new SwordsmanPlot();
    private GunnerPlot gunnerPlotHander = new GunnerPlot();
    private MagePlot magePlotHandler = new MagePlot();
    private IntroTitle loadHandler = new IntroTitle();
    private IntroTitle outroHandler = new IntroTitle();
    private Narration promptSeparatorHandler = new Narration();

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

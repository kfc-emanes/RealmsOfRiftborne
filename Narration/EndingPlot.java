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

        swordsmanPlotHandler.swordsManEndingPlot();
        //gunnerPlotHander.gunnerEndingPlot();
        //magePlotHandler.mageEndingPlot();

        loadHandler.startGame();
        promptSeparatorHandler.promptSeparator();
        outroHandler.mystvaleOutroTitle();

        System.exit(0);

    }







}

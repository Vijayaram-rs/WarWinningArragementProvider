package main.java.com.casademo;

import main.java.com.casademo.model.Advantage;
import main.java.com.casademo.model.Plantoon;
import main.java.com.casademo.model.WarPlantoonsSequence;
import main.java.com.casademo.service.WinningArrangementProviderService;
import main.java.com.casademo.service.WinningArrangementProviderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarWinningArrangementProvider {
    public static WinningArrangementProviderService winningArrangementProviderService = new WinningArrangementProviderServiceImpl();
    public static void main(String[] args) {

        String myArmyString = null;
        String oppArmyString = null;
        try {
            myArmyString = args[0];
            oppArmyString = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide 2 input strings as command line arguments");
            throw new RuntimeException(e);
        }

        WarPlantoonsSequence myPlantoons = new WarPlantoonsSequence(myArmyString);
        WarPlantoonsSequence oppPlantoons = new WarPlantoonsSequence(oppArmyString);
        List<Plantoon> winnableSeq = winningArrangementProviderService.getWinnableSeq(myPlantoons.getPlantoonsSeq(),
                oppPlantoons.getPlantoonsSeq(), 0);
        WarPlantoonsSequence winningWarPlantoonsSeq = new WarPlantoonsSequence( winnableSeq, myPlantoons.getPlantoonsCount());
        if (winningWarPlantoonsSeq.getPlantoonsSeq().isEmpty()) {
            System.out.println("There is no chance of winning");
            return;
        }
        System.out.println(winningWarPlantoonsSeq);
    }



}
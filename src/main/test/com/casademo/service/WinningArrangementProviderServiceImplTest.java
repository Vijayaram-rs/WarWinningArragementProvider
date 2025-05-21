package main.test.com.casademo.service;

import main.java.com.casademo.exception.InvalidInputException;
import main.java.com.casademo.model.Plantoon;
import main.java.com.casademo.model.WarPlantoonsSequence;
import main.java.com.casademo.service.WinningArrangementProviderService;
import main.java.com.casademo.service.WinningArrangementProviderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class WinningArrangementProviderServiceImplTest {

    private final WinningArrangementProviderService winningArrangementProviderService =
            new WinningArrangementProviderServiceImpl();

    @Test
    public void isWinnableWithAdv() {
        Plantoon myPlantoon = new Plantoon("Militia#1");
        Plantoon oppPlantoon = new Plantoon("Spearmen#1");
        Assertions.assertTrue(winningArrangementProviderService.isWinnable(myPlantoon, oppPlantoon));
    }
    @Test
    public void isWinnableWithoutAdv() {
        Plantoon myPlantoon = new Plantoon("Spearmen#2");
        Plantoon oppPlantoon = new Plantoon("Spearmen#1");
        Assertions.assertTrue(winningArrangementProviderService.isWinnable(myPlantoon, oppPlantoon));
    }
    @Test
    public void isWinnableDraw() {
        Plantoon myPlantoon = new Plantoon("Spearmen#1");
        Plantoon oppPlantoon = new Plantoon("Spearmen#1");
        Assertions.assertFalse(winningArrangementProviderService.isWinnable(myPlantoon, oppPlantoon));
    }

    @Test
    public void isWinnableException() {
        Plantoon myPlantoon = new Plantoon("Random#1");
        Plantoon oppPlantoon = new Plantoon("Spearmen#1");
        Assertions.assertThrows(InvalidInputException.class,
                () -> winningArrangementProviderService.isWinnable(myPlantoon, oppPlantoon));
    }

    @Test
    public void getWinnableSeqEmptyLists() {
        List<Plantoon> winnableSeq = winningArrangementProviderService.getWinnableSeq(
                new ArrayList<Plantoon>(), new ArrayList<Plantoon>(), 0);
        Assertions.assertEquals(0, winnableSeq.size());
    }

    @Test
    public void getWinnableSeqCase1() {
        String myArmyString = "Spearmen#10;Militia#30;FootArcher#20;LightCavalry#1000;HeavyCavalry#120";
        String oppArmyString = "Militia#10;Spearmen#10;FootArcher#1000;LightCavalry#120;CavalryArcher#100";
        WarPlantoonsSequence myPlantoons = new WarPlantoonsSequence(myArmyString);
        WarPlantoonsSequence oppPlantoons = new WarPlantoonsSequence(oppArmyString);

        WarPlantoonsSequence winningWarPlantoonsSeq = new WarPlantoonsSequence(
                winningArrangementProviderService.getWinnableSeq(myPlantoons.getPlantoonsSeq(), oppPlantoons.getPlantoonsSeq(), 0),
                myPlantoons.getPlantoonsCount());

        Assertions.assertTrue(checkWinnable(winningWarPlantoonsSeq.toString(), oppArmyString),
                "Should return winnable sequence");
    }

    @Test
    public void getWinnableSeqCase2() {
        String myArmyString = "Spearmen#10;Spearmen#10;Spearmen#100;Spearmen#100;Spearmen#100";
        String oppArmyString = "Spearmen#10;Spearmen#10;Spearmen#10;Spearmen#10;Spearmen#100";
        WarPlantoonsSequence myPlantoons = new WarPlantoonsSequence(myArmyString);
        WarPlantoonsSequence oppPlantoons = new WarPlantoonsSequence(oppArmyString);

        WarPlantoonsSequence winningWarPlantoonsSeq = new WarPlantoonsSequence(
                winningArrangementProviderService.getWinnableSeq(myPlantoons.getPlantoonsSeq(), oppPlantoons.getPlantoonsSeq(), 0),
                myPlantoons.getPlantoonsCount());

        Assertions.assertTrue(checkWinnable(winningWarPlantoonsSeq.toString(), oppArmyString),
                "Should return winnable sequence");
    }
    @Test
    public void getWinnableSeqCase3() {
        String myArmyString = "Spearmen#10;Spearmen#10;Spearmen#100;Spearmen#100;Spearmen#100";
        String oppArmyString = "Spearmen#1000;Spearmen#10;Spearmen#10;Spearmen#10;Spearmen#100";
        WarPlantoonsSequence myPlantoons = new WarPlantoonsSequence(myArmyString);
        WarPlantoonsSequence oppPlantoons = new WarPlantoonsSequence(oppArmyString);

        WarPlantoonsSequence winningWarPlantoonsSeq = new WarPlantoonsSequence(
                winningArrangementProviderService.getWinnableSeq(myPlantoons.getPlantoonsSeq(), oppPlantoons.getPlantoonsSeq(), 0),
                myPlantoons.getPlantoonsCount());

        Assertions.assertTrue(checkWinnable(winningWarPlantoonsSeq.toString(), oppArmyString),
                "Should return winnable sequence");
    }

    @Test
    public void getWinnableSeqCase4() {
        String myArmyString = "Spearmen#10;Spearmen#10;Spearmen#100;Spearmen#100;Militia#100";
        String oppArmyString = "Spearmen#199;Spearmen#100;Spearmen#10;Spearmen#10;Spearmen#100";
        WarPlantoonsSequence myPlantoons = new WarPlantoonsSequence(myArmyString);
        WarPlantoonsSequence oppPlantoons = new WarPlantoonsSequence(oppArmyString);

        WarPlantoonsSequence winningWarPlantoonsSeq = new WarPlantoonsSequence(
                winningArrangementProviderService.getWinnableSeq(myPlantoons.getPlantoonsSeq(), oppPlantoons.getPlantoonsSeq(), 0),
                myPlantoons.getPlantoonsCount());

        Assertions.assertTrue(checkWinnable(winningWarPlantoonsSeq.toString(), oppArmyString),
                "Should return winnable sequence");
    }

    private boolean checkWinnable (String winSeq, String oppSeq) {
        int winCount = 0;
        String[] oppPlantoons = oppSeq.split(";");
        String[] winPlantoons = winSeq.split(";");
        for (int i = 0; i < oppPlantoons.length; i++) {
            Plantoon oppPlantoon = new Plantoon(oppPlantoons[i]);
            Plantoon winPlantoon = new Plantoon(winPlantoons[i]);
            if (winningArrangementProviderService.isWinnable(winPlantoon, oppPlantoon)) {
                winCount++;
            }
        }
        return winCount >= 3;
    }
}

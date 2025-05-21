package main.java.com.casademo.service;

import main.java.com.casademo.model.Plantoon;

import java.util.List;

public interface WinningArrangementProviderService {
    // generates winnable sequence with null values indicating that slot of opposite plantoon cannot be won by
    // any plantoons from user
    List<Plantoon> getWinnableSeq (List<Plantoon> myPlantoons, List<Plantoon> oppPlantoons, int winCount);

    // returns true if myPlantoon can win oppPlantoon else false
    boolean isWinnable (Plantoon myPlantoon, Plantoon oppPlantoon);
}

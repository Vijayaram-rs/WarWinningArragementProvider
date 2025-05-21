package main.java.com.casademo.service;

import main.java.com.casademo.exception.InvalidInputException;
import main.java.com.casademo.model.Advantage;
import main.java.com.casademo.model.Plantoon;

import java.util.ArrayList;
import java.util.List;

public class WinningArrangementProviderServiceImpl implements WinningArrangementProviderService {
    @Override
    public List<Plantoon> getWinnableSeq (List<Plantoon> myPlantoons, List<Plantoon> oppPlantoons, int winCount) {
        // if already won 3 plantoons, no need to check further
        // if any of the plantoons are empty it means we reached end of computation
        if (winCount > 2 || myPlantoons.isEmpty() || oppPlantoons.isEmpty()) {
            return new ArrayList<>(myPlantoons);
        }

        List<Plantoon> winnablePlantoons = new ArrayList<>();

        /* for each opposite plantoon, we check all my plantoons. If any of my plantoons win, we move to
         * next remaining opposite plantoons by removing used my plantoon and corresponding opposite plantoon.
         * If cannot win by choosing removed plantoon, we readd it and check for other potential my plantoon by backtracking.
         * If none of my plantoons was able to win, null is added.
         */
        for (int i = 0; i < oppPlantoons.size(); i++) {
            Plantoon oppPlantoon = oppPlantoons.get(i);
            for (Plantoon myPlantoon : new ArrayList<>(myPlantoons)) {
                if (isWinnable(myPlantoon, oppPlantoon)) {
                    // if wins, add to winnable list and remove current used plantoon from usable list
                    winnablePlantoons.add(myPlantoon);
                    myPlantoons.remove(myPlantoon);
                    // removing current oppPlantoon by using sublist and incrementing wincount since current oppPlantoon can be won
                    List<Plantoon> nextWinnableSeq = getWinnableSeq(myPlantoons, oppPlantoons.subList(i + 1, oppPlantoons.size()),
                            winCount + 1);
                    if (!nextWinnableSeq.isEmpty()) {
                        //if able to find winning seq with next list opposite plantoons, we can add it to winnable sequence
                        winnablePlantoons.addAll(nextWinnableSeq);
                        return winnablePlantoons;
                    }
                    // readding for backtracking
                    myPlantoons.add(myPlantoon);
                }
            }
            // adding null to indicate this index oppPlantoon cannot be won
            winnablePlantoons.add(null);
        }
        return winnablePlantoons;
    }

    /*
        checks if myPlantoon can win oppPlantoon
        returns true if myPlantoon has advantage and has count *2 > opposite count
        return true if myPlantoon doesn't have advantage and has count > opposite count
        returns false otherwise
     */
    @Override
    public boolean isWinnable (Plantoon myPlantoon, Plantoon oppPlantoon) {
        if (Advantage.advantageMap.containsKey(myPlantoon.getType())) {
            if (Advantage.advantageMap.get(myPlantoon.getType()).contains(oppPlantoon.getType())) {
                return myPlantoon.getSoldierCount() * 2 > oppPlantoon.getSoldierCount();
            } else {
                return myPlantoon.getSoldierCount() > oppPlantoon.getSoldierCount();
            }
        } else {
            throw new InvalidInputException("Plantoon type is not present in advantage map");
        }
    }
}

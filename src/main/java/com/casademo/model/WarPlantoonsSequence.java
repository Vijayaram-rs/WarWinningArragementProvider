package main.java.com.casademo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarPlantoonsSequence {
    private int plantoonsCount;
    private List<Plantoon> plantoonsSeq;

    public WarPlantoonsSequence( String str ) {
        plantoonsSeq = new ArrayList<Plantoon>();
        String[] plantoonStrs = str.split(";");
        plantoonsCount = plantoonStrs.length;
        for (String plantoonStr : plantoonStrs) {
            plantoonsSeq.add(new Plantoon(plantoonStr));
        }
    }

    // used for creating winning sequence
    public WarPlantoonsSequence( List<Plantoon> plantoonsSeq , int plantoonsCount ) {
        this.plantoonsSeq = new ArrayList<>();
        this.plantoonsCount = plantoonsCount;
        // add all elements from input but replace null with element out of plantoonsCount index
        int nullCount = 0;
        for (int i = 0; i < plantoonsCount; i++) {
            if (Objects.nonNull(plantoonsSeq.get(i))) {
                this.plantoonsSeq.add(plantoonsSeq.get(i));
            } else {
                this.plantoonsSeq.add(plantoonsSeq.get(plantoonsSeq.size()-1-nullCount));
                nullCount++;
            }
        }
        // if more than 2 nulls are present, war cannot be won
        if (nullCount > 2) {
            this.plantoonsSeq = new ArrayList<>();
        }
    }

    public int getPlantoonsCount() {
        return plantoonsCount;
    }

    public void setPlantoonsCount(int plantoonsCount) {
        this.plantoonsCount = plantoonsCount;
    }

    public List<Plantoon> getPlantoonsSeq() {
        return plantoonsSeq;
    }

    public void setPlantoonsSeq(List<Plantoon> plantoonsSeq) {
        this.plantoonsSeq = plantoonsSeq;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Plantoon plantoon : plantoonsSeq) {
            if (plantoon != null) {
                sb.append(plantoon.toString());
            } else {
                sb.append("Loss;");
            }
        }
        return sb.toString();
    }
}

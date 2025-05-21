package main.java.com.casademo.model;

import main.java.com.casademo.exception.InvalidInputException;

public class Plantoon {

    private String type;
    private int soldierCount;

    public Plantoon(String inputString) {
        String[] splitStrings = inputString.split("#");
        try {
            this.type = splitStrings[0];
            this.soldierCount = Integer.parseInt(splitStrings[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Cannot find plantoon type or soldier count in "+ inputString +". Use type#count format." +
                    " type should be string and count should be integer");
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Cannot parse soldier count - "+splitStrings[1]+". Use type#count format" +
                    " type should be string and count should be integer");
        }

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSoldierCount() {
        return soldierCount;
    }

    public void setSoldierCount(int soldierCount) {
        this.soldierCount = soldierCount;
    }

    @Override
    public String toString() {
        return type + "#" + soldierCount + ";";
    }

}

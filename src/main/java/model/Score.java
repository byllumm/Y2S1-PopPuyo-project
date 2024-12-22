package model;

public class Score {
    // Attributes
    int score = 0;

    private static final int[] colorBonusTable = new int[6];
    static {
        colorBonusTable[1] = 0;
        colorBonusTable[2] = 3;
        colorBonusTable[3] = 6;
        colorBonusTable[4] = 12;
        colorBonusTable[5] = 24;
    }

    private static final int[] groupBonusTable = new int[12];
    static {
        groupBonusTable[4] = 0;
        groupBonusTable[5] = 2;
        groupBonusTable[6] = 3;
        groupBonusTable[7] = 4;
        groupBonusTable[8] = 5;
        groupBonusTable[9] = 6;
        groupBonusTable[10] = 7;
        groupBonusTable[11] = 10;
    }

    // Getters
    public int getScore() {
        return score;
    }

    public int[] getColorBonusTable(){
        return colorBonusTable;
    }
    public static int[] getGroupBonusTable() {
        return groupBonusTable;
    }

    // Setter
    public void setScore(int score) {
        this.score = score;
    }
}

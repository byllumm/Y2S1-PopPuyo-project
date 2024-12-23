package poppuyo.model;

public class Stage {
    // Attributes
    int stage = 1;
    private static final int scoreForFirstStage = 500;
    private static final double multiplier = 2;
    private static final double[] scoreForEachStage = computeScoreForEachStage();


    // Getter
    public int getStage(){ return stage; }
    public static double[] getScoreForEachStage(){ return scoreForEachStage; }


    // Setter
    public void setStage(int stage) { this.stage = stage; }


    // Class Methods

    // Score for stage n = Score required to reach Stage 1 * Multiplier^(n-1)
    private static double[] computeScoreForEachStage(){
        double[] scoreForStage = new double[10];
        for (int i = 0; i < scoreForStage.length; i++) {
            scoreForStage[i] = scoreForFirstStage * Math.pow(multiplier, i);
        }
        return scoreForStage;
    }
}

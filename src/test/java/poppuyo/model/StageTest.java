package poppuyo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StageTest {
    private Stage stage;

    @BeforeEach
    public void setUp() {
        stage = new Stage();
    }

    @Test
    public void getStage() {
        Assertions.assertEquals(1, stage.getStage());
    }

    @Test
    public void setStage() {
        stage.setStage(5);
        Assertions.assertEquals(5, stage.getStage());
    }

    @Test
    public void getScoreForEachStage() {
        double[] expectedScores = new double[10];
        for (int i = 0; i < expectedScores.length; i++) {
            expectedScores[i] = 500 * Math.pow(2, i);
        }
        Assertions.assertArrayEquals(expectedScores, Stage.getScoreForEachStage());
    }

    @Test
    public void computeScoreForEachStage() {
        double[] computedScores = Stage.getScoreForEachStage();

        Assertions.assertEquals(500, computedScores[0]);
        Assertions.assertEquals(1000, computedScores[1]);
        Assertions.assertEquals(2000, computedScores[2]);
        Assertions.assertEquals(4000, computedScores[3]);
        Assertions.assertEquals(8000, computedScores[4]);
        Assertions.assertEquals(16000, computedScores[5]);
        Assertions.assertEquals(32000, computedScores[6]);
        Assertions.assertEquals(64000, computedScores[7]);
        Assertions.assertEquals(128000, computedScores[8]);
        Assertions.assertEquals(256000, computedScores[9]);
    }
}


package poppuyo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    private Score score;

    @BeforeEach
    public void setUp() {
        score = new Score();
    }

    @Test
    public void getScore() {
        Assertions.assertEquals(0, score.getScore());
    }

    @Test
    public void setScore() {
        score.setScore(100);
        Assertions.assertEquals(100, score.getScore());
    }

    @Test
    public void getColorBonusTable() {
        int[] expectedColorBonusTable = {0, 0, 3, 6, 12, 24};
        Assertions.assertArrayEquals(expectedColorBonusTable, Score.getColorBonusTable());
    }

    @Test
    public void getGroupBonusTable() {
        int[] expectedGroupBonusTable = {0, 0, 0, 0, 0, 2, 3, 4, 5, 6, 7, 10};
        Assertions.assertArrayEquals(expectedGroupBonusTable, Score.getGroupBonusTable());
    }
}
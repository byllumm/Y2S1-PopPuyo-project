package poppuyo.controllers;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import poppuyo.model.Arena;
import poppuyo.model.grid.Grid;
import poppuyo.utils.puyoutils.Position;
import poppuyo.utils.puyoutils.PuyoPair;
import poppuyo.viewer.ArenaViewer;

public class ArenaControllerTest {
    @Mock private Arena mockArenaModel;
    @Mock private ArenaViewer mockArenaViewer;
    @Mock private GameLogicController mockGameLogicController;
    @Mock private GridController mockGridController;
    @Mock private ScoreController mockScoreController;
    @Mock private StageController mockStageController;
    @Mock private Grid mockGridModel;
    @Mock private PuyoPair mockPuyoPair;
    @Mock private TextGraphics mockTextGraphics;
    @Mock private Position mockPosition;
    private ArenaController arenaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
    }
}

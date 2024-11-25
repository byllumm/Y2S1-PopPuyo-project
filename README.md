# PopPuyo
![Logo](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/temporary_game_logo.png)
## Introduction
Our project, named **PopPuyo**, is a strategic tile-matching puzzle game. It challenges the player to score as many points as possible by clearing the board populated by colorful **Puyos**, while avoiding filling the third column, counting from left to right. Once this column is completely filled, the game ends.

## Gameplay Overview
**Puyos** are small balls that fall in pairs from the top of the screen. They come in five distinct colors: **red, blue, green, yellow and purple,** and can be **moved** left or right or **rotated** clockwise or counterclockwise as they fall. The goal is to strategically position these pairs to **form groups of four or more adjacent Puyos of the same color**, making the groups pop and disappear from the board. When this happens, players are awarded points.

Puyos can connect **horizontally** or **vertically** but never diagonally. Once a group of Puyos pops, any Puyos above them fall, potentially creating new groups and causing **chain reactions**. With each pop, the amount of points added to the score multiplies.

## Core mechanics and Features
1.  Scoring System: The game intends for the player to create larger chains and consecutive reactions. Each subsequent pop awards exponentially more points.
    
2.  Game Ending Condition: The game ends specifically when the third column is filled. Players must maximize their score without neglecting the risk of game over.
    
3.  Controls: Players can move and rotate falling Puyos with precision, allowing control of their board.
    
4.  Strategy: The player must anticipate chain reactions and plan multiple moves ahead. Arranging Puyos to maximize future pops while maintaining control of the grid is key in gameplay.

## Mockup
![Mockup](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/mockup_final_upscaled.png)
Special thanks to Matilde Martins (up202306445) for providing the drawing of the Golden Witch!

## UML Diagram
![UML](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/PopPuyoUML.png)

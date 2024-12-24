<h1 align="center">PopPuyo! - LDTS24/25</h1>
<p align="center">
  <img src="https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/new_logo.png" alt="Logo">
</p>

<h2 align="center">Introduction</h2>

<p align="center">
  <strong>PopPuyo</strong> is a strategic tile-matching puzzle game. It challenges the player to score as many points as possible by clearing the board populated by colorful <strong>Puyos</strong>, while avoiding filling the third column, counting from left to right. Once this column is completely filled, the game ends.
</p>
<p align="center">
  <img src="https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/game_demo.gif" alt="Gameplay Demo">
</p>
<p align="center">
  <em>Gameplay demo!</em>
</p>



## Gameplay Overview

**Puyos** are small balls that fall in pairs from the top of the screen. They come in five distinct colors: red, blue, green, yellow and purple, and can be moved left or right or rotated clockwise or counterclockwise as they fall. The goal is to strategically position these pairs to form groups of four or more adjacent Puyos of the same color, making the groups pop and disappear from the board. When this happens, players are awarded points.

Puyos can connect horizontally or vertically but never diagonally. Once a group of Puyos pops, any Puyos above them fall, potentially creating new groups and causing chain reactions. With each pop, the amount of points added to the score multiplies.



## Core Mechanics and Features

-   **State Transitions:** The game contains multiple states, including **MENU**, **CREDITS**, **PLAYING** and **EXIT**. Transitions between states are triggered based on user input and gameplay.
    
-   **Menu Screen:** The game initiates with a menu where players can navigate using the **up** and **down** arrow keys, and select options using the **Enter** key, allowing them to choose which screen or functionality they want to access.
    
-   **Credits Screen:** Selecting the credits option from the menu displays information about the game's creators and contributors. To return to the menu, players can press the **'m'** key on the keyboard.
    
-   **Exit Option:** The **Exit** option in the menu allows players to close the game and exit to the system.
    
-   **Single-Player Gameplay:** The game is designed for single-player only, where that one player is the one interacting with the game arena and controlling the falling puyos.
    
-   **Game Controls:** Players control the falling puyos using the **left**, **right** and **down** arrow keys. They can rotate puyos using the **'x'** (clockwise) and **'z'** (counterclockwise) keys. This precise control allows players to maneuver puyos and strategize their placements on the game board.
    
-   **Chains and Scoring:** The game includes a **chain** reaction mechanic. When puyos of the same color connect in chains of **four or more**, they pop, clearing space on the board and awarding points. The larger the chain, the more points for the player, with each subsequent pop giving exponentially more points.
    
-   **Strategy:** Thinking ahead is key to success. Players must anticipate chain reactions and plan moves beforehand to create bigger chains and maximize points.
    
-   **Game Stages:** As players progress and accumulate points, they reach new **stages**. Each new stage increases the falling speed of the Puyos, making the game more challenging over time.
    
-   **Game Ending Condition:** The game ends specifically when either the second or third column are filled. Players must maximize their score without neglecting the risk of the board filling up too quickly.
    
-   **Game Reset:** Once the game ends, the board is completely reset, and the game returns to the menu to allow the player to either start a new session or exit.
    
-   **Visuals:** All game images are rendered and displayed on the screen using sprites that represent all game elements.
    
-   **Error Handling:** The game is designed to handle potential errors ensuring that it doesn't crash or freeze if something goes wrong.


    
## Design Patterns

In our search for a more organized, testable, and maintainable code, we included a variety of design patterns to better the structure and functionality of our system. Central to this architecture is the **Model-View-Controller** (**MVC**) pattern, promoting separation of responsibilities and simplifying both development and testing.

We also employed the **State** pattern when implementing the game menu, ensuring smoother transitions between different behaviors and improving overall user experience. To optimize resource management and facilitate efficient creation of game instances, the **Singleton** pattern was mandatory to our design.

Additionally, our code contains remnants of the **Strategy** and **Factory Method** patterns, providing flexibility in handling different game behaviors and object creation processes. These design patterns collectively contribute to a robust codebase, making it easier to maintain, test, and extend.



## UML Diagrams

![Main Diagram](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/maindiagram.png)

*Main UML diagram of the project structure.*



![State Diagram](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/statediagram.png)

*UML Diagram focused on the State classes.*



![Runners Diagram](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/runnersdiagram.png)

*UML Diagram focused on the Runner classes.*



![Viewers Diagram](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/viewerdiagram.png)

*UML Diagram focused on the Viewer classes.*



## Test Results

![Mutation Results](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/mutation.png)

*Mutation Test Results, obtained with PITest.*

![Coverage Results](https://github.com/FEUP-LDTS-2024/project-t09g07/blob/main/docs/coverage.png)

*Code Coverage Results, obtained with JaCoCo.*

**Credits:** Gonçalo Santana (up202306919), Matilde Fonseca (up202306990) (Developers)
**Special Thanks** to Matilde Martins (up202306445) for contributing the drawing of the Golden Witch!

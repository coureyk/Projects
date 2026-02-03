**COMP 472 Project - Winter 2025**
-

This project involves the development of an AI for the Mini Chess game. Must implement an adversarial search using Minimax and Alpha-Beta Pruning, and develop at least two heuristics to play the game automatically.

Requirements
-
* Implement Mini Chess (2-player game on a 5x5 board with 6 pieces per player).
* Support both manual and automatic play modes.
* Implement the Minimax algorithm with Alpha-Beta pruning.
* Create two heuristics (e1, e2) for AI decision-making.
* The AI must choose optimal moves within a specified time and turn limit.

Deliverables
-
* D1: Manual mode (H-H) by Feb 13, 2025 (30%)
* D2: AI moves with Minimax/Alpha-Beta + heuristics (e0, e1, e2) by Mar 13, 2025 (45%)
* D3: Report and tournament participation by Apr 3, 2025 (10%)

How to Run this Project
-
* Save the project to your machine
* Open Command Prompt and type "cd your\path\to\miniChess"
* Enter python main.py
* Upon booting, the game will prompt you to enter values such as "AI timeout time", "Max number of turns", "Player One/Two type" and "Player One/Two name". While most of this information will not affect the gameplay for D1, all info entered will be stored in a newly generated text file `gameTrace-<b>-<t>-<m>.txt`, where b is either false if alpha-beta is off or true if alpha-beta is active; t is the value of the timeout in seconds; and m is the max number of turns.
* From here, you will be instructed on how to move the pieces and resign the game if you so choose and White will be prompted to make their first move.
* The game will continue until one player's King has been captured or a draw has been reached (i.e. the number of pieces on the board has not changed in 10 turns).
* Enjoy.

Team Members:
-
Haoyu Li,
Nicholas Kamra,
Kevin Courey

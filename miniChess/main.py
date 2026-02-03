from Board import Board
from Player import *
from Logger import *
from GameConfiguration import *
from Minimax import *

def console_game():

    print("Welcome to Mini Chess!")

    # Obtain and save our game preferences
    alpha_beta_active, timeout_time, max_number_turns, player_1_type, player_2_type, player_1_heuristic, player_2_heuristic = game_configuration()

    alpha_beta_active_BOOL = alpha_beta_active
    alpha_beta_active = str(alpha_beta_active_BOOL)

    # Creates a new .txt and overwrites others with the same name
    createTextFile(alpha_beta_active, timeout_time, max_number_turns)

    # 1.0 Game Parameter Trace
    traceGameParameters(alpha_beta_active, timeout_time, max_number_turns, player_1_type, player_2_type, player_1_heuristic, player_2_heuristic)

    if (player_1_type.upper() == "H"):
        p1Name = input("Please enter a name for Player 1 (White): ")
    else:
        p1Name = "AI_1"

    if (player_2_type.upper() == "H"):
        p2Name = input("Please enter a name for Player 2 (Black): ")
    else:
        p2Name = "AI_2"

    p1 = Player(p1Name, "White", player_1_heuristic)
    p2 = Player(p2Name, "Black", player_2_heuristic)
    currentPlayer = p1

    entered_depth = 5
    validDepth = False
    while (validDepth != True):
        if (p1.getHeuristic() != None or p2.getHeuristic() != None):
            entered_depth = input("Please enter how many moves the AI should look ahead: ")
            if (entered_depth.isnumeric() and int(entered_depth) >= 1):
                entered_depth = int(entered_depth)
                validDepth = True
            else:
                print("Invalid entry. Try again.")


    print("\nIn order to move a piece, you must enter the starting square of the piece you would like to move followed by the target square you would like this piece to move to (e.g. B2 B3).")
    print("If you wish to resign the game, enter \"resign\" when prompted to make a move.")

    # 2.0 Board Display and Configuration Trace
    Board.displayBoard()
    traceConfigurationOfBoard(Board)

    while Board.hasGameEnded() == False:        
        requestedMove = ""
        sourceSquare = None
        targetSquare = None
        piece = None
        # traceCurrentDepth(Minimax.current_depth)

        if (currentPlayer.getHeuristic() == None):
            requestedMove = input(f"{currentPlayer.getName()}'s move: ").upper().split(" ")
            
            if (len(requestedMove) == 1 and requestedMove[0] == "RESIGN"):
                print(f"{currentPlayer.getName()} resigned.")
                # Resignation Trace
                traceResign(currentPlayer.getName())
                currentPlayer.incrementTurnCount()

                if (currentPlayer == p1):
                    currentPlayer = p2
                else:
                    currentPlayer = p1
                break

            if (len(requestedMove) != 2): # Invalid movement
                print("Invalid move. Try again.")
                traceInvalidMove("Invalid move. Try again.", currentPlayer.getName())
                continue
            
            sourceSquare = Board.getSquare(requestedMove[0])
            targetSquare = Board.getSquare(requestedMove[1])
            
            if sourceSquare is None or targetSquare is None:
                print(f"Invalid square ID(s). Ensure they are within the board range.") # Invalid movement
                traceInvalidMove("Invalid square ID(s). Ensure they are within the board range.", currentPlayer.getName())
                continue

            piece = sourceSquare.getOccupant()
            if piece is None:
                print(f"No piece found at {sourceSquare.getID()}.") # Invalid movement
                traceInvalidMove(f"No piece found at {sourceSquare.getID()}.", currentPlayer.getName())
                continue
            elif sourceSquare.getOccupant().getColor() != currentPlayer.getColor():
                print(f"Selected square contains enemy piece.") # Invalid movement
                traceInvalidMove("Selected square contains enemy piece.", currentPlayer.getName())
                continue    
        else: 
            root_node = MoveNode(move=None, parent=None, depth=0)

            start_time = time.time()
            alpha = -9999
            beta = 9999

            opponentPlayer = None
            if (currentPlayer == p1):
                opponentPlayer = p2
            else:
                opponentPlayer = p1

            best_score, best_move_node = tree_based_minimax(
                root_node, depth=entered_depth, maximizing_player=True,
                current_player=currentPlayer, opponent_player=opponentPlayer, original_player=currentPlayer,
                start_time=start_time, time_limit=float(timeout_time), usingAlphaBeta=alpha_beta_active_BOOL, alpha=alpha, beta=beta
            )

            # Tracing for 3.1
            end_time = time.time()
            currentPlayer.current_best_score = best_score
            currentPlayer.current_start_time = start_time
            currentPlayer.current_end_time = end_time
            currentPlayer.current_alpha_beta_active = alpha_beta_active_BOOL

            invalidMove = False
            if best_move_node:
                best_move_sequence = best_move_node.get_move_sequence()
                print(f"\nBest Move Sequence: {' -> '.join(best_move_sequence)}")
                print(f"Best Evaluation Score: {best_score}")
                sourceSquare = Board.getSquare((best_move_sequence[0])[3:5])
                targetSquare = Board.getSquare((best_move_sequence[0])[7:9])
                
                piece = sourceSquare.getOccupant()
       
                if piece is None:
                    print(f"No piece found at {sourceSquare.getID()}.") # Invalid movement
                    traceInvalidMove(f"No piece found at {sourceSquare.getID()}.", currentPlayer.getName())
                    invalidMove = True
                elif sourceSquare.getOccupant().getColor() != currentPlayer.getColor():
                    print(f"Selected square contains enemy piece.") # Invalid movement
                    traceInvalidMove("Selected square contains enemy piece.", currentPlayer.getName())
                    invalidMove = True 
            else:
                print("No valid moves found.")
                invalidMove = True

            #If an invalid move was reached by AI, opponent wins
            if (invalidMove):
                if (currentPlayer == p1):
                    currentPlayer = p2
                else:
                    currentPlayer = p1
                break

        # For tracing the capture
        current_target_piece = None
        if targetSquare.getOccupant() is not None:
            current_target_piece = targetSquare.getOccupant().getName()

        if piece.move(targetSquare):
            # 3.1 Actions trace
            currentPlayer.incrementTurnCount()

            traceCurrentTurn(currentPlayer.getName(), currentPlayer.getTurn())
            traceConfigurationOfBoard(Board)
            traceAction(currentPlayer.getName(), piece.getName(), sourceSquare.getID(), targetSquare.getID(), currentPlayer.getTurn())
            if current_target_piece is not None:
                traceCapture(current_target_piece)
            if currentPlayer.getHeuristic() is not None:
                # Tracing for 3.1 part d
                traceAIActionTime(currentPlayer.current_start_time, currentPlayer.current_end_time)

                # Tracing for 3.1 part e
                evaluation = 0
                if (currentPlayer.getHeuristic() == 'e0'):
                    evaluation = e0(pieces, currentPlayer.getColor())
                elif (currentPlayer.getHeuristic() == 'e1'):
                    evaluation = e1(pieces, currentPlayer.getColor())
                else:
                    evaluation = e2(pieces, currentPlayer.getColor())

                traceHeuristicScoreOfBoard(evaluation)

                # Tracing for 3.1 part f
                traceSearchScore(currentPlayer.current_best_score, currentPlayer.current_alpha_beta_active)
                traceCumulativeStatistics(currentPlayer.cumulative_states_explored_by_depth,
                                          currentPlayer.number_of_parents_visited, currentPlayer.cumulative_children)

            print(f"{piece.getName()} moved from {sourceSquare.getID()} to {targetSquare.getID()}!")
            
            #Check if draw has occurred (20 moves [i.e. 10 turns] have been made without capture OR 2x moves have been made [where x = max_number_turns])
            if (Board.getMovesWithoutCapture() == 20 or Board.getTotalMoves() == 2 * int(max_number_turns)):
                traceDraw()
                Board.exitGame()
            
            #If game is not over, turn goes to next player
            if (Board.hasGameEnded() == False):
                if (currentPlayer == p1):
                    currentPlayer = p2
                else:
                    currentPlayer = p1
        else:
            print(f"Invalid move for {piece.getName()} from {sourceSquare.getID()} to {targetSquare.getID()}.")
            if (currentPlayer.getHeuristic() != None):
                if (currentPlayer == p1):
                    currentPlayer = p2
                else:
                    currentPlayer = p1
                break
        print("Updated Board:")
        Board.displayBoard()
    #If this point is reached, the game is over.
    if (Board.getMovesWithoutCapture() == 20 or Board.getTotalMoves() == 2 * int(max_number_turns)):
        print("A draw has been reached. Better luck next time!")
    else:
        print(f"Congratulations, {currentPlayer.getName()}. You won!")
        traceGameWon(currentPlayer.getName(), currentPlayer.turn)

console_game()
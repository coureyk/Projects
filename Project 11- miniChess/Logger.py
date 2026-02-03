import platform 
from io import StringIO

GAME_TRACE_FILE_NAME = ""

def createTextFile(b, t, m):
    if platform.system() == "Windows":
        file_name = f"gameTrace-({b})-({t})-({m}).txt"
    else:
        file_name = f"gameTrace-<{b}>-<{t}>-<{m}>.txt"

    global GAME_TRACE_FILE_NAME
    GAME_TRACE_FILE_NAME = file_name

    with open(file_name, 'w'):
        pass
    return

def log(message):
    global GAME_TRACE_FILE_NAME
    with open(GAME_TRACE_FILE_NAME, 'a') as file:
        file.write(message + "\n")
    return

def traceGameParameters(alpha_beta_active, timeout_time, max_number_turns, player_1_type, player_2_type, player_1_heuristic, player_2_heuristic):
    # 1.0 Game Parameters trace
    log("alpha_beta_active = " + alpha_beta_active)
    log("timeout_time = " + timeout_time)
    log("max_number_turns = " + max_number_turns)
    log("player 1 = " + player_1_type)
    log("player 2 = " + player_2_type)
    log("player 1 heuristic = " + player_1_heuristic) if player_1_heuristic != None else None
    log("player 2 heuristic = " + player_2_heuristic) if player_2_heuristic != None else None

def traceConfigurationOfBoard(Board):
    # 2.0 Board Configuration Trace
    # Use StringIO as a string buffer
    board_string = StringIO()

    for i in range(5):
        board_string.write("    +----+----+----+----+----+\n")
        board_string.write(str(5-i) + "   |")
        for j in range(5):
            if Board.squares[i][j].getOccupant() is not None:
                piece = Board.squares[i][j].getOccupant().getName()
            else:
                piece = "  "
            board_string.write(f" {piece} |")
        board_string.write("\n")
    board_string.write("    +----+----+----+----+----+\n")
    board_string.write("       A    B    C    D    E  \n")

    # Get the content as a single string
    result = board_string.getvalue()
    board_string.close()  # Clean up the buffer
    log(result)
    return

def traceCurrentTurn(playerName, turnCounter):
    log(f"Turn #{str(turnCounter)}. {playerName}'s move.")

def traceAction(pieceName, realPiece, source_square_id, target_square_id, turnCounter):
    # 3.1 Action Trace
    log(f"{pieceName} moved {realPiece} from {source_square_id} to {target_square_id}!")
    return

def traceCapture(currentPiece):
    log(currentPiece + " has been captured!")

def traceGameWon(pieceName, turnCounter):
    # 4. Game Won Trace
    log(f"{pieceName} won in {str(turnCounter)} turns.")
    return

def traceResign(pieceName):
    log(f"{pieceName} has resigned.")

def traceInvalidMove(message, playerName):
    log(f"{playerName} was given an error: '{message}'")

def traceDraw():
    log(f"Draw has been reached as 10 turns without a capture has been reached.")

def traceAIActionTime(start_time, end_time):
    time = (end_time - start_time)
    log(f"time for this action: {time:.3f} sec")

def traceHeuristicScoreOfBoard(best_score):
    log(f"heuristic score: {best_score}")

def traceSearchScore(best_score, alpha_beta_active):
    if alpha_beta_active:
        log(f"alpha-beta search score: {best_score}")
    else:
        log(f"minimax search score: {best_score}")

def traceCurrentDepth(currentDepth):
    log(f"the current depth is {currentDepth}")

def traceCumulativeStatistics(my_dict, number_of_parents, number_of_children):
    cumulative_states = 0
    copy_dict = {}
    for key in my_dict:
        if key != 0:
            copy_dict[key] = my_dict[key]
            cumulative_states += my_dict[key]
    log(f"cumulative states explored: {cumulative_states}")
    log(f"cumulative states explored by depth: {str(copy_dict)}")
    percent_dict = {}
    for key in copy_dict:
        percent_dict[key] = f"{(copy_dict[key] / cumulative_states) * 100: .1f}%"
    log(f"cumulative % states explored by depth: {percent_dict}")
    if number_of_parents != 0:
        average_branching_factor = f"average branching factor: {number_of_children / number_of_parents: .1f}"
        log(average_branching_factor)

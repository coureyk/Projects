from Chess import*
from Player import*
import time

pieces = [
    Piece("bK", "Black", 1000, KingMovement(), "A5"),
    Piece("bQ", "Black", 9, QueenMovement(), "B5"),
    Piece("bB", "Black", 3, BishopMovement(), "C5"),
    Piece("bN", "Black", 3, KnightMovement(), "D5"),
    Piece("bp", "Black", 1, PawnMovement(), "C4"),
    Piece("bp", "Black", 1, PawnMovement(), "D4"),
    Piece("wp", "White", 1, PawnMovement(), "B2"),
    Piece("wp", "White", 1, PawnMovement(), "C2"),
    Piece("wN", "White", 3, KnightMovement(), "B1"),
    Piece("wB", "White", 3, BishopMovement(), "C1"),
    Piece("wQ", "White", 9, QueenMovement(), "D1"),
    Piece("wK", "White", 1000, KingMovement(), "E1"),
]
"""
p1 = Player("WhiteAI", "White", "e0")
p2 = Player("BlackAI", "Black", "e0")
currentPlayer = p1
"""
class MoveNode:
    def __init__(self, move=None, parent=None, depth=0, captured_piece=None):
        """
        :param move: The move leading to this node (e.g., "wP B2->B3").
        :param parent: Parent node.
        :param depth: Depth of this node in the tree.
        :param captured_piece: Piece that was captured during the move (if any).
        """
        self.move = move  
        self.parent = parent  
        self.depth = depth  
        self.captured_piece = captured_piece
        self.children = []  
        self.evaluation = None  #
        self.has_been_added = False

    def set_has_been_added(self):
        self.has_been_added = True

    def get_has_been_added(self):
        return self.has_been_added

    def add_child(self, child_node):
        """ Adds a child node to the current node """
        self.children.append(child_node)

    def is_leaf(self):
        """ Returns True if node has no children """
        return len(self.children) == 0

    def get_move_sequence(self):
        """ Returns the full move sequence from root to this node """
        sequence = []
        node = self
        while node.parent:
            sequence.append(node.move)
            node = node.parent
        return list(reversed(sequence))

    def get_depth(self):
        return self.depth

def is_game_over(pieces):
    """
    Checks if either king has been captured.
    :param pieces: List of all Piece objects.
    :return: True if the game is over (king captured), False otherwise.
    """
    white_king_alive = False
    black_king_alive = False

    for piece in pieces:
        if not piece.is_captured:
            if piece.getName() == "wK":
                white_king_alive = True
            elif piece.getName() == "bK":
                black_king_alive = True

    if not white_king_alive or not black_king_alive:
        return True

    return False

#Heuristic 0 tallies the weights assigned to Player 1's and Player 2's pieces.
def e0(pieces,color):
    score = 0
    for piece in pieces:
        if not piece.is_captured:
            if piece.color == color:
                score += piece.value
            else:
                score -= piece.value
    return score

#Heuristic 1 does the same as Heuristic 0 AND adds each player's possible number of moves to the tally 
def e1(pieces, color):
    score = 0
    score += e0(pieces, color)
    for piece in pieces:
        if not piece.is_captured:
            if piece.color == color:
                score += piece.getTotalValidMoves()
            else:
                score -= piece.getTotalValidMoves()
    return score

#Heuristic 2 does the same as Heuristic 0 AND adds the number of pieces immediately next to (protecting) their King
def e2(pieces, color):
    score = 0
    score += e0(pieces, color)
    for piece in pieces:
        if not piece.is_captured:
            if (piece.getName()[1] == "K"):
                x, y = piece.currentSquare.getCoordinates()
                xRange = [-1,0,1]
                yRange = [-1,0,1]
                for xDisplacement in xRange:
                    targetX = x + xDisplacement 
                    for yDisplacement in yRange:
                        if (xDisplacement == 0 and yDisplacement == 0):
                            continue
                        targetY = y + yDisplacement
                        targetSquare = Board.getSquare(f"{chr(64 + targetX)}{targetY}")
                        if (targetSquare != None):
                            if (targetSquare.getOccupant() != None and targetSquare.getOccupant().getColor() == color):
                                score += 1
                            elif (targetSquare.getOccupant() != None and targetSquare.getOccupant().getColor() != color):
                                score -=1
    return score

def tree_based_minimax(node, depth, maximizing_player, current_player, opponent_player, original_player, start_time, time_limit, usingAlphaBeta, alpha, beta):
    """
    Tree-based Minimax with time tracking.
    :param node: Current MoveNode.
    :param depth: Current search depth.
    :param maximizing_player: Boolean flag for maximizing/minimizing.
    :param current_player: Current player.
    :param opponent_player: Opponent player.
    :param original_player: The player who initiated the Minimax call.
    :param start_time: The time when Minimax started.
    :param time_limit: The maximum allowed time in seconds.
    :return: Tuple(best evaluation score, corresponding MoveNode)
    """
    elapsed_time = time.time() - start_time
    if elapsed_time >= time_limit:
        print(f"Time limit exceeded: {elapsed_time:.2f} seconds. Returning best found move.")
        return node.evaluation if node.evaluation is not None else 0, node

    # -----------------------------------------------------------------------------
    # Nick's part for retrieving the statistics
    if not node.get_has_been_added():
        if original_player.cumulative_states_explored_by_depth.get(node.get_depth()) is None:
            original_player.cumulative_states_explored_by_depth[node.get_depth()] = 1
            node.set_has_been_added()
        else:
            original_player.cumulative_states_explored_by_depth[node.get_depth()] += 1
            node.set_has_been_added()
    # -----------------------------------------------------------------------------


    if depth == 0 or is_game_over(pieces):
        evaluation = 0
        if (current_player.getHeuristic() == 'e0'):
            evaluation = e0(pieces, original_player.getColor())
        elif (current_player.getHeuristic() == 'e1'):
            evaluation = e1(pieces, original_player.getColor())
        else:
            evaluation = e2(pieces, original_player.getColor())

        node.evaluation = evaluation
        #print(f"Depth {node.depth} | Move Sequence: {node.get_move_sequence()} | Evaluation: {evaluation}")
        return evaluation, node

    legal_moves = current_player.get_all_legal_moves()
    if not legal_moves:
        return e0(pieces, original_player.getColor()), node

    if maximizing_player:
        max_eval = float('-inf')
        best_move_node = None

        for piece, move in legal_moves:
            from_square = piece.currentSquare
            to_square = move

            captured_piece = make_move(piece, from_square, to_square)

            move_str = f"{piece.getName()} {from_square.getID()}->{to_square.getID()}"
            child_node = MoveNode(move=move_str, parent=node, depth=node.depth + 1, captured_piece=captured_piece)
            node.add_child(child_node)

            eval_score, _ = tree_based_minimax(child_node, depth - 1, False, opponent_player, current_player, original_player, start_time, time_limit, usingAlphaBeta, alpha, beta)

            undo_move(piece, from_square, to_square, captured_piece)

            if eval_score > max_eval:
                max_eval = eval_score
                best_move_node = child_node

            if (usingAlphaBeta == True):
                alpha = max(alpha, max_eval)
                if beta <= alpha:
                    break

            if time.time() - start_time >= time_limit:
                break

        if len(node.children) != 0:
            original_player.number_of_parents_visited += 1
            original_player.cumulative_children += len(node.children)
        if best_move_node is not None:
            for child in node.children[:]:  
                if child != best_move_node:
                    delete_node(child)
            node.children = [best_move_node]  

        node.evaluation = max_eval

        return max_eval, best_move_node

    else:
        min_eval = float('inf')
        best_move_node = None

        for piece, move in legal_moves:
            from_square = piece.currentSquare
            to_square = move

            captured_piece = make_move(piece, from_square, to_square)

            move_str = f"{piece.getName()} {from_square.getID()}->{to_square.getID()}"
            child_node = MoveNode(move=move_str, parent=node, depth=node.depth + 1, captured_piece=captured_piece)
            node.add_child(child_node)

            eval_score, _ = tree_based_minimax(child_node, depth - 1, True, opponent_player, current_player, original_player, start_time, time_limit, usingAlphaBeta, alpha, beta)

            undo_move(piece, from_square, to_square, captured_piece)

            if eval_score < min_eval:
                min_eval = eval_score
                best_move_node = child_node

            if (usingAlphaBeta == True):
                beta = min(beta, min_eval)
                if (beta <= alpha):
                    break
            
            if time.time() - start_time >= time_limit:
                break
        if len(node.children) != 0:
            original_player.number_of_parents_visited += 1
            original_player.cumulative_children += len(node.children)

        if best_move_node is not None:
            for child in node.children[:]:  # Iterate over a copy of the list
                if child != best_move_node:
                    delete_node(child)
            node.children = [best_move_node]  

        node.evaluation = min_eval

        return min_eval, best_move_node

def make_move(piece, from_square, to_square):
    """
    Simulates a move on the board.
    :param piece: The piece to move.
    :param from_square: The square the piece is moving from.
    :param to_square: The square the piece is moving to.
    :return: The captured piece (if any) to help with undoing the move.
    """
    captured_piece = to_square.getOccupant()
    to_square.setOccupant(piece)
    from_square.setOccupant(None)
    piece.currentSquare = to_square

    if captured_piece:
        #print(captured_piece.name + " is captured")
        captured_piece.currentSquare = None  
        captured_piece.is_captured = True

    # Debugging info
    """ print(f"Move: {piece.getName()} from {from_square.getID()} to {to_square.getID()}")
    if captured_piece:
        print(f"Captured: {captured_piece.getName()} at {to_square.getID()}")"""

    return captured_piece
                      
def undo_move(piece, from_square, to_square, captured_piece):
    """
    Undoes a move on the board.
    :param piece: The piece to move back.
    :param from_square: The square the piece came from.
    :param to_square: The square the piece moved to.
    :param captured_piece: The piece that was captured during the move (if any).
    """
    from_square.setOccupant(piece)
    to_square.setOccupant(captured_piece)
    piece.currentSquare = from_square

    if captured_piece:
        captured_piece.currentSquare = to_square
        captured_piece.is_captured = False
    
    # Debugging info
    """ print(f"Undo Move: {piece.getName()} back to {from_square.getID()}")
    if captured_piece:
        print(f"Restored: {captured_piece.getName()} at {to_square.getID()}")"""

def delete_node(node):
    """ Recursively delete a MoveNode and its children """
    for child in node.children:
        delete_node(child)
    node.children.clear()  
    del node

if __name__ == "__main__":
    p1 = Player("WhiteAI", "White", "e2")
    p2 = Player("BlackAI", "Black", "e2")

    root_node = MoveNode(move=None, parent=None, depth=0)

    time_limit = 10 
    start_time = time.time()
    usingAlphaBeta = False
    alpha = -9999
    beta = 9999
   
    best_score, best_move_node = tree_based_minimax(
        root_node, depth=5, maximizing_player=True,
        current_player=p1, opponent_player=p2, original_player=p1,
        start_time=start_time, time_limit=time_limit, usingAlphaBeta=usingAlphaBeta, alpha=alpha, beta=beta
    )

    if best_move_node:
        best_move_sequence = best_move_node.get_move_sequence()
        print("best move sequence: " + (best_move_sequence[0])[3:5] + " " + (best_move_sequence[0])[7:9])
        print(f"\nBest Move Sequence: {' -> '.join(best_move_sequence)}")
        print(f"Best Evaluation Score: {best_score}")
    else:
        print("No valid moves found.")

    Board.displayBoard()
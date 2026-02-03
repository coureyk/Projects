from Board import Board
class Player:

    def __init__(self, name, color, heuristic):
        self.name = name
        self.color = color
        self.heuristic = heuristic
        self.turn = 0
        # -----------------------------------------------
        # Nick's Part
        # For tracing the AI moves
        self.current_best_score = None
        self.current_board_heuristic_score = None
        self.current_start_time = None
        self.current_end_time = None
        self.current_alpha_beta_active = None
        self.cumulative_states_explored_by_depth = {}
        self.number_of_parents_visited = 0
        self.cumulative_children = 0
        # ------------------------------------------------
        """
        if (color == "White"):
            Pawn(color, "B2")
            Pawn(color, "C2")
            Knight(color, "B1")
            Bishop(color, "C1")
            Queen(color, "D1")
            King(color, "E1")
        else:
            Pawn(color, "C4")
            Pawn(color, "D4")
            Knight(color, "D5")
            Bishop(color, "C5")
            Queen(color, "B5")
            King(color, "A5")
        """

    def getName(self):
        return self.name
    
    def getColor(self):
        return self.color
    
    def getHeuristic(self):
        return self.heuristic
    
    def getTurn(self):
        return self.turn
    
    def incrementTurnCount(self):
        self.turn = self.turn + 1
    
    def get_all_legal_moves(self):
        """ Returns a list of (piece, move) tuples where move is a valid square """
        legal_moves = []
        for x in range(1, 6):  
            for y in range(1, 6):
                square = Board.getSquare(f"{chr(64 + x)}{y}")  
                if square and square.getOccupant() and square.getOccupant().getColor() == self.color:
                    piece = square.getOccupant()
                    for tx in range(1, 6):  
                        for ty in range(1, 6):
                            target_square = Board.getSquare(f"{chr(64 + tx)}{ty}")
                            if target_square and piece.validate_move(target_square):  
                                legal_moves.append((piece, target_square))

        return legal_moves

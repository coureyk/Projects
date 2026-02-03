from Board import Board
from Player import Player
from Chess import Piece, KingMovement, QueenMovement, BishopMovement, KnightMovement, PawnMovement

def setup_test_board():
    """ Sets up a test board with some pieces """
    print("Setting up test board...")
    
    for i in range(5):
        for j in range(5):
            Board.getSquare(f"{chr(65 + i)}{j+1}").setOccupant(None)
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

    print("Test board initialized!\n")
    Board.displayBoard()

    return Board,pieces

test_player = Player("WhiteAI", "White")

def test_get_all_legal_moves():
    """ Calls get_all_legal_moves() and prints the output """
    print("\nTesting get_all_legal_moves()...\n")
    
    legal_moves = test_player.get_all_legal_moves()
    
    if not legal_moves:
        print("❌ No legal moves found! Check your implementation.")
        return
    
    print(f"✅ {len(legal_moves)} legal moves found!")
    for piece, move in legal_moves:
        print(f"{piece.getName()} can move to {move.getID()}")

if __name__ == "__main__":
    setup_test_board()
    test_get_all_legal_moves()

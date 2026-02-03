from abc import ABC, abstractmethod
from Board import Board
from Logger import *

class MovementStrategy(ABC):

    @abstractmethod
    def validate_move(self, piece, target_square):
        pass

    @staticmethod
    def is_within_board(x, y):
        return 1 <= x <= 5 and 1 <= y <= 5
    
def is_path_clear(piece, target_square):
    totalStepsTaken = 0
    currentX, currentY = piece.currentSquare.getCoordinates()
    targetX, targetY = target_square.getCoordinates()
    dx = targetX - currentX
    dy = targetY - currentY
    stepX = (dx // abs(dx)) if dx != 0 else 0
    stepY = (dy // abs(dy)) if dy != 0 else 0
    x, y = currentX + stepX, currentY + stepY
    while (x, y) != (targetX, targetY):
        if not MovementStrategy.is_within_board(x, y):
            return False, totalStepsTaken
        square = Board.getSquare(f"{chr(64 + x)}{y}")
        if square is None or square.getOccupant() is not None:
            return False, totalStepsTaken
        x += stepX
        y += stepY
        totalStepsTaken += 1
    return True, totalStepsTaken
 
class KingMovement(MovementStrategy):

    def validate_move(self, piece, target_square):
        targetX, targetY = target_square.getCoordinates()
        currentX, currentY = piece.currentSquare.getCoordinates()
        dx = abs(targetX - currentX)
        dy = abs(targetY - currentY)
        if dx <= 1 and dy <= 1:
            if target_square.getOccupant() and target_square.getOccupant().getColor() == piece.getColor():
                return False
            return True
        return False

class QueenMovement(MovementStrategy):

    def validate_move(self, piece, target_square):
        targetX, targetY = target_square.getCoordinates()
        currentX, currentY = piece.currentSquare.getCoordinates()
        dx = abs(targetX - currentX)
        dy = abs(targetY - currentY)
        if dx == dy or dx == 0 or dy == 0:
            if target_square.getOccupant() and target_square.getOccupant().getColor() == piece.getColor():
                return False
            return is_path_clear(piece, target_square)[0]
        return False

class BishopMovement(MovementStrategy):

    def validate_move(self, piece, target_square):
        targetX, targetY = target_square.getCoordinates()
        currentX, currentY = piece.currentSquare.getCoordinates()
        dx = abs(targetX - currentX)
        dy = abs(targetY - currentY)
        if dx == dy:
            if target_square.getOccupant() and target_square.getOccupant().getColor() == piece.getColor():
                return False
            return is_path_clear(piece, target_square)[0]
        return False

class KnightMovement(MovementStrategy):

    def validate_move(self, piece, target_square):
        targetX, targetY = target_square.getCoordinates()
        currentX, currentY = piece.currentSquare.getCoordinates()
        if target_square.getOccupant() and target_square.getOccupant().getColor() == piece.getColor():
            return False
        dx = abs(targetX - currentX)
        dy = abs(targetY - currentY)
        return (dx, dy) in [(2, 1), (1, 2)]
    
class PawnMovement(MovementStrategy):

    def validate_move(self, piece, target_square):
        targetX, targetY = target_square.getCoordinates()
        currentX, currentY = piece.currentSquare.getCoordinates()
        direction = 1 if piece.getColor() == "White" else -1
        if targetX == currentX  and targetY == currentY + direction and target_square.getOccupant() == None: 
            return True
        if targetY == currentY + direction and abs(targetX - currentX) == 1:  
            if target_square.getOccupant() and target_square.getOccupant().getColor() != piece.getColor():       
                return True
        return False

    def handle_promotion(self, piece):
        _, currentY = piece.currentSquare.getCoordinates()
        if (piece.getColor() == "White" and currentY == 5) or (piece.getColor() == "Black" and currentY == 1):
            print(f"Pawn promoted to a Queen at {piece.currentSquare.getID()}!")
            piece.movement_strategy = QueenMovement()
            piece.value = 9
            piece.name = "wQ" if piece.getColor() == "White" else "bQ"

class Piece:

    def __init__(self, name, color, value, movement_strategy, squareID):
        self.name = name
        self.color = color
        self.value = value
        self.movement_strategy = movement_strategy
        self.currentSquare = Board.getSquare(squareID)
        self.currentSquare.setOccupant(self)
        self.is_captured = False

    def getName(self):
        return self.name

    def getColor(self):
        return self.color
    
    def getTotalValidMoves(self):
        total = 0
        x, y = self.currentSquare.getCoordinates()
        if (self.name[1] == "P"):
            xRange = [-1,0,1]
            yRange = [1]
            for yDisplacement in yRange:
                if (self.color == "Black"):
                    yDisplacement = -1
                targetY = y + yDisplacement
                for xDisplacement in xRange:
                    targetX = x + xDisplacement
                    targetSquare = Board.getSquare(f"{chr(64 + targetX)}{targetY}")
                    if (targetSquare != None):
                        if (self.validate_move(targetSquare)):
                            total += 1
        elif (self.name[1] == "N"):
            xRange = [-2,-1,1,2]
            yRange = [-2,-1,1,2]
            for yDisplacement in yRange:
                targetY = y + yDisplacement
                for xDisplacement in xRange:
                    if (abs(xDisplacement) == abs(yDisplacement)):
                        continue
                    targetX = x + xDisplacement
                    targetSquare = Board.getSquare(f"{chr(64 + targetX)}{targetY}")
                    if (targetSquare != None):
                        if (self.validate_move(targetSquare)):
                            total += 1
        elif (self.name[1] == "B" or self.name[1] == "Q"):
            directions = ["up-left", "up-right", "down-left", "down-right"]
            for option in directions:
                if (option == "up-left"):
                    maxStepCount = min(abs(1-x), abs(5-y))
                    if (maxStepCount == 0):
                        continue
                    targetX = x - maxStepCount
                    targetY = y + maxStepCount
                elif (option == "up-right"):
                    maxStepCount = min(abs(5-x), abs(5-y))
                    if (maxStepCount == 0):
                        continue
                    targetX = x + maxStepCount
                    targetY = y + maxStepCount
                elif (option == "down-left"):
                    maxStepCount = min(abs(1-x), abs(1-y))
                    if (maxStepCount == 0):
                        continue
                    targetX = x - maxStepCount
                    targetY = y - maxStepCount
                elif (option == "down-right"):
                    maxStepCount = min(abs(5-x), abs(1-y))
                    if (maxStepCount == 0):
                        continue
                    targetX = x + maxStepCount
                    targetY = y - maxStepCount

                targetSquare = Board.getSquare(f"{chr(64 + targetX)}{targetY}")
                if (targetSquare != None):
                    total += is_path_clear(self, targetSquare)[1]

            if (self.name[1] == "Q"):
                moreDirections = directions = ["up", "down", "left", "right"]
                for option in moreDirections:
                    if (option == "up"):
                        maxStepCount = abs(5-y)
                        if (maxStepCount == 0):
                            continue
                        targetX = x
                        targetY = y + maxStepCount
                    elif (option == "down"):
                        maxStepCount = abs(1-y)
                        if (maxStepCount == 0):
                            continue
                        targetX = x
                        targetY = y - maxStepCount
                    elif (option == "left"):
                        maxStepCount = abs(1-x)
                        if (maxStepCount == 0):
                            continue
                        targetX = x - maxStepCount
                        targetY = y
                    elif (option == "right"):
                        maxStepCount = abs(5-x)
                        if (maxStepCount == 0):
                            continue
                        targetX = x + maxStepCount
                        targetY = y

                    targetSquare = Board.getSquare(f"{chr(64 + targetX)}{targetY}")
                    if (targetSquare != None):
                        total += is_path_clear(self, targetSquare)[1]

        elif (self.name[1] == "K"):
            xRange = [-1,0,1]
            yRange = [-1,0,1]
            for yDisplacement in yRange:
                targetY = y + yDisplacement
                for xDisplacement in xRange:
                    if (yDisplacement == 0 and xDisplacement == 0):
                        continue
                    targetX = x + xDisplacement
                    targetSquare = Board.getSquare(f"{chr(64 + targetX)}{targetY}")
                    if (targetSquare != None):
                        if (self.validate_move(targetSquare)):
                            total += 1
        return total
    
    def validate_move(self, target_square):
        return self.movement_strategy.validate_move(self, target_square)

    def move(self, target_square):
        if self.validate_move(target_square):
            Board.incrementTotalMoves()
            if target_square.getOccupant():
                if target_square.getOccupant().getName()[1] == "K":
                    Board.exitGame()
                target_square.getOccupant().capture()
            else:
                Board.incrementMovesWithoutCapture()
            self.currentSquare.setOccupant(None)
            self.currentSquare = target_square
            self.currentSquare.setOccupant(self)
            if isinstance(self.movement_strategy, PawnMovement):
                self.movement_strategy.handle_promotion(self)
            return True
        return False
    
    def capture(self):
        #print(self.getName() +" has been captured!")
        self.currentSquare.setOccupant(None)  
        self.currentSquare = None 
        self.is_captured = True
        Board.resetMovesWithoutCapture()
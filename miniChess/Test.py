class Square:
    def __init__(self, x, y):
        dict = {  1: "A",
                  2: "B",
                  3: "C",
                  4: "D",
                  5: "E"
                }

        self.id = dict[x] + str(y)
        self.coordinates = (x, y) #A1 = (1,1), ..., E5 = (5,5)
        self.occupant = None

    def getID(self):
        return self.id
    
    def getCoordinates(self):
        return self.coordinates

    def getOccupant(self):
        return self.occupant
    
    def setOccupant(self, occupant):
        self.occupant = occupant

#static class
class Board:
    squares = [[],[],[],[],[]]

    for i in range(5):
        for j in range(5):
            squares[i].append(Square(j + 1, 5 - i))
            print (squares[i][j].getCoordinates(), end = " ")
        print()

    def getSquare(id):
        rowIndex = {    "5": 0,
                        "4": 1,
                        "3": 2,
                        "2": 3,
                        "1": 4
                    }
        
        columnIndex =   {   "A": 0,
                            "B": 1,
                            "C": 2,
                            "D": 3,
                            "E": 4,
                        }
        
        strID = str(id).upper()
        if (len(strID) == 2):
            x = rowIndex.get(strID[1])
            y = columnIndex.get(strID[0])
            if ((x != None) and (y != None)): #if strID[0] matches a key in columnIndex AND if strID[1] matches a key in rowIndex, accept id
                return Board.squares[x][y]
        else:
            return None
        
    def displayBoard():
        for i in range(5):
            print("    +----+----+----+----+----+")
            print(str(5-i) + "   |", end = "")
            for j in range(5):
                if (Board.squares[i][j].getOccupant() != None):
                    piece = Board.squares[i][j].getOccupant().getName()
                else:
                    piece = "  "
                print(" " + piece + " |", end = "")
            print()
        print("    +----+----+----+----+----+")
        print("       A    B    C    D    E  ")



class Knight:
    def __init__(self, color, squareID):
        if (color == "White"):
            self.name = "wK"
        else:
            self.name = "bK"
        self.color = color
        self.value = 3
        self.currentSquare = Board.getSquare(squareID)
        self.currentSquare.setOccupant(self)

    def getName(self):
        return self.name

    def getColor(self):
        return self.color

    def validateMove(self, targetSquare):        
        targetX = targetSquare.getCoordinates()[0]
        targetY = targetSquare.getCoordinates()[1]

        currentX = self.currentSquare.getCoordinates()[0]
        currentY = self.currentSquare.getCoordinates()[1]

        if ((targetSquare.getOccupant() != None) and (targetSquare.getOccupant().getColor() == self.getColor())):
            return False

        xDisplacement = [-2,-1,1,2]
        yDisplacement = [-2,-1,1,2]
        for x in xDisplacement:
            for y in yDisplacement:
                
                #x and y displacement cannot have the same absolute value
                if (abs(x) == abs(y)):
                    continue

                #If the current x,y coordinates match with the target coordinates, then the target coordinates are valid
                if (currentX + x == targetX and currentY + y == targetY):
                    self.currentSquare = targetSquare #move piece to target square
                    return True       
        return False

class Player:
    def __init__(self, name, color):
        self.name = name
        self.color = color
        if (color == "White"):
            #(Pawn(color, "B2"))
            #(Pawn(color, "C2"))
            Knight(color, "B1")
            #(Bishop(color, "C1"))
            #(Queen(color, "D1"))
            #(King(color, "E1"))
        else:
            #(Pawn(color, "C4"))
            #(Pawn(color, "D4"))
            Knight(color, "D5")
            #(Bishop(color, "C5"))
            #(Queen(color, "B5"))
            #(King(color, "A5"))

    def getColor(self):
        return self.color

def main():
    #Step 1: Initialize Players and Pieces
    p1 = Player("Kevin", "White")
    p2 = Player("Li", "Black")

    #Step 2: Play
    gameInSession = True
    p1Turn = True
    currentColor = "White"

    Board.displayBoard()
    while (gameInSession):
        requestedMove = input(f"{currentColor}'s move: ").split(" ")
        if (len(requestedMove) != 2):
            print("Invalid move. Try again.")
            continue
        
        initialSquare = Board.getSquare(requestedMove[0])
        targetSquare = Board.getSquare(requestedMove[1])

        #Check if squares exist and current player has a piece on initial square
        if ((initialSquare != None) and (targetSquare != None) and (initialSquare.getOccupant() != None) and (initialSquare.getOccupant().getColor() == currentColor)):
            
            #Validate the move
            validMove = initialSquare.getOccupant().validateMove(targetSquare)
            if (validMove == True):
                
                #Update board
                targetSquare.setOccupant(initialSquare.getOccupant())
                initialSquare.setOccupant(None)

                #Change to opponent's turn
                if (p1Turn):
                    p1Turn = False
                    currentColor = p2.getColor()
                else:
                    p1Turn = True
                    currentColor = p1.getColor()

                #Display Board
                Board.displayBoard();
                continue
        
        print("Invalid Move. Try again.")

if __name__ == "__main__":
    main()
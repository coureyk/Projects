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
    gameOver = False
    totalMoves = 0
    movesWithoutCapture = 0
    
    for i in range(5):
        for j in range(5):
            squares[i].append(Square(j + 1, 5 - i))
            #print (squares[i][j].getCoordinates(), end = " ")
        #print()

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

    def hasGameEnded():
        return Board.gameOver
    
    def exitGame():
        Board.gameOver = True

    def getTotalMoves():
        return Board.totalMoves
    
    def incrementTotalMoves():
        Board.totalMoves += 1
    
    def getMovesWithoutCapture():
        return Board.movesWithoutCapture
    
    def incrementMovesWithoutCapture():
        Board.movesWithoutCapture += 1

    def resetMovesWithoutCapture():
        Board.movesWithoutCapture = 0
    
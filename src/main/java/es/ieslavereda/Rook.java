package es.ieslavereda;

public class Rook extends Piece {
    public Rook(Board board, Coordinate position, Type type) {
        super(type.getType(), board.getCellAt(position));
    }

    //put your task here
    public Coordinate[] getNextMovements(){
        return getNextMovementsAsRook(this);
    }

    //put your task here
    public static Coordinate[] getNextMovementsAsRook(Piece piece) {

        Coordinate[] nextMovements = new Coordinate[0];
        Coordinate posicion = piece.getCell().getCoordinate();
        Coordinate c;
        Board board = piece.getCell().getBoard();

        // Comprobamos que la ficha este en el tablero
        if (posicion == null)
            return nextMovements;

        // Diagonal superior
        c = posicion;
        do {
            c = c.up();
            if (piece.canAddToNextMovements(c))
                nextMovements = Tool.add(c, nextMovements);
        } while (board.contains(c) && board.getCellAt(c).getPiece() == null);

        // Diagonal  der
        c = posicion;
        do {
            c = c.right();
            if (piece.canAddToNextMovements(c))
                nextMovements = Tool.add(c, nextMovements);
        } while (board.contains(c) && board.getCellAt(c).getPiece() == null);

        // Diagonal  izq
        c = posicion;
        do {
            c = c.left();
            if (piece.canAddToNextMovements(c))
                nextMovements = Tool.add(c, nextMovements);
        } while (board.contains(c) && board.getCellAt(c).getPiece() == null);

        // Diagonal inferior
        c = posicion;
        do {
            c = c.down();
            if (piece.canAddToNextMovements(c))
                nextMovements = Tool.add(c, nextMovements);
        } while (board.contains(c) && board.getCellAt(c).getPiece() == null);

        return nextMovements;
    }

    public enum Type {
        BLACK(Piece.Type.BLACK_ROOK), WHITE(Piece.Type.WHITE_ROOK);
        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}

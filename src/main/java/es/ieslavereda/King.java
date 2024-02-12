package es.ieslavereda;

public class King extends Piece {
    public King(Board board, Coordinate position, Type type) {
        super(type.getType(), board.getCellAt(position));
    }

    @Override
    public Coordinate[] getNextMovements() {

        Coordinate position = getCell().getCoordinate();
        Coordinate[] nextMovements = new Coordinate[0];

        if (canAddToNextMovements(position.up()))
            nextMovements = Tool.add(position.up(), nextMovements);

        if (canAddToNextMovements(position.up().right()))
            nextMovements = Tool.add(position.up().right(), nextMovements);

        if (canAddToNextMovements(position.right()))
            nextMovements = Tool.add(position.right(), nextMovements);

        if (canAddToNextMovements(position.down().right()))
            nextMovements = Tool.add(position.down().right(), nextMovements);

        if (canAddToNextMovements(position.down()))
            nextMovements = Tool.add(position.down(), nextMovements);

        if (canAddToNextMovements(position.down().left()))
            nextMovements = Tool.add(position.down().left(), nextMovements);

        if (canAddToNextMovements(position.left()))
            nextMovements = Tool.add(position.left(), nextMovements);

        if (canAddToNextMovements(position.up().left()))
            nextMovements = Tool.add(position.up().left(), nextMovements);

        return nextMovements;
    }

    public enum Type {
        BLACK(Piece.Type.BLACK_KING), WHITE(Piece.Type.WHITE_KING);
        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}

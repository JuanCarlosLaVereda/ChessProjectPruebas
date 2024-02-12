package es.ieslavereda;

public class Knight extends Piece {

    public Knight(Board board, Coordinate position, Type type) {
        super(type.getType(), board.getCellAt(position));
    }

    public Coordinate[] getNextMovements() {

        Coordinate[] nextMovements = new Coordinate[0];

        Coordinate myPosition = getCell().getCoordinate();
        Coordinate c;

        //Up
        c = myPosition.up().up().left();
        if(canAddToNextMovements(c)) nextMovements = Tool.add(c,nextMovements);

        c = myPosition.up().up().right();
        if(canAddToNextMovements(c)) nextMovements = Tool.add(c,nextMovements);

        //Down
        c = myPosition.down().down().left();
        if(canAddToNextMovements(c)) nextMovements = Tool.add(c,nextMovements);

        c = myPosition.down().down().right();
        if(canAddToNextMovements(c)) nextMovements = Tool.add(c,nextMovements);

        //Left
        c = myPosition.left().left().up();
        if(canAddToNextMovements(c)) nextMovements = Tool.add(c,nextMovements);

        c = myPosition.left().left().down();
        if(canAddToNextMovements(c)) nextMovements = Tool.add(c,nextMovements);

        //Right
        c = myPosition.right().right().up();
        if(canAddToNextMovements(c)) nextMovements = Tool.add(c,nextMovements);

        c = myPosition.right().right().down();
        if(canAddToNextMovements(c)) nextMovements = Tool.add(c,nextMovements);


        return nextMovements;
    }

    public enum Type {
        BLACK(Piece.Type.BLACK_KNIGHT), WHITE(Piece.Type.WHITE_KNIGHT);
        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}
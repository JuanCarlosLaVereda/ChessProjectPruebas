package es.ieslavereda.pruebas;

import javax.swing.plaf.PanelUI;
import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private Map<Coordinate, Cell> cells;
    private DeletedPieceManagerListImp piezasBorradas;
    public Board(){
        cells = new TreeMap<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 'A'; j <='H' ; j++) {
                Coordinate coord = new Coordinate((char) j, i);
                cells.put(coord, new Cell(this, coord));
            }
        }
        this.placePieces();
        piezasBorradas = new DeletedPieceManagerListImp(this);
    }

    public DeletedPieceManagerListImp getPiezasBorradas() {
        return piezasBorradas;
    }

    public boolean contains(Coordinate c) {
        if(c.getLetter()<'A' || c.getLetter()>'H') return false;
        if(c.getNumber()<1 || c.getNumber()>8) return false;

        return true;
    }

    public int countType(Piece.Type type){
        return (int)cells.values().stream()
                .filter(cell -> !cell.isEmpty())
                .filter(cell -> cell.getPiece().getType()==type)
                .count();
    }
    public Cell getCellAt(Coordinate c) {
        if(!contains(c)) return null;

        return cells.get(c);
    }

    public void highLight(Collection<Coordinate> coordinates){
        for (Coordinate c : coordinates)
            getCellAt(c).highlight();
    }

    public void removeHighLight(){
        cells.values().stream().forEach(cell -> cell.removeHighLight());
    }

    public Set<Coordinate> getNextMovements(Piece.Color piceColor){
        return cells.values().stream()
                .filter(cell -> !cell.isEmpty())
                .map(cell -> cell.getPiece())
                .filter(piece -> piece.getColor()==piceColor)
                .flatMap(piece -> piece.getNextMovements().stream())
                .collect(Collectors.toSet());
    }

    public Piece getKing(Piece.Color pieceColor){
        Optional optional = cells.values().stream()
                .filter(cell -> !cell.isEmpty())
                .map(cell -> cell.getPiece())
                .filter(piece -> piece instanceof King)
                .map(piece -> (King)piece)
                .filter(king -> king.getColor()==pieceColor)
                .findFirst();
        if (optional.isPresent()){
            return (King)optional.get();
        }
        return null;
    }

    public void placePieces(){
        //Peones
        for (int i = 'A'; i <= 'H'; i++) {
            new Pawn(this, new Coordinate((char) i, 7), Pawn.Type.BLACK);
        }
        for (int i = 'A'; i <= 'H'; i++) {
            new Pawn(this, new Coordinate((char) i, 2), Pawn.Type.WHITE);
        }
        //Torres
        new Rook(this, new Coordinate('A', 1), Rook.Type.WHITE);
        new Rook(this, new Coordinate('H', 1), Rook.Type.WHITE);
        new Rook(this, new Coordinate('A', 8), Rook.Type.BLACK);
        new Rook(this, new Coordinate('H', 8), Rook.Type.BLACK);
        //Caballos
        new Knight(this, new Coordinate('B', 1), Knight.Type.WHITE);
        new Knight(this, new Coordinate('G', 1), Knight.Type.WHITE);
        new Knight(this, new Coordinate('B', 8), Knight.Type.BLACK);
        new Knight(this, new Coordinate('G', 8), Knight.Type.BLACK);
        //Alfiles
        new Bishop(this, new Coordinate('C', 1), Bishop.Type.WHITE);
        new Bishop(this, new Coordinate('F', 1), Bishop.Type.WHITE);
        new Bishop(this, new Coordinate('C', 8), Bishop.Type.BLACK);
        new Bishop(this, new Coordinate('F', 8), Bishop.Type.BLACK);
        //Reinas
        new Queen(this, new Coordinate('D', 1), Queen.Type.WHITE);
        new Queen(this, new Coordinate('D', 8), Queen.Type.BLACK);
        //Reyes
        new King(this, new Coordinate('E', 1), King.Type.WHITE);
        new King(this, new Coordinate('E', 8), King.Type.BLACK);


    }


    @Override
    public String toString() {
        Iterator iterator = cells.values().iterator();
        String aux="    H  G  F  E  D  C  B  A\n";
        int row = 8;
        while (iterator.hasNext()){
            aux+=" " + row +" ";
            for (int i = 0; i < 8; i++) {
                aux += iterator.next().toString();
            }
            aux+=" " + row-- + "\n";
        }
        aux+="    H  G  F  E  D  C  B  A\n\n";

        aux += piezasBorradas.toString();

        return aux;
    }
}

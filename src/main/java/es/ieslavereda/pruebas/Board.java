package es.ieslavereda.pruebas;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private Map<Coordinate, Cell> cells;
    public Board(){
        cells = new TreeMap<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 'A'; j <='H' ; j++) {
                Coordinate coord = new Coordinate((char) j, i);
                cells.put(coord, new Cell(this, coord));
            }
        }
    }
    public boolean contains(Coordinate c) {
        if(c.getLetter()<'A' || c.getLetter()>'H') return false;
        if(c.getNumber()<1 || c.getNumber()>8) return false;

        return true;
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


    @Override
    public String toString() {
        Iterator iterator = cells.values().iterator();
        String aux="    A  B  C  D  E  F  G  H\n";
        int row = 1;
        while (iterator.hasNext()){
            aux+=" " + row +" ";
            for (int i = 0; i < 8; i++) {
                aux += iterator.next().toString();
            }
            aux+=" " + row++ + "\n";
        }
        aux+="    A  B  C  D  E  F  G  H";
        return aux;
    }
}

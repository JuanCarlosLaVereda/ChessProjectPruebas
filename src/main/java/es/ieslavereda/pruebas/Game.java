package es.ieslavereda.pruebas;

import java.util.HashSet;
import java.util.Set;

public class Game {
    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b);

        Set<Coordinate> coordinates = new HashSet<>();
        coordinates = b.getCellAt(new Coordinate('B', 7)).getPiece().getNextMovements();
        b.highLight(coordinates);
        System.out.println(b);
        coordinates = b.getCellAt(new Coordinate('E', 2)).getPiece().getNextMovements();
        b.highLight(coordinates);
        System.out.println(b);

    }
}

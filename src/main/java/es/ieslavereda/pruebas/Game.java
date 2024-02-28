package es.ieslavereda.pruebas;

import java.util.*;

public class Game {

    private Board board;
    List<Player> jugadores;

    private Map<Integer, String> movimientos;

    public Game(){
        board = new Board();
        jugadores = new LinkedList<>();
        movimientos = new LinkedHashMap<>();
        askPlayers();
        startGame();
    }

    public void askPlayers(){
        //boolean fin = false;
        jugadores.add(new Player(Input.getString("Name of the Player1: ")));
        jugadores.add(new Player(Input.getString("Name of the Player2: ")));
        String anterior = null;
        jugadores.get(0).setColor(Input.getOptionWhiteBlack("Which color do you want to be Player1? "));
        if (jugadores.get(0).getColor().equals(Piece.Color.WHITE)){
            jugadores.get(1).setColor(Piece.Color.BLACK);
        } else {
            jugadores.get(1).setColor(Piece.Color.WHITE);
        }
    }

    public void startGame(){
        boolean fin = false;
        Player blancas = null;
        Optional optional = jugadores.stream().filter(player -> player.getColor().equals(Piece.Color.WHITE)).findFirst();
        if (optional.isPresent()){
            blancas = (Player) optional.get();
        }
        Player negras = null;
        optional = jugadores.stream().filter(player -> player.getColor().equals(Piece.Color.BLACK)).findFirst();
        if (optional.isPresent()){
            negras = (Player) optional.get();
        }
        while (!fin){
            if (movimientos.size()%2==0){
                turno(blancas);
            } else {
                turno(negras);
            }
        }
    }

    public void turno(Player player){
        System.out.println(board);
        System.out.println(player.getNombre() + " turn");
        System.out.println("Wich piece do you wanna move?");
        Coordinate coordinate = null;
        while (!checkCoord(coordinate, player)){
            coordinate = new Coordinate(Input.getString("Give me a valid letter(A-H) and the piece has to be yours: ").charAt(0), Input.getInteger("Give me a valid number(1-8) and the piece has to be yours: "));
        }
        Set<Coordinate> posibleMoves = board.getCellAt(coordinate).getPiece().getNextMovements();
        board.highLight(posibleMoves);
        System.out.println(board);
        System.out.println("Where do you wanna move that piece?");
        Coordinate coordinateMoverse = new Coordinate('0', 0);
        while (!checkCoord(coordinateMoverse) && !posibleMoves.contains(coordinateMoverse)){
            coordinateMoverse = new Coordinate(Input.getString("Give me a valid letter(A-H) Where your piece can move: ").charAt(0), Input.getInteger("Give me a valid number(1-8) Where your piece can move: "));
        }
        board.getCellAt(coordinate).getPiece().moveTo(coordinateMoverse);
        movimientos.put(movimientos.size()+1, coordinate.toString() + "-->" + coordinateMoverse.toString());
        board.removeHighLight();
    }

    public void turnoBlack(Player negras){


    }

    public boolean checkCoord(Coordinate coordinate){
        if (coordinate == null) {
            return false;
        }

        char letter = coordinate.getLetter();
        int number = coordinate.getNumber();

        return letter>='A' && letter<='H' && number>=1 && number<=8;
    }

    public boolean checkCoord(Coordinate coordinate, Player player){
        if (coordinate==null){
            return false;
        }

        char letter = coordinate.getLetter();
        int number = coordinate.getNumber();

        return letter>='A' && letter<='H' && number>=1 && number<=8
                && !board.getCellAt(coordinate).isEmpty()
                && board.getCellAt(coordinate).getPiece().getColor().equals(player.getColor());
    }

    public Board getBoard() {
        return board;
    }
    public List<Player> getJugadores() {
        return jugadores;
    }
}

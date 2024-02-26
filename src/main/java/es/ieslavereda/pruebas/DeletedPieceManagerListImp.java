package es.ieslavereda.pruebas;

import java.util.ArrayList;
import java.util.List;

public class DeletedPieceManagerListImp implements IDeletedPieceManager{

    List<Piece> pieceList;

    public DeletedPieceManagerListImp(){
        pieceList = new ArrayList<>();
    }

    @Override
    public void addPiece(Piece piece) {
        pieceList.add(piece);
    }

    @Override
    public Piece removeLast() {
        Piece piece;

        if (pieceList==null){
            return null;
        }
        piece = pieceList.get(pieceList.size()-1);
        pieceList.remove(piece);
        return piece;
    }

    @Override
    public int count(Piece.Type pieceType) {
        int contador = 0;
        if (pieceList==null){
            return 0;
        }
        for (Piece piece:pieceList) {
            if (piece.getType()==pieceType){
                contador++;
            }
        }
        return contador;
    }
}

package es.ieslavereda.pruebas;

public interface IDeletedPieceManager {
    void addPiece(Piece piece);
    Piece removeLast();
    int count(Piece.Type pieceType);
}

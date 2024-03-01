package livecoding;

public abstract class Piece {

    Color color;
    Coordinates coordinates;

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }
}

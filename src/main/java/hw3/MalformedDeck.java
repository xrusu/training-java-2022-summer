package hw3;

class MalformedDeck extends IllegalArgumentException {
    private static final long serialVersionUID = 1;

    public MalformedDeck(String message) {
        super(message);
    }
}
public enum Status {
    GREEN,
    YELLOW,
    RED;

    public static Status getRandomStatus() {
        return values()[(int) (Math.random() * values().length)];
    }
}

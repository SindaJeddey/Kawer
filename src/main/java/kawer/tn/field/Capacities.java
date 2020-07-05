package kawer.tn.field;

public enum Capacities {
    VERY_LOW(5),
    LOW(6),
    MEDIUM(7),
    HIGH(8);

    private final int capacity;

    Capacities(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

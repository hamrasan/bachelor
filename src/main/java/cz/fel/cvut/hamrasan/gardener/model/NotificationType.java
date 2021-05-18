package cz.fel.cvut.hamrasan.gardener.model;

public enum NotificationType {
    LOWTEMPERATURE("LOWTEMPERATURE"), HIGHTEMPERATURE("HIGHTEMPERATURE"), VALVING("VALVING");

    private final String type;

    NotificationType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

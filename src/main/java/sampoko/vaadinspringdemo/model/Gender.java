package sampoko.vaadinspringdemo.model;

public enum Gender {

    MALE(0),
    FEMALE(10);

    private int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Gender getGender(final Integer value) {
        for (Gender gender : values()) {
            if (gender.getValue() == value) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unspecified gender value " + value);
    }

}

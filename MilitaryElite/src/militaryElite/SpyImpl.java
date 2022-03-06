package militaryElite;

public class SpyImpl extends SoldierImpl{
    private final String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + String.format("Code Number: %s", this.getCodeNumber());
    }
}

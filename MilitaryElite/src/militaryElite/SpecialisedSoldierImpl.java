package militaryElite;

public class SpecialisedSoldierImpl extends PrivateImpl{
    private Corp corp;
    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, Corp corp) {
        super(id, firstName, lastName, salary);
        this.corp = corp;
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }
}

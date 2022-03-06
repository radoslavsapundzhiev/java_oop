package militaryElite;

public class PrivateImpl extends SoldierImpl{
    private final double salary;

    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("Salary: %.2f", this.getSalary());
    }

    public double getSalary() {
        return salary;
    }
}

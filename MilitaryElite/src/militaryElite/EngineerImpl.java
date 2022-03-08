package militaryElite;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer{
    private Set<Repair> repairs;
    public EngineerImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new LinkedHashSet<>();
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(super.toString())
                .append(System.lineSeparator())
                .append(String.format("Corps: %s", this.getCorps()))
                .append(System.lineSeparator())
                .append("Repairs:");
        if(this.repairs.size() > 0) {
            builder.append(System.lineSeparator());
        }
        for (Repair repair: this.repairs) {
            builder.append("  ");
            builder.append(repair);
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}

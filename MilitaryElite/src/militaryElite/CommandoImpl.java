package militaryElite;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando{
    private Set<Mission> missions;
    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new LinkedHashSet<>();
    }

    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public Collection<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(super.toString())
                .append(System.lineSeparator())
                .append(String.format("Corps: %s", this.getCorps()))
                .append(System.lineSeparator())
                .append("Missions:");
        if(this.missions.size() > 0) {
            builder.append(System.lineSeparator());
        }
        for (Mission mission: this.missions) {
            builder.append("  ");
            builder.append(mission);
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}

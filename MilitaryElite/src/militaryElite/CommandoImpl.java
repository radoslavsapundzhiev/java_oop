package militaryElite;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando{
    private Set<Mission> missions;
    public CommandoImpl(Corp corp) {
        super(corp);
        this.missions = new HashSet<>();
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
                .append(String.format("Corps: %s", this.getCorp()))
                .append(System.lineSeparator());
        for (Mission mission: this.missions) {
            builder.append(mission);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}

package militaryElite;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral{
    private Set<PrivateImpl> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new HashSet<>();
    }
    @Override
    public void addPrivate(PrivateImpl priv) {
        this.privates.add(priv);
    }

    public Set<PrivateImpl> getPrivates() {
        return privates;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(super.toString())
                .append(System.lineSeparator())
                .append("Privates:")
                .append(System.lineSeparator());
        List<PrivateImpl> sortedSet =  this.privates.stream()
                .sorted((p1, p2) -> p2.getId() - p1.getId())
                .collect(Collectors.toList());
        for (PrivateImpl priv: sortedSet) {
            builder.append(priv);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}

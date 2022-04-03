package glacialExpedition.models.states;

import glacialExpedition.common.ExceptionMessages;

import java.util.Arrays;
import java.util.Collection;

public class StateImpl implements State{
    private String name;
    private Collection<String> exhibits;

    public StateImpl(String name, String[] exhibits) {
        this.setName(name);
        this.exhibits = Arrays.asList(exhibits);
    }

    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new NullPointerException(ExceptionMessages.STATE_NAME_NULL_OR_EMPTY);
        }
    }
}

package militaryElite;

public class Mission {
    private String codeName;
    private State state;

    public Mission(String codeName, State state) {
        this.codeName = codeName;
        this.state = state;
    }

    public void completeMission() {
        if(this.state.name().equals("finished")) {
            this.setState(State.valueOf("inProgress"));
        } else if (this.state.name().equals("inProgress")) {
            this.setState(State.valueOf("finished"));
        }
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s", this.getCodeName(), this.getState());
    }
}

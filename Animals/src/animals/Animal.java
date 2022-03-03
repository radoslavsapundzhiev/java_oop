package animals;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    protected Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    private void setName(String name) {
        if(name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    private void setAge(int age) {
        if(age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    private void setGender(String gender) {
        if(gender != null && !gender.trim().isEmpty()) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    protected abstract String produceSound();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(this.getClass().getSimpleName())
                .append(System.lineSeparator())
                .append(String.format("%s %d %s", this.getName(), this.getAge(), this.getGender()))
                .append(System.lineSeparator())
                .append(this.produceSound());
        return builder.toString();
    }
}

package entities;

import java.util.List;

public class User {
    private String name;
    private String email;
    private Integer age;
    private Double height;
    private List<String> additionalAnswers;

    public User(String name, String email, Integer age, Double height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public List<String> getAdditionalAnswers() {
        return additionalAnswers;
    }

    public void setAdditionalAnswers(List<String> additionalAnswers) {
        this.additionalAnswers = additionalAnswers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n")
                .append(email).append("\n")
                .append(age).append("\n")
                .append(height).append("\n");

        if (additionalAnswers != null && !additionalAnswers.isEmpty()) {
            sb.append("Perguntas Adicionais:\n");
            for (String answer : additionalAnswers) {
                sb.append("- ").append(answer).append("\n");
            }
        }

        return sb.toString();
    }
}

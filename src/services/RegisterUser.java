package services;

import entities.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterUser {
    private List<User> users = new ArrayList<User>();
    private String questionsFilePath;
    private String userDirectoryPath = "D:\\Projetos\\desafio - sistema de cadastros\\Cadastros";

    public RegisterUser(String questionsFilePath) {
        this.questionsFilePath = questionsFilePath;
        loadUsers();
    }

    public User registerUser(){
        Scanner sc = new Scanner(System.in);

        List<String> questions = new ArrayList<>();
        List<String> additionalAnswers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(questionsFilePath))) {
            String line = br.readLine();
            while (line != null) {
                questions.add(line);
                line = br.readLine();
            }

            String name = null;
            String email = null;
            int age = 0;
            double height = 0;

            System.out.println("Cadastre um usuário:");

            for (int i = 0; i < questions.size(); i++) {
                System.out.println(questions.get(i));

                switch (i) {
                    case 0:
                        name = sc.nextLine();
                        validateName(name);
                        break;
                    case 1:
                        email = sc.nextLine();
                        validateEmail(email);
                        checkDuplicateEmail(email);
                        break;
                    case 2:
                        try {
                            age = Integer.parseInt(sc.nextLine());
                            validateAge(age);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Idade deve ser um número inteiro.");
                        }
                        break;
                    case 3:
                        String heightInput = sc.nextLine();
                        height = parseHeight(heightInput);
                        break;
                    default:
                        additionalAnswers.add(sc.nextLine());
                        break;
                }
            }

            User newUser = new User(name, email, age, height);
            newUser.setAdditionalAnswers(additionalAnswers);
            users.add(newUser);

            System.out.println("\nUsuário cadastrado com sucesso!");

            return newUser;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void loadUsers() {
        File directory = new File(userDirectoryPath);

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String name = br.readLine();
                String email = br.readLine();
                int age = Integer.parseInt(br.readLine());
                double height = Double.parseDouble(br.readLine());

                users.add(new User(name, email, age, height));
            } catch (IOException | NumberFormatException e) {
                System.out.println("Erro ao carregar o arquivo: " + file.getName());
            }
        }
    }

    public List<String> searchUsers(String searchTerm) {
        String upperCase = searchTerm.toUpperCase();

        return users.stream()
                .filter(user -> user.getName().toUpperCase().contains(upperCase))
                .map(User::getName)
                .sorted(String::compareToIgnoreCase)
                .toList();
    }

    private void validateName(String name) {
        if (name.length() < 5){
            throw new IllegalArgumentException("O nome deve ter pelo menos 5 caracteres.");
        }
    }

    private void validateEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("O e-mail deve conter o caractere '@'.");
        }
    }

    private void checkDuplicateEmail(String email) {
        boolean exists = users.stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
        if (exists) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail.");
        }
    }

    private void validateAge(int age) {
        if (age <= 18) {
            throw new IllegalArgumentException("O usuário deve ser maior de idade.");
        }
    }

    private double parseHeight(String heightInput) {
        try {
            String standardizedHeight = heightInput.replace(",", ".");
            return Double.parseDouble(standardizedHeight);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Altura deve ser um número no formato correto (ex: 1,75).");
        }
    }
}
import entities.User;
import services.FormManager;
import services.SaveUserToFile;
import services.RegisterUser;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        String questionsFilePath = "D:\\Projetos\\desafio - sistema de cadastros\\formulario.txt";
        FormManager formManager = new FormManager(questionsFilePath);
        RegisterUser registerUser = new RegisterUser(questionsFilePath);

        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Cadastrar nova pergunta ao formulário");
            System.out.println("4 - Deletar pergunta do formulário");
            System.out.println("5 - Pesquisar usuário pelo nome inteiro ou parte dele");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Entrada vazia. Por favor, digite um número.");
                    continue;
                }

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        User user = registerUser.registerUser();
                        if (user != null) {
                            SaveUserToFile.saveUser(user.getName(), user.getEmail(), user.getAge(), user.getHeight());
                        }
                        break;

                    case 2:
                        System.out.println("\n=== Lista de Usuários ===");
                        List<User> users = registerUser.getUsers();
                        if (users.isEmpty()) {
                            System.out.println("Nenhum usuário cadastrado.");
                        } else {
                            int counter = 1;
                            for (User u : users) {
                                System.out.println(counter + " - " + u.getName());
                                counter++;
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Digite a nova pergunta: ");
                        String newQuestion = sc.nextLine();
                        formManager.addQuestion(newQuestion);
                        break;

                    case 4:
                        System.out.print("Digite o número da pergunta a ser deletada (você só pode " +
                                "deletar a partir da 5): ");
                        int questionNumber = Integer.parseInt(sc.nextLine());
                        formManager.deleteQuestion(questionNumber);
                        break;

                    case 5:
                        System.out.print("Digite o nome ou parte do nome para buscar: ");
                        String searchTerm = sc.nextLine();
                        List<String> searchResults = registerUser.searchUsers(searchTerm);
                        if (searchResults.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado com o termo \"" + searchTerm + "\".");
                        } else {
                            System.out.println("\n=== Usuários Encontrados ===");
                            searchResults.forEach(System.out::println);
                        }
                        break;

                    case 6:
                        running = false;
                        System.out.println("Saindo do programa...");
                        break;

                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

        sc.close();
    }
}
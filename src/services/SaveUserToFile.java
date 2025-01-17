package services;

import java.io.*;

public class SaveUserToFile {
    private static final String counterFile = "D:\\Projetos\\desafio - sistema de " +
            "cadastros\\Cadastros\\Contador\\counter.txt";
    private static int userCount = loadUserCount();

    public static void saveUser(String name, String email, int age, double height){
        String fileName = userCount + "-" + name.toUpperCase().replaceAll("\\s+", "") + ".txt";
        String filePath = "D:\\Projetos\\desafio - sistema de cadastros\\Cadastros" + "\\" + fileName;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(name);
            bw.newLine();
            bw.write(email);
            bw.newLine();
            bw.write(String.valueOf(age));
            bw.newLine();
            bw.write(String.format("%.2f", height));

            userCount++;
            saveUserCount(userCount);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static int loadUserCount() {
        try (BufferedReader br = new BufferedReader(new FileReader(counterFile))) {
            String line = br.readLine();
            return (line != null) ? Integer.parseInt(line) : 1;
        } catch (IOException e) {
            return 1;
        }
    }

    private static void saveUserCount(int count) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(counterFile))) {
            bw.write(String.valueOf(count));
        } catch (IOException e) {
            System.out.println("Erro ao salvar o contador: " + e.getMessage());
        }
    }
}

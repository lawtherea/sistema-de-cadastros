package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormManager {
    private final String questionsFilePath;


    public FormManager(String questionsFilePath) {
        this.questionsFilePath = questionsFilePath;
    }

    public void addQuestion(String newQuestion) {
        try (BufferedReader br = new BufferedReader(new FileReader(questionsFilePath))) {
            int questionCount = 0;
            while (br.readLine() != null) {
                questionCount++;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(questionsFilePath, true))) {
                bw.write((questionCount + 1) + " - " + newQuestion);
                bw.newLine();
            }

            System.out.println("Pergunta adicionada com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao adicionar pergunta: " + e.getMessage());
        }
    }

    public void deleteQuestion(int questionNumber) {
        List<String> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(questionsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                questions.add(line);
            }

            if (questionNumber < 5 || questionNumber > questions.size()) {
                System.out.println("Número inválido.");
                return;
            }

            questions.remove(questionNumber - 1);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(questionsFilePath))) {
                for (int i = 0; i < questions.size(); i++) {
                    String updatedQuestion = (i + 1) + " - " + questions.get(i).split(" - ", 2)[1];
                    bw.write(updatedQuestion);
                    bw.newLine();
                }
            }

            System.out.println("Pergunta deletada com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao deletar pergunta: " + e.getMessage());
        }
    }

    public void listQuestions() {
        try (BufferedReader br = new BufferedReader(new FileReader(questionsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar perguntas: " + e.getMessage());
        }
    }
}

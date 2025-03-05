package com.raulmaciel.util;

import com.raulmaciel.exception.LeituraArquivoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readForm(String formPath) {
        List<String> textoFormulario = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(formPath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                textoFormulario.add(linha);
            }

        } catch (IOException e) {
            throw new LeituraArquivoException("Erro na leitura do arquivo: " + formPath, e);
        }

        return textoFormulario;
    }

    public static void printForm(List<String> readForm) {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("\t\t\t\tCadastro de Usuário");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        readForm.forEach(System.out::println);
    }

    public static void writeForm(String filePath, List<String> text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            List<String> formulario = readForm(filePath);
            int i = formulario.size();
            for (String linha : text) {
                writer.newLine();
                writer.write((i + 1) + " - " + linha);
            }


        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void writeFormAppendOff(String filePath, List<String> text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String linha : text) {
                writer.write(linha);
                writer.newLine();
            }


        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void listarArquivos(String diretorioPath) {
        try {
            File diretorio = new File(diretorioPath);
            if (diretorio.exists() && diretorio.isDirectory()) {
                File[] arquivos = diretorio.listFiles();

                if (arquivos != null) {
                    for (File arquivo : arquivos) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                            String nomeArquivo = arquivo.getName();
                            String regex = "^\\d-[A-Z]+.txt";

                            Pattern pattern = Pattern.compile(regex);
                            Matcher matcher = pattern.matcher(nomeArquivo);

                            if (matcher.find()) {
                                System.out.println(arquivo.getName().charAt(0) + " - " + reader.readLine());
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    System.out.println("O diretorio não possui arquivos");
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteLine(String filePath) throws IOException {
        List<String> form = readForm(filePath);
        List<String> formUpdated = new ArrayList<>();

        for (int i = 0; i <form.size(); i++) {
            System.out.println("["+i+"]" + form.get(i));
        }
        System.out.print("Qual pergunta deseja excluir?: ");
        int indexToRemove = scanner.nextInt();

        if (form.isEmpty()) {
            System.err.println("Erro: O arquivo está vazio.");
            return;
        }

        if (indexToRemove < 0 || indexToRemove > form.size()) {
            System.err.println("Erro: Número da linha inválido.");
            return;
        }

        if (indexToRemove <= 3) {
            System.err.println("Erro: Você não pode excluir as perguntas nativas.");
            return;
        }


        for (int i = 0; i < form.size(); i++) {
            if (i != indexToRemove) {
                formUpdated.add(form.get(i));
            }
        }


        writeFormAppendOff(filePath, formUpdated);
        System.out.println("Linha removida com sucesso!");
    }

}

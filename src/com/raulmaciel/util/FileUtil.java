package com.raulmaciel.util;

import com.raulmaciel.exception.LeituraArquivoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> readForm(String formPath){
        List<String> textoFormulario = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(formPath))) {
            String linha;
            while ((linha = reader.readLine()) != null){
                textoFormulario.add(linha);
            }

        } catch (IOException e) {
            throw new LeituraArquivoException("Erro na leitura do arquivo: " + formPath, e);
        }

        return textoFormulario;
    }

    public static void printForm(List<String> readForm){
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("\t\t\t\tCadastro de Usuário");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        readForm.forEach(System.out::println);
    }

    public static void writeForm(String filePath, List<String> text){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String string : text) {
                writer.write(string);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

}

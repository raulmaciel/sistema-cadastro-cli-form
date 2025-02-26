package com.raulmaciel.util;

import com.raulmaciel.exception.LeituraArquivoException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        readForm.forEach(System.out::println);
    }

}

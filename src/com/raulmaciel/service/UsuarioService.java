package com.raulmaciel.service;

import com.raulmaciel.model.Usuario;
import com.raulmaciel.util.FileUtil;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioService {
    private final Scanner sc;
    public static final String CONTADOR_PATH = "data/contador.txt";
    public static final String DIRETORIO_PATH = "data/";
    private static int contador = carregarContador();

    public UsuarioService() {
        this.sc = new Scanner(System.in);
    }

    public Usuario cadastrarUsuario() {
        String nome = null;
        String email = null;
        int idade = 0;
        double altura = 0;
        try {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("1: ");
            nome = sc.nextLine();
            System.out.print("2: ");
            email = sc.nextLine();
            System.out.print("3: ");
            idade = sc.nextInt();
            sc.nextLine();
            System.out.print("4: ");
            altura = sc.nextDouble();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("Entrada Inválida: " + e.getMessage());
            sc.nextLine();
            return null;
        }

        if (nome.isEmpty() || email.isEmpty() || idade <= 0 || altura <= 0) {
            System.err.println("Erro: Não foi possível cadastrar o usuário devido a entradas inválidas.");
            return null;
        }

        return new Usuario(nome, email, idade, altura);
    }

    public static void salvarUsuario(Usuario usuario) {

        if (usuario == null){
            System.out.println("Impossivel salvar arquivo. Usuário não foi criado!");
            return;
        }
        String fileName = ("data/"+ contador + "-" + usuario.getNome().toUpperCase().replace(" ", "") +".txt");
        FileUtil.writeForm(fileName, Arrays.asList(
                     usuario.getNome(),
                "" + usuario.getEmail(),
                "" + usuario.getIdade(),
                "" + usuario.getAltura()
        ));
        contador++;
        salvarContador(contador);
    }

    private static int carregarContador(){

        File contador = new File(CONTADOR_PATH);
        if (!contador.exists() || contador.length() == 0){
            return 1;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(contador))){
            return Integer.parseInt(reader.readLine().trim());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar o contador. Reiniciando em 1.");
            return 1;
        }

    }

    private static void salvarContador(int valor){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CONTADOR_PATH))) {
            writer.write(String.valueOf(valor));
        } catch (IOException e) {
            System.err.println("Erro ao salvar o contador: " + e.getMessage());
        }
    }

    public static void buscarUsuario(String name) {
        try {
            File diretorio = new File(DIRETORIO_PATH);
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
                                List<String> readForm = FileUtil.readForm(arquivo.getAbsolutePath());

                                String nome = readForm.get(0);
                                String email = readForm.get(1);
                                int idade = Integer.parseInt(readForm.get(2));
                                double altura = Double.parseDouble(readForm.get(3));

                                Usuario usuario = new Usuario(nome, email, idade, altura);

                                if (usuario.getNome().toLowerCase().contains(name.toLowerCase())){
                                    System.out.println("-=-=" + usuario.getNome()+ "-=-=");
                                    System.out.println("Idade: " + usuario.getIdade());
                                    System.out.println("Altura: " + usuario.getAltura());
                                    System.out.println("Email: " + usuario.getEmail());
                                }
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
}

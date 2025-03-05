package com.raulmaciel.model;

import com.raulmaciel.cadastro.CadastroPergunta;
import com.raulmaciel.cadastro.CadastroUsuario;
import com.raulmaciel.util.FileUtil;
import com.raulmaciel.util.UsuarioUtil;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String filePath = "data/formulario.txt";

    public static void callMenu(){
        try {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("1- Cadastrar usuário");
            System.out.println("2- Listar todos os usuários cadastrados");
            System.out.println("3- Cadastrar nova pergunta no formulário");
            System.out.println("4- Deletar uma pergunta do formulário");
            System.out.println("5- Pesquisar usuário por nome ou idade ou email");
            System.out.println("6- Sair");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("Escolha: ");
            int option = scanner.nextInt();

            switch (option){
                case 1 :
                    CadastroUsuario.cadastrarUsuario();
                    callMenu();
                    break;
                case 2:
                    System.out.println("\n\t-=-=-= Relátorio -=-=-=");
                    UsuarioUtil.listarUsuarios("data");
                    System.out.println("\n");
                    callMenu();
                    break;
                case 3:
                    System.out.println("\n\t-=-=-= Cadastrar Pergunta -=-=-=");
                    CadastroPergunta.cadastrarNovaPergunta();
                    callMenu();
                    break;
                case 4:
                    System.out.println("\n\t-=-=-= Deletar Pergunta -=-=-=");
                    try {
                        FileUtil.deleteLine(filePath);
                    } catch (IOException e) {
                        System.out.println("Impossivel ler formulario: " + e.getMessage());
                    }
                    callMenu();
                    break;
                case 5:
                    System.out.println("Feature em desenvolvimento!");
                    callMenu();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Entrada inválida!");
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!" + e.getCause());
        }
    }
}

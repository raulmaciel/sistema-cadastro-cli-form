package com.raulmaciel.service;

import com.raulmaciel.model.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UsuarioService {
    private final Scanner sc;

    public UsuarioService() {
        this.sc = new Scanner(System.in);
    }

    public Usuario cadastrarUsuario(){
        String nome = null;
        String email = null;
        int idade = 0;
        double altura = 0;
        try {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=");
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

        if (nome.isEmpty() || email.isEmpty() || idade <= 0 || altura <= 0){
            System.err.println("Erro: Não foi possível cadastrar o usuário devido a entradas inválidas.");
            return null;
        }

        return new Usuario(nome, email,idade,altura);
    }
}

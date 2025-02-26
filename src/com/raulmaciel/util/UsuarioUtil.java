package com.raulmaciel.util;

import com.raulmaciel.model.Usuario;

public class UsuarioUtil {
    public static void imprimeUsuario (Usuario usuario){
        if (usuario != null){
            System.out.println("-=-=--= Relatório =-=-=-=-=");
            System.out.println(usuario.getNome());
            System.out.println(usuario.getEmail());
            System.out.println(usuario.getIdade());
            System.out.println(usuario.getAltura());
        }
        System.out.println("Impossivel gerar relatório: Usuário não encontrado!");
    }
}

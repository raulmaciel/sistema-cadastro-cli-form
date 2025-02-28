package com.raulmaciel.cadastro;

import com.raulmaciel.model.Usuario;
import com.raulmaciel.service.UsuarioService;
import com.raulmaciel.util.FileUtil;
import com.raulmaciel.util.UsuarioUtil;

public class CadastroUsuario {
    public static void cadastrarUsuario(){
        FileUtil.printForm(FileUtil.readForm("data/formulario.txt"));
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.cadastrarUsuario();
        UsuarioUtil.imprimeUsuario(usuario);
        UsuarioService.salvarUsuario(usuario);
        System.out.println("Usuário Cadastrado!");
    }
}

package com.raulmaciel.debug;

import com.raulmaciel.model.Usuario;
import com.raulmaciel.service.UsuarioService;
import com.raulmaciel.util.FileUtil;
import com.raulmaciel.util.UsuarioUtil;

public class Main {
    public static void main(String[] args) {
        FileUtil.printForm(FileUtil.readForm("data/formulario.txt"));
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.cadastrarUsuario();
        UsuarioUtil.imprimeUsuario(usuario);

    }
}
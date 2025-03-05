package com.raulmaciel.cadastro;

import com.raulmaciel.util.FileUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroPergunta {
    public static void cadastrarNovaPergunta(){
        Scanner sc = new Scanner(System.in);
        List<String> text = new ArrayList<>();
        System.out.println("Digite a pergunta que deseja adicionar: ");
        text.add(sc.nextLine());

        FileUtil.writeForm("data\\formulario.txt", text);
        System.out.println("Pergunta Cadastrada!");
    }
}

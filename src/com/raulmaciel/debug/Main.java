package com.raulmaciel.debug;

import com.raulmaciel.util.FileUtil;

public class Main {
    public static void main(String[] args) {
        FileUtil.printForm(FileUtil.readForm("data/formulario.txt"));
    }
}
package com.raulmaciel.exception;

public class LeituraArquivoException extends  RuntimeException{
    public LeituraArquivoException(String mensagem,  Throwable causa) {
        super(mensagem, causa);
    }
}

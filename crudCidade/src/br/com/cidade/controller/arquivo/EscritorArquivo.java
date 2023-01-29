package br.com.cidade.controller.arquivo;

public interface EscritorArquivo<T> {

    void escrever(T object, String arquivo);

    T apagar(String arquivo);
}

package br.com.cidade.controller.arquivo;
import java.io.IOException;
import java.util.List;
java.util.ist;
public interface LeitorArquivo<T> {

    T ler(String arquivo);

    List<T> ler();
}

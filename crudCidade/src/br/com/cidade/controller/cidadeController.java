package br.com.cidade.controller;
import br.com.cidade.model.Cidade;
import java.util.*;
public interface cidadeController {
    void cadastrar(Cidade cidade);

    List<Cidade> listar();


}

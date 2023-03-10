package br.com.cidade.controller;
import br.com.cidade.model.Estado;
import java.util.*;
public interface estadoController {
    void cadastrar(Estado estado);

    Estado ler(UUID id);

    List<Estado> listar();

    void update(UUID id, Estado estado);

    Estado delete(UUID id);
}
package br.com.cidade.controller;
import br.com.cidade.model.Estado;
import java.util.*;
public interface estadoController {
    void cadastrar(Estado estado);

    List<Estado> listar();
}

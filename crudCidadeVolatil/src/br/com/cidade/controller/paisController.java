package br.com.cidade.controller;
import br.com.cidade.model.Pais;
import java.util.*;
public interface paisController {
    void cadastrar(Pais pais);

    Pais ler(UUID id);
    List<Pais> listar();

    void update(UUID id, Pais pais);

    Pais delete(UUID id);
}

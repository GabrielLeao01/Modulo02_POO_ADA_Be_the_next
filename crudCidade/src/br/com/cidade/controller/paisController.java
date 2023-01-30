package br.com.cidade.controller;
import br.com.cidade.model.Pais;
import java.util.*;
public interface paisController {
    void cadastrar(Pais pais);
    List<Pais> listar();
}

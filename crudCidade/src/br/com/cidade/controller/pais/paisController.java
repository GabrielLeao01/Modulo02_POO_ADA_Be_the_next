package br.com.cidade.controller.pais;
import br.com.cidade.model.Pais.Pais;
import java.util.*;
public interface paisController {
    void cadastrar(Pais pais);

    void ler(UUID id);
    List<Pais> listar();

    Void update(UUID id, Pais pais);

    Pais delete(UUID id);
}

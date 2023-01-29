package br.com.cidade.controller.cidade;
import br.com.cidade.model.Cidade.Cidade;
import java.util.*;
public interface cidadeController {
    void cadastrar(Cidade cidade);

    Cidade ler(UUID id);

    List<Cidade> listar();

    void update(UUID id, Cidade cidade);

    Cidade delete(UUID id);

}

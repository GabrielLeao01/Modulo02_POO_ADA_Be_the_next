package br.com.cidade.controller.estado;
import br.com.cidade.model.Estado.Estado;
import java.util.*;
public interface estadoController {
    void cadastrar(Estado estado);

    void ler(UUID id);

    List<Estado> listar();

    void update(UUID id, Estado estado);

    Estado delete(UUID id);
}

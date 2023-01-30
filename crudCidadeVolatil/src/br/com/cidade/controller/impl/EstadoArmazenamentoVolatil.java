package br.com.cidade.controller.impl;
import br.com.cidade.controller.estadoController;
import br.com.cidade.model.Estado;

import java.util.*;

public class EstadoArmazenamentoVolatil implements estadoController{
    private Map<UUID, Estado> estados = new HashMap<>();
    @Override
    public void cadastrar(Estado estado) {
        estado.setId(UUID.randomUUID());
        estados.put(estado.getId(),estado);
    }

    @Override
    public List<Estado> listar() {
        return new ArrayList<>(estados.values());
    }
}

package br.com.cidade.controller.impl;
import br.com.cidade.controller.estado.estadoController;
import br.com.cidade.model.Estado.Estado;

import java.util.*;

public class EstadoArmazenamentoVolatil implements estadoController{
    private Map<UUID, Estado> estados = new HashMap<>();
    @Override
    public void cadastrar(Estado estado) {
        estado.setId(UUID.randomUUID());
        estados.put(estado.getId(),estado);
    }

    @Override
    public void ler(UUID id) {
        Estado encontrado = estados.get(id);
        if(encontrado = null){
            throw new RuntimeException()
        }
        return encontrado;
    }

    @Override
    public List<Estado> listar() {
        return new ArrayList<>(estados.values());
    }

    @Override
    public void update(UUID id, Estado estado) {
        if(estados.containsKey(id)){
            estados.put(id,estados);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Override
    public Estado delete(UUID id) {
        Estado apagado = estados.remove(id);
        if (apagado == null) {
            throw new RuntimeException();
        }
        return apagado;
    }
}

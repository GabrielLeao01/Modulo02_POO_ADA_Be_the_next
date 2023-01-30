package br.com.cidade.controller.impl;
import br.com.cidade.controller.cidadeController;
import br.com.cidade.model.Cidade;

import java.util.*;
public class CidadeArmazenamentoVolatil implements cidadeController {
    private Map<UUID,Cidade> cidades = new HashMap<>();

    @Override
    public void cadastrar(Cidade cidade) {
        cidade.setId(UUID.randomUUID());
        cidades.put(cidade.getId(),cidade);
    }

    @Override
    public Cidade ler(UUID id) {
        Cidade encontrada = cidades.get(id);
        if(encontrada == null){
            throw new RuntimeException();
        }
        return encontrada;
    }

    @Override
    public List<Cidade> listar() {
        return new ArrayList<>(cidades.values());
    }

    @Override
    public void update(UUID id, Cidade cidade) {
        if(cidades.containsKey(id)){
            cidades.put(id,cidade);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Override
    public Cidade delete(UUID id) {
        Cidade apagada = cidades.remove(id);
        if(apagada == null){
            throw new RuntimeException();
        }
        return apagada;
    }
}
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
    public List<Cidade> listar() {
        return new ArrayList<>(cidades.values());
    }
}

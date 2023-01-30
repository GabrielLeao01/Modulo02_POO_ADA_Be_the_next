package br.com.cidade.controller.impl;
import java.util.*;
import br.com.cidade.controller.paisController;
import br.com.cidade.model.Pais;

public class PaisArmazenamentoVolatil implements paisController{
    private Map<UUID,Pais>paises = new HashMap<>();
    @Override
    public void cadastrar(Pais pais) {
        pais.setId(UUID.randomUUID());
        paises.put(pais.getId(),pais);
    }

    @Override
    public List<Pais> listar() {
        return new ArrayList<>(paises.values());
    }
}

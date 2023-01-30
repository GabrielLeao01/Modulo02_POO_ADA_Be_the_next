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
    public Pais ler(UUID id) {
        Pais encontrado = paises.get(id);
        if(encontrado == null){
            throw new RuntimeException();
        }
        return encontrado;
    }

    @Override
    public List<Pais> listar() {
        return new ArrayList<>(paises.values());
    }

    @Override
    public void update(UUID id, Pais pais) {
        if(paises.containsKey(id)){
            paises.put(id,pais);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Override
    public Pais delete(UUID id) {
        Pais apagado = paises.remove(id);
        if (apagado == null) {
            throw new RuntimeException();
        }
        return apagado;
    }
}

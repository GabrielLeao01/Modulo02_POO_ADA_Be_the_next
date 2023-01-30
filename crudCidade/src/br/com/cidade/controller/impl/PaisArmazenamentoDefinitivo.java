package br.com.cidade.controller.impl;
import br.com.cidade.controller.pais.paisController;
import br.com.cidade.model.Cidade.dao.DAOException;
import br.com.cidade.model.Pais.Pais;
import br.com.cidade.model.Pais.dao.PaisDAO;

import java.util.List;
import java.util.UUID;

public class PaisArmazenamentoDefinitivo implements paisController {

    private PaisDAO paisDAO;

    public PaisArmazenamentoDefinitivo(PaisDAO paisDAO) {
        this.paisDAO = paisDAO;
    }

    @Override
    public void cadastrar(Pais pais) {
        try {
            if (pais.getNome() == null) {
                throw new RuntimeException("Pessoa deve ter o nome preenchido");
            }
            pais.setId(UUID.randomUUID());
            paisDAO.cadastrar(pais);
        } catch (DAOException ex) {
            throw new Exception("Falha ao cadastrar", ex);
        }
    }

    @Override
    public void ler(UUID id) {
        try {
            return paisDAO.buscar(id);
        } catch (DAOException e) {
            throw new Exception("Falha ao ler", e);
        }
    }

    @Override
    public List<Pais> listar() {
        try {
            return paisDAO.listar();
        } catch (DAOException e) {
            throw new Exception("Falha ao ler", e);
        }
    }

    @Override
    public void update(UUID id, Pais pais) {
        try {
            paisDAO.atualizar(id, pais);
        } catch (DAOException ex) {
            throw new Exception("Falha ao atualizar", ex);
        }
    }

    @Override
    public Pais delete(UUID id) {
        try {
            return paisDAO.apagar(id);
        } catch (DAOException ex) {
            throw new Exception("Falha ao apagar", ex);
        }
    }
}

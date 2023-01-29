package br.com.cidade.controller.impl;
import br.com.cidade.controller.cidade.cidadeController;
import br.com.cidade.model.Cidade.Cidade;
import br.com.cidade.model.Cidade.dao.CidadeDAO;
import br.com.cidade.model.Cidade.dao.DAOException;

import java.util.List;
import java.util.UUID;

public class CidadeArmazenamentoDefinitivo implements cidadeController{

    private CidadeDAO cidadeDAO;

    public CidadeArmazenamentoDefinitivo(CidadeDAO cidadeDAO) {
        this.cidadeDAO=cidadeDAO;
    }

    @Override
    public void cadastrar(Cidade cidade) {
        try {
            if (cidade.getNome() == null) {
                throw new RuntimeException("Pessoa deve ter o nome preenchido");
            }
            cidade.setId(UUID.randomUUID());
            cidadeDAO.cadastrar(cidade);
        } catch (DAOException ex) {
            throw new Exception("Falha ao cadastrar", ex);
        }
    }

    @Override
    public Cidade ler(UUID id) {
        try {
            return cidadeDAO.buscar(id);
        } catch (DAOException e) {
            throw new Exception("Falha ao ler", e);
        }
    }

    @Override
    public List<Cidade> listar() {
        try {
        return cidadeDAO.listar();
        } catch (DAOException e) {
        throw new Exception("Falha ao ler", e);
        }
    }

    @Override
    public void update(UUID id, Cidade cidade) {
        try {
            cidadeDAO.atualizar(id, cidade);
        } catch (DAOException ex) {
            throw new Exception("Falha ao atualizar", ex);
        }
    }

    @Override
    public Cidade delete(UUID id) {
        try {
            return cidadeDAO.apagar(id);
        } catch (DAOException ex) {
            throw new Exception("Falha ao apagar", ex);
        }
    }
}

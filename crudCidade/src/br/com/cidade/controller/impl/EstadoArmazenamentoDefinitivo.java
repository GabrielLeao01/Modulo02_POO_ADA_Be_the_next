package br.com.cidade.controller.impl;
import br.com.cidade.controller.estado.estadoController;
import br.com.cidade.model.Cidade.dao.DAOException;
import br.com.cidade.model.Estado.Estado;
import br.com.cidade.model.Estado.dao.EstadoDAO;

import java.util.List;
import java.util.UUID;

public class EstadoArmazenamentoDefinitivo implements estadoController {
    private EstadoDAO estadoDAO;

    public EstadoArmazenamentoDefinitivo(EstadoDAO estadoDAO) {
        this.estadoDAO = estadoDAO;
    }

    @Override
    public void cadastrar(Estado estado) {
        try {
            if (estado.getNome() == null) {
                throw new RuntimeException("Pessoa deve ter o nome preenchido");
            }
            estado.setId(UUID.randomUUID());
            estadoDAO.cadastrar(estado);
        } catch (DAOException ex) {
            throw new Exception("Falha ao cadastrar", ex);
        }
    }

    @Override
    public void ler(UUID id) {
        try {
            return estadoDAO.buscar(id);
        } catch (DAOException e) {
            throw new Exception("Falha ao ler", e);
        }
    }

    @Override
    public List<Estado> listar() {
        try {
            return estadoDAO.listar();
        } catch (DAOException e) {
            throw new Exception("Falha ao ler", e);
        }
    }

    @Override
    public void update(UUID id, Estado estado) {
        try {
            estadoDAO.atualizar(id, estado);
        } catch (DAOException ex) {
            throw new Exception("Falha ao atualizar", ex);
        }
    }

    @Override
    public Estado delete(UUID id) {
        try {
            return estadoDAO.apagar(id);
        } catch (DAOException ex) {
            throw new Exception("Falha ao apagar", ex);
        }
    }
}

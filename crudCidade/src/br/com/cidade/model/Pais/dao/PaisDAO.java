package br.com.cidade.model.Pais.dao;

import br.com.cidade.model.Cidade.dao.DAOException;
import br.com.cidade.model.Pais.Pais;

import java.util.List;
import java.util.UUID;

public interface PaisDAO {
    void cadastrar(Pais pais) throws DAOException;

    List<Pais> listar() throws DAOException;

    Pais buscar(UUID id) throws DAOException;

    void atualizar(UUID id, Pais pais) throws DAOException;

    Pais apagar(UUID id) throws DAOException;
}

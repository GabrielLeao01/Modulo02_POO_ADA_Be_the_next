package br.com.cidade.model.Cidade.dao;

import java.util.List;
import java.util.UUID;
import br.com.cidade.model.Cidade.Cidade;
public interface CidadeDAO {
    void cadastrar(Cidade cidade) throws DAOException;

    List<Cidade> listar() throws DAOException;

    Cidade buscar(UUID id) throws DAOException;

    void atualizar(UUID id, Cidade cidade) throws DAOException;

    Cidade apagar(UUID id) throws DAOException;
}

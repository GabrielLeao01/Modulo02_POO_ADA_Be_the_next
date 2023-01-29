package br.com.cidade.model.Estado.dao;
import br.com.cidade.model.Estado.Estado

import java.util.List;
import java.util.UUID;

public interface EstadoDAO {
    void cadastrar(Estado estado) throws DAOException;

    List<Estado> listar() throws DAOException;

    Estado buscar(UUID id) throws DAOException;

    void atualizar(UUID id, Estado estado) throws DAOException;

    Estado apagar(UUID id) throws DAOException;
}

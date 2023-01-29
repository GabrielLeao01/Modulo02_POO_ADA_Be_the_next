package br.com.cidade.model.Estado.dao.impl;

import br.com.cidade.controller.arquivo.EscritorArquivo;
import br.com.cidade.controller.arquivo.LeitorArquivo;

import br.com.cidade.model.Cidade.Cidade;
import br.com.cidade.model.Estado.Estado;
import br.com.cidade.model.Estado.dao.DAOException;
import br.com.cidade.model.Estado.dao.EstadoDAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EstadoArquivoDAO implements EstadoDAO {
    private EscritorArquivo<Estado> escritor;
    private LeitorArquivo<Estado> leitor;
    public EstadoArquivoDAO(EscritorArquivo<Estado> escritor,LeitorArquivo<Estado> leitor) {
        this.escritor=escritor;
        this.leitor=leitor;
    }

    @Override
    public void cadastrar(Estado estado) throws DAOException {
        try {
            escritor.escrever(estado, estado.getId().toString());
        } catch (IOException | RuntimeException ex) {
            throw new DAOException("Erro no cadastro", ex);
        }
    }

    @Override
    public List<Estado> listar() throws DAOException {
        return listar(5);
    }

    private List<Estado> listar(Integer cont) throws DAOException {
        try {
            if (cont == 0) {
                return Collections.emptyList();
            }
            return leitor.ler();
        } catch (IOException ex) {
            System.out.println("Leitura insucedida");
            ex.printStackTrace();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex1) {
                throw new DAOException("Ocorreu falha na leitura da lista e não foi possível se recuperar", ex);
            }
            return listar(cont - 1);
        } catch (ClassNotFoundException | RuntimeException ex) {
            throw new DAOException("Não foi possível consultar a lista", ex);
        }
    }

    @Override
    public Estado buscar(UUID id) throws DAOException {
        try{
            Estado estado = leitor.ler(id.toString());
            return estado;
        } catch (IOException | RuntimeException | ClassNotFoundException ex) {
            throw new DAOException("Cidade não encontrada!", ex);
        }
    }

    @Override
    public void atualizar(UUID id, Estado estado) throws DAOException {
        try {
            escritor.escrever(estado, id.toString());
        } catch (IOException | RuntimeException ex) {
            throw new DAOException("Falha ao atualizar", ex);
        }
    }

    @Override
    public Estado apagar(UUID id) throws DAOException {
        try {
            return escritor.apagar(id.toString());
        } catch (IOException | ClassNotFoundException | RuntimeException ex) {
            throw new DAOException("Falha ao apagar", ex);
        }
    }
}


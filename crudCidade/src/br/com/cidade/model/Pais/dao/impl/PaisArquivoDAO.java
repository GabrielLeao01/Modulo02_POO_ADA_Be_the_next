package br.com.cidade.model.Pais.dao.impl;

import br.com.cidade.controller.arquivo.EscritorArquivo;
import br.com.cidade.controller.arquivo.LeitorArquivo;
import br.com.cidade.model.Cidade.dao.DAOException;
import br.com.cidade.model.Estado.Estado;
import br.com.cidade.model.Pais.Pais;
import br.com.cidade.model.Pais.dao.PaisDAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PaisArquivoDAO implements PaisDAO {
    private EscritorArquivo<Pais> escritor;
    private LeitorArquivo<Pais> leitor;
    public PaisArquivoDAO(EscritorArquivo<Pais> escritor,LeitorArquivo<Pais> leitor) {
        this.escritor=escritor;
        this.leitor=leitor;
    }
    @Override
    public void cadastrar(Pais pais) throws DAOException {
        try {
            escritor.escrever(pais, pais.getId().toString());
        } catch (IOException | RuntimeException ex) {
            throw new DAOException("Erro no cadastro", ex);
        }
    }

    @Override
    public List<Pais> listar() throws DAOException {
        return listar(5);
    }
    private List<Pais> listar(Integer cont) throws DAOException {
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
    public Pais buscar(UUID id) throws DAOException {
        try{
            Pais pais = leitor.ler(id.toString());
            return pais;
        } catch (IOException | RuntimeException | ClassNotFoundException ex) {
            throw new DAOException("Cidade não encontrada!", ex);
        }
    }

    @Override
    public void atualizar(UUID id, Pais pais) throws DAOException {
        try {
            escritor.escrever(pais, id.toString());
        } catch (IOException | RuntimeException ex) {
            throw new DAOException("Falha ao atualizar", ex);
        }
    }

    @Override
    public Pais apagar(UUID id) throws DAOException {
        try {
            return escritor.apagar(id.toString());
        } catch (IOException | ClassNotFoundException | RuntimeException ex) {
            throw new DAOException("Falha ao apagar", ex);
        }
    }
}

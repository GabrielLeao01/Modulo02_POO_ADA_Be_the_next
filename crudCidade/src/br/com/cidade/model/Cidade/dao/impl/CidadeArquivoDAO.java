package br.com.cidade.model.Cidade.dao.impl;

import br.com.cidade.controller.arquivo.EscritorArquivo;
import br.com.cidade.controller.arquivo.LeitorArquivo;
import br.com.cidade.model.Cidade.Cidade;
import br.com.cidade.model.Cidade.dao.CidadeDAO;
import br.com.cidade.model.Cidade.dao.DAOException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CidadeArquivoDAO implements CidadeDAO {

    private EscritorArquivo<Cidade> escritor;

    private LeitorArquivo<Cidade> leitor;

    public CidadeArquivoDAO(EscritorArquivo<Cidade> escritor,LeitorArquivo<Cidade> leitor){
        this.escritor=escritor;
        this.leitor=leitor;
    }

    @Override
    public void cadastrar(Cidade cidade) throws DAOException {
        try {
            escritor.escrever(cidade, cidade.getId().toString());
        } catch (IOException | RuntimeException ex) {
            throw new DAOException("Erro no cadastro", ex);
        }
    }

    @Override
    public List<Cidade> listar() throws DAOException {
        return listar(5);
    }

    private List<Cidade> listar(Integer cont) throws DAOException{
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
    public Cidade buscar(UUID id) throws DAOException {
        try{
            Cidade cidade = leitor.ler(id.toString());
            return cidade;
        } catch (IOException | RuntimeException | ClassNotFoundException ex) {
            throw new DAOException("Cidade não encontrada!", ex);
        }
        }


    @Override
    public void atualizar(UUID id, Cidade cidade) throws DAOException {
        try {
            escritor.escrever(cidade, id.toString());
        } catch (IOException | RuntimeException ex) {
            throw new DAOException("Falha ao atualizar", ex);
        }
    }

    @Override
    public Cidade apagar(UUID id) throws DAOException {
        try {
            return escritor.apagar(id.toString());
        } catch (IOException | ClassNotFoundException | RuntimeException ex) {
            throw new DAOException("Falha ao apagar", ex);
        }
    }
}

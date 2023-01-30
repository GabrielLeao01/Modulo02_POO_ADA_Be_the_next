package br.com.cidade.model;
import java.util.*;

public class Cidade {
    private UUID id;
    private String Nome;
    private Estado estado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }


}

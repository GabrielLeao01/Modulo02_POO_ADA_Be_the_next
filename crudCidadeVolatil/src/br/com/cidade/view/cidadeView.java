package br.com.cidade.view;
import br.com.cidade.controller.cidadeController;
import br.com.cidade.controller.estadoController;
import br.com.cidade.controller.impl.*;
import br.com.cidade.controller.paisController;
import br.com.cidade.model.Cidade;
import br.com.cidade.model.Estado;
import br.com.cidade.model.Pais;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class cidadeView {
    private cidadeController controllerCidade = new CidadeArmazenamentoVolatil();
    private estadoController controllerEstado = new EstadoArmazenamentoVolatil();
    private paisController controllerPais = new PaisArmazenamentoVolatil();

    private Scanner sc = new Scanner(System.in);
    
    public void cadastrarCidade(){
    Cidade cidade = new Cidade();
        System.out.println("Digite o nome da cidade:");
        String nome = sc.next();
        cidade.setNome(nome);
        listarEstado();
        System.out.println("Selecione o id do estado no qual a cidade pertence: ");
        List<Estado> estados = controllerEstado.listar();
        int idEstado = sc.nextInt();
        cidade.setEstado(estados.get(idEstado-1));
        controllerCidade.cadastrar(cidade);
    }
    public void cadastrarEstado(){
        Estado estado = new Estado();
        System.out.println("Digite o nome do estado:");
        String nome = sc.next();
        estado.setNome(nome);
        System.out.println("Digite a sigla do estado:");
        String sigla = sc.next();
        estado.setSigla(sigla);
        listarPais();
        System.out.println("Selecione o id do pais no qual o estado pertence: ");
        List<Pais> paises = controllerPais.listar();
        int idPais = sc.nextInt();
        estado.setPais(paises.get(idPais-1));
        controllerEstado.cadastrar(estado);
    }
    public void cadastrarPais(){
        Pais pais = new Pais();
        System.out.println("Digite o nome do pais:");
        String nome = sc.next();
        pais.setNome(nome);
        System.out.println("Digite a sigla do pais:");
        String sigla = sc.next();
        pais.setSigla(sigla);
        controllerPais.cadastrar(pais);
    }

    public void listarCidade(){
        List<Cidade> cidades = controllerCidade.listar();
        for (int i = 0; i < cidades.size(); i++) {
            System.out.print("ID - "+ (i+1)+" - ");
            mostrarCidade(cidades.get(i));
        }
    }
    public void listarEstado(){
        List<Estado> estados = controllerEstado.listar();
        for (int i = 0; i < estados.size(); i++) {
            System.out.print("ID - "+ (i+1)+" - ");
            mostrarEstado(estados.get(i));
        }
    }
    public void listarPais(){
        List<Pais> paises = controllerPais.listar();
        for (int i = 0; i < paises.size(); i++) {
            System.out.print("ID - "+ (i+1)+" - ");
            mostrarPais(paises.get(i));
        }
    }
    public void mostrarCidade(Cidade cidade){
        System.out.println("Cidade: "+cidade.getNome()+" - Estado: "+cidade.getEstado().getNome()+" - Pais: "+cidade.getEstado().getPais().getNome());

    }
    public void mostrarEstado(Estado estado){
        System.out.println("Estado: "+estado.getNome()+" - Sigla: "+estado.getSigla()+" - Pais: "+estado.getPais().getNome());

    }
    public void mostrarPais(Pais pais){
        System.out.println("Estado: "+pais.getNome()+" - Sigla: "+pais.getSigla());

    }
    public void apagarCidade() {
        listarCidade();
        System.out.println("Informe a cidade que deseja apagar:");
        Integer numero = sc.nextInt();
        sc.nextLine();
        Cidade cidade = controllerCidade.listar().get(numero - 1);
        apagarCidade(cidade.getId());
    }

    public void apagarCidade(UUID id) {
        try {
            Cidade cidade = controllerCidade.delete(id);
            System.out.println("Cidade apagada foi:");
            mostrarCidade(cidade);
        } catch (RuntimeException ex) {
            System.out.println("Cidade não localizada. Tente novamente!");
        }
    }
    public void apagarEstado() {
        listarEstado();
        System.out.println("Informe o estado que deseja apagar:");
        Integer numero = sc.nextInt();
        sc.nextLine();
        Estado estado = controllerEstado.listar().get(numero - 1);
        apagarEstado(estado.getId());
    }

    public void apagarEstado(UUID id) {
        try {
            Estado estado = controllerEstado.delete(id);
            System.out.println("Estado apagado foi:");
            mostrarEstado(estado);
        } catch (RuntimeException ex) {
            System.out.println("Estado não localizado. Tente novamente!");
        }
    }
    public void apagarPais() {
        listarPais();
        System.out.println("Informe o país que deseja apagar:");
        Integer numero = sc.nextInt();
        sc.nextLine();
        Pais pais= controllerPais.listar().get(numero - 1);
        apagarPais(pais.getId());
    }

    public void apagarPais(UUID id) {
        try {
            Pais pais = controllerPais.delete(id);
            System.out.println("Pais apagado foi:");
            mostrarPais(pais);
        } catch (RuntimeException ex) {
            System.out.println("Pais não localizado. Tente novamente!");
        }
    }
    public void atualizarCidade() {
        listarCidade();
        System.out.println("Informe o id da cidade:");
        Integer id = sc.nextInt();
        sc.nextLine();
        Cidade cidade= controllerCidade.listar().get(id - 1);
        atualizarCidade(cidade);
    }

    public void atualizarCidade(Cidade cidade) {
        mostrarCidade(cidade);
        System.out.println("Informe o novo nome:");
        String nome = sc.nextLine();
        cidade.setNome(nome);
        try {
            controllerCidade.update(cidade.getId(), cidade);
        } catch (RuntimeException ex) {
            System.out.println("Cidade nao existente");
        }
    }
    public void atualizarEstado() {
        listarEstado();
        System.out.println("Informe o id do estado:");
        Integer id = sc.nextInt();
        sc.nextLine();
        Estado estado= controllerEstado.listar().get(id - 1);
        atualizarEstado(estado);
    }

    public void atualizarEstado(Estado estado) {
        mostrarEstado(estado);
        System.out.println("Informe o novo nome:");
        String nome = sc.next();
        estado.setNome(nome);
        System.out.println("Informe a nova sigla");
        String sigla = sc.next();
        estado.setSigla(sigla);
        try {
            controllerEstado.update(estado.getId(), estado);
        } catch (RuntimeException ex) {
            System.out.println("Estado nao existente");
        }
    }
    public void atualizarPais() {
        listarPais();
        System.out.println("Informe o id do pais:");
        Integer id = sc.nextInt();
        sc.nextLine();
        Pais pais= controllerPais.listar().get(id - 1);
        atualizarPais(pais);
    }

    public void atualizarPais(Pais pais) {
        mostrarPais(pais);
        System.out.println("Informe o novo nome:");
        String nome = sc.next();
        pais.setNome(nome);
        System.out.println("Informe a nova sigla");
        String sigla = sc.next();
        pais.setSigla(sigla);
        try {
            controllerPais.update(pais.getId(), pais);
        } catch (RuntimeException ex) {
            System.out.println("Pais nao existente");
        }
    }
    public void menu(){
        System.out.println("Digite 1 para o menu de cidades, 2 para o menu de estados, 3 para o menu de paises ou 4 para sair");
        int op = sc.nextInt();
        switch (op){
            case 1:
                menuCidade();
                break;
            case 2:
                menuEstado();
                break;
            case 3:
                menuPais();
            case 4:
                System.exit(0);
            default:
                System.out.println("Número invalido");
        }
        menu();
    }
    public void menuCidade(){
        System.out.println("Digite 1 para cadastrar uma cidade, 2 para listar as cidades existentes, 3 para atualizar informações da cidade, 4 para deletar ou 5 para voltar");
        int op = sc.nextInt();
        switch (op){
            case 1:
                cadastrarCidade();
                break;
            case 2:
                listarCidade();
                break;
            case 3:
                atualizarCidade();
                break;
            case 4:
                apagarCidade();
                break;
            case 5:
                menu();
                break;
            default:
                System.out.println("Número invalido");
        }
        menuCidade();
    }
    public void menuEstado(){
        System.out.println("Digite 1 para cadastrar um estado, 2 para listar os estados existentes,3 para atualizar informações do estado, 4 para deletar ou 5 para voltar");
        int op = sc.nextInt();
        switch (op){
            case 1:
                cadastrarEstado();
                break;
            case 2:
                listarEstado();
                break;
            case 3:
                atualizarEstado();
                break;
            case 4:
                apagarEstado();
                break;
            case 5:
                menu();
                break;
            default:
                System.out.println("Número invalido");
        }
        menuEstado();
    }
    public void menuPais(){
        System.out.println("Digite 1 para cadastrar um pais, 2 para listar os paises existentes, 3 para atualizar informações do pais, 4 para deletar ou 5 para voltar");
        int op = sc.nextInt();
        switch (op){
            case 1:
                cadastrarPais();
                break;
            case 2:
                listarPais();
                break;
            case 3:
                atualizarPais();
                break;
            case 4:
                apagarPais();
                break;
            case 5:
                menu();
                break;
            default:
                System.out.println("Número invalido");
        }
        menuPais();
    }

}

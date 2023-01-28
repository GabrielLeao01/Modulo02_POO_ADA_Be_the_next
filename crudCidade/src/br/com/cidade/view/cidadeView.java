package br.com.cidade.view;
import br.com.cidade.controller.*;
import br.com.cidade.controller.impl.*;
import br.com.cidade.model.*;
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
        cidade.setEstado(estados.get(idEstado));
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
        estado.setPais(paises.get(idPais));
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
            mostrarCidade(cidades.get(i));
        }
    }
    public void listarEstado(){
        List<Estado> estados = controllerEstado.listar();
        for (int i = 0; i < estados.size(); i++) {
            mostrarEstado(estados.get(i));
        }
    }
    public void listarPais(){
        List<Pais> paises = controllerPais.listar();
        for (int i = 0; i < paises.size(); i++) {
            mostrarPais(paises.get(i));
        }
    }
    public void mostrarCidade(Cidade cidade){
        System.out.println("Cidade: "+cidade.getNome()+" - Estado: "+cidade.getEstado().getNome());

    }
    public void mostrarEstado(Estado estado){
        System.out.println("Estado: "+estado.getNome()+" - Sigla: "+estado.getSigla()+" - Pais: "+estado.getPais().getNome());

    }
    public void mostrarPais(Pais pais){
        System.out.println("Estado: "+pais.getNome()+" - Sigla: "+pais.getSigla());

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
        System.out.println("Digite 1 para cadastrar uma cidade, 2 para listar as cidades existentes ou 3 para voltar");
        int op = sc.nextInt();
        switch (op){
            case 1:
                cadastrarCidade();
                break;
            case 2:
                listarCidade();
                break;
            case 3:
                menu();
                break;
            default:
                System.out.println("Número invalido");
        }
        menuCidade();
    }
    public void menuEstado(){
        System.out.println("Digite 1 para cadastrar um estado, 2 para listar os estados existentes ou 3 para voltar");
        int op = sc.nextInt();
        switch (op){
            case 1:
                cadastrarEstado();
                break;
            case 2:
                listarEstado();
                break;
            case 3:
                menu();
                break;
            default:
                System.out.println("Número invalido");
        }
        menuEstado();
    }
    public void menuPais(){
        System.out.println("Digite 1 para cadastrar um pais, 2 para listar os paises existentes ou 3 para voltar");
        int op = sc.nextInt();
        switch (op){
            case 1:
                cadastrarPais();
                break;
            case 2:
                listarPais();
                break;
            case 3:
                menu();
                break;
            default:
                System.out.println("Número invalido");
        }
        menuPais();
    }

}

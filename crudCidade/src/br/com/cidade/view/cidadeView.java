package br.com.cidade.view;
import br.com.cidade.controller.cidadeController;
import br.com.cidade.controller.impl.armazenamentoVolatil;
import br.com.cidade.model.Cidade;

import java.util.*;

public class cidadeView {
    private cidadeController controller = new armazenamentoVolatil();

    private Scanner sc = new Scanner(System.in);

    public void cadastrar(){
    Cidade cidade = new Cidade();
        System.out.println("Digite o nome da cidade:");
        String nome = sc.next();
        cidade.setNome(nome);
        System.out.println("Digite o nome do estado:");
        String estado = sc.next();
        cidade.setEstado(estado);
        controller.cadastrar(cidade);
    }

    public void listar(){
        List<Cidade> cidades = controller.listar();
        for (int i = 0; i < cidades.size(); i++) {
            mostrarCidade(cidades.get(i));
        }
    }
    public void mostrarCidade(Cidade cidade){
        System.out.println("Cidade: "+cidade.getNome()+" - Estado: "+cidade.getEstado());

    }
    public void menu(){
        System.out.println("Digite 1 para cadastrar uma cidade ou 2 para listar as cidades existentes");
        int op = sc.nextInt();
        switch (op){
            case 1:
                cadastrar();
                break;
            case 2:
                listar();
                break;
            default:
                System.out.println("Número invalido");
        }
        menu();
    }

}

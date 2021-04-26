package services;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientesServices {

    public static int exibeAcoesClientes(){
        Scanner leitor = new Scanner(System.in);
        ArrayList<String> listaAcoesFuncionario = new ArrayList();

        listaAcoesFuncionario.add("Atualizar Dados");
        listaAcoesFuncionario.add("Realizar uma locação");
        listaAcoesFuncionario.add("Histórico de veículos alugados"); //Histórico
        listaAcoesFuncionario.add("Sair");


        System.out.printf("x-------------------------x\n" +
                "Painel do Funcionário: \n");
        int n = listaAcoesFuncionario.size();
        for (int i=0; i<n; i++) {
            System.out.printf("%d. %s\n", i+1, listaAcoesFuncionario.get(i));
        }

        System.out.print("Escolha uma opção: ");
        return leitor.nextInt();
    }

    public static int exibeListaAtualizacaoCliente(){
        Scanner leitor = new Scanner(System.in);
        ArrayList<String> listaAtualizacao = new ArrayList();

        listaAtualizacao.add("Nome");
        listaAtualizacao.add("Cpf");
        listaAtualizacao.add("Senha");

        System.out.printf("Atualizar: \n");
        int j = listaAtualizacao.size();
        for (int i=0; i<j; i++) {
            System.out.printf("%d. %s\n", i+1, listaAtualizacao.get(i));
        }

        System.out.print("Escolha uma opção: ");
        return leitor.nextInt();
    }
}

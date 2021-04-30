import dao.*;
import models.*;
import services.UsuariosServices;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class MovidaApplication {

    EntityManager entityManager;

    public MovidaApplication(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        new MovidaApplication(entityManager);
        entityManager.getTransaction().begin();

        //SERVICES
        UsuariosServices usuariosServices = new UsuariosServices();
        //

        //DAO'S
        VeiculosDAO veiculosDAO =new VeiculosDAO(entityManager);
        UsuariosDAO usuariosDAO = new UsuariosDAO(entityManager);
        CategoriasUsuarioDAO categoriasUsuarioDAO = new CategoriasUsuarioDAO(entityManager);
        LoginDAO loginDAO = new LoginDAO(entityManager);
        VeiculosAlugadosDAO veiculosAlugadosDAO = new VeiculosAlugadosDAO(entityManager);
        CategoriasVeiculoDAO categoriasVeiculoDAO = new CategoriasVeiculoDAO(entityManager);
        //

        //
        Scanner leitor = new Scanner(System.in);
        //

        boolean condicao = true;
        while(true){

            //apenas para clientes
            System.out.print("Possui Cadastro? (S/N) ");
            String possuiCadastro = leitor.nextLine();

            switch (possuiCadastro){
                case "N":
                    System.out.print("Nome: ");
                    String nome = leitor.nextLine();

                    System.out.print("Cpf: ");
                    String cpf = leitor.nextLine();

                    System.out.print("Username: ");
                    String username = leitor.nextLine();

                    System.out.print("Senha: ");
                    String senha = leitor.nextLine();

                    CategoriasUsuario categoria = categoriasUsuarioDAO.buscarPorNome("Cliente");
                    Login login = new Login(username,senha);
                    Usuarios novoUsuario = new Usuarios(nome,cpf,categoria,login);
                    loginDAO.cadastrar(login);
                    usuariosDAO.cadastrar(novoUsuario);
                    entityManager.getTransaction().commit();
                    System.out.println("x-------------------------x");

                case "S":
                    System.out.println("x-- Painel de Login --x");
                    System.out.print("Username: ");
                    String usernameSolicitado = leitor.nextLine();
                    System.out.print("Senha: ");
                    String senhaSolicitada = leitor.nextLine();

                    Usuarios usuario = usuariosDAO.buscarPorUsername(usernameSolicitado);

                    if(usuario.getLogin().getSenha().equals(senhaSolicitada)){
                        System.out.println("Seja Bem Vindo " + usuario.getCategoria().getNome() + " " + usuario.getNome());
                        condicao = true;
                        while(condicao){ //fazer condicao = false, quando solicitar para sair do sistema


                            if(usuario.getCategoria().getNome().equals("Cliente")){
                                int opcaoPainelCliente = UsuariosServices.exibePainelCliente();

                                switch (opcaoPainelCliente){
                                    case 1: //Atualizar Dados
                                        break;
                                    case 2:
                                        realizarLocacao(entityManager, leitor, usuario,veiculosAlugadosDAO,veiculosDAO);
                                        break;

                                    case 3: //Histórico de veículos alugados (do usuario)
                                        System.out.println(usuario.getHistorioVeiculosAlugados()); //ainda não está conectado com o banco
                                        break;
                                    case 4: //Sair
                                        System.out.println("Saindo do Sistema!\n" +
                                                "x-------------------------x");
                                        condicao = false;
                                        break;
                                }
                            } else {

                                int opcaoPainelFuncionario = UsuariosServices.exibePainelFuncionario();

                                switch (opcaoPainelFuncionario){
                                    case 1:
                                        cadastrarNovoVeiculo(entityManager, leitor,categoriasVeiculoDAO,veiculosDAO);
                                        break;
                                    case 2: //Atualizar veículos
                                        break;
                                    case 3:
                                        consultarVeiculosCadastrados(entityManager,veiculosDAO);
                                        break;
                                    case 4:
                                        desativarVeiculo(entityManager, veiculosDAO, leitor);
                                        //nao atualiza na hora, precisa reiniciar a aplicacao
                                        break;
                                    case 5: //Consultar clientes cadastrados
                                        break;
                                    case 6: //Desativar cliente
                                        break;
                                    case 7: //Consultar veículos alugados
                                        break;
                                    case 8: //Finalizar Locação
                                        break;
                                    case 9: //Exibir Relatório de Locações
                                        break;
                                    case 10: //Sair
                                        System.out.println("Saindo do Sistema!\n" +
                                                "x-------------------------x");
                                        condicao = false;
                                        break;

                                }
                            }
                        }

                    } else {
                        System.out.println("Username ou senha incorretos. Tente novamente!");
                    }
                }
            //entityManager.close();
        }
    }

    private static void desativarVeiculo(EntityManager entityManager, VeiculosDAO veiculosDAO, Scanner leitor) {
        consultarVeiculosCadastrados(entityManager, veiculosDAO);
        System.out.println("Placa do Veiculo a ser desativado:");
        String placaVeiculoParaDesativar = leitor.nextLine();
        //entityManager.getTransaction().begin();
        veiculosDAO.desativarVeiculo(placaVeiculoParaDesativar);
        entityManager.getTransaction().commit();
        // entityManager.close();
    }

    private static void consultarVeiculosCadastrados(EntityManager entityManager,VeiculosDAO veiculosDAO) {
        veiculosDAO.buscarTodos().forEach(v -> System.out.println(v.toString()));
    }

    private static void realizarLocacao(EntityManager entityManager, Scanner leitor, Usuarios usuario,VeiculosAlugadosDAO veiculosAlugadosDAO, VeiculosDAO veiculosDAO) {

        veiculosDAO.buscarTodos().forEach(v -> System.out.println(v.toString()));
        System.out.print("Escolha a placa do veículo: ");
        String placaVeiculoAlugarEscolhido = leitor.nextLine();
        Veiculos veiculo = veiculosDAO.buscarPorPlaca(placaVeiculoAlugarEscolhido);

        System.out.print("Número Diarias: "); //o certo é data, ver como formatar
        int numeroDiarias = leitor.nextInt();

        //entityManager.getTransaction().begin();
        veiculosAlugadosDAO.cadastrar(new VeiculosAlugados(usuario,veiculo,new Date(2020,04,30)));//corrigir a data
        entityManager.getTransaction().commit();
        //entityManager.close();
    }

    private static void cadastrarNovoVeiculo(EntityManager entityManager, Scanner leitor, CategoriasVeiculoDAO categoriasVeiculoDAO, VeiculosDAO veiculosDAO) {

        System.out.println("Forneça as seguitnes informações");

        System.out.print("Marca: ");
        String marca = leitor.nextLine();

        System.out.print("Placa: ");
        String placa = leitor.nextLine();

        System.out.print("Valor: ");
        BigDecimal valor = leitor.nextBigDecimal();

        //verificar erro na ordem do id da categoria
        categoriasVeiculoDAO.buscarTodos().forEach(c -> System.out.println(c.toString()));
        System.out.print("Selecione o id da categoria: ");
        Long idCategoria = leitor.nextLong();
        CategoriasVeiculo categoria = categoriasVeiculoDAO.buscarPorId(idCategoria);

        Veiculos veiculo = new Veiculos(marca,placa,valor,categoria);

        //entityManager.getTransaction().begin();
        categoriasVeiculoDAO.cadastrar(categoria);
        veiculosDAO.cadastrar(veiculo);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }
}

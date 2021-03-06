import dao.*;
import models.*;
import services.UsuariosServices;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
                        while(condicao){


                            if(usuario.getCategoria().getNome().equals("Cliente")){
                                int opcaoPainelCliente = UsuariosServices.exibePainelCliente();

                                switch (opcaoPainelCliente){
                                    case 1: //Atualizar Dados
                                        break;
                                    case 2:
                                        realizarLocacao(entityManager, leitor, usuario,veiculosAlugadosDAO,veiculosDAO);
                                        break;
                                    case 3:
                                        usuario.getHistorioVeiculosAlugados().forEach(h -> System.out.println(h.toString()));
                                        break;
                                    case 4:
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
                                    case 2: //Atualizar ve??culos
                                        break;
                                    case 3:
                                        consultarVeiculosCadastrados(veiculosDAO);
                                        break;
                                    case 4:
                                        desativarVeiculo(entityManager, veiculosDAO, leitor);
                                        break;
                                    case 5:
                                        consultarClientesCadastrados(usuariosDAO);
                                        break;
                                    case 6: //Fazer com que o usuarios s?? possa logar se n??oe estiver desativado
                                        desativarCliente(entityManager, usuariosDAO, leitor);
                                        break;
                                    case 7:
                                        consultarTodosVeiculosAlugados(veiculosAlugadosDAO);
                                        break;
                                    case 8:
                                        desativarLocacao(entityManager, veiculosAlugadosDAO, leitor);
                                        break;
                                    case 9: //Exibir Relat??rio de Loca????es
                                        break;
                                    case 10:
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
            //entityManager.clear();
        }
    }

    private static void desativarLocacao(EntityManager entityManager, VeiculosAlugadosDAO veiculosAlugadosDAO, Scanner leitor) {
        consultarTodosVeiculosAlugados(veiculosAlugadosDAO);
        System.out.print("Id da loca????o a ser desativada: ");
        Long idLocacaoParaDesativar = leitor.nextLong();
        //entityManager.getTransaction().begin();
        veiculosAlugadosDAO.desativarLocacao(idLocacaoParaDesativar);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    private static void desativarCliente(EntityManager entityManager, UsuariosDAO usuariosDAO, Scanner leitor) {
        //posteriormente realizar um filtro para o funcionario s?? poder exlcluir outros funcionarios se for um gerente
        consultarClientesCadastrados(usuariosDAO);
        System.out.print("Cpf do Cliente a ser desativado: ");
        String cpfClienteParaDesativar = leitor.nextLine();
        //entityManager.getTransaction().begin();
        usuariosDAO.desativarCliente(cpfClienteParaDesativar);
        entityManager.getTransaction().commit();
        entityManager.clear();
        // entityManager.close();
    }
    //usuarioservices
    private static void consultarTodosVeiculosAlugados(VeiculosAlugadosDAO veiculosAlugadosDAO) {
        veiculosAlugadosDAO.buscarTodos().forEach(vecalu -> System.out.println(vecalu.toString()));
    }
    //usuarioservices
    private static void consultarClientesCadastrados(UsuariosDAO usuariosDAO) {
        usuariosDAO.buscarTodos().forEach(u -> System.out.println(u.toString()));
    }
    //usuarioservices
    private static void desativarVeiculo(EntityManager entityManager, VeiculosDAO veiculosDAO, Scanner leitor) {
        consultarVeiculosCadastrados(veiculosDAO);
        System.out.print("Placa do Veiculo a ser desativado: ");
        String placaVeiculoParaDesativar = leitor.nextLine();
        //entityManager.getTransaction().begin();
        veiculosDAO.desativarVeiculo(placaVeiculoParaDesativar);
        entityManager.getTransaction().commit();
        entityManager.clear();
        // entityManager.close();
    }
    //usuarioservices
    private static void consultarVeiculosCadastrados(VeiculosDAO veiculosDAO) {
        veiculosDAO.buscarTodos().forEach(v -> System.out.println(v.toString()));
    }
    //usuarioservices
    private static void realizarLocacao(EntityManager entityManager, Scanner leitor, Usuarios usuario,VeiculosAlugadosDAO veiculosAlugadosDAO, VeiculosDAO veiculosDAO) {

        veiculosDAO.buscarTodos().forEach(v -> System.out.println(v.toString()));
        System.out.print("Escolha a placa do ve??culo: ");
        String placaVeiculoAlugarEscolhido = leitor.nextLine();
        Veiculos veiculo = veiculosDAO.buscarPorPlaca(placaVeiculoAlugarEscolhido);

        System.out.print("N??mero Diarias: "); //o certo ?? data, ver como formatar
        int numeroDiarias = leitor.nextInt();

        GregorianCalendar data = new GregorianCalendar();
        data.add(Calendar.DAY_OF_MONTH, numeroDiarias);
        Date dataFinal = data.getTime();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //String dataFinalFormatada = dateFormat.format(dataFinal);
        //corrigir pois preciso enviar um DATE e a data formata ?? uma String

        //entityManager.getTransaction().begin();
        veiculosAlugadosDAO.cadastrar(new VeiculosAlugados(usuario,veiculo,dataFinal,numeroDiarias)); //MELHORAR, sem pedir numero de diarias ou data final
        entityManager.getTransaction().commit();
        //entityManager.close();
    }
    //usuarioservices
    private static void cadastrarNovoVeiculo(EntityManager entityManager, Scanner leitor, CategoriasVeiculoDAO categoriasVeiculoDAO, VeiculosDAO veiculosDAO) {

        System.out.println("Forne??a as seguitnes informa????es");

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

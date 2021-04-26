import dao.FuncionariosDAO;
import models.Autenticavel;
import models.Funcionarios;
import services.FuncionariosServices;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class MovidaApplication {
    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        boolean condicao = true;
        while(true){
            //feito porcamente
            Scanner leitorLogin = new Scanner(System.in);
            System.out.print("Usuário: ");
            String usuarioSolicitado = leitorLogin.nextLine();
            System.out.print("Senha: ");
            String senhaSolicitada = leitorLogin.nextLine();
//            System.out.println("Funcionário ou Cliente? ");
//            String categoriaAcesso = leitorLogin.nextLine();

            FuncionariosDAO funcionariosDAO = new FuncionariosDAO(entityManager);
            Autenticavel usuario = funcionariosDAO.buscaFuncionarioPorUsuario(usuarioSolicitado);





//            if(categoriaAcesso.equals("Funcionário")){
//                FuncionariosDAO funcionarioDAO = new FuncionariosDAO(entityManager);
//                System.out.print("Usuário: ");
//                String userNameSolicitado = leitorLogin.nextLine();
//                System.out.print("Senha: ");
//                String senhaSolicitada = leitorLogin.nextLine();
//                String senhaBuscada = funcionarioDAO.buscaSenhaPorLogin(userNameSolicitado);
//
//
//            } else if (categoriaAcesso.equals("Cliente"){
//                System.out.print("Usuário: ");
//                String userNameSolicitado = leitorLogin.nextLine();
//                System.out.print("Senha: ");
//                String senhaSolicitada = leitorLogin.nextLine();
//            } else {
//                System.out.println("Informe uma categoria válida");
//            }





            while(condicao){
                FuncionariosServices.exibeAcoesFuncionario();
            }

        }
    }
}

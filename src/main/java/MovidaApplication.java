import dao.UsuariosDAO;
import models.Autenticavel;
import models.Usuarios;
import services.UsuariosServices;
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
            System.out.print("Username: ");
            String usernameSolicitado = leitorLogin.nextLine();
            System.out.print("Senha: ");
            String senhaSolicitada = leitorLogin.nextLine();
//            System.out.println("Funcionário ou Cliente? ");
//            String categoriaAcesso = leitorLogin.nextLine();

            UsuariosDAO usuariosDAO = new UsuariosDAO(entityManager);
            usuariosDAO.buscarPorUsername(usernameSolicitado); //retornando o objeto usuario solicitado, basta realizar a verificação
            //System.out.println(usuariosDAO.buscarPorUsername(usernameSolicitado).getNome());





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
                UsuariosServices.exibeAcoesFuncionario();
            }

        }
    }
}

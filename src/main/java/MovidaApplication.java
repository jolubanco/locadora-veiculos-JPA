import dao.UsuariosDAO;
import models.Usuarios;
import services.UsuariosServices;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class MovidaApplication {
    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        UsuariosDAO usuariosDAO = new UsuariosDAO(entityManager);
        UsuariosServices usuariosServices = new UsuariosServices();

        boolean condicao = true;
        while(true){

            Scanner leitorLogin = new Scanner(System.in);
            System.out.print("Username: ");
            String usernameSolicitado = leitorLogin.nextLine();
            System.out.print("Senha: ");
            String senhaSolicitada = leitorLogin.nextLine();

            Usuarios usuario = usuariosDAO.buscarPorUsername(usernameSolicitado);

            if(usuario.getLogin().getSenha().equals(senhaSolicitada)){

                //condicao = true;
                while(condicao){ //fazer condicao = false, quando solicitar para sair do sistema

                    System.out.println("Seja Bem Vindo " + usuario.getCategoria().getNome() + " " + usuario.getNome());
                    if(usuario.getCategoria().getNome().equals("Cliente")){
                        int opcaoPainelCliente = UsuariosServices.exibePainelCliente();










                    } else {
                        UsuariosServices.exibePainelFuncionario();
                    }
                }

            } else {
                System.out.println("Username ou senha incorretos. Tente novamente!");
            }

        }
    }
}


/*
*/


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        /* Criando cinco usuarios */

        Perfil perfil0 = new Perfil('f', "28/09/1990", "Sao Paulo", "Sao Paulo", "2723-5782", false);
        Usuario usuario0 = new Usuario("Leticia", "leticia@gmail.com", "a1b2c3d4", true, perfil0);         // id = 1

        Perfil perfil1 = new Perfil('m', "22/03/1990", "Sao Paulo", "Sao Paulo", "5829-9294", false);
        Usuario usuario1 = new Usuario("Rafael", "rafael@gmail.com", "AaBbCcDd", true, perfil1);           // id = 2

        Perfil perfil2 = new Perfil('f', "07/09/1995", "Campinas", "Sao Paulo", "3821-9384", false);
        Usuario usuario2 = new Usuario("Isabela", "isabela@gmail.com", "isabela", true, perfil2);          // id = 3

        Perfil perfil3 = new Perfil('m', "14/03/1993", "Campinas", "Sao Paulo", "4822-5879", false);
        Usuario usuario3 = new Usuario("Fernando", "fernando@gmail.com", "1403", true, perfil3);           // id = 4

        Perfil perfil4 = new Perfil('f', "23/01/1990", "Campinas", "Sao Paulo", "9993-6472", false);
        Usuario usuario4 = new Usuario("Luisa", "luisa@gmail.com", "luisa123", true, perfil4);             // id = 5


        /*  Tornando todos os usuários caronantes. */

        Caronante caronante0 = new Caronante("LMT-2809", "444444444", "Honda", "Civic", 3);
        caronante0.setPerfil(perfil0);
        perfil0.setCaronante(caronante0);

        Caronante caronante1 = new Caronante("ABC-1234", "00000000", "Honda", "Civic", 3);
        caronante1.setPerfil(perfil1);
        perfil1.setCaronante(caronante1);

        Caronante caronante2 = new Caronante("HMN-1424", "11111111", "Volkswagen", "Fusca", 3);
        caronante2.setPerfil(perfil2);
        perfil2.setCaronante(caronante2);

        Caronante caronante3 = new Caronante("RWD=3810", "222222222", "Honda", "Civic", 3);
        caronante3.setPerfil(perfil3);
        perfil3.setCaronante(caronante3);

        Caronante caronante4 = new Caronante("WWB-0028", "333333333", "Volkswagen", "Fusca", 3);
        caronante4.setPerfil(perfil4);
        perfil4.setCaronante(caronante4);



        /*  Tornando todos os usuários caroneiros. */

        Caroneiro caroneiro0 = new Caroneiro("10391");
        perfil0.setCaroneiro(caroneiro0);
        caroneiro0.setPerfil(perfil0);

        Caroneiro caroneiro1 = new Caroneiro("47298");
        perfil1.setCaroneiro(caroneiro1);
        caroneiro1.setPerfil(perfil1);

        Caroneiro caroneiro2 = new Caroneiro("84284");
        perfil2.setCaroneiro(caroneiro2);
        caroneiro2.setPerfil(perfil2);

        Caroneiro caroneiro3 = new Caroneiro("57817");
        perfil3.setCaroneiro(caroneiro3);
        caroneiro3.setPerfil(perfil3);

        Caroneiro caroneiro4 = new Caroneiro("57179");
        perfil4.setCaroneiro(caroneiro4);
        caroneiro4.setPerfil(perfil4);


        /*  Criação de um arraylist de usuários. */

        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario0);
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);


        /*  Criando um grupo público 'gpu' e um grupo privado 'EC017', com o usuario0 como dono de ambos. */

        GrupoPublico gpu = usuario0.criarGrupoPublico("gpu", "Grupo público");
        GrupoPrivado ec017 = usuario0.criarGrupoPrivado("EC017", "Apenas para estudantes de EC com ingresso em 2017");


        /*  Imprime o grupo privado EC017. */

        System.out.println(ec017);


        /*  Tenta inserir dois novos membros ao grupo privado EC017. Ambas as inserções devem ocorrer corretamente,
         *  uma vez que o usuário que está realizando a inserção é o dono do grupo.
         */

        try {
            usuario0.adicionarUsuarioAUmGrupo(usuario1, ec017);
            usuario0.adicionarUsuarioAUmGrupo(usuario2, ec017);
        }
        catch (InsercaoEmGrupoPrivado i){
            System.out.println(i.getMessage());
        }


        /*  Imprime o grupo privado EC017 após duas tentativas de inserção de novos membros. */

        System.out.println(ec017);


        /*  O usuário2, que não é dono do grupo EC017, tenta inserir outro usuário no grupo privado. O programa
         *   deve gerar uma exceção e lidar com ele através de uma mensagem de erro.
         */

        try{
            usuario2.adicionarUsuarioAUmGrupo(usuario4, ec017);
        }
        catch (InsercaoEmGrupoPrivado i){
            System.out.println(i.getMessage());
        }


        /*  Imprime o grupo privado EC017, que não deve ter sido alterado. */

        System.out.println(ec017);


        /*  Os usuarios 2, 3 e 4 se adicionam no grupo público gpu. */

        usuario2.adicionarGrupo(gpu);
        usuario3.adicionarGrupo(gpu);
        usuario4.adicionarGrupo(gpu);

        System.out.println(gpu);


        /*  O usuário3 cria uma carona pública e adiciona o grupo público gpu a esta carona. */

        CaronaPublica caronaPublica = caronante3.oferecerCaronaPublica();
        caronaPublica.adicionarGrupo(gpu);


        /*  O usuario0 tenta se retirar do grupo público. A remoção não deve ocorrer pois usuario0 é o dono do
         *  grupo. A exceção deve apresentar uma mensagem de erro.
         */

        try {
            System.out.println("Tentativa de retirar usuario0 do grupo público gpu: " + usuario0.removerGrupo(gpu));
        }
        catch (UsuarioNaoPertenceAoGrupo u){
            System.out.println(u.getMessage());
        }


        /*  O usuario2 cria uma carona privada e adiciona o grupo privado EC017 na carona. Deve ocorrer corretamente,
         *  pois o usuário2 pertence ao grupo privado inserido e tem permissão para fazer a inserção.
         */

        CaronaPrivada caronaPrivada = caronante2.oferecerCaronaPrivada();
        try {
            caronaPrivada.adicionarGrupo(ec017);
        }
        catch(UsuarioNaoPertenceAoGrupoPrivado i){
            System.out.println(i.getMessage());
        }


        /*  O usuario4 cria uma carona privada e tenta adicionar o grupo privado EC017 na carona. O programa deve
         *  gerar uma mensagem de exceção, pois o usuário não pertence ao grupo privado e, portanto, não possui
         *  permissão para fazer essa inserção.
         */

        CaronaPrivada caronaPrivada2 = caronante4.oferecerCaronaPrivada();
        try {
            caronaPrivada2.adicionarGrupo(ec017);
        }
        catch(UsuarioNaoPertenceAoGrupoPrivado i){
            System.out.println(i.getMessage());
        }


        System.out.println("\nInserção do usuário0 na carona: " +caroneiro0.pedirCarona(caronaPrivada));
        System.out.println("Inserção do usuário1 na carona: " +caroneiro1.pedirCarona(caronaPrivada));
        System.out.println("Inserção do usuário3 na carona: " +caroneiro3.pedirCarona(caronaPrivada));


        ArrayList<Perfil> perfis = new ArrayList<>();
        perfis.add(perfil0);
        perfis.add(perfil1);
        perfis.add(perfil2);

        caronaPrivada.atribuirNotaCaronante(6.7f);
        caronaPrivada.atribuirNotaCaroneiro(0, 8.4f);
        caronaPrivada.atribuirNotaCaroneiro(1, 5.1f);

        Collections.sort(perfis);


        /*  Imprimindo os cinco usuários criados. */

        System.out.println(usuarios);


        /*  Imprimimos os dois grupos criados. */

        System.out.println("Imprimindo grupo público\n" + gpu + "\n");
        System.out.println("Imprimindo grupo privado\n" + ec017 + "\n");


        /*  Imprimindo as duas caronas criadas. */

        System.out.println("Imprimindo a primeira carona\n" + caronaPrivada + "\n");
        System.out.println("Imprimindo a segunda carona\n" + caronaPrivada2 + "\n");


        System.out.println(perfis);

    }
}

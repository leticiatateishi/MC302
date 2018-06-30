/*  Questão 1
 *  AWT é um conjunto mais simples de componentes, que pode ter comportamentos distintos em ambientes
 *  distintos e cuja plataforma controla a aparência dos componentes. Enquanto Swing possui componentes
 *  mais elaborados e é consistente em diferentes plataformas.
 *
 *  Questão 2
 *  Um container possui diversos layers, sendo o content pane o layer usado para "segurar" outros componentes.
 *
 *  Questão 3
 *  Quando um JDialog visível, não temos acesso às outras janelas do programa até que ele seja fechado.
 *
 *  Questão 4
 *  O BorderLayout possui 5 áreas disponíveis: Norte, Sul, Leste, Oeste e Centro. O BoxLayout distribui os
 *  componentes em uma só coluna, um debaixo do outro. O FlowLayout distribui os componentes na horizontal,
 *  um do lado do outro. O GridLayout coloca os componentes em uma "tabela", distribuindo na horizontal E
 *  na vertical.
 *  Neste laboratório, utilizei majoritariamente BoxLayout e FlowLayout, por ser comum a necessidade de
 *  posicionar componentes em uma mesma linha ou em uma mesma coluna.
 *
 *  Questão 5
 *  Um função callback é chamada quando um evento ocorre. Em Java, são implementadas através de um método
 *  em um objeto (chamado de listener). Esse método recebe como parâmetro o evento ocorrido e realiza as
 *  operações necessárias.
 *
 *  Questão 6
 *  O Model é representado pelas classes que representam usuários, grupos e caronas, como todos os atri-
 *  butos e métodos nelas armazenados. O View é a interface gráfica, que permite que clientes tenham
 *  acesso à visualização do estado do sistema. Controller são os botões e campos da interface gráfica,
 *  que permitem que clientes possam interagir com o sistema.
 *
 */


import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        /* Criando cinco usuarios e seus respectivos perfis. */

        Perfil perfil0 = new Perfil('f', "28/09/1990", "Sao Paulo", "Sao Paulo", "2723-5782", false);
        Usuario usuario0 = new Usuario("Leticia", "leticia@gmail.com", "a1b2c3d4", true, perfil0);         // id = 1

        Perfil perfil1 = new Perfil('m', "22/03/1990", "Sao Paulo", "Sao Paulo", "5829-9294", false);
        Usuario usuario1 = new Usuario("Rafael", "rafael@gmail.com", "AaBbCcDd", true, perfil1);           // id = 2

        Perfil perfil2 = new Perfil('f', "07/09/1995", "Campinas", "Sao Paulo", "3821-9384", false);
        Usuario usuario2 = new Usuario("Isabela", "isabela@gmail.com", "isabela", true, perfil2);          // id = 3


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


        /*  Criando de um arraylist de usuários. */

        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario0);
        usuarios.add(usuario1);
        usuarios.add(usuario2);


        /*  Criando um grupo público 'gpu' e um grupo privado 'EC017', com o usuario0 como dono de ambos. */

        GrupoPublico gpu = usuario0.criarGrupoPublico("gpu", "Grupo público");
        GrupoPrivado ec017 = usuario0.criarGrupoPrivado("EC017", "Apenas para estudantes de EC com ingresso em 2017");


        /*  Tenta inserir dois novos membros ao grupo privado EC017. Ambas as inserções devem ocorrer corretamente,
         *  uma vez que o usuário que está realizando a inserção é o dono do grupo. */

        try {
            usuario0.adicionarUsuarioAUmGrupo(usuario1, ec017);
            usuario0.adicionarUsuarioAUmGrupo(usuario2, ec017);
        } catch (InsercaoEmGrupoPrivado i) {
            System.out.println(i.getMessage());
        }


        /*  Os usuarios 2, 3 e 4 se adicionam no grupo público gpu. */

        try {
            usuario1.adicionarGrupo(gpu);
            usuario2.adicionarGrupo(gpu);
        } catch (GrupoInexistente excecao) {
            excecao.getMessage();
        }


        /*  O usuario0 tenta se retirar do grupo público. A remoção não deve ocorrer pois usuario0 é o dono do
         *  grupo. A exceção deve apresentar uma mensagem de erro. */

        try {
            System.out.println("Tentativa de retirar usuario0 do grupo público gpu: " + usuario0.removerGrupo(gpu));
        } catch (UsuarioNaoPertenceAoGrupo u) {
            System.out.println(u.getMessage());
        }


        /*  O usuario2 cria uma carona privada e adiciona o grupo privado EC017 na carona. Deve ocorrer corretamente,
         *  pois o usuário2 pertence ao grupo privado inserido e tem permissão para fazer a inserção. */

        CaronaPrivada caronaPrivada = caronante2.oferecerCaronaPrivada();
        caronaPrivada.adicionarGrupo(ec017);
        caronaPrivada.setHoraDiaEncontro("09/04, às 10h");
        caronaPrivada.setValor(10.0f);


        /*  Os caroneiros 0, 1 e 3 pedem a carona privada oferecida pelo caronante 2. As duas primeiras inserções
         *  devem ocorrer corretamente pois os usuarios 0 e 1 pertencem ao grupo privado 'ec017'. A terceira
         *  inserção não deve ocorrer pois o usuário 3 não pertence a algum grupo privado da carona. */

        System.out.println("\nInserção do usuário0 na carona: " + caroneiro0.pedirCarona(caronaPrivada));
        System.out.println("Inserção do usuário1 na carona: " + caroneiro1.pedirCarona(caronaPrivada));


        /*  Criando um array list com os perfis dos usuários 0, 1 e 2, que participaram da caronaPrivada. */

        ArrayList<Perfil> perfis = new ArrayList<>();
        perfis.add(perfil0);
        perfis.add(perfil1);
        perfis.add(perfil2);


        /*  Atribuímos notas do caronante e dos caroneiros à caronaPrivada gerada. */

        caronaPrivada.atribuirNotaCaronante(6.7f);
        caronaPrivada.atribuirNotaCaroneiro(0, 8.4f);
        caronaPrivada.atribuirNotaCaroneiro(1, 5.1f);


        /*  Ordenamos o ArrayList de perfis em ordem crescente de avaliação. */

        Collections.sort(perfis);


        System.out.println("\n\n******************************\n");
        System.out.println("Imprimindo todas as instancias para mostrar o estado do sistema:\n");
        System.out.println(usuarios);
        System.out.println(perfis);
        System.out.println(gpu);
        System.out.println(ec017);
        System.out.println(caronaPrivada);


        /*  Salvavamos no arquivo 'Usuarios.txt' informações a respeito dos cinco usuários, seus respectivos
         *  perfis, caronantes e caroneiros. */

        for (Usuario usuario : usuarios) {
            usuario.salvarParaArquivo();
            usuario.getPerfil().salvarParaArquivo();
            usuario.getPerfil().getCaronante().salvarParaArquivo();
            usuario.getPerfil().getCaroneiro().salvarParaArquivo();
        }

        GrupoPublico grupoSP = usuario1.criarGrupoPublico("SP", "Para moradores de SP");
        try{
            usuario0.adicionarGrupo(grupoSP);
        }catch (Exception e){
            e.printStackTrace();
        }


        /*  Salvamos no arquivo 'Grupos.txt' informações a respeito dos grupos (privados ou públicos) e dos
         *  GrupoUsuario's. */

        gpu.salvarParaArquivo();
        ec017.salvarParaArquivo();


        JFrame frame = new Janela(usuario0);
    }
}

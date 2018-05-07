
/*  Não é possível criar um método que transforme um objeto da classe GrupoPublico para classe GrupoPrivado, pois
    não podemos transformar um objeto de uma subclasse a um objeto de outra subclasse. Só podemos transformar objetos
    de subclasses em objetos da classe mãe (Grupo), através do upcasting.

    Podemos criar um objeto da classe Grupo, instanciado como GrupoPublico e chamarmos o método testeDinamico (da
    classe GrupoPublico), entretanto é necessário fazer o upcasting para GrupoPúblico. Não podemos instanciar um
    objeto da classe Grupo, mas se pudéssemos (se a classe não fosse abstrata), não poderíamos chamar o método
    testeDinamico, a não ser que fizéssemos o downcasting.

    Não podemos acessar o método testeDinamico com um objeto de GrupoPrivado, pois ambas as classes GrupoPublico e
    GrupoPrivado possuem a mesma classe mãe Grupo (ambas são subclasses), portanto uma não possui acesso aos atributos
    e métodos exclusivos da outra. Não há alternativa para fazermos isso, pois não podemos transformar um objeto de
    uma subclasse em um objeto de outra subclasse.

    Uma vantagem de tornar Grupo e Carona abstratos é que tornamos impossível a instanciação dessas classes, uma vez
    que desejamos que existam apenas grupos/caronas privados ou públicos. Outra vantagem é que podemos escrever os
    métodos abstratos de Grupo e Carona em suas subclasses,tornando-os compatíveis às subclasses.

    Não é possível sobrescrever ou sobrecarregar métodos em relacionamentos que não sejam herança, pois, em outros
    relacionamentos, uma classe não herda os métodos da outra e, portanto, não podemos sobrescrevê-los ou sobrecar-
    regá-los.
*/


public class Main {

    public static void main(String[] args) {

        /* Criando quatro usuarios */

        Perfil perfil1 = new Perfil('f', "28/09/1990", "Sao Paulo", "Sao Paulo", "2723-5782", false);
        Usuario usuario1 = new Usuario("Leticia", "leticia@gmail.com", "a1b2c3d4", true, perfil1);         // id = 1

        Perfil perfil2 = new Perfil('m', "22/03/1990", "Sao Paulo", "Sao Paulo", "5829-9294", false);
        Usuario usuario2 = new Usuario("Rafael", "rafael@gmail.com", "AaBbCcDd", true, perfil2);           // id = 2

        Perfil perfil3 = new Perfil('f', "07/09/1995", "Campinas", "Sao Paulo", "3821-9384", false);
        Usuario usuario3 = new Usuario("Isabela", "isabela@gmail.com", "isabela", true, perfil3);          // id = 3

        Perfil perfil4 = new Perfil('m', "14/03/1993", "Campinas", "Sao Paulo", "4822-5879", false);
        Usuario usuario4 = new Usuario("Fernando", "fernando@gmail.com", "1403", true, perfil3);           // id = 4


        /*  Criando um grupo público e um grupo privado através de estrutura polimórfica. */

        Grupo grupo1, grupo2;
        grupo1 = new GrupoPrivado("Unicamp", "Caronas para alunos e funcionarios da Unicamp", usuario3);
        grupo1.adicionarMembro(usuario4);
        grupo2 = new GrupoPublico("Campinas-SP", "Caronas entre Campinas e São Paulo", usuario1);
        grupo2.adicionarMembro(usuario2);


        /*  Tornando os usuarios 1 e 3 caronantes para podermos criar duas caronas. */

        Caronante caronante1 = new Caronante(10, "rock", "ABC-1234", "00000000", "Honda", "Civic", 3);
        caronante1.setPerfil(perfil1);
        perfil1.setCaronante(caronante1);

        Caronante caronante2 = new Caronante(10, "mpb", "HMN-1424", "11111111", "Volkswagen", "Fusca", 3);
        caronante2.setPerfil(perfil3);
        perfil3.setCaronante(caronante2);


        /*  Criando uma carona publica e uma carona privada através de estrutura polimórfica e adicionando o grupo
         *   privado à carona privada e o grupo público à carona pública. */

        Carona carona1, carona2;
        carona1 = new CaronaPrivada(caronante1);
        ((CaronaPrivada) carona1).adicionarGrupo((GrupoPrivado) grupo1);
        carona2 = new CaronaPublica(caronante2);
        ((CaronaPublica) carona2).adicionarGrupo((GrupoPublico) grupo2);


        /*  Tornando o usuário 2 caroneiro. */

        Caroneiro caroneiro = new Caroneiro("47298478279847");
        perfil2.setCaroneiro(caroneiro);
        caroneiro.setPerfil(perfil2);


        /*  Fazemos com que um caronante ofereça uma carona (que será pública), adicionamos o grupo público a essa
         *  carona e fazemos com que o caroneiro peça a carona. O pedido de carona deve retornar true pois o caroneiro
         *  está no grupo público associado à carona oferecida. */

        Carona carona = caronante1.oferecerCarona();
        ((CaronaPublica) carona).adicionarGrupo((GrupoPublico) grupo2);
        System.out.println("\nAdicionando caroneiro à carona: " + caroneiro.pedirCarona(carona) + "\n");


        /*  Gerando notas para a carona criada. */

        carona.atribuirNotaCaronante(8.6f);
        carona.atribuirNotaCaroneiro(2, 9.2f);


        /*  Imprimindo as notas atribuídas. */

        System.out.println("Nota atribuida ao caronante: " + carona.getCaronante().getCaronante().getAvalicao(carona));
        System.out.println("Nota atribuida ao caroneiro: " + carona.getCaroneiro(2).getAvalicao(carona) + "\n");


        /*  Imprimimos os dois grupos criados. */

        System.out.println("Imprimindo grupo privado\n" + grupo1 + "\n");
        System.out.println("Imprimindo grupo público\n" + grupo2 + "\n");


        /*  Imprimindo os quatro usuários criados. */

        System.out.println(usuario1);
        System.out.println(usuario2);
        System.out.println(usuario3);
        System.out.println(usuario4);


        /*  Teste para responder as questões 2 e 3. */

        Grupo a = new GrupoPublico("Grupo teste", "Teste", usuario1);
        System.out.println("Teste dinâmico: " + ((GrupoPublico) a).testeDinamico());

        Grupo b = new GrupoPrivado("Grupo teste", "Teste", usuario1);
//        System.out.println("Teste dinâmico: " +b.testeDinamico());

    }
}

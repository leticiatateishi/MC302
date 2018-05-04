/*  A vantagem de relacionamentos bidirecionais envolve a possibilidade de se poder acessar informacoes da classe com
    a qual esta relacionada. Assim, a classe Carona e a classe CaronaCaroneiro podem ser acessadas uma a partir da
    outra. Uma desvantagem deste relacionamento eh o cuidado ao lidar com alteracoes relacionadas ao atributo da outra
    classe, para evitar recursividade ou esquecimento de se atualizar o outro lado do relacionamento.

    Como uma carona so pode possuir um caronante (apesar de um caronante poder participar de varias caronas), a criacao
    da classe associativa CaronaCaronante pode nao ser muito vantajosa. Uma alternativa que ainda armazena o
    relacionamento mas sem a necessidade de criar uma classe associativa eh o armazenamento das caronas dadas por certo
    caronante em um array list dentro da classe Caronante e mantendo o atributo "avaliacao" como um atributo da classe
    carona (como avaliacaoCaronante), uma vez que ha apenas um caronante e havera apenas uma avaliacao do caronante
    para esta carona. Entretanto, essa alternativa nao seria vantajosa se possuissemos muitos atributos como avaliacao
    para armazenar em Carona. A vantagem seria a maior facilididade de se alterar o atributo avaliacao (apenas com
    carona.setAvalaiacaoCaronante(avaliacao)), sem a necessidade de se atualizar objetos da classe CaronaCaronante em
    Carona e em Caronante. A desvantagem envolve o armazenamento em uma classe especifica de dados relacionados a carona
    e ao caronante, que deveriam ser acessiveis em ambas as classes.

    Eh possivel que haja problemas com relacionamentos. Por exemplo, devemos nos assegurar de que sempre criamos uma
    carona atraves do metodo oferecerCarona de caronante. Nao podemos instanciar um objeto do tipo caronante e depois
    fazer "Carona c = new Carona(caronante)", pois desta maneira nao sera criado um objeto CaronaCaronante que sera
    adicionado ao arraylist de caronas do caronante. Assim, embora o atributo caronante da classe Carona vai estar
    associado a um caronante, este mesmo caronante nao estara associado a essa carona, uma vez que ela nao pertence ao
    seu arraylist de caronas.
*/


public class Main {

	public static void main (String[] args) {

        /* Criando quatro usuarios */

        Perfil perfil1 = new Perfil('f', "28/09/1990", "Sao Paulo", "Sao Paulo", "2723-5782", false);
        Usuario usuario1 = new Usuario("Leticia", "leticia@gmail.com", "a1b2c3d4", true, perfil1);         // id = 1


        Perfil perfil2 = new Perfil('m', "22/03/1990", "Sao Paulo", "Sao Paulo", "5829-9294", false);
        Usuario usuario2 = new Usuario("Rafael", "rafael@gmail.com", "AaBbCcDd", true, perfil2);           // id = 2


        /*  Tornando o usuario1 caronante. */

        Caronante caronante = new Caronante(10, "rock", "ABC-1234", "00000000", "Honda", "Civic", 3);
        caronante.setPerfil(perfil1);
        perfil1.setCaronante(caronante);


        /*  Tornando os outros tres usuarios caroneiros. */

        Caroneiro caroneiro = new Caroneiro("47298478279847");
        perfil2.setCaroneiro(caroneiro);
        caroneiro.setPerfil(perfil2);


        /* Criando a carona. */

        Carona carona = caronante.oferecerCarona();
        carona.adicionarCaroneiro(caroneiro);
        carona.setHoraDiaEncontro("17 de fevereiro de 2018, as 16h");


        /*  Atribuindo notas ao caronante e aos caroneiros. */

        System.out.println("Atribuicao da nota do caronante: " + carona.atribuirNotaCaronante(8.5f));
        System.out.println("Atribuicao da nota do caroneiro1: " + carona.atribuirNotaCaroneiro(2, 6.2f));


        /*  Imprime os 4 usuarios criados. */

        System.out.println("\nUsuario 1:" + usuario1);
        System.out.println("\nUsuario 2:" + usuario2);


        /*  Imprime a carona e a nota que cada usuario atribuiu a ela. */

        System.out.println(carona);
        System.out.println("Nota atribuida pelo caronante (id "
                + carona.getCaronante().getCaronante().getPerfil().getUsuario().getId() + "): "
                + carona.getCaronante().getAvaliacao() + "\n");
        System.out.println("Nota atribuida pelo caroneiro1 (id " + caroneiro.getPerfil().getUsuario().getId() + "): "
                + carona.encontrarCaroneiro(caroneiro).getAvaliacao());


    }

}

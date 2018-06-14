import javax.swing.*;
import java.awt.*;

public class Janela extends JFrame {

    public Janela(Usuario usuario){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("<html>Informações do usuário<br><br>" +
                "Nome: " +usuario.getNome()+
                "<br>Id: " +usuario.getId()+
                "<br>Email: " +usuario.getEmail()+
                "<br>Cidade: " +usuario.getPerfil().getCidade() + " (" +usuario.getPerfil().getEstado()+
                ")<br><br></html>");

        JButton button1 = new JButton("Caronas");
        JButton button2 = new JButton("Grupos");
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(label, BorderLayout.PAGE_START);
        getContentPane().add(button1, BorderLayout.CENTER);
        getContentPane().add(button2, BorderLayout.PAGE_END);
        pack();
        setVisible(true);

    }
}

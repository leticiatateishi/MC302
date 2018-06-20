import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame {

    public Janela(Usuario usuario){

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("<html>Informações do usuário<br><br>" +
                "Nome: " +usuario.getNome()+
                "<br>Id: " +usuario.getId()+
                "<br>Email: " +usuario.getEmail()+
                "<br>Cidade: " +usuario.getPerfil().getCidade() + " (" +usuario.getPerfil().getEstado()+
                ")<br><br></html>");
        JLabel label2 = new JLabel();

        JPanel panel = new JPanel();
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel.setLayout(new FlowLayout());
        JButton button1 = new JButton("Caronas");
        JButton button2 = new JButton("Grupos");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(label, BorderLayout.PAGE_START);
        panel.add(button1);
        panel.add(button2);
        add(panel, BorderLayout.SOUTH);
        getContentPane().add(label2);

        button1.addActionListener(new CaronasActionListener(usuario));
        button2.addActionListener(new GruposActionListener(usuario));

        setVisible(true);

    }
}

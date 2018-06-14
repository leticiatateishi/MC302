import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame {

    public Janela(Usuario usuario){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("<html>Informações do usuário<br><br>" +
                "Nome: " +usuario.getNome()+
                "<br>Id: " +usuario.getId()+
                "<br>Email: " +usuario.getEmail()+
                "<br>Cidade: " +usuario.getPerfil().getCidade() + " (" +usuario.getPerfil().getEstado()+
                ")<br><br></html>");
        JLabel label2 = new JLabel();

        JButton button1 = new JButton("Caronas");
        JButton button2 = new JButton("Grupos");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(label, BorderLayout.PAGE_START);
        getContentPane().add(button1, BorderLayout.WEST);
        getContentPane().add(button2, BorderLayout.EAST);
        getContentPane().add(label2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Caronas");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
                JLabel label3 = new JLabel("<html>Caronas das quais o usuário participa:<br>" + usuario.getCaronas()
                        + "</html>");
                frame.getContentPane().add(label3);
                pack();
                setVisible(true);
            }
        });


        pack();
        setVisible(true);

    }
}

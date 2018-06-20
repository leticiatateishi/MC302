import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaronasActionListener implements ActionListener {

    private Usuario usuario;

    public CaronasActionListener(Usuario u){
        usuario = u;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Caronas");
        frame.setSize(400, 200);
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button = new JButton("Criar nova carona");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane optionPane = new JOptionPane();
                int resposta = optionPane.showOptionDialog(null, "Você deseja criar uma carona pública ou privada?",
                        "Criar carona", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, new String[]{"Pública", "Privada"}, "default");
                String horaDiaEncontro = optionPane.showInputDialog("Qual será a data e o horário de encontro?");
                String valorString = optionPane.showInputDialog("Qual será o valor da carona?");
                float valorFloat = Float.parseFloat(valorString);

                if (resposta == JOptionPane.OK_CANCEL_OPTION){
                    CaronaPublica carona = usuario.getPerfil().getCaronante().oferecerCaronaPublica();
                    carona.setValor(valorFloat);
                    carona.setHoraDiaEncontro(horaDiaEncontro);
                    frame.revalidate();
                }
                else{
                    CaronaPrivada carona = usuario.getPerfil().getCaronante().oferecerCaronaPrivada();
                    carona.setValor(valorFloat);
                    carona.setHoraDiaEncontro(horaDiaEncontro);
                    frame.revalidate();
                }
            }
        });
        panel.add(button);

        JLabel label = new JLabel("<html>Caronas das quais o usuário participa:<br></html>");

        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
}

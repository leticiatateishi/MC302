import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GruposActionListener implements ActionListener {

    private Usuario usuario;

    public GruposActionListener(Usuario u){
        usuario = u;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFrame frame = new JFrame("Grupos");
        frame.setSize(400, 200);
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button1 = new JButton("Criar grupo");
        JButton button2 = new JButton("Sair de grupo");
        panel.add(button1);
        panel.add(button2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane optionPane = new JOptionPane();
                int resposta = optionPane.showOptionDialog(null, "Você deseja criar um grupo pública ou privado?",
                        "Criar grupo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, new String[]{"Público", "Privado"}, "default");
                String nome = optionPane.showInputDialog("Qual será o nome do grupo?");
                String descricao = optionPane.showInputDialog("Qual a descrição do grupo?");

                if (resposta == JOptionPane.OK_CANCEL_OPTION){
                    usuario.criarGrupoPublico(nome, descricao);
                    frame.revalidate();
                }
                else{
                    usuario.criarGrupoPrivado(nome, descricao);
                    frame.revalidate();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame2 = new JFrame();
                JPanel panel2 = new JPanel();
                String[] grupos = usuario.getNomesGrupos();
                JComboBox listaGrupos = new JComboBox(grupos);
                panel2.add(listaGrupos);
                listaGrupos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JComboBox cb = (JComboBox)actionEvent.getSource();
                        String grupoSair = (String)cb.getSelectedItem();
                        try {
                            usuario.removerGrupo(grupoSair);
                        }catch (UsuarioNaoPertenceAoGrupo excecao){
                            excecao.printStackTrace();
                        }
                    }
                });
                frame2.add(panel2);
                frame2.pack();
                frame2.setVisible(true);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}

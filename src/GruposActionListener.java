import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GruposActionListener implements ActionListener {

    private Usuario usuario;

    public GruposActionListener(Usuario u){
        usuario = u;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFrame frame = new JFrame("Grupos");
        frame.setSize(500, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel grupos = new JLabel("<html><br>Grupos dos quais o usuário participa:<br></html>");
        grupos.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTable gruposTabela = new JTable();
        gruposTabela.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(gruposTabela);

        desenharTabela(gruposTabela);

        JButton criarGrupo = new JButton("Criar novo grupo");
        JButton sairGrupo = new JButton("Sair do grupo");
        criarGrupo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairGrupo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(criarGrupo);

        criarGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JOptionPane optionPane = new JOptionPane();
                int resposta = optionPane.showOptionDialog(null, "Você deseja criar um grupo pública ou privado?",
                        "Criar grupo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, new String[]{"Público", "Privado"}, "default");
                String nome = optionPane.showInputDialog("Qual será o nome do grupo?");
                String descricao = optionPane.showInputDialog("Qual a descrição do grupo?");

                Grupo grupo;
                if (resposta == JOptionPane.OK_CANCEL_OPTION){
                    grupo = usuario.criarGrupoPublico(nome, descricao);
                }
                else{
                    grupo = usuario.criarGrupoPrivado(nome, descricao);
                }
                desenharTabela(gruposTabela);
            }
        });


        JPanel sairDeUmGrupo = new JPanel(new FlowLayout());
        ArrayList<String> nomesGrupos = usuario.getNomesGrupos();
        String[] nomesArray = new String[nomesGrupos.size()];
        for (int i = 0; i < nomesGrupos.size(); i++)
            nomesArray[i] = nomesGrupos.get(i);
        JComboBox listaGrupos = new JComboBox(nomesArray);

        sairGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String nomeGrupoSair = listaGrupos.getSelectedItem().toString();
                try{
                    usuario.removerGrupo(nomeGrupoSair);
                    desenharTabela(gruposTabela);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(panel, "Não foi possível sair do grupo", "Usuario", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        sairDeUmGrupo.add(listaGrupos);
        sairDeUmGrupo.add(sairGrupo);

        panel.add(grupos);
        panel.add(scroll);
        panel.add(sairDeUmGrupo);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void desenharTabela (JTable tabela){
        Object colunas[] = {"Nome", "Descrição", "Dono"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(colunas);
        tabela.setModel(model);
        List<GrupoUsuario> gruposParticipantes = usuario.getGrupos();
        Grupo grupo;
        for (GrupoUsuario i: gruposParticipantes){
            grupo = i.getGrupo();
            Object[] valores = {grupo.getNome(), grupo.getDescricao(), grupo.getDono().getNome()};
            model.addRow(valores);
        }
    }
}

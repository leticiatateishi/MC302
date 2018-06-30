import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CaronasActionListener implements ActionListener {

    private Usuario usuario;

    public CaronasActionListener(Usuario u) {
        usuario = u;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame janela = new JFrame("Caronas");
        janela.setSize(500, 200);
        JPanel painelPrincipal = new JPanel();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));


        JLabel caronasParticipadas = new JLabel("<html><br>Caronas das quais o usuário participa:<br></html>");
        caronasParticipadas.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTable caronasTabela = new JTable();
        Object colunas[] = {"Hora e dia de encontro", "Valor", "Caronante"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(colunas);
        caronasTabela.setModel(model);
        caronasTabela.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(caronasTabela);

        List<CaronaCaronante> caronasCaronante = usuario.getPerfil().getCaronante().getCaronas();
        for (CaronaCaronante i : caronasCaronante) {
            Carona carona = i.getCarona();
            String[] valores = {carona.getHoraDiaEncontro(), String.valueOf(carona.getValor()),
                    carona.getCaronante().getCaronante().getPerfil().getUsuario().getNome()};
            model.addRow(valores);
        }

        List<CaronaCaroneiro> caronasCaroneiro = usuario.getPerfil().getCaroneiro().getCaronas();
        for (CaronaCaroneiro i : caronasCaroneiro) {
            Carona carona = i.getCarona();
            String[] valores = {carona.getHoraDiaEncontro(), String.valueOf(carona.getValor()),
                    carona.getCaronante().getCaronante().getPerfil().getUsuario().getNome()};
            model.addRow(valores);
        }


        JButton novaCarona = new JButton("Criar nova carona");
        novaCarona.setAlignmentX(Component.CENTER_ALIGNMENT);
        novaCarona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame janelaNovaCarona = new JFrame("Nova carona");
                janelaNovaCarona.setVisible(true);
                janelaNovaCarona.setSize(600, 200);
//                painelPrincipal.setVisible(false);
                JPanel painelNovaCarona = new JPanel();
                painelNovaCarona.setLayout(new BoxLayout(painelNovaCarona, BoxLayout.Y_AXIS));

                JLabel horaDiaEncontro = new JLabel("Dia e horário de encontro: ");
                JLabel valor = new JLabel("Valor: ");

                JTextField horaDiaEncontroCampo = new JTextField(10);
                JTextField valorCampo = new JTextField(10);
                valorCampo.setEditable(false);

                JPanel horaDiaEncontroPainel = new JPanel();
                horaDiaEncontroPainel.setLayout(new FlowLayout());
                horaDiaEncontroPainel.add(horaDiaEncontro);
                horaDiaEncontroPainel.add(horaDiaEncontroCampo);


                JCheckBox pagamentoDinheiro = new JCheckBox("Dinheiro");
                JCheckBox pagamentoCartaoDeCredito = new JCheckBox("Cartão de crédito");
                JCheckBox gratuito = new JCheckBox("Gratuita");

                pagamentoDinheiro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (gratuito.isSelected()) gratuito.setSelected(false);
                        valorCampo.setEditable(true);
                    }
                });

                pagamentoCartaoDeCredito.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (gratuito.isSelected()) gratuito.setSelected(false);
                        valorCampo.setEditable(true);
                    }
                });

                gratuito.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (pagamentoDinheiro.isSelected()) pagamentoDinheiro.setSelected(false);
                        if (pagamentoCartaoDeCredito.isSelected()) pagamentoCartaoDeCredito.setSelected(false);
                        valorCampo.setEditable(false);
                    }
                });


                JPanel formasPagamento = new JPanel();
                formasPagamento.setLayout(new FlowLayout());
                formasPagamento.add(new JLabel("Formas de pagamento aceitas:"));
                formasPagamento.add(pagamentoCartaoDeCredito);
                formasPagamento.add(pagamentoDinheiro);
                formasPagamento.add(gratuito);

                JPanel valorPainel = new JPanel(new FlowLayout());
                valorPainel.add(valor);
                valorPainel.add(valorCampo);


                JPanel buttonsPanel = new JPanel(new FlowLayout());
                JRadioButton privada = new JRadioButton("Privada");
                JRadioButton publica = new JRadioButton("Pública");
                ButtonGroup radioButtons = new ButtonGroup();
                radioButtons.add(privada);
                radioButtons.add(publica);
                buttonsPanel.add(new JLabel("Carona: "));
                buttonsPanel.add(privada);
                buttonsPanel.add(publica);

                JButton finalizarCarona = new JButton("Criar carona");
                finalizarCarona.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Carona carona;
                        if (privada.isSelected())
                            carona = usuario.getPerfil().getCaronante().oferecerCaronaPrivada();
                        else
                            carona = usuario.getPerfil().getCaronante().oferecerCaronaPublica();

                        carona.setHoraDiaEncontro(horaDiaEncontroCampo.getText());

                        if (gratuito.isSelected()) {
                            carona.adicionarFormaPagamento(MetodoPagamento.GRATIS);
                            String[] valores = {horaDiaEncontroCampo.getText(), String.valueOf(0), usuario.getNome()};
                            model.addRow(valores);
                        }
                        if (pagamentoCartaoDeCredito.isSelected() || pagamentoDinheiro.isSelected()) {
                            String[] valores = {horaDiaEncontroCampo.getText(), valorCampo.getText(), usuario.getNome()};
                            carona.setValor(Float.parseFloat(valorCampo.getText()));
                            if (pagamentoDinheiro.isSelected())
                                carona.adicionarFormaPagamento(MetodoPagamento.DINHEIRO);
                            if (pagamentoCartaoDeCredito.isSelected())
                                carona.adicionarFormaPagamento(MetodoPagamento.CARTAODECREDITO);
                            model.addRow(valores);
                        }

                        JOptionPane.showMessageDialog(painelNovaCarona, "Carona criada com sucesso!", "Parabéns!",
                                JOptionPane.INFORMATION_MESSAGE);

                        horaDiaEncontroCampo.setText("");
                        pagamentoCartaoDeCredito.setSelected(false);
                        pagamentoDinheiro.setSelected(false);
                        gratuito.setSelected(false);
                        valorCampo.setText("");
                        privada.setSelected(false);
                        publica.setSelected(false);

                    }
                });


                painelNovaCarona.add(horaDiaEncontroPainel);
                painelNovaCarona.add(formasPagamento);
                painelNovaCarona.add(valorPainel);
                painelNovaCarona.add(buttonsPanel);
                painelNovaCarona.add(finalizarCarona);
                painelNovaCarona.setVisible(true);
                janelaNovaCarona.add(painelNovaCarona);
//                painelPrincipal.setVisible(true);
            }
        });


        painelPrincipal.add(novaCarona);
        painelPrincipal.add(caronasParticipadas);
        painelPrincipal.add(scroll);
        janela.add(painelPrincipal);
        janela.setVisible(true);

    }
}

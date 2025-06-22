package com.br.breno.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.br.breno.dao.EventoDAO;
import com.br.breno.dao.PalestranteDAO;
import com.br.breno.dao.ParticipanteDAO;
import com.br.breno.model.Evento;
import com.br.breno.model.Palestrante;
import com.br.breno.model.Participante;

public class MainFrame extends JFrame {
    private EventoDAO eventoDAO = new EventoDAO();
    private ParticipanteDAO participanteDAO = new ParticipanteDAO();
    private PalestranteDAO palestranteDAO = new PalestranteDAO();

    // Components for Evento tab
    private JTextField nomeEventoField = new JTextField(15);
    private JTextField descEventoField = new JTextField(15);
    private JTextField dataEventoField = new JTextField(10); // yyyy-mm-dd
    private JTextField localEventoField = new JTextField(10);
    private JTextField capacidadeEventoField = new JTextField(5);
    private JButton addEventoBtn = new JButton("Adicionar Evento");
    private JButton updateEventoBtn = new JButton("Atualizar Evento");
    private JButton deleteEventoBtn = new JButton("Remover Evento");
    private JComboBox<Evento> eventoComboBox = new JComboBox<>();
    private JList<Evento> eventoList = new JList<>();
    private DefaultListModel<Evento> eventoListModel = new DefaultListModel<>();

    // Components for Participante tab
    private JTextField nomePartField = new JTextField(15);
    private JTextField emailPartField = new JTextField(15);
    private JTextField cpfPartField = new JTextField(12);
    private JButton addPartBtn = new JButton("Adicionar Participante");
    private JComboBox<Participante> participanteComboBox = new JComboBox<>();

    // Components for Palestrante tab
    private JTextField nomePalField = new JTextField(15);
    private JTextField curriculoPalField = new JTextField(15);
    private JTextField areaPalField = new JTextField(15);
    private JButton addPalBtn = new JButton("Adicionar Palestrante");
    private JComboBox<Palestrante> palestranteComboBox = new JComboBox<>();

    // Association
    private JButton assocPartEventoBtn = new JButton("Associar Participante ao Evento");
    private JButton assocPalEventoBtn = new JButton("Associar Palestrante ao Evento");
    private JComboBox<Evento> eventoComboBoxAssocPart = new JComboBox<>();
    private JComboBox<Evento> eventoComboBoxAssocPal = new JComboBox<>();

    public MainFrame() {
        setTitle("Gestão de Eventos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600); // Aumenta o tamanho da janela
        setLocationRelativeTo(null); // Centraliza na tela

        JTabbedPane tabs = new JTabbedPane();

        // Evento Tab
        JPanel eventoPanel = new JPanel(new BorderLayout(10, 10));
        JPanel eventoFields = new JPanel(new GridLayout(6, 2, 10, 10));
        eventoFields.add(new JLabel("Nome:"));
        eventoFields.add(nomeEventoField);
        eventoFields.add(new JLabel("Descrição:"));
        eventoFields.add(descEventoField);
        eventoFields.add(new JLabel("Data (yyyy-mm-dd):"));
        eventoFields.add(dataEventoField);
        eventoFields.add(new JLabel("Local:"));
        eventoFields.add(localEventoField);
        eventoFields.add(new JLabel("Capacidade:"));
        eventoFields.add(capacidadeEventoField);
        eventoFields.add(new JLabel("Eventos:"));
        eventoFields.add(new JScrollPane(eventoList));

        JPanel eventoButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        eventoButtons.add(addEventoBtn);
        eventoButtons.add(updateEventoBtn);
        eventoButtons.add(deleteEventoBtn);

        eventoPanel.add(eventoFields, BorderLayout.CENTER);
        eventoPanel.add(eventoButtons, BorderLayout.SOUTH);

        // Participante Tab
        JPanel partPanel = new JPanel(new BorderLayout(10, 10));
        JPanel partFields = new JPanel(new GridLayout(4, 2, 10, 10));
        partFields.add(new JLabel("Nome:"));
        partFields.add(nomePartField);
        partFields.add(new JLabel("Email:"));
        partFields.add(emailPartField);
        partFields.add(new JLabel("CPF:"));
        partFields.add(cpfPartField);
        partFields.add(new JLabel("Participantes:"));
        partFields.add(participanteComboBox);

        JPanel partButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        partButtons.add(addPartBtn);

        partPanel.add(partFields, BorderLayout.CENTER);
        partPanel.add(partButtons, BorderLayout.SOUTH);

        // Palestrante Tab
        JPanel palPanel = new JPanel(new BorderLayout(10, 10));
        JPanel palFields = new JPanel(new GridLayout(4, 2, 10, 10));
        palFields.add(new JLabel("Nome:"));
        palFields.add(nomePalField);
        palFields.add(new JLabel("Currículo:"));
        palFields.add(curriculoPalField);
        palFields.add(new JLabel("Área de Atuação:"));
        palFields.add(areaPalField);
        palFields.add(new JLabel("Palestrantes:"));
        palFields.add(palestranteComboBox);

        JPanel palButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        palButtons.add(addPalBtn);

        palPanel.add(palFields, BorderLayout.CENTER);
        palPanel.add(palButtons, BorderLayout.SOUTH);

        // Associação Tab
        JPanel assocPanel = new JPanel(new GridLayout(4, 3, 10, 20));

        // Associação Participante x Evento
        assocPanel.add(new JLabel("Evento:"));
        assocPanel.add(new JLabel("Participante:"));
        assocPanel.add(new JLabel(""));
        assocPanel.add(eventoComboBoxAssocPart);
        assocPanel.add(participanteComboBox);
        assocPanel.add(assocPartEventoBtn);

        // Associação Palestrante x Evento
        assocPanel.add(new JLabel("Evento:"));
        assocPanel.add(new JLabel("Palestrante:"));
        assocPanel.add(new JLabel(""));
        assocPanel.add(eventoComboBoxAssocPal);
        assocPanel.add(palestranteComboBox);
        assocPanel.add(assocPalEventoBtn);

        tabs.addTab("Eventos", eventoPanel);
        tabs.addTab("Participantes", partPanel);
        tabs.addTab("Palestrantes", palPanel);
        tabs.addTab("Associações", assocPanel);

        add(tabs);

        // Listeners
        addEventoBtn.addActionListener(e -> {
            try {
                Evento evento = new Evento(
                        nomeEventoField.getText(),
                        descEventoField.getText(),
                        LocalDate.parse(dataEventoField.getText()),
                        localEventoField.getText(),
                        Integer.parseInt(capacidadeEventoField.getText())
                );
                eventoDAO.create(evento);
                JOptionPane.showMessageDialog(this, "Evento adicionado!");
                refreshEventos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        updateEventoBtn.addActionListener(e -> {
            Evento evento = eventoList.getSelectedValue();
            if (evento != null) {
                try {
                    evento.setNome(nomeEventoField.getText());
                    evento.setDescricao(descEventoField.getText());
                    evento.setData(LocalDate.parse(dataEventoField.getText()));
                    evento.setLocal(localEventoField.getText());
                    evento.setCapacidade(Integer.parseInt(capacidadeEventoField.getText()));
                    eventoDAO.update(evento);
                    JOptionPane.showMessageDialog(this, "Evento atualizado!");
                    refreshEventos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
                }
            }
        });

        deleteEventoBtn.addActionListener(e -> {
            Evento evento = eventoList.getSelectedValue();
            if (evento != null) {
                eventoDAO.delete(evento.getId());
                JOptionPane.showMessageDialog(this, "Evento removido!");
                refreshEventos();
            }
        });

        addPartBtn.addActionListener(e -> {
            try {
                Participante p = new Participante(
                        nomePartField.getText(),
                        emailPartField.getText(),
                        cpfPartField.getText()
                );
                participanteDAO.create(p);
                JOptionPane.showMessageDialog(this, "Participante adicionado!");
                refreshParticipantes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        addPalBtn.addActionListener(e -> {
            try {
                Palestrante p = new Palestrante(
                        nomePalField.getText(),
                        curriculoPalField.getText(),
                        areaPalField.getText()
                );
                palestranteDAO.create(p);
                JOptionPane.showMessageDialog(this, "Palestrante adicionado!");
                refreshPalestrantes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        assocPartEventoBtn.addActionListener(e -> {
            Evento evento = (Evento) eventoComboBoxAssocPart.getSelectedItem();
            Participante participante = (Participante) participanteComboBox.getSelectedItem();
            if (evento != null && participante != null) {
                participanteDAO.associarParticipanteEvento(participante.getId(), evento.getId());
                JOptionPane.showMessageDialog(this, "Participante associado ao evento!");
            }
        });

        assocPalEventoBtn.addActionListener(e -> {
            Evento evento = (Evento) eventoComboBoxAssocPal.getSelectedItem();
            Palestrante palestrante = (Palestrante) palestranteComboBox.getSelectedItem();
            if (evento != null && palestrante != null) {
                palestranteDAO.associarPalestranteEvento(palestrante.getId(), evento.getId());
                JOptionPane.showMessageDialog(this, "Palestrante associado ao evento!");
            }
        });

        eventoList.addListSelectionListener(e -> {
            Evento evento = eventoList.getSelectedValue();
            if (evento != null) {
                nomeEventoField.setText(evento.getNome());
                descEventoField.setText(evento.getDescricao());
                dataEventoField.setText(evento.getData().toString());
                localEventoField.setText(evento.getLocal());
                capacidadeEventoField.setText(String.valueOf(evento.getCapacidade()));
            }
        });

        refreshEventos();
        refreshParticipantes();
        refreshPalestrantes();
    }

    private void refreshEventos() {
        eventoListModel.clear();
        eventoComboBox.removeAllItems();
        eventoComboBoxAssocPart.removeAllItems();
        eventoComboBoxAssocPal.removeAllItems();
        List<Evento> eventos = eventoDAO.findAll();
        for (Evento e : eventos) {
            eventoListModel.addElement(e);
            eventoComboBox.addItem(e);
            eventoComboBoxAssocPart.addItem(e);
            eventoComboBoxAssocPal.addItem(e);
        }
        eventoList.setModel(eventoListModel);
    }

    private void refreshParticipantes() {
        participanteComboBox.removeAllItems();
        List<Participante> participantes = participanteDAO.findAll();
        for (Participante p : participantes) participanteComboBox.addItem(p);
    }

    private void refreshPalestrantes() {
        palestranteComboBox.removeAllItems();
        List<Palestrante> palestrantes = palestranteDAO.findAll();
        for (Palestrante p : palestrantes) palestranteComboBox.addItem(p);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

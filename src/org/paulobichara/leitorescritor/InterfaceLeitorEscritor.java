package org.paulobichara.leitorescritor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Rectangle;

public class InterfaceLeitorEscritor extends JFrame implements ActionListener{
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JComboBox jComboBoxTipo = new JComboBox();
  JButton jButtonCriar = new JButton();
  JLabel jLabelLeitores = new JLabel();
  JComboBox jComboBoxLeitAt = new JComboBox();
  JLabel jLabelEscritor = new JLabel();
  JLabel jLabelBloqueados = new JLabel();
  JComboBox jComboBoxBloqueados = new JComboBox();
  JTextField jTextFieldEscritor = new JTextField();
  JButton jButtonExcluir = new JButton();
  JComboBox jComboBoxProcs = new JComboBox();
  JLabel jLabel4 = new JLabel();
  JButton jButtonFinalizar = new JButton();
  JTextField jTextFieldPID = new JTextField();
  JLabel jLabel3 = new JLabel();
  Sistema sistema;
  JComboBox jComboBoxPID = new JComboBox();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();

  public InterfaceLeitorEscritor() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public void actionPerformed(ActionEvent aeEvento){
    Object obSource = aeEvento.getSource();

    if (obSource == jButtonCriar) {
      String SPID = jTextFieldPID.getText(), tipo = (String)jComboBoxTipo.getSelectedItem();
      int PID = Integer.valueOf(SPID).intValue();
      boolean ativo;
      if ((SPID.equals("") == false) && (sistema.checaPID(PID) == false)) {
        ativo = sistema.addProcesso(PID, tipo);
        if (ativo == false) {
          jComboBoxBloqueados.addItem(SPID + " - " + tipo);
        } else {
          if (tipo == "Escritor") {
            jTextFieldEscritor.setText(SPID);
          } else {
            jComboBoxLeitAt.addItem(SPID);
          }
        }
        jComboBoxProcs.addItem(SPID + " - " + tipo);
        jComboBoxPID.addItem(SPID);
      } else {
        JOptionPane.showMessageDialog(null, "PID inválido ou já existente!", "AVISO", JOptionPane.INFORMATION_MESSAGE);
      }
    } else {
      if (obSource == jButtonExcluir) {
        String SPID = (String)jComboBoxPID.getSelectedItem();
        int PID = Integer.valueOf(SPID).intValue();
        boolean achou = false;
        Processo escritorAtivo;
        ArrayList bloqueados, leitoresAtivos, processos;
        sistema.excluirProcesso(PID);
        bloqueados = sistema.getProcessosBloqueados();
        leitoresAtivos = sistema.getLeitoresAtivos();
        escritorAtivo = sistema.getEscritorAtivo();
        processos = sistema.getProcessos();
        jComboBoxProcs.removeAllItems();
        jComboBoxPID.removeAllItems();
        for (int i = 0; (i < processos.size()); i++) {
        String processo = String.valueOf(((Processo)processos.get(i)).getPID());
          jComboBoxPID.addItem(processo);
          processo = processo + " - " + ((Processo)processos.get(i)).getTipo();
          jComboBoxProcs.addItem(processo);
        }
        jComboBoxBloqueados.removeAllItems();
        for (int i = 0; (i < bloqueados.size()); i++) {
        String bloqueado = String.valueOf(((Processo)bloqueados.get(i)).getPID()) + " - " + ((Processo)bloqueados.get(i)).getTipo();
          jComboBoxBloqueados.addItem(bloqueado);
        }
        jComboBoxLeitAt.removeAllItems();
        for (int i = 0; (i < leitoresAtivos.size()); i++) {
        String leitor = String.valueOf(((Processo)leitoresAtivos.get(i)).getPID());
          jComboBoxLeitAt.addItem(leitor);
        }
        if (escritorAtivo != null) {
          jTextFieldEscritor.setText(String.valueOf(escritorAtivo.getPID()));
        } else {
          jTextFieldEscritor.setText("");
        }
      } else {
        if (obSource == jButtonFinalizar) {
          System.exit(0);
        }
      }
    }
  }


  void jbInit() throws Exception {
    jButtonFinalizar.setBounds(new Rectangle(137, 428, 151, 39));
    jButtonFinalizar.setFont(new java.awt.Font("Arial", 1, 12));
    jButtonFinalizar.setForeground(Color.blue);
    jButtonFinalizar.setText("Finalizar");
    jButtonFinalizar.addActionListener(this);
    jButtonExcluir.setText("Excluir");
    jButtonExcluir.setBounds(new Rectangle(332, 224, 80, 25));
    jButtonExcluir.setFont(new java.awt.Font("Arial", 1, 12));
    jButtonExcluir.setForeground(Color.blue);
    jButtonExcluir.addActionListener(this);
    jComboBoxProcs.setBackground(Color.lightGray);
    jComboBoxProcs.setFont(new java.awt.Font("Arial", 0, 12));
    jComboBoxProcs.setForeground(Color.blue);
    jComboBoxProcs.setBounds(new Rectangle(130, 176, 161, 21));
    jLabel1.setFont(new java.awt.Font("Arial", 1, 14));
    jLabel1.setForeground(Color.blue);
    jLabel1.setBorder(BorderFactory.createEtchedBorder());
    jLabel2.setForeground(Color.blue);
    jComboBoxTipo.setForeground(Color.blue);
    jButtonCriar.setFont(new java.awt.Font("Arial", 1, 12));
    jButtonCriar.setForeground(Color.blue);
    jButtonCriar.addActionListener(this);
    jLabel4.setBounds(new Rectangle(37, 180, 68, 15));
    jLabel4.setText("Processos");
    jLabel4.setFont(new java.awt.Font("Arial", 0, 12));
    jLabel4.setForeground(Color.blue);
    jLabelLeitores.setFont(new java.awt.Font("Arial", 0, 12));
    jLabelLeitores.setForeground(Color.blue);
    jLabelEscritor.setFont(new java.awt.Font("Arial", 0, 12));
    jLabelEscritor.setForeground(Color.blue);
    jComboBoxLeitAt.setBackground(Color.lightGray);
    jComboBoxLeitAt.setFont(new java.awt.Font("Arial", 0, 12));
    jComboBoxLeitAt.setForeground(Color.blue);
    jTextFieldEscritor.setFont(new java.awt.Font("Arial", 0, 12));
    jTextFieldEscritor.setForeground(Color.blue);
    jTextFieldEscritor.setDisabledTextColor(Color.blue);
    jTextFieldEscritor.setBackground(Color.lightGray);
    jTextFieldEscritor.setEnabled(false);
    jLabelBloqueados.setFont(new java.awt.Font("Arial", 0, 12));
    jLabelBloqueados.setForeground(Color.blue);
    jTextFieldPID.setBounds(new Rectangle(128, 82, 87, 21));
    jTextFieldPID.setText("");
    jTextFieldPID.setForeground(Color.blue);
    jTextFieldPID.setBackground(Color.lightGray);
    jTextFieldPID.setFont(new java.awt.Font("Arial", 0, 12));
    jLabel3.setBounds(new Rectangle(34, 112, 59, 15));
    jLabel3.setText("Tipo");
    jLabel3.setFont(new java.awt.Font("Arial", 0, 12));
    jLabel3.setForeground(Color.blue);
    this.getContentPane().setLayout(null);
    jPanel1.setForeground(Color.blue);
    jPanel1.setBorder(BorderFactory.createEtchedBorder());
    jPanel1.setBounds(new Rectangle(3, 0, 430, 490));
    jPanel1.setLayout(null);
    jLabel1.setText("     LEITOR/ESCRITOR");
    jLabel1.setBounds(new Rectangle(127, 16, 182, 25));
    jLabel2.setFont(new java.awt.Font("Arial", 0, 12));
    jLabel2.setText("PID");
    jLabel2.setBounds(new Rectangle(35, 87, 28, 15));
    jComboBoxTipo.setBackground(Color.lightGray);
    jComboBoxTipo.setFont(new java.awt.Font("Arial", 0, 12));
    jComboBoxTipo.setBounds(new Rectangle(128, 109, 121, 21));
    jButtonCriar.setBounds(new Rectangle(331, 90, 73, 25));
    jButtonCriar.setText("Criar");
    jLabelLeitores.setBounds(new Rectangle(38, 290, 84, 15));
    jLabelLeitores.setText("Leitores Ativos");
    jComboBoxLeitAt.setBounds(new Rectangle(185, 289, 158, 21));
    jLabelEscritor.setText("Escritor Ativo");
    jLabelEscritor.setBounds(new Rectangle(36, 331, 98, 15));
    jLabelBloqueados.setBounds(new Rectangle(36, 371, 131, 15));
    jLabelBloqueados.setText("Processos Bloqueados");
    jComboBoxBloqueados.setBackground(Color.lightGray);
    jComboBoxBloqueados.setForeground(Color.blue);
    jComboBoxBloqueados.setBounds(new Rectangle(184, 368, 157, 21));
    jTextFieldEscritor.setText("");
    jTextFieldEscritor.setBounds(new Rectangle(184, 327, 155, 21));
    jComboBoxPID.setBounds(new Rectangle(132, 227, 161, 21));
    jComboBoxPID.setForeground(Color.blue);
    jComboBoxPID.setBackground(Color.lightGray);
    jComboBoxPID.setFont(new java.awt.Font("Arial", 0, 12));
    jLabel5.setBounds(new Rectangle(39, 233, 28, 15));
    jLabel5.setText("PID");
    jLabel5.setFont(new java.awt.Font("Arial", 0, 12));
    jLabel5.setForeground(Color.blue);
    jLabel6.setBorder(BorderFactory.createEtchedBorder());
    jLabel6.setText("");
    jLabel6.setBounds(new Rectangle(23, 151, 380, 11));
    jLabel7.setBounds(new Rectangle(26, 264, 380, 11));
    jLabel7.setText("");
    jLabel7.setBorder(BorderFactory.createEtchedBorder());
    this.getContentPane().add(jPanel1, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jButtonCriar, null);
    jPanel1.add(jTextFieldPID, null);
    jPanel1.add(jComboBoxTipo, null);
    jPanel1.add(jLabel3, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(jComboBoxProcs, null);
    jPanel1.add(jButtonFinalizar, null);
    jPanel1.add(jLabelLeitores, null);
    jPanel1.add(jLabelBloqueados, null);
    jPanel1.add(jComboBoxBloqueados, null);
    jPanel1.add(jTextFieldEscritor, null);
    jPanel1.add(jLabelEscritor, null);
    jPanel1.add(jComboBoxLeitAt, null);
    jPanel1.add(jButtonExcluir, null);
    jPanel1.add(jComboBoxPID, null);
    jPanel1.add(jLabel5, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel7, null);
    jComboBoxTipo.addItem("Leitor");
    jComboBoxTipo.addItem("Escritor");
    sistema = new Sistema();
  }

  public static void main(String[] args) {
    InterfaceLeitorEscritor interfaceLeitorEscritor = new InterfaceLeitorEscritor();
    interfaceLeitorEscritor.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    interfaceLeitorEscritor.pack();
    interfaceLeitorEscritor.setVisible(true);
    interfaceLeitorEscritor.setBounds(new Rectangle(0, 3, 516, 508));
    interfaceLeitorEscritor.setLocation(250,100);
    interfaceLeitorEscritor.setTitle("Problema Leitor - Escritor");

  }
}

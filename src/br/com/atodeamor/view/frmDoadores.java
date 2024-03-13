/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.atodeamor.view;

import java.sql.*;
import br.com.atodeamor.jdbc.ModuloConexao;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
//iimportando recursos da biblioteca rs2xml.jar
//import net.proteanit.sql.DbUtils;

public class frmDoadores extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public frmDoadores() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //METODO PARA ADICIONAR DOADORES
    private void adicionar() {
        String sql = "insert into tbl_doa(NOME_DOA,END_DOA,FONE_DOA,EMAIL_DOA) values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEnd.getText());
            pst.setString(3, txtDoaCep.getText());
            pst.setString(4, txtCliEmail.getText());

            //validação dos campos obrigatorios.
            if ((((txtCliNome.getText().isEmpty()) || (txtCliEnd.getText().isEmpty()) || (txtDoaCep.getText().isEmpty()) || (txtCliEmail.getText().isEmpty())))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                //serve para atualiar tabelas de usuarios com os dados do form
                //Usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                //APOIO PARA VERIFICAR MINHA LOGICA;
                //System.out.println("ADICIONADO");
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Doador adicionado com sucesso!");
                    limpar();

                    txtCliNome.requestFocusInWindow();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo para pesquisar doadores
    private void pesquisar_doadores() {
        String sql = "select ID_DOA as ID, NOME_DOA as NOME, END_DOA as ENDEREÇO,FONE_DOA as TELEFONE,EMAIL_DOA as EMAIL from tbl_doa where NOME_DOA like ?";
       try {
            //pasando o conteudo da caixa de pesquisa para o ?
            pst = conexao.prepareStatement(sql);
           pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            // linha que serve para usar a biblioteca para preencher a tabela.
            txtCliTab.setModel(DbUtils.resultSetToTableModel(rs));
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo para setar campos do formulario com o conteudo da tabela.
    public void setar_campos() {
        int setar = txtCliTab.getSelectedRow();
        txtCliId.setText(txtCliTab.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(txtCliTab.getModel().getValueAt(setar, 1).toString());
        txtCliEnd.setText(txtCliTab.getModel().getValueAt(setar, 2).toString());
        txtDoaCep.setText(txtCliTab.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(txtCliTab.getModel().getValueAt(setar, 4).toString());
        // linha abaixo desabilita o botão adicionar
        btnAdicionarCli.setEnabled(false);
    }
    // criando o metodo para alterar dados do doador
    private void alterar() {
        String sql = "update tbl_doa set NOME_DOA=?,END_DOA=?,FONE_DOA=?,EMAIL_DOA=? WHERE ID_DOA=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEnd.getText());
            pst.setString(3, txtDoaCep.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText());
            //validação dos campos obrigatorios
            if ((((txtCliNome.getText().isEmpty()) || (txtCliEnd.getText().isEmpty()) || (txtDoaCep.getText().isEmpty()) || (txtCliEmail.getText().isEmpty())))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                //serve para atualiar tabelas de clientes com os dados do form
                //Usada para confirmar a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                //APOIO PARA VERIFICAR MINHA LOGICA
                //System.out.println("ADICIONADO");
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do doador alterados com sucesso!");
                    limpar();

                    txtCliPesquisar.requestFocusInWindow();
                    //reabilita o botão 
                    btnAdicionarCli.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//metodo responsável pela remoção de doadores

    private void remover() {
        //confirmação
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este doador?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbl_doa where ID_DOA=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Doador removido com sucesso");
                    limpar();

                    txtCliPesquisar.requestFocusInWindow();
                    //reabilita o botão 
                    btnAdicionarCli.setEnabled(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    //metodo para limpar os campos do furmulario
    private void limpar() {
        txtCliPesquisar.setText(null);
        txtCliId.setText(null);
        txtCliNome.setText(null);
        txtCliEnd.setText(null);
        txtDoaCep.setText(null);
        txtCliEmail.setText(null);
        ((DefaultTableModel)txtCliTab.getModel()).setRowCount(0);
        
    }
        /**
         * }
         *
         *
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        btnAdicionarCli = new javax.swing.JButton();
        btnExcluirCli = new javax.swing.JButton();
        btnAlterarCli = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lblSenha3 = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        lblNome1 = new javax.swing.JLabel();
        txtCliEnd = new javax.swing.JTextField();
        txtCliId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        lblSenha2 = new javax.swing.JLabel();
        txtCliTel = new javax.swing.JFormattedTextField();
        lblSenha4 = new javax.swing.JLabel();
        txtDoaCep = new javax.swing.JFormattedTextField();
        lblSenha1 = new javax.swing.JLabel();
        txtDoaNum = new javax.swing.JTextField();
        lblSenha5 = new javax.swing.JLabel();
        txtDoaCpto = new javax.swing.JTextField();
        lblSenha6 = new javax.swing.JLabel();
        txtDoaBai = new javax.swing.JTextField();
        lblSenha7 = new javax.swing.JLabel();
        txtDoaCid = new javax.swing.JTextField();
        lblSenha8 = new javax.swing.JLabel();
        cbDoaUf = new javax.swing.JComboBox<>();
        lblSenha9 = new javax.swing.JLabel();
        txtDoaCpf = new javax.swing.JFormattedTextField();
        txtDoaRg1 = new javax.swing.JLabel();
        txtDoaRg = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCliTab = new javax.swing.JTable();
        btnPesquisarCli = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        lblNome2 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setResizable(true);
        setTitle("DOADORES");
        setPreferredSize(new java.awt.Dimension(1105, 500));

        panel1.setBackground(new java.awt.Color(153, 153, 153));

        btnAdicionarCli.setText("ADICIONAR");
        btnAdicionarCli.setToolTipText("ADICIONAR NOVO CLIENTE");
        btnAdicionarCli.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdicionarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCliActionPerformed(evt);
            }
        });

        btnExcluirCli.setText("EXCLUIR");
        btnExcluirCli.setToolTipText("EXCLUIR CLIENTE");
        btnExcluirCli.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExcluirCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCliActionPerformed(evt);
            }
        });

        btnAlterarCli.setText("ALTERAR");
        btnAlterarCli.setToolTipText("ALTERA INFO DO CLIENTE");
        btnAlterarCli.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAlterarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnAdicionarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addComponent(btnAlterarCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(btnExcluirCli)
                .addGap(70, 70, 70))
        );

        panel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdicionarCli, btnAlterarCli, btnExcluirCli});

        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluirCli)
                            .addComponent(btnAlterarCli)))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdicionarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdicionarCli, btnAlterarCli, btnExcluirCli});

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cadastro de Doadores");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        lblSenha3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha3.setText("*E-mail:");

        lblSenha.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha.setText("*Endereço:");

        lblNome1.setText("ID_DOADOR");

        txtCliId.setEnabled(false);

        jLabel1.setText("*Campos obrigatórios ");

        lblNome.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblNome.setText("*Nome:");

        lblSenha2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha2.setText("*Telefone:");

        try {
            txtCliTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCliTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliTelActionPerformed(evt);
            }
        });

        lblSenha4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha4.setText("*CEP:");

        try {
            txtDoaCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##### - ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDoaCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoaCepActionPerformed(evt);
            }
        });

        lblSenha1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha1.setText("N°:");

        lblSenha5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha5.setText("Complemento:");

        lblSenha6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha6.setText("*Bairro:");

        lblSenha7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha7.setText("*Cidade:");

        lblSenha8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha8.setText("UF");

        cbDoaUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP", "MG", "RJ", "BA", "SC" }));

        lblSenha9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSenha9.setText("*CPF:");

        try {
            txtDoaCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDoaCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoaCpfActionPerformed(evt);
            }
        });

        txtDoaRg1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtDoaRg1.setText("*RG:");

        try {
            txtDoaRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.### - #")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDoaRg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoaRgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(624, 624, 624))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(txtDoaRg1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDoaRg, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(lblSenha9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDoaCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(lblSenha4)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtDoaCep, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(109, 109, 109))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(lblSenha2)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtCliTel)
                                            .addGap(25, 25, 25)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblSenha6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDoaBai, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblSenha7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDoaCid))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblSenha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCliEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCliEmail)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblSenha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblSenha8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDoaNum, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbDoaUf, 0, 1, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblSenha5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDoaCpto, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNome)
                        .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDoaRg1)
                        .addComponent(txtDoaRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDoaCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSenha9)
                        .addComponent(lblNome1)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha2)
                    .addComponent(txtCliTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha3)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSenha4)
                        .addComponent(txtDoaCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSenha)
                        .addComponent(txtCliEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSenha1)
                        .addComponent(txtDoaNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSenha5)
                        .addComponent(txtDoaCpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha7)
                    .addComponent(txtDoaCid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha6)
                    .addComponent(txtDoaBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha8)
                    .addComponent(cbDoaUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados Pessoais", jPanel2);

        txtCliTab = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        txtCliTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "ENDEREÇO", "FONE", "EMAIL"
            }
        ));
        txtCliTab.setFocusable(false);
        txtCliTab.getTableHeader().setReorderingAllowed(false);
        txtCliTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCliTabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtCliTab);

        btnPesquisarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-pesquisar-24.png"))); // NOI18N
        btnPesquisarCli.setToolTipText("ADICIONAR NOVO USUARIO");
        btnPesquisarCli.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPesquisarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCliActionPerformed(evt);
            }
        });

        txtCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisarActionPerformed(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        lblNome2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblNome2.setText("*Nome:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNome2))
                    .addComponent(btnPesquisarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta de Doadores", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1105, 661);
    }// </editor-fold>//GEN-END:initComponents

   //evento que será usado para setar compos clicando com o mouse
    private void txtCliTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCliTabMouseClicked
        // chamando o metodo setar campos
        setar_campos();
    }//GEN-LAST:event_txtCliTabMouseClicked

    private void btnAlterarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarCliActionPerformed
        // Chamando o metodo alterar
        alterar();
    }//GEN-LAST:event_btnAlterarCliActionPerformed

    private void btnExcluirCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCliActionPerformed
        // Chamando o metodo remover
        remover();
    }//GEN-LAST:event_btnExcluirCliActionPerformed

    private void btnAdicionarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCliActionPerformed
        // Chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnAdicionarCliActionPerformed

//GEN-FIRST:event_txtCliPesquisarKeyReleased
 private void txtCliPesquisarKeyReleased (java.awt.event.KeyEvent evt){
     pesquisar_doadores();
 }
//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void btnPesquisarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCliActionPerformed
        //BOTÃO PARA PESQUISAR
        pesquisar_doadores();
        
    }//GEN-LAST:event_btnPesquisarCliActionPerformed

    private void txtDoaRgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoaRgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoaRgActionPerformed

    private void txtDoaCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoaCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoaCpfActionPerformed

    private void txtDoaCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoaCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoaCepActionPerformed

    private void txtCliTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliTelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCli;
    private javax.swing.JButton btnAlterarCli;
    private javax.swing.JButton btnExcluirCli;
    private javax.swing.JButton btnPesquisarCli;
    private javax.swing.JComboBox<String> cbDoaUf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome2;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenha1;
    private javax.swing.JLabel lblSenha2;
    private javax.swing.JLabel lblSenha3;
    private javax.swing.JLabel lblSenha4;
    private javax.swing.JLabel lblSenha5;
    private javax.swing.JLabel lblSenha6;
    private javax.swing.JLabel lblSenha7;
    private javax.swing.JLabel lblSenha8;
    private javax.swing.JLabel lblSenha9;
    private java.awt.Panel panel1;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEnd;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTable txtCliTab;
    private javax.swing.JFormattedTextField txtCliTel;
    private javax.swing.JTextField txtDoaBai;
    private javax.swing.JFormattedTextField txtDoaCep;
    private javax.swing.JTextField txtDoaCid;
    private javax.swing.JFormattedTextField txtDoaCpf;
    private javax.swing.JTextField txtDoaCpto;
    private javax.swing.JTextField txtDoaNum;
    private javax.swing.JFormattedTextField txtDoaRg;
    private javax.swing.JLabel txtDoaRg1;
    // End of variables declaration//GEN-END:variables
}

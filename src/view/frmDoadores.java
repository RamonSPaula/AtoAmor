/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.sql.*;
import conexxao_BD.ModuloConexao;
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
            pst.setString(3, txtCliTel.getText());
            pst.setString(4, txtCliEmail.getText());

            //validação dos campos obrigatorios.
            if ((((txtCliNome.getText().isEmpty()) || (txtCliEnd.getText().isEmpty()) || (txtCliTel.getText().isEmpty()) || (txtCliEmail.getText().isEmpty())))) {
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
        txtCliTel.setText(txtCliTab.getModel().getValueAt(setar, 3).toString());
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
            pst.setString(3, txtCliTel.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText());
            //validação dos campos obrigatorios
            if ((((txtCliNome.getText().isEmpty()) || (txtCliEnd.getText().isEmpty()) || (txtCliTel.getText().isEmpty()) || (txtCliEmail.getText().isEmpty())))) {
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
        txtCliTel.setText(null);
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

        lblSenha = new javax.swing.JLabel();
        txtCliEnd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        lblSenha2 = new javax.swing.JLabel();
        txtCliTel = new javax.swing.JTextField();
        lblSenha3 = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCliTab = new javax.swing.JTable();
        lblNome1 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        lblNome2 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        btnPesquisarCli = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        btnAdicionarCli = new javax.swing.JButton();
        btnExcluirCli = new javax.swing.JButton();
        btnAlterarCli = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setResizable(true);
        setTitle("DOADORES");
        setPreferredSize(new java.awt.Dimension(1105, 500));

        lblSenha.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblSenha.setText("*ENDEREÇO");

        jLabel1.setText("*Campos obrigatórios ");

        lblNome.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNome.setText("*NOME");

        lblSenha2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblSenha2.setText("*TELEFONE");

        lblSenha3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblSenha3.setText("*EMAIL");

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

        lblNome1.setText("ID_DOADOR");

        txtCliId.setEnabled(false);

        lblNome2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lblNome2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNome2.setText("Cadastro de Doadores");
        lblNome2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panel1.setBackground(new java.awt.Color(204, 204, 204));

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
                .addContainerGap()
                .addComponent(btnAdicionarCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAlterarCli)
                .addGap(18, 18, 18)
                .addComponent(btnExcluirCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
                .addComponent(btnPesquisarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdicionarCli)
                        .addComponent(btnExcluirCli)
                        .addComponent(btnAlterarCli))
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSenha)
                                            .addComponent(txtCliEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(148, 148, 148)
                                        .addComponent(lblNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblSenha2)
                                    .addComponent(txtCliTel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(180, 180, 180))
                            .addComponent(lblNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(lblNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNome1)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSenha)
                                .addGap(18, 18, 18)
                                .addComponent(txtCliEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblNome)
                                .addGap(8, 8, 8)
                                .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSenha3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSenha2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(16, 16, 16)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCli;
    private javax.swing.JButton btnAlterarCli;
    private javax.swing.JButton btnExcluirCli;
    private javax.swing.JButton btnPesquisarCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome2;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenha2;
    private javax.swing.JLabel lblSenha3;
    private java.awt.Panel panel1;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEnd;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTable txtCliTab;
    private javax.swing.JTextField txtCliTel;
    // End of variables declaration//GEN-END:variables
}

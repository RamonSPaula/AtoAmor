/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.atodeamor.view;

/**
 *
 * @author Ramon Santos
 */
import java.sql.*;
import br.com.atodeamor.dao.ModuloConexao;
import javax.swing.JOptionPane;

public class frmUsuarios extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //construtor, sera usado para inciar modo de conexão.
    public frmUsuarios() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
//metodo para consultar usuários

    private void consultar() {
        String sql = "select * from tbl_usuario where ID_USUARIO=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUserId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                //pegando nome no banco de daods
                txtUserNome.setText(rs.getString(3));
                txtUserLogin.setText(rs.getString(2));
                txtUserSenha.setText(rs.getString(4));
                txtUserPerfil.setSelectedItem(rs.getString(5));

            } else {
                //quando não encontrar
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                //limpam os campos
                txtUserNome.setText(null);
                txtUserLogin.setText(null);
                txtUserSenha.setText(null);
                txtUserPerfil.setSelectedItem(null);
                txtUserId.requestFocusInWindow();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //metodo para adicionar usuários
    private void adicionar() {
        String sql = "insert into tbl_usuario(ID_USUARIO, LOGIN, NOME,SENHA,PERFIL) values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUserId.getText());
            pst.setString(2, txtUserLogin.getText());
            pst.setString(3, txtUserNome.getText());
            pst.setString(4, txtUserSenha.getText());
            pst.setString(5, txtUserPerfil.getSelectedItem().toString());
            //validação dos campos obrigatorios
            if ((((txtUserId.getText().isEmpty()) ||(txtUserNome.getText().isEmpty()) ||(txtUserLogin.getText().isEmpty()) ||(txtUserSenha.getText().isEmpty())))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                
            } else {

                //serve para atualiar tabelas de usuarios com os dados do form
                //Usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                //APOIO PARA VERIFICAR MINHA LOGICA
                //System.out.println("ADICIONADO");
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
                    txtUserId.setText(null);
                    txtUserNome.setText(null);
                    txtUserLogin.setText(null);
                    txtUserSenha.setText(null);
                    txtUserPerfil.setSelectedItem(null);
                    txtUserId.requestFocusInWindow();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // criando o metodo para alterar dados do user
    private void alterar(){
        String sql="update tbl_usuario set LOGIN=?,NOME=?,SENHA=?,PERFIL=? WHERE ID_USUARIO=?";
       try{
           pst=conexao.prepareStatement(sql);
           pst.setString(1, txtUserLogin.getText());
           pst.setString(2, txtUserNome.getText());
           pst.setString(3, txtUserSenha.getText());
           pst.setString(4, txtUserPerfil.getSelectedItem().toString());
           pst.setString(5, txtUserId.getText());
           //validação dos campos obrigatorios
            if ((((txtUserId.getText().isEmpty()) ||(txtUserNome.getText().isEmpty()) ||(txtUserLogin.getText().isEmpty()) ||(txtUserSenha.getText().isEmpty())))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                
            } else {

                //serve para atualiar tabelas de usuarios com os dados do form
                //Usada para confirmar a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                //APOIO PARA VERIFICAR MINHA LOGICA
                //System.out.println("ADICIONADO");
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso!");
                    txtUserId.setText(null);
                    txtUserNome.setText(null);
                    txtUserLogin.setText(null);
                    txtUserSenha.setText(null);
                    txtUserPerfil.setSelectedItem(null);
                    txtUserId.requestFocusInWindow();
                }
            }
       } catch (Exception e)  {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    //metodo responsável pela remoção de user
    private void remover(){
        //confirmação
        int confirma=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?","Atenção",JOptionPane.YES_NO_OPTION);
        if (confirma==JOptionPane.YES_OPTION){
            String sql="delete from tbl_usuario where ID_USUARIO=?";
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1,txtUserId.getText());
                int apagado = pst.executeUpdate();
                if(apagado>0){
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso"); 
                    txtUserId.setText(null);
                    txtUserNome.setText(null);
                    txtUserLogin.setText(null);
                    txtUserSenha.setText(null);
                    txtUserPerfil.setSelectedItem(null);
                    txtUserId.requestFocusInWindow();
                }
                
            }catch (Exception e){
               JOptionPane.showMessageDialog(null, e); 
            }
             
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUser = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        txtUserNome = new javax.swing.JTextField();
        lblPerfil = new javax.swing.JLabel();
        txtUserPerfil = new javax.swing.JComboBox<>();
        lblSenha1 = new javax.swing.JLabel();
        txtUserLogin = new javax.swing.JTextField();
        btnConsultarUser = new javax.swing.JButton();
        btnAlterarUser = new javax.swing.JButton();
        btnExcluirUser = new javax.swing.JButton();
        btnAdicionarUser = new javax.swing.JButton();
        txtUserSenha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("USUÁRIOS");
        setPreferredSize(new java.awt.Dimension(1105, 700));

        lblUser.setText("*ID_USUARIO");

        txtUserId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserIdActionPerformed(evt);
            }
        });

        lblSenha.setText("*SENHA");

        lblNome.setText("*NOME");

        lblPerfil.setText("*PERFIL");

        txtUserPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE", "USER", "ADM" }));
        txtUserPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserPerfilActionPerformed(evt);
            }
        });

        lblSenha1.setText("*LOGIN");

        btnConsultarUser.setText("CONSULTAR");
        btnConsultarUser.setToolTipText("CONSULTA DE USUÁRIOS ");
        btnConsultarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarUserActionPerformed(evt);
            }
        });

        btnAlterarUser.setText("ALTERAR");
        btnAlterarUser.setToolTipText("ALTERA INFO DO USUÁRIO");
        btnAlterarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarUserActionPerformed(evt);
            }
        });

        btnExcluirUser.setText("EXCLUIR");
        btnExcluirUser.setToolTipText("EXCLUIR USUÁRIO");
        btnExcluirUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluirUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirUserActionPerformed(evt);
            }
        });

        btnAdicionarUser.setText("ADICIONAR");
        btnAdicionarUser.setToolTipText("ADICIONAR NOVO USUARIO");
        btnAdicionarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarUserActionPerformed(evt);
            }
        });

        jLabel1.setText("*Campos obrigatórios ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionarUser)
                        .addGap(52, 52, 52)
                        .addComponent(btnConsultarUser)
                        .addGap(76, 76, 76)
                        .addComponent(btnAlterarUser)
                        .addGap(64, 64, 64)
                        .addComponent(btnExcluirUser))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(txtUserNome, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                    .addComponent(txtUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdicionarUser, btnAlterarUser, btnConsultarUser, btnExcluirUser});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha1)
                    .addComponent(txtUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPerfil)
                    .addComponent(txtUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnConsultarUser)
                    .addComponent(btnAlterarUser)
                    .addComponent(btnExcluirUser)
                    .addComponent(btnAdicionarUser))
                .addContainerGap(277, Short.MAX_VALUE))
        );

        setBounds(0, 0, 881, 653);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarUserActionPerformed
        // Chamando o metodo consultar
        consultar();
    }//GEN-LAST:event_btnConsultarUserActionPerformed

    private void txtUserPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserPerfilActionPerformed

    private void btnAdicionarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarUserActionPerformed
        // Chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnAdicionarUserActionPerformed

    private void btnAlterarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarUserActionPerformed
         // Chamando o metodo alterar
        alterar();
    }//GEN-LAST:event_btnAlterarUserActionPerformed

    private void btnExcluirUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirUserActionPerformed
       // Chamando o metodo remover
       remover();
    }//GEN-LAST:event_btnExcluirUserActionPerformed

    private void txtUserIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserIdActionPerformed

    }//GEN-LAST:event_txtUserIdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarUser;
    private javax.swing.JButton btnAlterarUser;
    private javax.swing.JButton btnConsultarUser;
    private javax.swing.JButton btnExcluirUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenha1;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtUserId;
    private javax.swing.JTextField txtUserLogin;
    private javax.swing.JTextField txtUserNome;
    private javax.swing.JComboBox<String> txtUserPerfil;
    private javax.swing.JTextField txtUserSenha;
    // End of variables declaration//GEN-END:variables
}

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
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;

// ...


public class frmProduto extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //construtor, sera usado para inciar modo de conexão.
    public frmProduto() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
 //metodo para adicionar produtos
private void adicionar() {
    String sql = "insert into tbl_produtos (COD_PRODUTO, ALIMENTO, VALID_PROD, UNI_MEDIDA, DATA_ENTRADA, QUANT_PROD, DOADOR) values(?,?,?,?,?,?,?)";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtProCod.getText());
        pst.setString(2, txtProAli.getText());

        // Formatando a data de validade
        String dataValidadeFormatada = formatarData(txtProVali.getText());
        pst.setString(3, dataValidadeFormatada);

        pst.setString(4, txtProUni.getText());

        // Formatando a data de entrada
        String dataEntradaFormatada = formatarData(txtProDat.getText());
        pst.setString(5, dataEntradaFormatada);

        pst.setString(6, txtProQua.getText());
        pst.setString(7, txtProDoa.getText());

        // validação dos campos obrigatórios
        if (txtProCod.getText().isEmpty() || txtProAli.getText().isEmpty() || txtProVali.getText().isEmpty()
                || txtProUni.getText().isEmpty() || txtProDat.getText().isEmpty() || txtProQua.getText().isEmpty()
                || txtProDoa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
        } else {
            // serve para atualizar tabelas de usuários com os dados do form
            // Usada para confirmar a inserção dos dados na tabela
            int adicionado = pst.executeUpdate();
            // APOIO PARA VERIFICAR MINHA LÓGICA
            System.out.println("ADICIONADO");
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
                limparCampos();
                txtProCod.requestFocusInWindow();
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}

// Função para formatar a data
private String formatarData(String data) {
    try {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd");

        Date dataFormatada = formatoEntrada.parse(data);
        return formatoSaida.format(dataFormatada);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao formatar data: " + e.getMessage());
        return null;
    }
}

// Função para limpar os campos após a inserção
private void limparCampos() {
    txtProCod.setText(null);
    txtProAli.setText(null);
    txtProVali.setText(null);
    txtProUni.setText(null);
    txtProDat.setText(null);
    txtProQua.setText(null);
    txtProDoa.setText(null);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        txtProAli = new javax.swing.JTextField();
        lblNome1 = new javax.swing.JLabel();
        txtProCod = new javax.swing.JTextField();
        lblNome2 = new javax.swing.JLabel();
        lblNome3 = new javax.swing.JLabel();
        txtProUni = new javax.swing.JTextField();
        lblNome4 = new javax.swing.JLabel();
        lblNome5 = new javax.swing.JLabel();
        txtProQua = new javax.swing.JTextField();
        lblNome7 = new javax.swing.JLabel();
        txtProDoa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAdicionarCli = new javax.swing.JButton();
        btnAlterarCli = new javax.swing.JButton();
        btnExcluirCli = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCliTab = new javax.swing.JTable();
        btnAdicionarCli1 = new javax.swing.JButton();
        btnExcluirCli1 = new javax.swing.JButton();
        btnAlterarCli1 = new javax.swing.JButton();
        txtProVali = new javax.swing.JFormattedTextField();
        txtProDat = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("PRODUTOS");
        setPreferredSize(new java.awt.Dimension(1105, 700));

        lblNome.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNome.setText("Alimento:");

        lblNome1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNome1.setText("Doador:");

        lblNome2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNome2.setText("Validade:");

        lblNome3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNome3.setText("Unidade de Medida:");

        lblNome4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNome4.setText("Cod. Produto");

        lblNome5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNome5.setText("Data da entrada:");

        lblNome7.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblNome7.setText("Quantidade");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/rs_sistemas 2.png"))); // NOI18N

        btnAdicionarCli.setText("ADICIONAR");
        btnAdicionarCli.setToolTipText("ADICIONAR NOVO CADASTRO");
        btnAdicionarCli.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdicionarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCliActionPerformed(evt);
            }
        });

        btnAlterarCli.setText("ALTERAR");
        btnAlterarCli.setToolTipText("ALTERAR PRODUTO");
        btnAlterarCli.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAlterarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarCliActionPerformed(evt);
            }
        });

        btnExcluirCli.setText("EXCLUIR");
        btnExcluirCli.setToolTipText("EXCLUIR PRODUTO");
        btnExcluirCli.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExcluirCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCliActionPerformed(evt);
            }
        });

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

        btnAdicionarCli1.setText("ADICIONAR");
        btnAdicionarCli1.setToolTipText("ADICIONAR NOVO CLIENTE");
        btnAdicionarCli1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdicionarCli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCli1ActionPerformed(evt);
            }
        });

        btnExcluirCli1.setText("EXCLUIR");
        btnExcluirCli1.setToolTipText("EXCLUIR CLIENTE");
        btnExcluirCli1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExcluirCli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCli1ActionPerformed(evt);
            }
        });

        btnAlterarCli1.setText("ALTERAR");
        btnAlterarCli1.setToolTipText("ALTERA INFO DO CLIENTE");
        btnAlterarCli1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAlterarCli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarCli1ActionPerformed(evt);
            }
        });

        try {
            txtProVali.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtProDat.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(444, 444, 444)
                        .addComponent(btnAdicionarCli)
                        .addGap(52, 52, 52)
                        .addComponent(btnAlterarCli)
                        .addGap(47, 47, 47)
                        .addComponent(btnExcluirCli))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1049, 1049, 1049)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblNome3)
                            .addGap(38, 38, 38)
                            .addComponent(txtProUni, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(227, 227, 227)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblNome4)
                                            .addComponent(lblNome)
                                            .addComponent(lblNome7)
                                            .addComponent(lblNome1)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(192, 192, 192)
                                        .addComponent(lblNome2)))
                                .addComponent(lblNome5, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(38, 38, 38)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtProAli, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addComponent(txtProCod, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addComponent(txtProQua, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addComponent(txtProDoa, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addComponent(txtProVali)
                                .addComponent(txtProDat))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(btnAdicionarCli1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterarCli1)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirCli1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome4)
                            .addComponent(txtProCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome)
                            .addComponent(txtProAli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProVali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNome5)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtProDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome7))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNome1)
                    .addComponent(txtProDoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarCli1)
                    .addComponent(btnExcluirCli1)
                    .addComponent(btnAlterarCli1))
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluirCli)
                        .addComponent(btnAlterarCli))
                    .addComponent(btnAdicionarCli))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1000, 1031);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCliActionPerformed
        // Chamando o metodo adicionar
        
    }//GEN-LAST:event_btnAdicionarCliActionPerformed

    private void btnAlterarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarCliActionPerformed
        // Chamando o metodo alterar
        //alterar();
    }//GEN-LAST:event_btnAlterarCliActionPerformed

    private void btnExcluirCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCliActionPerformed
        // Chamando o metodo remover
        //remover();
    }//GEN-LAST:event_btnExcluirCliActionPerformed

    private void txtCliTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCliTabMouseClicked
        // chamando o metodo setar campos
        //setar_campos();
    }//GEN-LAST:event_txtCliTabMouseClicked

    private void btnAdicionarCli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCli1ActionPerformed
        // Chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnAdicionarCli1ActionPerformed

    private void btnExcluirCli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCli1ActionPerformed
        // Chamando o metodo remover
        //remover();
    }//GEN-LAST:event_btnExcluirCli1ActionPerformed

    private void btnAlterarCli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarCli1ActionPerformed
        // Chamando o metodo alterar
        //alterar();
    }//GEN-LAST:event_btnAlterarCli1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCli;
    private javax.swing.JButton btnAdicionarCli1;
    private javax.swing.JButton btnAlterarCli;
    private javax.swing.JButton btnAlterarCli1;
    private javax.swing.JButton btnExcluirCli;
    private javax.swing.JButton btnExcluirCli1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome2;
    private javax.swing.JLabel lblNome3;
    private javax.swing.JLabel lblNome4;
    private javax.swing.JLabel lblNome5;
    private javax.swing.JLabel lblNome7;
    private javax.swing.JTable txtCliTab;
    private javax.swing.JTextField txtProAli;
    private javax.swing.JTextField txtProCod;
    private javax.swing.JFormattedTextField txtProDat;
    private javax.swing.JTextField txtProDoa;
    private javax.swing.JTextField txtProQua;
    private javax.swing.JTextField txtProUni;
    private javax.swing.JFormattedTextField txtProVali;
    // End of variables declaration//GEN-END:variables

   
}

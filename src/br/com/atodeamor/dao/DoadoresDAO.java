/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.atodeamor.dao;

import br.com.atodeamor.jdbc.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import br.com.atodeamor.model.Doadores;
import java.sql.SQLException;

public class DoadoresDAO {
  
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public DoadoresDAO() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //METODO PARA ADICIONAR DOADORES
    public void adicionarDoadores(Doadores obj) {
        //1° criação do comando SQL;
        try {
            String sql = "insert into tbl_doa(nome_doa,rg_doa,cpf_doa,email_doa,telefone_doa,cep_doa,endereco_doa,numero_doa,complemento_doa,bairro_doa,cidade_doa,estado_doa)" +
                 "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        //2° conexão do BD e organização do comando SQL;
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, obj.getNome_doa());
            pst.setString(2, obj.getRg_doa());
            pst.setString(3, obj.getCpf_doa());
            pst.setString(4, obj.getEmail_doa());
            pst.setString(5, obj.getTelefone_doa());
            pst.setString(6, obj.getCep_doa());
            pst.setString(7, obj.getEndereco_doa());
            pst.setInt(8, obj.getNumero_doa());
            pst.setString(9, obj.getComplemento_doa());
            pst.setString(10, obj.getBairro_doa());
            pst.setString(11, obj.getCidade_doa());
            pst.setString(13, obj.getUf_doa());
           
            pst.execute();
            pst.close();
            
            JOptionPane.showMessageDialog(null, "Doador adicionado com sucesso!");
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}

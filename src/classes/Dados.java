
package classes;


public class Dados {
    public boolean validarUsuarios(String usuario, String senha){
        if(usuario.equals("adm")&& senha.equals("123")){
            return true;
        }else{
                return false;
                //parei no ep 7
        }
    }

    public boolean validarUsuario(String text, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

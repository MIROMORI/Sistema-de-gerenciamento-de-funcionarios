import Dominio.Funcionario;
import Repository.FuncionarioDAO;

public class ConnectionFactoryTest {
    public static void main(String[] args) {
        FuncionarioDAO.insertFuncionario(new Funcionario());
    }
}

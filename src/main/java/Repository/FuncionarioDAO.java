package Repository;

import Conn.ConnectionFactory;
import Dominio.Cargo;
import Dominio.Funcionario;
import java.sql.*;
import java.util.ArrayList;


public class FuncionarioDAO {

    public static void insertFuncionario(Funcionario funcionario){
        String sql = "INSERT INTO FUNCIONARIO (nome, idade, salario, cargo) VALUES (?, ?, ?, ?)";

        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getIdade());
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setString(4, funcionario.getCargo().toString());
            int rows = stmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteFuncionario(int id){
        String sql = "DELETE FROM `funcionario` WHERE `id` = %d".formatted(id);

        try(Connection con = ConnectionFactory.getConnection();
            Statement st = con.createStatement()){
            int rows = st.executeUpdate(sql);
            System.out.printf("Rows affected: %d\n", rows);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateNome(int id, String nome){
        String sql = "UPDATE funcionario SET nome = '%s' WHERE id = %d".formatted(nome, id);

        try(Connection con = ConnectionFactory.getConnection();
            Statement st = con.createStatement()){
            st.executeUpdate(sql);
            System.out.println("Nome atualizado!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateSalario(int id, double salario){
        String sql = "UPDATE funcionario SET salario = ? WHERE id = ?";

        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement st = con.prepareStatement(sql)){
            st.setDouble(1, salario);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("Salario atualizado!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateIdade(int id, int idade){
        String sql = "UPDATE funcionario SET idade = '%d' WHERE id = %d".formatted(idade, id);

        try(Connection con = ConnectionFactory.getConnection();
            Statement st = con.createStatement()){
            st.executeUpdate(sql);
            System.out.println("Idade atualizada!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateCargo(int id, Cargo cargo){
        String sql = "UPDATE funcionario SET cargo = '%s' WHERE id = %d".formatted(cargo.toString(), id);

        try(Connection con = ConnectionFactory.getConnection();
            Statement st = con.createStatement()){
            st.executeUpdate(sql);
            System.out.println("Cargo atualizado!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Funcionario> searchFuncionario(int id){
        String sql = "SELECT * FROM funcionario WHERE ID = %d".formatted(id);
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

        try(Connection con = ConnectionFactory.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while(rs.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setID(rs.getInt("ID"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setIdade(rs.getInt("idade"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setCargo(stringToCargo(rs.getString("cargo")));
                listaFuncionarios.add(funcionario);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        if(listaFuncionarios.isEmpty()){
            System.out.println("Não foi encontrado nenhum funcionario");
        } else{
            for(Funcionario f: listaFuncionarios){
                System.out.println(f);
            }
        }
        return listaFuncionarios;
    }

    private static Cargo stringToCargo(String string){
        return switch(string){
            case "GERENTE" -> Cargo.GERENTE;
            case "ESTAGIARIO" -> Cargo.ESTAGIARIO;
            case "SUPERVISOR" -> Cargo.SUPERVISOR;
            case "DESENVOLVEDOR" -> Cargo.DESENVOLVEDOR;
            case "GERENTE_DE_MARKETING" -> Cargo.GERENTE_DE_MARKETING;
            case "RH" -> Cargo.RH;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };

    }
}

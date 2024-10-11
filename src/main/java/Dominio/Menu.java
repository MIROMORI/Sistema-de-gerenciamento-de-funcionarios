package Dominio;

import Repository.FuncionarioDAO;
import java.util.Objects;
import java.util.Scanner;

public class Menu {

    public static void startMenu(){
        showMenu();
        action(checkAction());
    }

    private static void showMenu(){
        System.out.println("""
                1 - Adicionar um novo funcionario
                2 - Editar um funcionario
                3 - Remover um funcionario
                4 - Buscar um funcionario na base de dados
                5 - Encerrar aplicação""");
    }

    private static int checkAction(){
        int action;
        Scanner scanner = new Scanner(System.in);
        action = scanner.nextInt();
        return action;
    }

    private static void action(int checkAction){
        switch(checkAction){
            case 1:
                //Adicionar novo funcionario
                FuncionarioDAO.insertFuncionario(Funcionario.createFuncionario());
                startMenu();
                break;
            case 2:
                //Editar um funcionario
                Scanner scanner = new Scanner(System.in);
                switch(askForCampo()){
                    case 1:
                        System.out.println("Nome: ");
                        String nome = scanner.nextLine();
                                FuncionarioDAO.updateNome(askForID(), nome);
                                startMenu();
                        break;
                    case 2:
                        System.out.println("Idade: ");
                        int idade = scanner.nextInt();
                        FuncionarioDAO.updateIdade(askForID(), idade);
                        startMenu();
                        break;
                    case 3:
                        FuncionarioDAO.updateCargo(askForID(), Objects.requireNonNull(selectCargo()));
                        startMenu();
                        break;
                    case 4:
                        System.out.println("Salario: ");
                        double salario = scanner.nextDouble();
                        FuncionarioDAO.updateSalario(askForID(), salario);
                        startMenu();
                        break;
                }

                break;

            case 3:
                //Remover um funcionario
                FuncionarioDAO.deleteFuncionario(askForID());
                startMenu();
                break;

            case 4:
                //Buscar um funcionario
                FuncionarioDAO.searchFuncionario(askForID());
                startMenu();
                break;

            case 5:
                System.out.println("Encerrando o programa");
                break;
            default:
                System.err.println("O valor inserido não é um valor válido");
                startMenu();
                break;
        }
    }

    private static int askForID(){
        int id;
        System.out.println("Informe o ID do funcionario: ");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        if(id > 0){
            return id;
        } else {
            System.out.println("Este ID não é válido");
            return askForID();
        }
    }

    private static int askForCampo(){
        int campo;
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                        Qual campo deseja alterar?
                        1 - Nome
                        2 - Idade
                        3 - Cargo
                        4 - Salario:
                        """);
        campo = scanner.nextInt();
        if(campo >=1 && campo <= 4){
            return campo;
        } else {
            System.out.println("Essa não é uma opção válida");
            return askForCampo();
        }
    }

    private static Cargo selectCargo(){
        int cargo;
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                1 - RH
                2 - Gerente
                3 - Estagiario
                4 - Supervisor
                5 - Gerente de marketing
                6 - Desenvolvedor
                """);
        cargo = scan.nextInt();
        return switch (cargo) {
            case 1 -> Cargo.RH;
            case 2 -> Cargo.GERENTE;
            case 3 -> Cargo.ESTAGIARIO;
            case 4 -> Cargo.SUPERVISOR;
            case 5 -> Cargo.GERENTE_DE_MARKETING;
            case 6 -> Cargo.DESENVOLVEDOR;
            default -> {
                System.out.println("Essa não é uma opção válida");
                yield selectCargo();
            }
        };
    }
}

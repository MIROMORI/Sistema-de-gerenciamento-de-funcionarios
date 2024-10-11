package Dominio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Scanner;

@Getter
@Setter
@ToString
public class Funcionario {
    Integer ID;
    Double salario;
    Integer idade;
    String nome;
    Cargo cargo;

    public static Funcionario createFuncionario(){
        Funcionario funcionario = new Funcionario();
        Scanner scan = new Scanner(System.in);
        System.out.println("Nome: ");
        funcionario.nome = scan.nextLine();
        System.out.println("Idade: ");
        funcionario.idade = scan.nextInt();
        System.out.println("Cargo: ");
        funcionario.cargo = selectCargo();
        System.out.println("Salario: ");
        funcionario.salario = scan.nextDouble();
        return funcionario;
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

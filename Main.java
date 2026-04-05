import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> veiculos = new ArrayList<>();
    public static void main(String[] args) {

        IO.println("Bem vindo ao Sistema CadVeiculos");

        String menu = """
                MENU DE OPÇÕES
                1 - Cadastrar Veículo
                2 - Listar Veículos
                3 - Remover por Índice
                4 - Buscar Veículo
                5 - Editar Veículo
                6 - Remover por Nome
                0 - Sair
                """;

        int opcao;

        do {
            IO.println(menu);
            opcao = Input.scanInt("Digite a opção desejada: ");

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> removerIndice();
                case 4 -> buscar();
                case 5 -> editar();
                case 6 -> removerNome();
                case 0 -> IO.println("Volte sempre!!!");
                default -> IO.println("Opção inválida!");
            }

            if (opcao != 0)
                IO.readln("Pressione Enter para continuar...");

        } while (opcao != 0);
    }
    static void cadastrar() {
        String veiculo = IO.readln("Digite o nome do veículo: ").trim();

        if (veiculo.isEmpty()) {
            IO.println("Nome inválido!");
            return;
        }

        for (String v : veiculos) {
            if (v.equalsIgnoreCase(veiculo)) {
                IO.println("Veículo já cadastrado!");
                return;
            }
        }

        veiculos.add(veiculo);
        IO.println("Veículo cadastrado com sucesso!");
    }

    static void listar() {
        if (veiculos.isEmpty()) {
            IO.println("Lista vazia!");
            return;
        }

        ordenar();

        for (int i = 0; i < veiculos.size(); i++) {
            IO.println((i + 1) + " - " + veiculos.get(i));
        }

        IO.println("Total de veículos: " + veiculos.size());
    }

    static void buscar() {
        if (veiculos.isEmpty()) {
            IO.println("Lista vazia!");
            return;
        }

        String nome = IO.readln("Digite o nome para busca: ").trim();

        ordenar();

        boolean encontrado = false;

        for (String v : veiculos) {
            if (v.equalsIgnoreCase(nome)) {
                encontrado = true;
                break;
            }
        }

        if (encontrado)
            IO.println("Veículo encontrado!");
        else
            IO.println("Veículo não encontrado!");

        IO.println("Total de veículos: " + veiculos.size());
    }

    static void removerIndice() {
        if (veiculos.isEmpty()) {
            IO.println("Lista vazia!");
            return;
        }

        listar();

        int indice = Input.scanInt("Digite o número do veículo: ");

        if (indice < 1 || indice > veiculos.size()) {
            IO.println("Índice inválido!");
            return;
        }

        veiculos.remove(indice - 1);
        IO.println("Veículo removido!");
    }

    static void removerNome() {
        if (veiculos.isEmpty()) {
            IO.println("Lista vazia!");
            return;
        }

        String nome = IO.readln("Digite o nome para remover: ").trim();

        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).equalsIgnoreCase(nome)) {
                veiculos.remove(i);
                IO.println("Veículo removido!");
                return;
            }
        }

        IO.println("Veículo não encontrado!");
    }

    static void editar() {
        if (veiculos.isEmpty()) {
            IO.println("Lista vazia!");
            return;
        }

        listar();

        int indice = Input.scanInt("Digite o número do veículo para editar: ");

        if (indice < 1 || indice > veiculos.size()) {
            IO.println("Índice inválido!");
            return;
        }

        String novo = IO.readln("Digite o novo nome: ").trim();

        if (novo.isEmpty()) {
            IO.println("Nome inválido!");
            return;
        }

        for (String v : veiculos) {
            if (v.equalsIgnoreCase(novo)) {
                IO.println("Já existe um veículo com esse nome!");
                return;
            }
        }

        veiculos.set(indice - 1, novo);
        IO.println("Veículo atualizado!");
    }

    static void ordenar() {
        for (int i = 0; i < veiculos.size() - 1; i++) {
            for (int j = 0; j < veiculos.size() - 1 - i; j++) {
                if (veiculos.get(j).compareToIgnoreCase(veiculos.get(j + 1)) > 0) {
                    String temp = veiculos.get(j);
                    veiculos.set(j, veiculos.get(j + 1));
                    veiculos.set(j + 1, temp);
                }
            }
        }
    }
}
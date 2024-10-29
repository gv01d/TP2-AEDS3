import java.util.ArrayList;
import java.util.Scanner;

public class MenuTarefas {
    private Tarefas_ctl ctlTarefas;
    private Categorias_ctl ctlCategorias;
    private Scanner scanner;

    public MenuTarefas(Tarefas_ctl ctlTarefas, Categorias_ctl ctlCategorias) {
        this.ctlTarefas = ctlTarefas;
        this.ctlCategorias = ctlCategorias;
        this.scanner = new Scanner(System.in);
    }

    public void menu() throws Exception {
        int opcao;
        do {
            System.out.println("-----------------");
            System.out.println("> Inicio > Tarefas\n");
            System.out.println("1- Incluir");
            System.out.println("2- Excluir");
            System.out.println("3- Listar");
            System.out.println("0- Voltar");
            System.out.print("\nEscolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    excluirTarefa();
                    break;
                case 3:
                    listarTarefas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private void adicionarTarefa() throws Exception {
        System.out.print("Nome da Tarefa: ");
        String nome = scanner.nextLine();

        System.out.println("Escolha uma categoria:");
        ArrayList<Categoria> categorias = ctlCategorias.printCategorias();
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println("\t(" + (i + 1) + ") " + categorias.get(i).getNome());
        }

        System.out.print("Opcao: ");
        int escolhaCategoria = scanner.nextInt();
        scanner.nextLine(); 

        int idCategoria = categorias.get(escolhaCategoria - 1).getId();

        Tarefa tarefa = new Tarefa(-1, nome, idCategoria);
        if (ctlTarefas.adicionarTarefa(tarefa)) {
            System.out.println("Tarefa adicionada com sucesso!");
        } else {
            System.out.println("Erro ao adicionar tarefa.");
        }
    }

    private void excluirTarefa() throws Exception {
        ArrayList<Tarefa> tarefas = ctlTarefas.printTarefas();

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para excluir.");
            return;
        }

        System.out.println("Escolha a tarefa a ser excluida:");
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + ") " + tarefas.get(i).getNome());
        }

        System.out.print("Opcao: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); 

        if (escolha < 1 || escolha > tarefas.size()) {
            System.out.println("Opcao invalida.");
            return;
        }

        int idTarefa = tarefas.get(escolha - 1).getId();
        if (ctlTarefas.excluirTarefa(idTarefa)) {
            System.out.println("Tarefa excluida com sucesso!");
        } else {
            System.out.println("Erro ao excluir tarefa.");
        }
    }

    private void listarTarefas() throws Exception {
        ArrayList<Tarefa> tarefas = ctlTarefas.printTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa para listar");
        } else {
            System.out.println("Lista de Tarefas:");
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa.toString());
            }
        }
    }
}

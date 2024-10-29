// import aed3.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCategorias {
    private Categorias_ctl ctlCategorias;
    private Scanner scanner;


    public MenuCategorias(Categorias_ctl ctlCategorias, Tarefas_ctl ctlTarefas) {
        this.ctlCategorias = ctlCategorias;
        this.scanner = new Scanner(System.in);
    }


    // Interface de Categorias
    public void menu() throws Exception {
        int opcao;
        do {
            System.out.println("------------------");
            System.out.println("> Inicio > Categorias\n");
            System.out.println("1- Incluir");
            System.out.println("2- Excluir");
            System.out.println("3- Listar");
            System.out.println("4- Gerar Relatorio de Tarefas por Categoria");
            System.out.println("0- Voltar");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarCategoria();
                    break;
                case 2:
                    excluirCategoria();
                    break;
                case 3:
                    listarCategorias();
                    break;
                case 4:
                    gerarRelatorioTarefasPorCategoria();
                    break;
                case 0:
                    System.out.println("Saindo do menu de categorias...");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }





    // Controle 
    private void adicionarCategoria() throws Exception {
        System.out.print("Nome da nova categoria: ");
        String nome = scanner.nextLine();
        Categoria categoria = new Categoria(-1, nome); 
        if (ctlCategorias.adicionarCategoria(categoria)) {
            System.out.println("Categoria adicionada com sucesso!");
        } else {
            System.out.println("Erro ao adicionar categoria.");
        }
    }

    private void excluirCategoria() throws Exception {
        ArrayList<Categoria> categorias = ctlCategorias.printCategorias();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada para excluir.");
            return;
        }

        System.out.println("Escolha a categoria a ser excluida:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println("\t("+(i + 1) + ") " + categorias.get(i).getNome());
        }

        System.out.print("Opcao: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); 

        if (escolha < 1 || escolha > categorias.size()) {
            System.out.println("Opcao invalida.");
            return;
        }

        int idCategoria = categorias.get(escolha - 1).getId();
        if (ctlCategorias.excluirCategoria(idCategoria)) {
            System.out.println("Categoria excluida com sucesso!");
        } else {
            System.out.println("Nao foi possivel excluir a categoria.");
        }
    }

    private void listarCategorias() throws Exception {
        System.out.println("Lista de Categorias:\n");
        for (Categoria categoria : ctlCategorias.printCategorias()) {
            System.out.println("ID: [" + categoria.getId() + "] | NOME: " + categoria.getNome());
        }
    }

    private void gerarRelatorioTarefasPorCategoria() throws Exception {
        ctlCategorias.gerarRelatorioTarefasPorCategoria();
    }

    // private void gerarRelatorioTarefasPorCategoria() {
    //     
    // }
}

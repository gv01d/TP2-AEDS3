import java.util.Scanner;

public class IO {

    public static void main(String[] args) {

        try {
            ArquivoCategorias arqCategorias = new ArquivoCategorias(Categoria.class.getConstructor(), "categorias");
            ArquivoTarefas arqTarefas = new ArquivoTarefas(Tarefa.class.getConstructor(), "tarefas");

            Categorias_ctl ctlCategorias = new Categorias_ctl(arqCategorias, arqTarefas);
            Tarefas_ctl ctlTarefas = new Tarefas_ctl(arqTarefas, arqCategorias);

            MenuCategorias menuCategorias = new MenuCategorias(ctlCategorias, ctlTarefas);
            MenuTarefas menuTarefas = new MenuTarefas(ctlTarefas, ctlCategorias);

            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("--------------");
                System.out.println("|--- MENU ---|");
                System.out.println("--------------");
                System.out.println(">Inicio ");
                System.out.println("1- Categorias");
                System.out.println("2- Tarefas");
                System.out.println("0- Encerrar");
                System.out.print("Escolha uma opcao: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                    menuCategorias.menu();
                        break;
                    case 2:
                    menuTarefas.menu();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                }
            } while (opcao != 0);

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

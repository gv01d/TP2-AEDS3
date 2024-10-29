import java.util.ArrayList;

public class Categorias_ctl {
    private ArquivoCategorias arquivoCategorias;
    private ArquivoTarefas arquivoTarefas;

    public Categorias_ctl(ArquivoCategorias arquivoCategorias, ArquivoTarefas arquivoTarefas) {
        this.arquivoCategorias = arquivoCategorias;
        this.arquivoTarefas = arquivoTarefas;
    }

    public boolean adicionarCategoria(Categoria categoria) throws Exception {
        ArrayList<Categoria> categoriasExistentes = arquivoCategorias.searchByNome(categoria.getNome());
        if (!categoriasExistentes.isEmpty()) {
            System.out.println("Categoria '" + categoria.getNome() + "' ja existe.");
            return false; 
        }
    
        arquivoCategorias.create(categoria);
        return true;
    }
    
    

    public boolean excluirCategoria(int idCategoria) throws Exception {
        ArrayList<Tarefa> tarefas = arquivoTarefas.buscarPorCategoria(idCategoria);
        if (tarefas.isEmpty()) {
            return arquivoCategorias.delete(idCategoria);
        } else {
            System.out.println("Nao foi possível excluir: existem tarefas com essa categoria.");
            return false;
        }
    }
    

    public void gerarRelatorioTarefasPorCategoria() throws Exception {
        ArrayList<Categoria> categorias = arquivoCategorias.printCategorias();
        for (Categoria categoria : categorias) {
            System.out.println("\nCategoria: " + categoria.getNome() +"\n");
            ArrayList<Tarefa> tarefas = arquivoTarefas.buscarPorCategoria(categoria.getId());
            System.out.println("- Tarefa(s): \n");
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa.toString());
            }
        }
    }

    public ArrayList<Categoria> printCategorias() throws Exception {
        return arquivoCategorias.printCategorias();
    }
}

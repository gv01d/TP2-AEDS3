import java.util.ArrayList;

public class Tarefas_ctl {
    private ArquivoTarefas arqTarefas;
    private ArquivoCategorias arqCategorias;

    public Tarefas_ctl(ArquivoTarefas arqTarefas, ArquivoCategorias arqCategorias) {
        this.arqTarefas = arqTarefas;
        this.arqCategorias = arqCategorias;
    }

    public boolean adicionarTarefa(Tarefa tarefa) throws Exception {
        arqTarefas.create(tarefa);
        return true; 
    }

    public boolean excluirTarefa(int idTarefa) throws Exception {
        return arqTarefas.delete(idTarefa);
    }

    public ArrayList<Tarefa> printTarefas() throws Exception {
        return arqTarefas.printTarefas();
    }

    public ArrayList<Categoria> printCategorias() throws Exception {
        return arqCategorias.printCategorias();
    }
}


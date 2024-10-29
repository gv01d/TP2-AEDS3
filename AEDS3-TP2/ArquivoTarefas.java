import aed3.Arquivo;
import aed3.ArvoreBMais;
import java.util.ArrayList;
import java.lang.reflect.Constructor;

public class ArquivoTarefas extends Arquivo<Tarefa> {

    private ArvoreBMais<ParIdId> indiceCategoriaTarefa;
    
    // Relacionamento entre tarefas e categorias
    
    public ArquivoTarefas(Constructor<Tarefa> construtor, String nomeArquivo) throws Exception {
        super(construtor, nomeArquivo);
        indiceCategoriaTarefa = new ArvoreBMais<>(ParIdId.class.getConstructor(), 5,
                "dados/arvoreTarefasPorCategoria.db");
    }

    @Override
    public void create(Tarefa tarefa) throws Exception {
        super.create(tarefa);
        indiceCategoriaTarefa.create(new ParIdId(tarefa.getIdCategoria(), tarefa.getId()));
    }

    @Override
    public Tarefa read(int id) throws Exception {
        return super.read(id);
    }

    @Override
    public boolean update(Tarefa tarefa) throws Exception {
        Tarefa antigaTarefa = read(tarefa.getId());
        if (antigaTarefa != null) {
            if (antigaTarefa.getIdCategoria() != tarefa.getIdCategoria()) {
                indiceCategoriaTarefa.delete(new ParIdId(antigaTarefa.getIdCategoria(), antigaTarefa.getId()));
                indiceCategoriaTarefa.create(new ParIdId(tarefa.getIdCategoria(), tarefa.getId()));
            }
            return super.update(tarefa);
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Tarefa tarefa = read(id);
        if (tarefa != null) {
            indiceCategoriaTarefa.delete(new ParIdId(tarefa.getIdCategoria(), id));
            return super.delete(id);
        }
        return false;
    }

    public ArrayList<Tarefa> buscarPorCategoria(int idCategoria) throws Exception {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        ArrayList<ParIdId> pares = indiceCategoriaTarefa.read(new ParIdId(idCategoria, -1));
        for (ParIdId par : pares) {
            Tarefa tarefa = read(par.getId2());
            if (tarefa != null) {
                tarefas.add(tarefa); // 
            }
        }
        return tarefas;
    }

    public ArrayList<Tarefa> printTarefas() throws Exception {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        int id = 0;
        int countNull = 0; 
  
    
        while (countNull < 10) {
            Tarefa tarefa = read(id);
            if (tarefa != null) {
                tarefas.add(tarefa);
                countNull = 0; 
            } else {
                countNull++;
            }
            id++;
        }
    
        return tarefas;
    }
    
}

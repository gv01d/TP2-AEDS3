import java.util.ArrayList;
import java.util.Comparator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import aed3.*;

public class ArquivoCategorias extends Arquivo<Categoria> {

    private ArvoreBMais<ParNomeId> indNomeCat;

    public ArquivoCategorias(Constructor<Categoria> construtor, String nomeArquivo) throws Exception {
        super(construtor, nomeArquivo);
        indNomeCat = new ArvoreBMais<>(ParNomeId.class.getConstructor(), 5,
                "dados/arvoreCategoriasPorNome.db"); // arquivo com as categorias usando nome
    }

    @Override
    public void create(Categoria categoria) throws Exception {
        super.create(categoria);
        indNomeCat.create(new ParNomeId(categoria.getNome(), categoria.getId()));
    }

    @Override
    public Categoria read(int id) throws Exception {
        return super.read(id);
    }

    @Override
    public boolean update(Categoria categoria) throws Exception {
        Categoria antigaCategoria = read(categoria.getId());
        if (antigaCategoria != null) {
            if (!antigaCategoria.getNome().equals(categoria.getNome())) {
                indNomeCat.delete(new ParNomeId(antigaCategoria.getNome(), antigaCategoria.getId()));
                indNomeCat.create(new ParNomeId(categoria.getNome(), categoria.getId()));
            }
            return super.update(categoria);
        }
        return false;
    }

    public ArrayList<Categoria> searchByNome(String nome) throws Exception {
        ArrayList<Categoria> categorias = new ArrayList<>();
        ArrayList<ParNomeId> pares = indNomeCat.read(new ParNomeId(nome, -1));

        Field idField = ParNomeId.class.getDeclaredField("id");
        idField.setAccessible(true); 

        for (ParNomeId par : pares) {
            int idCategoria = (int) idField.get(par);
            Categoria categoria = read(idCategoria);
            if (categoria != null) {
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Categoria categoria = read(id);
        if (categoria != null) {
            categoria.setExcluido(true);
            update(categoria);

            indNomeCat.delete(new ParNomeId(categoria.getNome(), id));
            return super.delete(id); 
        }
        return false;
    }


    public ArrayList<Categoria> printCategorias() throws Exception {
        ArrayList<Categoria> categorias = new ArrayList<>();
        int id = 0;
        int countNull = 0; 
     

        while (countNull < 10) {
            Categoria categoria = read(id);
            if (categoria != null && !categoria.isExcluido()) { 
                categoria.setNome(formatNome(categoria.getNome()));
                categorias.add(categoria);
                countNull = 0; 
            } else {
                countNull++;
            }
            id++;
        }

        categorias.sort(Comparator.comparing(Categoria::getNome));
        return categorias;
    }

    private String formatNome(String nome) { // funcao para formatar o nome com primeira letra maiuscula
        if (nome == null || nome.isEmpty()) {
            return nome;
        }
        return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
    }
}

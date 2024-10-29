import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import aed3.Registro;

public class Categoria implements Registro {
    
    private int id;
    private String nome;
    private boolean excluido = false; 

    public Categoria() {
        this.id = -1;
        this.nome = "";
    }
    
    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChaveIndice() {
        return this.nome.toLowerCase();
    }

    
    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }
    
    public boolean isExcluido() {
        return excluido;
    }
    
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(nome);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
    }

    public int compareTo(Object c) {
        return this.id - ((Categoria)c).id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome;
    }
}

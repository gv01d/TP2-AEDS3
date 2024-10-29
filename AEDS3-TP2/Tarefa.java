
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import aed3.Registro;
public class Tarefa implements Registro {

    int id;
    String nome;
    LocalDate dataCriacao;
    LocalDate dataConclusao;
    byte status; 
    byte prioridade; 
    int idCategoria;

    public Tarefa() {
        this(-1, "", LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), (byte)1, (byte)2, -1);
    }

    public Tarefa(int id, String nome, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.idCategoria = idCategoria;
        this.dataCriacao = LocalDate.now(); 
        this.dataConclusao = LocalDate.now(); 
        this.status = 0;                
        this.prioridade = 0;            
    }

    public Tarefa (String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade, int idCategoria) {
        this(-1, nome, dataCriacao, dataConclusao, status, prioridade, idCategoria);
        
    }

    public Tarefa(int id, String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.prioridade = prioridade;
        this.idCategoria = idCategoria;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }

    public void setPrioridade(byte prioridade) {
        this.prioridade = prioridade;
    }

    public byte getPrioridade() {
        return prioridade;
    }

    public void setIdCategoria(int id)
    {
        this.idCategoria = id;
    }

    public int getIdCategoria()
    {
        return idCategoria;
    }


    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.nome);
        dos.writeInt((int)this.dataCriacao.toEpochDay());
        dos.writeInt((int)this.dataConclusao.toEpochDay());
        dos.writeByte(this.status);
        dos.writeByte(this.prioridade);
        dos.writeInt(this.idCategoria);

        return baos.toByteArray();
    }

    
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
        this.dataCriacao = LocalDate.ofEpochDay(dis.readInt());
        this.dataConclusao = LocalDate.ofEpochDay(dis.readInt());
        this.status = dis.readByte();
        this.prioridade = dis.readByte();
        this.idCategoria = dis.readInt();
    }
    public int compareTo(Object p) {
        return this.id - ((Tarefa)p).id;
    }

    public String toString() {
        return 
             "\nID:               : " + this.id + 
             "\nNome:             : " + this.nome + 
             "\nData de Criacao   : " + this.dataCriacao + 
             "\nData de Conclusao : " + this.dataConclusao + 
             "\nStatus            : " + this.status + 
             "\nPrioridade        : " + this.prioridade +
             "\nID Categoria      : " + this.idCategoria;
    }
}

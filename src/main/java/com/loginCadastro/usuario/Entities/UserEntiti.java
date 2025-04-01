package com.loginCadastro.usuario.Entities;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class UserEntiti {
    //defindo chave primaria no banco
    // @GeneratedValue(strategy = GenerationType.AUTO) similar ao AUTO_INCREMENT do mysql
    //anotacao do @Id serve para indicar que e uma chave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Configuracoes das colunas como, colunas unicas e se pode ser null
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean usuarioVerificado;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String password;

    @Column(nullable = false)
    private String timeCode;

    public int getCode() {  
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isUsuarioVerificado() {
        return usuarioVerificado;
    }

    public void setUsuarioVerificado(boolean usuarioVerificado) {
        this.usuarioVerificado = usuarioVerificado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return password;
    }

    public void setSenha(String senha) {
        this.password = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeCode() {
        return timeCode;
    }

    public void setTimeCode(String timeCode) {
        this.timeCode = timeCode;
    }
}

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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

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
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

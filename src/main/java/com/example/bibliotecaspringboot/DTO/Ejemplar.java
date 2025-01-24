package com.example.bibliotecaspringboot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    @JsonIgnore
    @JsonIncludeProperties({"isbn"})
    private Libro isbn;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(name = "estado")
    private String estado;

    public Ejemplar(Libro isbn, String estado) {
        this.isbn = isbn;
        this.estado = estado;
    }

    public Ejemplar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Libro getIsbn() {
        return isbn;
    }

    public void setIsbn(Libro isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", isbn=" + isbn.getIsbn() +
                ", estado='" + estado + '\'' +
                '}';
    }
}
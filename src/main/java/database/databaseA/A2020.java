/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.databaseA;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "a2020")
@NamedQueries({
    @NamedQuery(name = "A2020.findAll", query = "SELECT a FROM A2020 a"),
    @NamedQuery(name = "A2020.findById", query = "SELECT a FROM A2020 a WHERE a.id = :id"),
    @NamedQuery(name = "A2020.findByNama", query = "SELECT a FROM A2020 a WHERE a.nama = :nama"),
    @NamedQuery(name = "A2020.findByTglLahir", query = "SELECT a FROM A2020 a WHERE a.tglLahir = :tglLahir")})
public class A2020 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nama")
    private String nama;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "tglLahir")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;

    public A2020() {
    }

    public A2020(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof A2020)) {
            return false;
        }
        A2020 other = (A2020) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.databaseA.A2020[ id=" + id + " ]";
    }
    
}

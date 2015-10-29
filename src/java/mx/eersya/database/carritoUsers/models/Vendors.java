/*
 * Copyright (C) 2015 eersya
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package mx.eersya.database.carritoUsers.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eersya
 */
@Entity
@Table(name = "vendors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendors.findAll", query = "SELECT v FROM Vendors v"),
    @NamedQuery(name = "Vendors.findByIdVendor", query = "SELECT v FROM Vendors v WHERE v.idVendor = :idVendor")})
public class Vendors implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vendor")
    private Integer idVendor;
    @JoinColumn(name = "id_vuser", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private Users idVuser;

    public Vendors() {
    }

    public Vendors(Integer idVendor) {
        this.idVendor = idVendor;
    }

    public Integer getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(Integer idVendor) {
        this.idVendor = idVendor;
    }

    public Users getIdVuser() {
        return idVuser;
    }

    public void setIdVuser(Users idVuser) {
        this.idVuser = idVuser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVendor != null ? idVendor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendors)) {
            return false;
        }
        Vendors other = (Vendors) object;
        if ((this.idVendor == null && other.idVendor != null) || (this.idVendor != null && !this.idVendor.equals(other.idVendor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.eersya.database.carritosItems.models.Vendors[ idVendor=" + idVendor + " ]";
    }
    
}

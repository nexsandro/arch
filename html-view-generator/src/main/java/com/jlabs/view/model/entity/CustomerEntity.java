package com.jlabs.view.model.entity;

import com.jlabs.view.model.entity.common.AbstractEntityBase;

import javax.persistence.*;

/**
 * The customer.
 * 
 * @author sebastiao.santos
 *
 */
@Entity
@Table(name="tb_mdic_cust", uniqueConstraints={
		@UniqueConstraint(columnNames={"nm_name"})
		})
public class CustomerEntity extends AbstractEntityBase {

	private static final long serialVersionUID = 229150576460349152L;

	private Integer id;
	
	private String name;
	
	public CustomerEntity() {
		super();
	}

	public CustomerEntity(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue( generator = "sq_cust", strategy = GenerationType.SEQUENCE )
	@SequenceGenerator( name = "sq_cust", sequenceName = "sq_cust", initialValue = 1, allocationSize = 1 )
	@Column( name = "sq_cust" )
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Column(name="nm_name", nullable=false, length=255)
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getName();
	}
}

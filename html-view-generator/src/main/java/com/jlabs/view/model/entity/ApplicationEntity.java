package com.jlabs.view.model.entity;

import com.jlabs.view.model.entity.common.AbstractEntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
		name = "tb_mdic_appl", 
		uniqueConstraints = {
				@UniqueConstraint( columnNames = "nm_appl")
		})
public class ApplicationEntity extends AbstractEntityBase {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5315265140826430674L;

	/**
	 * Application code
	 */
	private Integer id;
	
	/**
	 * Application name
	 */
	private String name;
	
	/**
	 * Versions of this application
	 */
	private Set<ApplicationVersionEntity> versions;
	
	public ApplicationEntity() {
		super();
		versions = new HashSet<ApplicationVersionEntity>();
	}

	public ApplicationEntity(Integer id, int entityVersion, String name) {
		this();
		this.id = id;
		this.name = name;
		setEntityVersion(entityVersion);
	}

	@Id
	@GeneratedValue(generator = "sq_appl", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator( name = "sq_appl", sequenceName = "sq_appl", initialValue = 1, allocationSize=1 )
	@Column( name = "sq_appl" )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	@Version
	@Column( name = "nu_vers", nullable = false )
	public int getEntityVersion() {
		return super.getEntityVersion();
	}

	@Column( name = "nm_appl", length = 200, nullable = false )
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany( mappedBy = "application" )
	public Set<ApplicationVersionEntity> getVersions() {
		return versions;
	}

	public void setVersions(Set<ApplicationVersionEntity> versions) {
		this.versions = versions;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ApplicationEntity other = (ApplicationEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}

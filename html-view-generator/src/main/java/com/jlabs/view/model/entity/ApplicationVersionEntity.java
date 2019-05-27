package com.jlabs.view.model.entity;

import com.jlabs.view.model.entity.common.AbstractEntityBase;

import javax.persistence.*;

/**
 * Version of an application.
 */
@Entity
@Table(
		name = "tb_mdic_appl_vers",
		uniqueConstraints = {
				@UniqueConstraint( columnNames = {"nm_vers", "sq_appl"})
		})
public class ApplicationVersionEntity extends AbstractEntityBase {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5371162194851082887L;

	/**
	 * Version id
	 */
	private Integer id;

	/**
	 * Application relative
	 */
	private ApplicationEntity application;
	
	/**
	 * Version name
	 */
	private String name;
	
	public ApplicationVersionEntity() {
		super();
	}

	public ApplicationVersionEntity(String name) {
		super();
		this.name = name;
	}
	
	
	public ApplicationVersionEntity(Integer id, int entityVersion, ApplicationEntity application, String name) {
		super();
		this.id = id;
		this.application = application;
		this.name = name;
		setEntityVersion(entityVersion);
	}

	@Id
	@GeneratedValue(generator = "sq_appl_vers", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator( name = "sq_appl_vers", sequenceName = "sq_appl_vers", initialValue = 1, allocationSize=1 )
	@Column( name = "sq_appl_vers" )
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

	@Column( name = "nm_vers", length = 50, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn( name = "sq_appl", nullable = false )
	public ApplicationEntity getApplication() {
		return application;
	}

	public void setApplication(ApplicationEntity application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
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
		final ApplicationVersionEntity other = (ApplicationVersionEntity) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}

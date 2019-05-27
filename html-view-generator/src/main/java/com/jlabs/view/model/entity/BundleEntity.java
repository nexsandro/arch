package com.jlabs.view.model.entity;


import com.jlabs.view.model.entity.common.AbstractEntityBase;

import javax.persistence.*;
import java.util.Set;

/**
 * A bundle of keywords.
 * 
 * @author s.santos
 *
 */
@Entity
@Table( name = "tb_mdic_bndl",
		uniqueConstraints = {
		@UniqueConstraint( columnNames = {"nm_bndl", "sq_appl", "sq_appl_vers"})
	})
public class BundleEntity extends AbstractEntityBase {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 467740337078081307L;
	
	private Integer id;
	private String name;
	private ApplicationEntity application;
	private ApplicationVersionEntity applicationVersion;
	private Set<KeywordEntity> keywords;
	
	public BundleEntity() {
		super();
	}
	
	public BundleEntity(String name, ApplicationEntity application,
			ApplicationVersionEntity applicationVersion) {
		this();
		this.name = name;
		this.application = application;
		this.applicationVersion = applicationVersion;
	}

	@Id
	@GeneratedValue( generator = "sq_bndl", strategy = GenerationType.SEQUENCE )
	@SequenceGenerator( name = "sq_bndl", sequenceName = "sq_bndl", initialValue = 1, allocationSize=1 )
	@Column( name = "sq_bndl")
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

	@Column( name = "nm_bndl", length = 255)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the application
	 */
	@ManyToOne
	@JoinColumn(name="sq_appl", referencedColumnName="sq_appl")
	public ApplicationEntity getApplication() {
		return application;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(ApplicationEntity application) {
		this.application = application;
	}

	/**
	 * @return the applicationVersion
	 */
	@ManyToOne
	@JoinColumn(name="sq_appl_vers", referencedColumnName="sq_appl_vers")
	public ApplicationVersionEntity getApplicationVersion() {
		return applicationVersion;
	}

	/**
	 * @param applicationVersion the applicationVersion to set
	 */
	public void setApplicationVersion(ApplicationVersionEntity applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	@OneToMany(mappedBy="bundle")
	public Set<KeywordEntity> getKeywords() {
		return keywords;
	}
	
	public void setKeywords(Set<KeywordEntity> keywords) {
		this.keywords = keywords;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		final BundleEntity other = (BundleEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}

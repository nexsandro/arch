package com.jlabs.view.model.entity;

import com.jlabs.view.model.entity.common.AbstractEntityBase;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Palavra-chave do dicionário.
 * @author s.santos
 *
 */
@Entity
@Table(name="tb_mdic_keyw",
		uniqueConstraints={
			@UniqueConstraint(columnNames={"nm_keyw", "sq_cust", "sq_appl", "sq_appl_vers", "sq_bndl"})
		}
)
public class KeywordEntity extends AbstractEntityBase {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4610996282414556848L;
	
	/**
	 * Max size of description text.
	 */
	public static final int MAX_DESCRIPTION_SIZE = 4000;
	
	/**
	 * Código do termo
	 */
	private Integer id;
	
	/**
	 * Palavra-chave.
	 */
	private String keyword;

	private CustomerEntity customer;
	
	private ApplicationEntity application;

	private ApplicationVersionEntity applicationVersion;
	
	private BundleEntity bundle;

	private boolean inactive;
	
	/**
	 * Termos associados a esta palavra-chave
	 */
	private Map<LanguageEnum, TermEntity> terms;
	
	/**
	 * Keyword description
	 */
	private String description;

	/**
	 * Construtor default
	 */
	public KeywordEntity() {
		super();
		terms = new HashMap<LanguageEnum, TermEntity>();
		for(LanguageEnum language : LanguageEnum.ALL_LANGUAGE) {
			TermEntity termEntity = new TermEntity();
			termEntity.setLanguage(language);
			terms.put(language, termEntity);
			termEntity.setKeyword(this);
		}
	}

	public KeywordEntity(CustomerEntity customer,
			String keyword,
			String description,
			ApplicationEntity application,
			ApplicationVersionEntity applicationVersion, BundleEntity bundle) {
		this(customer, application, applicationVersion, bundle);
		this.keyword = keyword;
		this.description = description;
	}

	public KeywordEntity(CustomerEntity customer,
			ApplicationEntity application,
			ApplicationVersionEntity applicationVersion, BundleEntity bundle) {
		this();
		this.customer=customer;
		this.application = application;
		this.applicationVersion = applicationVersion;
		this.bundle = bundle;
	}

	@Id
	@GeneratedValue(generator = "sq_keyw", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator( name = "sq_keyw", sequenceName = "sq_keyw", initialValue = 1, allocationSize=1 )
	@Column( name = "sq_keyw" )
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

	@Column( name = "nm_keyw", length = 255, nullable = false)
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@MapKey(name="language")
	@OneToMany( mappedBy = "keyword", cascade={CascadeType.ALL}, fetch= FetchType.EAGER )
	@LazyCollection(value= LazyCollectionOption.FALSE)
	public Map<LanguageEnum, TermEntity> getTerms() {
		return terms;
	}

	public void setTerms(Map<LanguageEnum, TermEntity> terms) {
		this.terms = terms;
	}

	@Column( name="nm_desc", length = 4000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if ( description != null && description.length() > 4000 )
			description = description.substring(0, 4000);
		this.description = description;
	}
	
	
	/**
	 * The keyword refer to any custom if the custom is set null.
	 * @return the customer
	 */
	@ManyToOne
	@JoinColumn(name="sq_cust", referencedColumnName="sq_cust", nullable=true)
	public CustomerEntity getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
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
	 * @return the bundle
	 */
	@ManyToOne
	@JoinColumn(name="sq_bndl", referencedColumnName="sq_bndl")
	public BundleEntity getBundle() {
		return bundle;
	}

	/**
	 * @param bundle the bundle to set
	 */
	public void setBundle(BundleEntity bundle) {
		this.bundle = bundle;
	}

	/**
	 * @param applicationVersion the applicationVersion to set
	 */
	public void setApplicationVersion(ApplicationVersionEntity applicationVersion) {
		this.applicationVersion = applicationVersion;
	}


	@Column( name="in_actv", length = 1)
	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
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
		final KeywordEntity other = (KeywordEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return keyword;
	}

	public int compareTo(Object o) {
		KeywordEntity other = (KeywordEntity) o;
		return this.keyword.compareTo( other.getKeyword() );
	}
}

package com.jlabs.view.model.entity;

import com.jlabs.view.model.entity.common.AbstractEntityBase;

import javax.persistence.*;

/**
 * Um termo aplicado à palavra chave, como: teste (singular + portugues), testes (plural + portugues), test (singular + english), etc.
 * @author s.santos
 */
@Entity
@Table(name = "tb_mdic_term",
		uniqueConstraints = {
		@UniqueConstraint(columnNames={"sq_keyw", "sq_lang"})
	}
)
public class TermEntity extends AbstractEntityBase implements Comparable<TermEntity> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3094161771465695656L;

	private Integer id;
	private String value;
	private KeywordEntity keyword;
	private LanguageEnum language;
	
	/**
	 * Construtor default
	 */
	public TermEntity() {
		super();
	}
	
	/**
	 * Construtor com atributos.
	 * @param id
	 * @param value
	 */
	public TermEntity(String value, KeywordEntity keyword,
			LanguageEnum language) {
		super();
		this.value = value;
		this.keyword = keyword;
		this.language = language;
	}

	@Id
	@GeneratedValue(generator = "sq_term", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator( name = "sq_term", sequenceName = "sq_term", initialValue = 1, allocationSize=1 )
	@Column(name="sq_term")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	@Version
	@Column( name = "nu_vers" )
	public int getEntityVersion() {
		return super.getEntityVersion();
	}

	@Column( name = "nm_term", length = 1000 )
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn( name = "sq_keyw" )
	public KeywordEntity getKeyword() {
		return keyword;
	}

	public void setKeyword(KeywordEntity keyword) {
		this.keyword = keyword;
	}

	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn( name = "sq_lang" )
	public LanguageEnum getLanguage() {
		return language;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}
	
	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
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
		final TermEntity other = (TermEntity) obj;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		return true;
	}

	public int compareTo(TermEntity other) {
		if (other == null) return 1;
		return keyword.getKeyword().compareTo(other.getKeyword().getKeyword());
	}
	
}

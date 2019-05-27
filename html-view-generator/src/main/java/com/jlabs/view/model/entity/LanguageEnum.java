package com.jlabs.view.model.entity;


import com.jlabs.view.model.entity.common.AbstractEnumerationEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_mdic_lang")
public class LanguageEnum extends AbstractEnumerationEntityBase {

	public static final LanguageEnum DEFAULT = new LanguageEnum( 1, "Português (Default)", "");
	
	public static final LanguageEnum SPANISH = new LanguageEnum( 2, "Espanhol", "es");

	public static final LanguageEnum ENGLISH = new LanguageEnum( 3, "Inglês", "en");

	public static final LanguageEnum[] ALL_LANGUAGE = new LanguageEnum[]{ DEFAULT, SPANISH, ENGLISH };
	
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8433332706289151455L;

	/**
	 * Sufixo da linguagem no arquivo properties.
	 */
	private String sufix;
	
	/**
	 * Construtor default.
	 */
	public LanguageEnum() {
		super();
	}
	
	/**
	 * Construtor com atributos.
	 * @param id
	 * @param value
	 */
	public LanguageEnum(Integer id, String value, String sufix) {
		super();
		this.id = id;
		setValue( value );
		this.sufix = sufix;
	}

	@Id
	@Column(name = "sq_lang")
	public Integer getId() {
		return super.getId();
	}

	@Column(name = "nm_lang", length=60)
	public String getValue() {
		return super.getValue();
	}

	@Column(name = "nm_sufx", length = 6)
	public String getSufix() {
		return sufix;
	}

	public void setSufix(String sufix) {
		this.sufix = sufix;
	}
	
	@Override
	public String toString() {
		return super.getValue();
	}

}

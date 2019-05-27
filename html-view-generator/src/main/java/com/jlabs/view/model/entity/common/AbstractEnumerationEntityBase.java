package com.jlabs.view.model.entity.common;

/**
 * Uma entidade padrão com id e valor.
 * 
 * @author s.santos
 *
 */
public abstract class AbstractEnumerationEntityBase extends AbstractEntityBase implements EnumerationEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6061052207988631336L;
	protected Integer id;
	protected String value;

	public AbstractEnumerationEntityBase() {
		super();
	}
	
	protected AbstractEnumerationEntityBase(Integer id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		final AbstractEnumerationEntityBase other = (AbstractEnumerationEntityBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

package com.jlabs.view.model.entity.common;

import java.io.Serializable;

/**
 * An enumeration of id x value.
 * 
 * @author s.santos
 *
 */
public interface EnumerationEntity extends Serializable {

	/**
	 * Returns the code of the enumeration item.
	 * @return code
	 */
	public abstract Integer getId();
	
	/**
	 * Returns the enumerated value.
	 * @return value
	 */
	public abstract String getValue();
	
}

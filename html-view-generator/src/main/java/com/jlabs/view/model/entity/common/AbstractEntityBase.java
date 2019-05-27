package com.jlabs.view.model.entity.common;

import java.io.Serializable;

/**
 * 
 * @author sebastiao.santos
 *
 */
public abstract class AbstractEntityBase implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -914226072700896689L;
	private int entityVersion;
	
	public AbstractEntityBase() {
		super();
	}

	/**
	 * @return the entityVersion
	 */
	public int getEntityVersion() {
		return entityVersion;
	}

	/**
	 * @param entityVersion the entityVersion to set
	 */
	public void setEntityVersion(int entityVersion) {
		this.entityVersion = entityVersion;
	}
	
}

package com.aaw.bean.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * 只包含ID注解的基础bean对象
 * 
 * @author YangH
 * 
 */
@MappedSuperclass
public abstract class BaseBean {

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 11)
	protected Integer id;

	public BaseBean() {
	}

	public BaseBean(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
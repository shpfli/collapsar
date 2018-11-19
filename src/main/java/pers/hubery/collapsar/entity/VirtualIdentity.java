/*
 * Copyright (c) 2018. Hubery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package pers.hubery.collapsar.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * The type Virtual identity.
 *
 * @author hubery
 */
@Entity
@Table(name = "virtual_identity")
public class VirtualIdentity {

	/** 虚拟身份ID，物理主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 虚拟世界 */
	@Column
	private String world;

	/** 虚拟身份 */
	@Column
	private String identity;

	/** 密码 */
	@Column
	private String password;

	/** 备注 */
	@Column
	private String comment;

	/** 记录创建时间 */
	@Column
	private Date createTime;

	/** 记录修改时间 */
	@Column
	private Date modifiedTime;

	/**
	 * Getter method for property <tt>id</tt>.
	 *
	 * @return property value of id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter method for property <tt>id</tt>.
	 *
	 * @param id value to be assigned to property id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter method for property <tt>world</tt>.
	 *
	 * @return property value of world
	 */
	public String getWorld() {
		return world;
	}

	/**
	 * Setter method for property <tt>world</tt>.
	 *
	 * @param world value to be assigned to property world
	 */
	public void setWorld(String world) {
		this.world = world;
	}

	/**
	 * Getter method for property <tt>identity</tt>.
	 *
	 * @return property value of identity
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * Setter method for property <tt>identity</tt>.
	 *
	 * @param identity value to be assigned to property identity
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * Getter method for property <tt>password</tt>.
	 *
	 * @return property value of password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method for property <tt>password</tt>.
	 *
	 * @param password value to be assigned to property password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter method for property <tt>comment</tt>.
	 *
	 * @return property value of comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Setter method for property <tt>comment</tt>.
	 *
	 * @param comment value to be assigned to property comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Getter method for property <tt>createTime</tt>.
	 *
	 * @return property value of createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Setter method for property <tt>createTime</tt>.
	 *
	 * @param createTime value to be assigned to property createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * Getter method for property <tt>modifiedTime</tt>.
	 *
	 * @return property value of modifiedTime
	 */
	public Date getModifiedTime() {
		return modifiedTime;
	}

	/**
	 * Setter method for property <tt>modifiedTime</tt>.
	 *
	 * @param modifiedTime value to be assigned to property modifiedTime
	 */
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("world", world).append("identity", identity).append("password", password)
				.append("comment", comment).append("createTime", createTime).append("modifiedTime", modifiedTime).toString();
	}
}

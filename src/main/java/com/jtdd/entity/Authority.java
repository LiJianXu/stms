package com.jtdd.entity;
// Generated 2017-11-4 17:55:03 by Hibernate Tools 4.0.1.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Authority generated by hbm2java
 */
@Entity
@Table(name = "authority", catalog = "stms")
public class Authority implements java.io.Serializable {
	
	private Integer authId;
	private String authName;
	private String authDescribe;
	private String resourceUrl;
	@JsonBackReference
	private Set<RoleAuthRelation> roleAuthRelations = new HashSet<RoleAuthRelation>(0);

	public Authority() {
	}

	public Authority(Integer authId, String authName, String authDescribe, String resourceUrl) {
		super();
		this.authId = authId;
		this.authName = authName;
		this.authDescribe = authDescribe;
		this.resourceUrl = resourceUrl;
	}

	public Authority(String resourceUrl,String authName, String authDescribe) {
		this.resourceUrl=resourceUrl;
		this.authName = authName;
		this.authDescribe = authDescribe;
	}

	public Authority(String resourceUrl, String authName, String authDescribe,
			Set<RoleAuthRelation> roleAuthRelations) {
		this.resourceUrl=resourceUrl;
		this.authName = authName;
		this.authDescribe = authDescribe;
		this.roleAuthRelations = roleAuthRelations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "auth_id", unique = true, nullable = false)
	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	@Column(name = "auth_name", nullable = false, length = 20)
	public String getAuthName() {
		return this.authName;
	}


	public void setAuthName(String authName) {
		this.authName = authName;
	}
	
	@Column(name = "resource_url", nullable = false, length = 255)
	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	
	@Column(name = "auth_describe", nullable = false, length = 200)
	public String getAuthDescribe() {
		return this.authDescribe;
	}

	public void setAuthDescribe(String authDescribe) {
		this.authDescribe = authDescribe;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authority")
	public Set<RoleAuthRelation> getRoleAuthRelations() {
		return this.roleAuthRelations;
	}

	public void setRoleAuthRelations(Set<RoleAuthRelation> roleAuthRelations) {
		this.roleAuthRelations = roleAuthRelations;
	}
	
}
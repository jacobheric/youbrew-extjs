package com.jacobheric.youbrew.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "yeast")
public class Yeast implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;

	private String description;

	private String name;

	private short attenuation;

	private static final long serialVersionUID = 1L;

	public Yeast() {
		super();
	}

	public Yeast(String name) {
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAttenuation() {
		return this.attenuation;
	}

	public void setAttenuation(short attenuation) {
		this.attenuation = attenuation;
	}

}

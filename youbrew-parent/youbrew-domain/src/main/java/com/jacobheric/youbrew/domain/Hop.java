package com.jacobheric.youbrew.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "hop")
public class Hop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String origin;

	private String name;

	private String description;

	private String alpha;

	private static final long serialVersionUID = 1L;

	public Hop() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAlpha() {
		return this.alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

}

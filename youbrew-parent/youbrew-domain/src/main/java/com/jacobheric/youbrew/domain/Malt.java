package com.jacobheric.youbrew.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "malt")
public class Malt implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String origin;

	private double potential;

	private String description;

	private String name;

	private short color;

	private double yield;

	private static final long serialVersionUID = 1L;

	public Malt() {
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

	public double getPotential() {
		return this.potential;
	}

	public void setPotential(double potential) {
		this.potential = potential;
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

	public short getColor() {
		return this.color;
	}

	public void setColor(short color) {
		this.color = color;
	}

	public double getYield() {
		return this.yield;
	}

	public void setYield(double yield) {
		this.yield = yield;
	}

}

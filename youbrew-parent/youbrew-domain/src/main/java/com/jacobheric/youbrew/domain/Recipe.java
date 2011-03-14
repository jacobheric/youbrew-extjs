package com.jacobheric.youbrew.domain;

import com.jacobheric.youbrew.domain.Hop;
import com.jacobheric.youbrew.domain.Malt;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;

	private String name;

	@Column(name = "brew_notes")
	private String brewNotes;

	private Timestamp start;

	private Timestamp end;

	@Column(name = "taste_notes")
	private String tasteNotes;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "recipe_hops",
		  joinColumns = @JoinColumn(name = "recipe_id"),
		  inverseJoinColumns = @JoinColumn(name = "hop_id")
	)
	private Set<Hop> hops = new HashSet<Hop>();

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "recipe_malts",
		  joinColumns = @JoinColumn(name = "recipe_id"),
		  inverseJoinColumns = @JoinColumn(name = "malt_id")
	)
	private Set<Malt> malts = new HashSet<Malt>();

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Yeast yeast;

	@Transient
	private String yeastName;  //convenience, view helper

	private static final long serialVersionUID = 1L;

	public Recipe() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrewNotes() {
		return this.brewNotes;
	}

	public void setBrewNotes(String brewNotes) {
		this.brewNotes = brewNotes;
	}

	public Timestamp getStart() {
		return this.start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return this.end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public String getTasteNotes() {
		return this.tasteNotes;
	}

	public void setTasteNotes(String tasteNotes) {
		this.tasteNotes = tasteNotes;
	}

	public Yeast getYeast() {
		return this.yeast;
	}

	public void setYeast(Yeast yeast) {
		this.yeast = yeast;
	}

	public String getYeastName() {
		return yeastName;
	}

	public void setYeastName(String yname) {
		this.yeastName = yname;
	}
}

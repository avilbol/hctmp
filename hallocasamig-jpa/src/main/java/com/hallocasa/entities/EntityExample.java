package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.Tables;

/**
 * Entidad de la tabla "Example"
 * @author avillamil
 */
@Entity
@Table(name = Tables.EXAMPLE)
@NamedQueries({ @NamedQuery(name = EntityExample.QUERY_FIND_BY_IDENTIFIER, 
	query = "select e from EntityExample e where e.identifier = ?1"), })
public class EntityExample implements HallocasaEntity {

	public static final String QUERY_FIND_BY_IDENTIFIER = "EntityExample.queryFindByIdentifier";
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Long identifier;

    @Column(name = "description")
    private String description;

	public EntityExample(Long identifier, String description) {
		super();
		this.identifier = identifier;
		this.description = description;
	}

	public EntityExample() {
		super();
	}

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

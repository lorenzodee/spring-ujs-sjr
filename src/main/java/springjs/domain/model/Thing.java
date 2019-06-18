package springjs.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="things")
public class Thing {
	
	@Id
	@GeneratedValue(generator="uuid2")
	@Column(columnDefinition="BINARY(16)")
	@org.hibernate.annotations.GenericGenerator(name="uuid2", strategy="uuid2")
	private UUID id;
	@NotBlank
	@Column(length=50, nullable=false)
	private String name;
	@Column(length=140)
	private String description;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

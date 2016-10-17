

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@MappedSuperclass
//@UuidGenerator(name="ID_GEN")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public abstract class AbstractMasterEntity {
	
	@Id
	//@GeneratedValue(generator="ID_GEN")
	private String id;
	
	private String descr;
	
	@Version
	private long version;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1105516472830451679L;

	public AbstractMasterEntity() {
		// TODO Auto-generated constructor stub
	}
	/*
	@Override
	public String toString() {
		// FIXME there is an issue with recursive many to many relationships
		return new Gson().toJson(this);
	}
	*/
	
	@Override
	public String toString() {
		// FIXME there is an issue with recursive many to many relationships
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		}
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}	
	
	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}
}

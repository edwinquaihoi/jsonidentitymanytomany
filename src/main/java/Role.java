

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Cacheable(false)
public class Role extends AbstractMasterEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3650640336695648089L;
	
	@ManyToMany(mappedBy="roles")
	private Set<Group> groups;
	
	@ManyToMany(mappedBy="roles")
	@JsonIgnore
	private Set<User> users;
	
	public Set<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}

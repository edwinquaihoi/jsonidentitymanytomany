

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="_GROUP")
@Cacheable(false)
public class Group extends AbstractMasterEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3650640336695648089L;
	
	@ManyToMany
	private Set<Role> roles;
	
	@ManyToMany(mappedBy="groups")
	@JsonIgnore
	private Set<User> users;

	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}

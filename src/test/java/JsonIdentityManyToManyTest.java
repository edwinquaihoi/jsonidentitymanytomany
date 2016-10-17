import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonIdentityManyToManyTest {

	/**
	 * This test works as I am reusing objects.
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test
	public void testWithObjectReuse() throws JsonParseException, JsonMappingException, IOException {
		// create a group
		Group group = new Group();
		group.setId("GROUP");

		// a group which is not related to another role
		Group group2 = new Group();
		group2.setId("GROUP2");
		
		// create a role
		Role role = new Role();
		role.setId("ROLE");
		
		// link roles to group
		group.setRoles(new HashSet<Role>());
		group.getRoles().add(role);
		
		//link group to roles
		role.setGroups(new HashSet<Group>());
		role.getGroups().add(group);
				
		// create a user
		User user = new User();
		user.setId("USER");
				
		// link roles & groups to user
		user.setGroups(new HashSet<Group>());
		user.getGroups().add(group);
		user.getGroups().add(group2);
		user.setRoles(new HashSet<Role>());
		user.getRoles().add(role);

		// output to string works here
		System.out.println(user);
		System.out.println("=============================================================");
				
		// store the User json for desrialization
		String userString = user.toString();
		
		// deserialize works here
		User u2 = new ObjectMapper().readValue(userString, User.class);
	}
	
	/**
	 * This test does not work as I am not reusing objects. I am using angular2 to generate the JSON
	 * which is having difficulties being serialized by Jackson.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test
	public void testWithNewObjects() throws JsonParseException, JsonMappingException, IOException {
		Role r1, _r1;
		Group g1, _g1;
		User u1;
		
		r1 = new Role();
		r1.setId("R1");
		
		_r1 = new Role();
		_r1.setId("R1");
		
		g1 = new Group();
		g1.setId("G1");

		_g1 = new Group();
		_g1.setId("G1");
		
		_r1.setGroups(new HashSet<Group>());
		_r1.getGroups().add(_g1);
		_g1.setRoles(new HashSet<Role>());
		_g1.getRoles().add(_r1);
		// _r1 -> _g1 & _g1 -> _r1
		
		g1.setRoles(new HashSet<Role>());
		g1.getRoles().add(r1);
		r1.setGroups(new HashSet<Group>());
		r1.getGroups().add(g1);
		// r1 -> g1 & g1 -> r1
		
		u1 = new User();
		u1.setId("U1");		
		u1.setGroups(new HashSet<Group>());
		u1.setRoles(new HashSet<Role>());
		
		u1.getGroups().add(g1);
		u1.getRoles().add(_r1);
		// u1 -> _r1 & u1 -> g1
		
		System.out.println(u1);
		
		String user = u1.toString();
		
		// deserialize doesn't work here
		User u2 = new ObjectMapper().readValue(user, User.class);
	}
}

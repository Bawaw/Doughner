package be.ibizz.hackathon.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Objects;
import java.util.Date;

/**
 * The domain class U. This Class will be used as an object to store in the
 * database. *
 */
@TypeDiscriminator("doc.type == 'user'")
@SessionAttributes
public class User extends CouchDbDocument {
	private String type;
	private String userID;
	private String username;
	private String email;
	private String password;
	private String salt; 
	private int commits;

	public int getCommits() {
		return commits;
	}

	public void setCommits(int commits) {
		this.commits = commits;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, userID, username, password, salt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		return Objects.equals(this.type, other.type)
				&& Objects.equals(this.userID, other.userID);
	}

	@Override
	  public String toString() {
	    return "User{" +
	      ", type='" + type + '\'' +
	      "  id=" + this.getId() +
	      "  userID=" + userID +
	      ", email=" + email +
	      ", username=" + username +
	      ", password=" + password +
	      ", commits=" + commits +
	      ", salt=" + salt +"}";
	  }
}
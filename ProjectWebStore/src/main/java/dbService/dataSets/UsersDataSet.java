package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "userName", unique = true, updatable = false)
    private String userName;
    
    @Column(name = "password", updatable = false)
    private String password;

    public UsersDataSet(String userName, String password) {
    	this.userName = userName;
    	this.password = password;
    }
    
    public UsersDataSet(){
    	
    }

    public UsersDataSet(long id, String userName, String password) {
        this.setId(id);
        this.setUserName(userName);
        this.setPassword(password);
    }

   /* public UsersDataSet(String name) {
        this.setId(-1);
        this.setName(name);
    }*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public void setPassword(String password){
    	this.password = password;
    }
    
    public String getPassword(){
    	return password;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + userName + '\'' + " "+ password+
                '}';
    }
}
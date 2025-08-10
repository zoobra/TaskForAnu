package nz.me.mavenproject6.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private boolean loggedIn;

    private static final String USER = System.getenv("GARAGE_SERVICE_USER");
    private static final String PASS= System.getenv("GARAGE_SERVICE_PASS");


    public String login() {
        if (USER.equals(username) && PASS.equals(password)) {
            loggedIn = true;
            return "dashboard?faces-redirect=true";
        }
        
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Invalid Credentials", null));
        return null;
    }
    
    public String logout() {
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    
}
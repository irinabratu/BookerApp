package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenGenerationCredentials {

    private String username;
    private String password;

    public TokenGenerationCredentials(String username, String password){
        this.password=password;
        this.username=username;
    }

    public TokenGenerationCredentials(){
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public TokenGenerationCredentials setUsername(String username) {
        this.username = username;
        return this;
    }

    public TokenGenerationCredentials setPassword(String password) {
        this.password = password;
        return this;
    }
}

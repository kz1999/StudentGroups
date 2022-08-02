package opp.rest;

import org.springframework.web.bind.annotation.PostMapping;

public class CreateGroupDTO {
    private String name;
    private String leadJmbag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeadJmbag() {
        return leadJmbag;
    }

    public void setLeadJmbag(String leadJmbag) {
        this.leadJmbag = leadJmbag;
    }
}

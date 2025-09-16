package it.ivan.bdn.utility;

import lombok.Data;

import java.security.Principal;

@Data
public class CustomPrincipal {
    private Principal principal;
    private String hospitalCode;

    public CustomPrincipal(Principal principal) {
        this.principal = principal;
    }
}

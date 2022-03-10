package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.interfaces.IAuthService;

import javax.inject.Inject;

public class SpotitubeService {

    protected IAuthService authService;

    @Inject
    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }
}

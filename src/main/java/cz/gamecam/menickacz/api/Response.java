/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.api;

/**
 *
 * @author Erik Juríček
 */
public class Response {

    public enum ResponseType {
        SUCCESS,
        FAILED,
        NOT_FOUND
    };

    private ResponseType responseReason;

    public ResponseType getResponseReason() {
        return responseReason;
    }

    public void setResponseReason(ResponseType responseReason) {
        this.responseReason = responseReason;
    }

}

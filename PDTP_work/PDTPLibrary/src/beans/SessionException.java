/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author eugenio
 */
public class SessionException extends Exception {

    public enum sessionStatus {

        /**
         *
         */
        LOGOUTSTAUS,
    }
    private sessionStatus status;

    public SessionException(sessionStatus status, String message) {
        super(message);
        this.status = status;
    }

    public sessionStatus getStatus() {
        return status;
    }

    public void setStatus(sessionStatus status) {
        this.status = status;
    }
}

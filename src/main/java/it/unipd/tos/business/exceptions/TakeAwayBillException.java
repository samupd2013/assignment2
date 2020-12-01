////////////////////////////////////////////////////////////////////
// Samuele De Simone 1219399
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exceptions;

public class TakeAwayBillException extends Throwable {
    private String message;

    public TakeAwayBillException(String exception){
        this.message = exception;
    }

    public String getMessage(){
        return this.message;
    }
}
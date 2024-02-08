package cft.testTask.textFilter.exceptions;

public class FileRWException extends RuntimeException{
    String reason;

    public FileRWException(String reason){
        this.reason=reason;
    }
    public FileRWException(String reason, Throwable cause){
        super(cause);
        this.reason =reason;
    }

    public String getReason(){
        return this.reason;
    }
    public void printReason(Throwable cause){
        System.out.println(reason + "/n " + cause);
    }
}

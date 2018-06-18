package chat;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    /*Deserializer uses this field to determinate
     * if bytes that are representing object are in the same version as used definition. */
    private static final long serialVersionUID = 1L;

    private String author;
    private String message;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

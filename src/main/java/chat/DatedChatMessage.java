package chat;

import java.util.Date;

public class DatedChatMessage extends ChatMessage {
    private static final long serialVersionUID = 1L;

    private Date receiveDate;

    public DatedChatMessage(ChatMessage chatMessage) {
        setMessage(chatMessage.getMessage());
        setAuthor(chatMessage.getAuthor());
        this.receiveDate = new Date();
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
}

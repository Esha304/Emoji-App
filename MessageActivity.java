package org.meicode.firebase;

public class MessageActivity {
    String senderUsername;
    String senderEmail;
    String receiver;
    int img_src;

  public MessageActivity(String senderUsername,String senderEmail, String receiver, int getEmoji){
    this.senderEmail = senderEmail;
    this.senderUsername = senderUsername;
    this.receiver = receiver;
    this.img_src = getEmoji;
  }

    public String getSenderUsername() {
      return senderUsername;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getReceiver() {
      return receiver;
    }

    public int getImg_src() {
        return img_src;
    }
}
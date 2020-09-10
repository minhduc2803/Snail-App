package bla.nah.example.entity.request;

import lombok.Getter;

@Getter
public class MessageRequest {
  private String sender;
  private String receiver;
  private String message;
}

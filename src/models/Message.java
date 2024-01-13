package models;

public class Message {
  private int type;
  private int id;
  private String objReference;
  private String methodId;
  private byte[] arguments;

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getObjReference() {
    return objReference;
  }

  public void setObjReference(String objReference) {
    this.objReference = objReference;
  }

  public String getMethodId() {
    return methodId;
  }

  public void setMethodId(String methodId) {
    this.methodId = methodId;
  }
  
  public byte[] getArguments() {
    return arguments;
  }

  public void setArguments(byte[] arguments) {
    this.arguments = arguments;
  }

}

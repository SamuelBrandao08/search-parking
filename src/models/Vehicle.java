package models;

public class Vehicle {
  
  private String id;
  private String model;
  private String brand;
  private String color;
  private String year;
  private String ownerId;

  public Vehicle(String id, String model, String brand, String color, String year, String ownerId){

    this.id = id;
    this.model = model;
    this.brand = brand;
    this.color = color;
    this.year = year;
    this.ownerId = ownerId;
    
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }
  
  public String addVehicle(Vehicle vehicle) {
	  Vehicle v = new Vehicle(id, model, brand, color, year, ownerId);
	
	  v.setId(vehicle.getId());
	  v.setModel(vehicle.getModel());
	  v.setBrand(vehicle.getBrand());
	  v.setColor(vehicle.getColor());
	  v.setYear(vehicle.getYear());
	  v.setOwnerId(vehicle.getOwnerId());
		
	  return "CARRO CADASTRADO   |   id: " + v.getId();
  }
  
}

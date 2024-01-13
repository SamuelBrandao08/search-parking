package models;

import java.util.ArrayList;
import java.util.List;

public class Park {

  private int id;
  private String local;
  private Boolean ocuped;

  
  List<Park> vagas = new ArrayList<Park>();

  public Park(int id, String local){
    this.id = id;
    this.local = local;
    this.ocuped = false;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public Boolean getOcuped() {
    return ocuped;
  }

  public void setOcuped(Boolean ocuped) {
    this.ocuped = ocuped;
  }
  
  public List<Park> listParkFree() {
	  List<Park> vag = new ArrayList<Park>();
    for(int i = 0; i < 5; i++){
      Park p = new Park(i, "LOCAL " + i);
      p.setOcuped(false);
      vag.add(p);
    }
    return vag;
  }
  
  public List<Park> listForLocal(String local) {
	  List<Park> vagasLocal = new ArrayList<Park>();
    List<Park> vagasEncontradas = new ArrayList<Park>();

    for(int i = 0; i < 5; i++){
      Park p = new Park(i, "LOCAL" + String.valueOf(i));
      p.setOcuped(false);
      vagasLocal.add(p);
    }

    for (Park p : vagasLocal) {
      if(p.getLocal().equals(local)){
        vagasEncontradas.add(p);
      }
    }

    return vagasEncontradas;
  }
  
  public Park parking(int parkId) {
    

    List<Park> vag = new ArrayList<Park>();
    for(int i = 0; i < 10; i++){
      Park p = new Park(i, "LOCAL " + i);
      p.setOcuped(false);
      vag.add(p);
    }

    Park newV = vag.get(parkId);

    if(vag.contains(newV)){
      newV.setOcuped(true);
      vag.set(parkId, newV);
      this.vagas = vag;
      return newV;
    }

    return null;
  }


}

package org.paulobichara.leitorescritor;

import java.lang.*;

public class Processo {

  private int PID;
  private String tipo;

  public Processo(int id, String tp) {
    PID = id;
    tipo = tp;
  }

  public int getPID() {
    return PID;
  }

  public String getTipo() {
    return tipo;
  }

}

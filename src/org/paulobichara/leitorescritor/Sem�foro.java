package org.paulobichara.leitorescritor;

public class Semáforo {
  private int mutex;
  private int contadorLeitores;

  public Semáforo() {
    mutex = 1;
    contadorLeitores = 0;
  }

  public boolean downSemaforo() {
    boolean conseguiu = false;
    if (mutex == 1) {
      mutex = 0;
      conseguiu = true;
    }
    return conseguiu;
  }

  public void upSemaforo() {
    if (mutex == 0) {
      mutex = 1;
    }
  }

  public void decresceContadorL() {
    contadorLeitores = contadorLeitores - 1;
  }

  public void cresceContadorL() {
    contadorLeitores = contadorLeitores + 1;
  }


  public int getContadorL() {
    return contadorLeitores;
  }

}

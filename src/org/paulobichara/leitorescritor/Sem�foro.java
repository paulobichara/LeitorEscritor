/*
   Copyright 2011 Paulo Augusto Dacach Bichara

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

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

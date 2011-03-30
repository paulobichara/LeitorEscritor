package org.paulobichara.leitorescritor;

import java.util.*;

public class Sistema {
  private ArrayList processos;
  private ArrayList filaProcessos;
  private ArrayList leitoresAtivos;
  private Processo escritorAtivo;
  private Semáforo semaforo;

  public Sistema() {
    processos = new ArrayList();
    filaProcessos = new ArrayList();
    leitoresAtivos = new ArrayList();
    escritorAtivo = null;
    semaforo = new Semáforo();
  }

  public boolean checaPID(int PID) {
    boolean achou = false;
    for (int i = 0; ((i < processos.size()) && (achou != true)); i++) {
      if (PID == ((Processo)processos.get(i)).getPID()) {
        achou = true;
      }
    }
    return achou;
  }

  public int buscaProcesso(int PID, ArrayList lista) {
    Processo proc = null;
    boolean achou = false;
    int indice = -1, i;
    for (i = 0; ((i < lista.size()) && (achou != true)); i++) {
      proc = (Processo)lista.get(i);
      if (proc.getPID() == PID) {
        achou = true;
        indice = i;
      }
    }
    return indice;
  }

  public boolean addProcesso(int PID, String tipo) {
    Processo p = new Processo(PID, tipo);
    boolean conseguiu;
    processos.add(p);
    if (tipo.equals("Escritor")) {
      conseguiu = requerirEscrita(p);
    } else {
      conseguiu = requerirLeitura(p);
    }
    if (conseguiu == false) {
      filaProcessos.add(p);
    }
    return conseguiu;
  }

  public boolean requerirLeitura(Processo leitor) {
    boolean concessao, conseguiu = false;
    concessao = semaforo.downSemaforo();
    if (concessao == true) {
      leitoresAtivos.add(leitor);
      semaforo.cresceContadorL();
      conseguiu = true;
    } else {
      if (filaProcessos.size() != 0) {
        String tipo = ((Processo)filaProcessos.get(0)).getTipo();
        if ((semaforo.getContadorL() > 0) && (tipo.equals("Leitor"))) {
          leitoresAtivos.add(leitor);
          semaforo.cresceContadorL();
          conseguiu = true;
        }
      } else {
        if (semaforo.getContadorL() > 0){
          leitoresAtivos.add(leitor);
          semaforo.cresceContadorL();
          conseguiu = true;
        }
      }
    }
    return conseguiu;
  }

  public boolean requerirEscrita(Processo escritor) {
    boolean concessao = semaforo.downSemaforo();
    if (concessao == true) {
      escritorAtivo = escritor;
    }
    return concessao;
  }

  public void manutencaoLista() {
    boolean pararManut = false, concessao;
    Processo proc;
    int i = 0;
    if (filaProcessos.size() != 0) {
      do {
        proc = (Processo) filaProcessos.get(i);
        if (proc.getTipo().equals("Escritor")) {
          concessao = requerirEscrita(proc);
        }
        else {
          concessao = requerirLeitura(proc);
        }
        if (concessao == true) {
          filaProcessos.remove(i);
        }
      }
      while ( (concessao != false) && (filaProcessos.size() != 0));
    }
  }

  public void excluirProcesso(int PID) {
    int indiceFila, indiceProcs, indiceLeitAt;
    indiceProcs = buscaProcesso(PID, processos);
    indiceFila = buscaProcesso(PID, filaProcessos);
    indiceLeitAt = buscaProcesso(PID, leitoresAtivos);
    if (indiceFila != -1) {
      processos.remove(indiceProcs);
      filaProcessos.remove(indiceFila);
    } else {
      if ((indiceLeitAt != -1) && (semaforo.getContadorL() == 1)) {
        leitoresAtivos.remove(indiceLeitAt);
        processos.remove(indiceProcs);
        semaforo.decresceContadorL();
        semaforo.upSemaforo();
      } else {
        if ((indiceLeitAt != -1) && (semaforo.getContadorL() > 1)) {
          leitoresAtivos.remove(indiceLeitAt);
          processos.remove(indiceProcs);
          semaforo.decresceContadorL();
        } else {
          if (((Processo)processos.get(indiceProcs)).getPID() == escritorAtivo.getPID()) {
            escritorAtivo = null;
            processos.remove(indiceProcs);
            semaforo.upSemaforo();
          }
        }
      }
    }
    manutencaoLista();
  }

  public ArrayList getLeitoresAtivos() {
    return leitoresAtivos;
  }

  public Processo getEscritorAtivo() {
    return escritorAtivo;
  }

  public ArrayList getProcessosBloqueados() {
    return filaProcessos;
  }

  public ArrayList getProcessos() {
    return processos;
  }
}

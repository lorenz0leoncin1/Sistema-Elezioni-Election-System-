# Sistema Elettorale(Election System)

---

## Descrizione del Progetto
Il progetto consiste nella realizzazione di un sistema di voto e scrutinio elettronico. Il sistema deve essere progettato in modo generico per supportare diverse modalità di voto e definizione del vincitore.

### Modalità di Voto Supportate
1. **Voto Ordinale:**
   - L'elettore ordina i candidati (o gruppi/partiti) presenti nella scheda in base alle proprie preferenze.

2. **Voto Categorico:**
   - L'elettore inserisce una preferenza per un candidato (o gruppo/partito).

3. **Voto Categorico con Preferenze:**
   - L'elettore inserisce una preferenza per un gruppo/partito e può indicare una o più preferenze tra i candidati del gruppo/partito selezionato (senza voto disgiunto).

4. **Referendum:**
   - Domanda fatta all'elettorato con risposta affermativa o negativa.

### Modalità di Definizione del Vincitore
1. **Maggioranza:**
   - Il vincitore è il candidato che ha ottenuto il maggior numero di voti.

2. **Maggioranza Assoluta:**
   - Il vincitore è il candidato che ha ottenuto la maggioranza assoluta dei voti (50% + 1).

3. **Referendum Senza Quorum:**
   - Il conteggio dei voti avviene indipendentemente dalla partecipazione della maggioranza degli aventi diritto al voto.

4. **Referendum con Quorum:**
   - Il conteggio dei voti avviene solo se la maggioranza degli aventi diritto al voto partecipa alla consultazione.

### Tipologie di Utente
1. **Elettore:**
   - Può esprimere il voto di persona in un seggio elettorale o a distanza.
   - La fase di identificazione e verifica del diritto di voto può essere manuale o automatizzata.

2. **Impiegato/Gestore del Sistema:**
   - Configura una sessione di voto.
   - Specifica modalità di voto e calcolo del vincitore.
   - Inserisce le liste dei candidati.
   - Avvia la fase di scrutinio.
   - Visualizza l'esito del voto.

### Requisiti Essenziali
1. Il voto espresso deve rimanere segreto e non deve essere riconducibile all'elettore.
2. Ogni elettore può votare una sola volta.
3. Per ogni scheda è ammesso un solo voto valido o l'esercizio della facoltà di astenersi dalla scelta (scheda bianca).
4. Il conteggio dei voti elettronici si attiva solo dopo la chiusura di tutte le operazioni di voto.

### Sistema di Auditing
- Il sistema di auditing è basato su un sistema di log semplificato.

---

# Documentazione Completa e Diagrammi

## PDF della Documentazione
La documentazione completa del progetto, inclusi i diagrammi che seguono il design pattern, è disponibile nel file PDF `gruppo_Capelli_Leoncini.pdf`. Il file contiene dettagliate informazioni sull'analisi e la progettazione del sistema.

## Diagrammi Disponibili
Nel PDF sono inclusi i seguenti diagrammi:
1. Diagramma dei casi d'uso.
2. Diagramma delle classi.
3. Diagramma delle attività.
4. Altri diagrammi specifici per il design pattern utilizzato.

Si prega di consultare il PDF per una visione completa e dettagliata della documentazione del progetto.

---




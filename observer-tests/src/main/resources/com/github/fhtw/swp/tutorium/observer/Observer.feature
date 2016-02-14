#language: de
#noinspection SpellCheckingInspection

Funktionalität: Observer

  Szenariogrundriss:
    Gegeben sei eine Liste von Subjekten
    Dann darf diese Liste nicht leer sein

    Szenario: Alle Subjekte sollen eine Methode zum Hinzufügen eines Beobachters bieten
      Dann sollen alle Subjekte eine Methode zum Hinzufügen bieten

    Szenario: Alle Subjekte sollen eine Methode zum Entfernen eines Beobachters bieten
      Dann sollen alle Subjekte eine Methode zum Entfernen bieten

    Szenario: Alle Subjekte sollen eine Methode zum Aktualisieren aller Beobachters bieten
      Dann sollen alle Subjekte eine Methode zum Aktualisieren bieten

    Szenario: Beobachter sollen aufgerufen werden, wenn sie registriert sind
      Gegeben sei ein Beobachter
      Wenn sich dieser Beobachter registriert
      Und die Method zum Aktualisieren aufgerufen wird
      Dann soll der Beobachter aufgerufen werden

    Szenario: Beobachter sollen nicht mehr aufgerufen werden, wenn sie sich vom Subjekt abmelden
      Gegeben sei ein Beobachter
      Wenn sich dieser Beobachter registriert
      Und sich dieser Beobachter wieder abmeldet
      Und die Method zum Aktualisieren aufgerufen wird
      Dann soll der Beobachter nicht aufgerufen worden sein
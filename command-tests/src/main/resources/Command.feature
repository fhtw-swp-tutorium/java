#language: de
#noinspection SpellCheckingInspection

Funktionalität: Kommando

  Grundlage:
    Gegeben sei eine Liste von Klassen mit dem Attribut "Invoker"
    Dann darf diese Liste nicht leer sein

    Szenario: Alle Invoker müssen genaue eine Methode mit dem Attribut "InvokeCommand" haben
      Wenn ich in jeder Klasse nach einer Methode mit dem Attribut "InvokeCommand" suche
      Dann erwarte ich mir jeweils eine Methode

    Szenario: Jede Methode mit dem Attribut "InvokeCommand" darf genau ein Interface als Parameter nehmen
      Wenn ich in jeder Klasse nach einer Methode mit dem Attribut "InvokeCommand" suche
      Dann muss jede Methode genau einen Parameter haben
      Und muss jeder Parameter ein Interface sein
      
    Szenario: Jedes Kommando-Interface muss eine Implementierung haben
      Wenn ich in jeder Klasse nach einer Methode mit dem Attribut "InvokeCommand" suche
      Dann muss es für jeden Interface Parameter eine Implementierung geben
      
    Szenario: Kommando soll ausgeführt werden, wenn es an Invoker übergeben wird
      Wenn ich in jeder Klasse nach einer Methode mit dem Attribut "InvokeCommand" suche
	  #Preconditions? (alle von oben?)
      Und ich eine Instanz des Invokers erzeuge
      Und ich eine dynamische Instanz des Kommandos erzeuge
      Und dieses Kommando an den Invoker übergebe
      Dann soll mindestens eine Methode des Kommandos aufgerufen werden

#language: de
#noinspection SpellCheckingInspection

Funktionalität: Dekorator

  Szenario: Angegebener Komponententyp eines Dekorators muss ein Interface sein
    Gegeben sei der Dekorator-Typ
    Dann muss der angegebene Komponententyp ein Interface sein

  Szenario: Ein Decorator muss den angegebenen Komponenententype implementieren
    Gegeben sei der Dekorator-Typ
    Dann muss der Typ den angegebenen Komponententyp implementieren

  Szenario: Ein Decorator muss ein Feld haben, welches mit "Component" annotiert ist
    Gegeben sei das Attribut "Component"
    Dann erwarte ich mir ein Feld welches mit diesem Attribut annotiert ist

  Szenario: Der Typ des annotierten Feldes muss dem Komponententyp entsprechen
    Gegeben sei das annotierte Feld
    Dann muss der Typ des Feldes dem angebenen Komponententyp entsprechen

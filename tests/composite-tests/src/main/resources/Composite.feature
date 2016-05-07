#language: de
#noinspection SpellCheckingInspection

Funktionalität: Kompositum

  Szenario: Angegebener Komponententyp eines Composites muss ein Interface sein
    Gegeben sei der Composite-Typ
    Dann muss der angegebene Komponententyp ein Interface sein

  Szenario: Ein Composite muss den angegebenen Komponenententype implementieren
    Gegeben sei der Composite-Typ
    Dann muss der Type den angegebenen Komponententyp implementieren

  Szenario: Ein Composite muss genaue eine Methode mit dem Attribut "ComponentOperation" haben
    Gegeben sei das Attribut "ComponentOperation"
    Dann erwarte ich mir genau eine Methode die mit diesem Attribut annotiert ist

  Szenario: Ein Composite muss genau eine Methode mit dem Attribut "AddComposite" haben
    Gegeben sei das Attribut "AddComposite"
    Dann erwarte ich mir genau eine Methode die mit diesem Attribut annotiert ist

  Szenario: Die Methode zum Hinzufügen einer Komponente zu einem Composite muss genau einen Parameter haben
    Gegeben sei die Methode zum Hinzufügen einer Komponente
    Dann erwarte ich mir eine Methode mit genau einem Parameter

  Szenario: Der Parameter der Methode zum Hinzufügen einer Komponente muss dem Komponententype entsprechen
    Gegeben sei die Methode zum Hinzufügen einer Komponente
    Dann erwarte ich mir eine Methode deren Parameter dem Komponententyp entspricht

  Szenario: Es muss mindestens ein Leaf geben
    Gegeben sei der Composite-Typ
    Dann erwarte ich mir mindestens ein Leaf

  Szenario: Wenn ich die ComponentOperation aufrufe, soll die ComponentOperation aller hinzugefügten Components aufgerufen werden
    Wenn ich eine Instanz des Composites erzeuge
    Und ich eine dynamische Component-Instanz erzeuge
    Und ich diese Instanz zu dem Composite hinzufüge
    Und ich die ComponentOperation des Composites aufrufe
    Dann soll die ComponentOperation der dynamischen Component-Instanz aufgerufen werden
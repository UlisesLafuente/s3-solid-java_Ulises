# Solución

La clase InstrumentPlayer confiaba de un modo frágil y propenso a errores la identificación de cada instrumento a una cadena de texto. He creado una clase abstracta Instrument y extraido el comportamiento específico de cada intrumento a subclases propias. De esta manera aseguro el polimorfismo y la expansión de comportamiento.
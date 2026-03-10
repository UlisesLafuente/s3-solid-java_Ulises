# Solución: 

El código de la clase ServicePerson dependía de la implementación explicita del método savePerson de la clase MySql. He creado una clase interface llamada PersonDataControl con un método abstracto savePerson, depués he implementado este interface en la clase MySql. El resultado es que la clase ServicePerson recoge en sus parámetros cualquier implementación de PersonDataControl.
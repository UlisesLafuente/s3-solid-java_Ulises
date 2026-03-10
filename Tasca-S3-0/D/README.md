# D - Principio de inversión de dependencias (DIP)

## ¿Qué es?

El **principio de inversión de dependencias (DIP)** establece que:

> **Las clases deben depender de abstracciones, no de clases concretas.**

En otras palabras, el código debe **basarse en interfaces o clases abstractas, no en implementaciones específicas**. Esto permite **desacoplar** las diferentes partes del sistema, lo que **facilita el cambio, la sustitución y la reutilización** de los componentes.

Esto se puede resumir en dos reglas clave:
- 1 **Los módulos de alto nivel** (lógica de negocio) **no deben depender de los de bajo nivel** (implementaciones).
- 2 **Ambos deben depender de abstracciones**.

## ¿Por qué es importante?
Sin DIP, los componentes de alto nivel pueden quedar **fuertemente acoplados a implementaciones concretas**, lo que hace que el sistema sea **difícil de modificar, probar o ampliar**.

Cuando se aplica DIP correctamente:

- El código es más flexible y fácil de probar.
- Las implementaciones se pueden intercambiar sin afectar a la lógica central.
- Fomenta la inyección de dependencias y la programación basada en la abstracción.

### **Ejemplo:**

Supongamos que tienes una aplicación que gestiona notificaciones, y estas siempre se envían por correo electrónico:

```java
public class EmailService {
    public void enviarEmail(String missatge) {
        System.out.println("Enviant email: " + missatge);
    }
}
```
```java
public class GestorNotificacions {
    private EmailService serveiEmail;

    public GestorNotificacions() {
        this.serveiEmail = new EmailService();
    }

    public void notificar(String missatge) {
        serveiEmail.enviarEmail(missatge);
    }
}
```

Problema:
`GestorNotificacions` depende directamente de `EmailService` (una implementación concreta). Si se desea añadir otros canales como SMS, WhatsApp o Push, sería necesario modificar la clase.

Esto viola el **OCP** y también el **DIP**.

Solución con DIP:

- ** Crear una abstraccion (interfaz) para el servicio de notificaciones**

```java
public interface CanalNotificacio {
    void enviar(String missatge);
}

```
- ** Hacer que EmailService implemente la interfaz:**

```java
public class EmailService implements CanalNotificacio {
    @Override
    public void enviar(String missatge) {
        System.out.println("Enviant email: " + missatge);
    }
}
```
- ** Modificar GestorNotificacions para que dependa de la abstracción:**

```java
public class GestorNotificacions {
    private CanalNotificacio canal;

    public GestorNotificacions(CanalNotificacio canal) {
        this.canal = canal;
    }

    public void notificar(String missatge) {
        canal.enviar(missatge);
    }
}

```
- ** Ahora puedes inyectar diferentes canales sin modificar GestorNotificacions:**

```java
public class Main {
    public static void main(String[] args) {
        CanalNotificacio canal = new EmailService(); // o new SmsService(), new PushService()...
        GestorNotificacions gestor = new GestorNotificacions(canal);
        gestor.notificar("Hola món!");
    }
}
```
---

## Objetivo del ejercicio

En el archivo Java incluido en este directorio, encontrarás una clase que depende **directamente de otra clase concreta**.

Tu reto es:

1. Identificar esta dependencia directa.
2. Crear una **interfaz o abstracción** adecuada.
3. Refactorizar las clases para que **dependan de la abstracción**, no de la implementación concreta.
4. Aplicar la **inyección de dependencia** (a través del constructor, el setter o el método).
---

## Consejos para aplicar DIP

Las clases de alto nivel deben ser **independientes de los detalles técnicos**.

Utiliza **interfaces o clases abstractas** para desacoplar.

Aplica patrones como **inyección de dependencia (DI) o fábrica**.

Escribir pruebas unitarias es mucho más fácil cuando se aplica **DIP**.

---

## Reflexión

Cuando sigues el principio **DIP**:
- Tu código se vuelve **modular y fácil de mantener**.
- **Se pueden añadir nuevas funciones sin romper las existentes**.
- Fomentas un sistema más limpio, **más fácil de probar y más escalable**.

**Cambia las implementaciones, no los diseños**.

---

¡Adelante! Refactoriza teniendo en cuenta el principio DIP y mejora la estructura de tu código.

**¿Dependes de clases concretas? ¿Cómo podrías invertir esta dependencia?**
 



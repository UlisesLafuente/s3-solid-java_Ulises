public class Ghost extends Character {
    @Override
    public void attack() {
        System.out.println("The ghost casts a spooky spell.");
    }

    @Override
    public void takeDamage(int points) {
        //throw new UnsupportedOperationException("A ghost cannot take physical damage!");
        //En vez de arrojar excepción, la subclase Ghost debe implementar un comportamiento consistente con su superclase para no romper el LSP.
        System.out.println("The ghost is inmunne to physical attacks.");
    }
}

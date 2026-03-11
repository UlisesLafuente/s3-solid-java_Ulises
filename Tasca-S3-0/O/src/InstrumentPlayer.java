public class InstrumentPlayer {
    public void play(Instrument instrument) {
        try{
            instrument.play();
        } catch(RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        InstrumentPlayer player = new InstrumentPlayer();
        Instrument guitar = new Guitar();
        Instrument drums = new Drums();
        Instrument piano = new Piano();
        player.play(guitar);
        player.play(drums);
        player.play(piano);
    }
}

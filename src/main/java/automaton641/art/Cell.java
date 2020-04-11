package automaton641.art;

public class Cell {
    public int level;
    public int tickModulus;
    public int tickIndex;
    public void tick() {
        level++;
        level %= App.modulus;
    }
    public void tryTick() {
        if (tickIndex == tickModulus-1 ){
            tick();
        }
        tickIndex++;
        tickIndex %= tickModulus;
    }
}
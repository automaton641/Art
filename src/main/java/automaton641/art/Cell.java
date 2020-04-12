package automaton641.art;

public class Cell {
    public int level;
    public int tickModulus;
    public int tickPower;
    public int tickIndex;
    public int value;
    public int tickMoulusIndex;
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
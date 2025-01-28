package victorgponce.com.Objects;

public class Move {

    private final int id;
    private final String name;
    private final String type;
    private final int power;
    private final int accuracy;
    private final int pp;
    private final String damageClass;
    private final String effect;

    public Move(int id,
                String name,
                String type,
                int power,
                int accuracy,
                int pp,
                String damageClass,
                String effect) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.damageClass = damageClass;
        this.effect = effect;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getPower() { return power; }
    public int getAccuracy() { return accuracy; }
    public int getPp() { return pp; }
    public String getDamageClass() { return damageClass; }
    public String getEffect() { return effect; }
}
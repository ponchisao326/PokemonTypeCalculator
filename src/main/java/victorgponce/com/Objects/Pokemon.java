package victorgponce.com.Objects;

import java.util.Arrays;

public class Pokemon {
    /* ----------------------------- */
    // Datos Base (Desde CSV/API)
    private final int POKEDEX_NUMBER;
    private final String NAME;
    private final String[] TYPES;
    private final int BASE_HP;
    private final int BASE_ATTACK;
    private final int BASE_DEFENSE;
    private final int BASE_SP_ATTACK;
    private final int BASE_SP_DEFENSE;
    private final int BASE_SPEED;

    /* ----------------------------- */
    // Parámetros de Entrenador
    private int level;
    private Move[] moves = new Move[4];

    // IVs (Inmutables)
    private final int HP_IV;
    private final int ATTACK_IV;
    private final int DEFENSE_IV;
    private final int SP_ATTACK_IV;
    private final int SP_DEFENSE_IV;
    private final int SPEED_IV;

    // EVs (Editables)
    private int hpEV;
    private int attackEV;
    private int defenseEV;
    private int spAttackEV;
    private int spDefenseEV;
    private int speedEV;

    // Constructor principal
    public Pokemon(
            int pokedexNumber,
            String name,
            String[] types,
            int baseHp,
            int baseAttack,
            int baseDefense,
            int baseSpAttack,
            int baseSpDefense,
            int baseSpeed,
            int level,
            int hpIV, int attackIV, int defenseIV,
            int spAttackIV, int spDefenseIV, int speedIV,
            int hpEV, int attackEV, int defenseEV,
            int spAttackEV, int spDefenseEV, int speedEV) {

        // Validaciones
        validateIVs(hpIV, attackIV, defenseIV, spAttackIV, spDefenseIV, speedIV);
        validateEVs(hpEV, attackEV, defenseEV, spAttackEV, spDefenseEV, speedEV);

        // Asignación de datos base
        this.POKEDEX_NUMBER = pokedexNumber;
        this.NAME = name;
        this.TYPES = types;
        this.BASE_HP = baseHp;
        this.BASE_ATTACK = baseAttack;
        this.BASE_DEFENSE = baseDefense;
        this.BASE_SP_ATTACK = baseSpAttack;
        this.BASE_SP_DEFENSE = baseSpDefense;
        this.BASE_SPEED = baseSpeed;

        // Asignación de IVs/EVs
        this.HP_IV = hpIV;
        this.ATTACK_IV = attackIV;
        this.DEFENSE_IV = defenseIV;
        this.SP_ATTACK_IV = spAttackIV;
        this.SP_DEFENSE_IV = spDefenseIV;
        this.SPEED_IV = speedIV;

        this.hpEV = hpEV;
        this.attackEV = attackEV;
        this.defenseEV = defenseEV;
        this.spAttackEV = spAttackEV;
        this.spDefenseEV = spDefenseEV;
        this.speedEV = speedEV;

        this.level = level;
    }

    /* ----------------------------- */
    // Métodos de Cálculo de Stats
    public int getMaxHP() {
        return calculateStat(BASE_HP, HP_IV, hpEV, level + 10);  // Offset: Nivel + 10
    }

    public int getAttack() {
        return calculateStat(BASE_ATTACK, ATTACK_IV, attackEV, 5);  // Offset: 5
    }

    public int getSpecialAttack() {
        return calculateStat(BASE_SP_ATTACK, SP_ATTACK_IV, spAttackEV, 5);
    }

    public int getDefense() {
        return calculateStat(BASE_DEFENSE, DEFENSE_IV, defenseEV, 5);
    }

    public int getSpecialDefense() {
        return calculateStat(BASE_SP_DEFENSE, SP_DEFENSE_IV, spDefenseEV, 5);
    }

    public int getSpeed() {
        return calculateStat(BASE_SPEED, SPEED_IV, speedEV, 5);
    }

    private int calculateStat(int baseStat, int iv, int ev, int offset) {
        return (int)(((2 * baseStat + iv + ((double) ev / 4)) * level) / 100.0) + offset;
    }

    /* ----------------------------- */
    // Getters y Setters
    public int getPokedexNumber() { return POKEDEX_NUMBER; }
    public String getName() { return NAME; }
    public String[] getTypes() { return TYPES; }

    public int getLevel() { return level; }
    public void setLevel(int level) {
        if(level < 1 || level > 100) throw new IllegalArgumentException("Nivel inválido");
        this.level = level;
    }

    public void setMove(int index, Move move) {
        if (index < 0 || index >= moves.length) {
            throw new IllegalArgumentException("Index out of range");
        }
        moves[index] = move;
    }

    public Move getmove(int index) {
        if (index < 0 || index >= moves.length) {
            throw new IllegalArgumentException("Index out of range");
        }
        return moves[index];
    }

    // EVs
    public int getHpEV() { return hpEV; }
    public void setHpEV(int ev) {
        validateEVs(ev, 0, 0, 0, 0, 0);
        this.hpEV = ev;
    }

    public int getAttackEv() { return attackEV; }
    public void setAttackEV(int ev) {
        validateEVs(ev, 0, 0, 0, 0, 0);
        this.attackEV = ev;
    }

    public int getDefenseEV() { return hpEV; }
    public void setDefenseEV(int ev) {
        validateEVs(ev, 0, 0, 0, 0, 0);
        this.defenseEV = ev;
    }

    public int getSpAttackEV() { return hpEV; }
    public void setSpAttackEV(int ev) {
        validateEVs(ev, 0, 0, 0, 0, 0);
        this.spAttackEV = ev;
    }

    /*
    private int speedEV; */

    public int getSpDefenseEV() { return hpEV; }
    public void setSpDefenseEV(int ev) {
        validateEVs(ev, 0, 0, 0, 0, 0);
        this.spDefenseEV = ev;
    }

    public int getSpeedEV() { return hpEV; }
    public void setSpeedEV(int ev) {
        validateEVs(ev, 0, 0, 0, 0, 0);
        this.speedEV = ev;
    }

    // Validaciones
    private void validateIVs(int... ivs) {
        for(int iv : ivs) {
            if(iv < 0 || iv > 31) throw new IllegalArgumentException("IVs fuera de rango");
        }
    }

    private void validateEVs(int... evs) {
        int total = Arrays.stream(evs).sum();
        if(total > 510) throw new IllegalArgumentException("EVs totales exceden 510");

        for(int ev : evs) {
            if(ev < 0 || ev > 255) throw new IllegalArgumentException("EV individual fuera de rango");
        }
    }


    @Override
    public String toString() {
        return String.format(
                "%03d - %s | Nv. %d | PS: %d",
                POKEDEX_NUMBER, NAME, level, getMaxHP()
        );
    }
}
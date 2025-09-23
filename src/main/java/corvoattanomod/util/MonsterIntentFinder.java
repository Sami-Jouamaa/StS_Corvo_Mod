package corvoattanomod.util;

import com.badlogic.gdx.Game;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.scene.DefectVictorymoveRollberEffect;

public class MonsterIntentFinder {
    public static String ATTACK = "Attack";
    public static String ATTACK_DEBUFF = "Attack and Debuff";
    public static String DEFEND = "Defend";
    public static String DEBUFF = "Debuff";
    public static String UNKNOWN = "????";
    public static String ESCAPE = "Escape";
    public static String DEFEND_BUFF = "Defend and Buff";
    public static String ATTACK_DEFEND = "Attack and Defend";
    public static String BUFF = "BUFF";
    public static String ATTACK_BUFF = "Attack and Buff";
    public static String ATTACK_DEBUFF_BUFF = "Attack and Debuff, Buff itself";
    public static String DEFEND_DEBUFF = "Defend and Debuff";
    
    public static boolean getRandomBoolean(float probability)
    {
        boolean result = AbstractDungeon.aiRng.randomBoolean(probability);
        AbstractDungeon.aiRng.counter--;
        return result;
    }

    public static boolean getRandomBoolean()
    {
        boolean result = AbstractDungeon.aiRng.randomBoolean();
        AbstractDungeon.aiRng.counter--;
        return result;
    }

    public static int getMoveRoll()
    {
        int result = AbstractDungeon.aiRng.random(99);
        AbstractDungeon.aiRng.counter--;
        return result;
    }

    public static int getMoveRoll(int floor, int ceiling)
    {
        int result = AbstractDungeon.aiRng.random(floor, ceiling);
        AbstractDungeon.aiRng.counter--;
        return result;
    }

    public static int getMoveRoll(int ceiling)
    {
        int result = AbstractDungeon.aiRng.random(ceiling);
        AbstractDungeon.aiRng.counter--;
        return result;
    }

    public static String LargeAcidSlimeIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (m.currentHealth <= m.maxHealth/2)
        {
            return UNKNOWN;
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (moveRoll < 40) {
                if (currentMove == 1 && lastMove == 1) {
                    if (getRandomBoolean(0.6F)) {
                        return ATTACK;
                    } else {
                        return DEBUFF;
                    }
                } else {
                    return ATTACK_DEBUFF;
                }
            } else if (moveRoll < 70) {
                if (lastMove == 2 && currentMove == 2) {
                    if (getRandomBoolean(0.6F)) {
                        return ATTACK_DEBUFF;
                    } else {
                        return DEBUFF;
                    }
                } else {
                    return ATTACK;
                }
            } else if (currentMove == 4) {
                if (getRandomBoolean(0.4F)) {
                    return ATTACK_DEBUFF;
                } else {
                    return ATTACK;
                }
            } else {
                return DEBUFF;
            }
        } else if (moveRoll < 30) {
            if (currentMove == 1 && lastMove == 1) {
                if (getRandomBoolean()) {
                    return ATTACK;
                } else {
                    return DEBUFF;
                }
            } else {
                return ATTACK_DEBUFF;
            }
        } else if (moveRoll < 70) {
            if (currentMove == 2) {
                if (getRandomBoolean(0.4F)) {
                    return ATTACK_DEBUFF;
                } else {
                    return DEBUFF;
                }
            } else {
                return ATTACK;
            }
        } else if (lastMove == 4 && currentMove == 4) {
            if (getRandomBoolean(0.4F)) {
                return ATTACK_DEBUFF;
            } else {
                return ATTACK;
            }
        } else {
            return DEBUFF;
        }
    }

    public static String JawWormIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (moveRoll < 25)
        {
            if (currentMove == 1)
            {
                if (getRandomBoolean(0.5625F))
                {
                    return DEFEND_BUFF;
                }
                return ATTACK_DEFEND;
            }
            return ATTACK;
        }
        else if (moveRoll < 55)
        {
            if (currentMove == 3 && lastMove == 3)
            {
                if (getRandomBoolean(0.357F))
                {
                    return ATTACK;
                }
                return DEFEND_BUFF;
            }
            return ATTACK_DEFEND;
        }
        else if (currentMove == 2 && lastMove == 2)
        {
            if (getRandomBoolean(0.416F))
            {
                return ATTACK;
            }
            return ATTACK_DEFEND;
        }
            return DEFEND_BUFF;
    }

    public static String NormalLouseIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (moveRoll < 25) {
                if (currentMove == 4) {
                    return ATTACK;
                } else {
                    return BUFF;
                }
            } else if (currentMove == 3 && lastMove == 3) {
                return BUFF;
            } else {
                return ATTACK;
            }
        } else if (moveRoll < 25) {
            if (currentMove == 4 && lastMove == 4) {
                return ATTACK;
            } else {
                return BUFF;
            }
        }
        else if (currentMove == 3 && lastMove == 3)
        {
            return BUFF;
        }
        else
        {
            return ATTACK;
        }
    }

    public static String DefensiveLouseIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (moveRoll < 25) {
                if (currentMove == 4) {
                    return ATTACK;
                } else {
                    return DEBUFF;
                }
            } else if (currentMove == 3 && lastMove == 3) {
                return DEBUFF;
            } else {
                return ATTACK;
            }
        } else if (moveRoll < 25) {
            if (currentMove == 4 && lastMove == 4) {
                return ATTACK;
            } else {
                return DEBUFF;
            }
        } else if (currentMove == 3 && lastMove == 3) {
            return DEBUFF;
        } else {
            return ATTACK;
        }
    }

    public static String MediumAcidSlimeIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (moveRoll < 40) {
                if (currentMove == 1 && lastMove == 1) {
                    if (getRandomBoolean()) {
                        return ATTACK;
                    } else {
                        return DEBUFF;
                    }
                } else {
                    return ATTACK_DEBUFF;
                }
            } else if (moveRoll < 80) {
                if (currentMove == 2 && lastMove == 2) {
                    if (getRandomBoolean(0.5F)) {
                        return ATTACK_DEBUFF;
                    } else {
                        return DEBUFF;
                    }
                } else {
                    return ATTACK;
                }
            } else if (currentMove == 4) {
                if (getRandomBoolean(0.4F)) {
                    return ATTACK_DEBUFF;
                } else {
                    return ATTACK;
                }
            } else {
                return DEBUFF;
            }
        } else if (moveRoll < 30) {
            if (currentMove == 1 && lastMove == 1) {
                if (getRandomBoolean()) {
                    return ATTACK;
                } else {
                    return DEBUFF;
                }
            } else {
                return ATTACK_DEBUFF;
            }
        } else if (moveRoll < 70) {
            if (currentMove == 2) {
                if (getRandomBoolean(0.4F)) {
                    return ATTACK_DEBUFF;
                } else {
                    return DEBUFF;
                }
            } else {
                return ATTACK;
            }
        } else if (currentMove == 4 && lastMove == 4) {
            if (getRandomBoolean(0.4F)) {
                return ATTACK_DEBUFF;
            } else {
                return ATTACK;
            }
        } else {
            return DEBUFF;
        }
    }

    public static String SmallAcidSlimeIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (currentMove == 1 && lastMove == 1) {
                return ATTACK;
            } else {
                return DEBUFF;
            }
        } else if (getRandomBoolean()) {
            return ATTACK;
        } else {
            return DEBUFF;
        }
    }

    public static String FungiBestIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (moveRoll < 60) {
            if (currentMove == 1 && lastMove == 1) {
                return BUFF;
            } else {
                return ATTACK;
            }
        } else if (currentMove == 2) {
            return ATTACK;
        } else {
            return BUFF;
        }
    }

    public static String GremlinNobIntent(AbstractMonster m, AbstractPlayer p)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 18) {
            if (currentMove != 2 && lastMove != 2) {
                if (!p.hasPower("Vulnerable")) {
                    return ATTACK_DEBUFF;
                } else {
                    return ATTACK;
                }
            }
            if (currentMove == 1 && lastMove == 1) {
                if (!p.hasPower("Vulnerable")) {
                    return ATTACK_DEBUFF;
                } else {
                    return ATTACK;
                }
            } else {
                return ATTACK;
            }
        } else {
            if (moveRoll < 33) {
                if (!p.hasPower("Vulnerable")) {
                    return ATTACK_DEBUFF;
                } else {
                    return ATTACK;
                }
            }
            if (currentMove == 1 && lastMove == 1) {
                if (!p.hasPower("Vulnerable")) {
                    return ATTACK_DEBUFF;
                } else {
                    return ATTACK;
                }
            } else {
                return ATTACK;
            }
        }
    }

    public static String ShieldGremlinIntent()
    {
        if (AbstractDungeon.getCurrRoom().monsters.monsters.size() == 1)
        {
            return ATTACK;
        }
        return DEFEND;
    }

    public static String WizardGremlinIntent(AbstractMonster m)
    {
        if (GameActionManager.turn == 2)
        {
            return ATTACK;
        }
        if (GameActionManager.turn < 2)
        {
            return UNKNOWN;
        }
        if (AbstractDungeon.ascensionLevel >= 17)
        {
            return ATTACK;
        }
        else
        {
            if (GameActionManager.turn % 3 == 0)
            {
                return ATTACK;
            }
        }
        return UNKNOWN;
    }

    public static String HexaghostIntent(AbstractMonster m)
    {
        if (GameActionManager.turn == 1)
        {
            return ATTACK;
        }
        if (GameActionManager.turn == 2)
        {
            return ATTACK_DEBUFF;
        }

        switch (GameActionManager.turn % 7)
        {
            case 0:
                return ATTACK_DEBUFF;
            case 1:
                return ATTACK;
            case 2:
                return ATTACK_DEBUFF;
            case 3:
                return ATTACK;
            case 4:
                return ATTACK_DEBUFF;
            case 5:
                return BUFF;
            case 6:
                return ATTACK;
            default:
                return "Help, something went wrong!";
        }
    }

    public static String LagavulinIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (GameActionManager.turn <= 2)
        {
            return "Sleep";
        }

        switch (GameActionManager.turn % 3)
        {
            case 0:
                return ATTACK;
            case 1:
                return ATTACK;
            case 2:
                return DEBUFF;
            default:
                return "Something went wrong, help...";
        }
    }

    public static String LooterIntent(AbstractMonster m)
    {
        int lastMove = -1;
        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (GameActionManager.turn < 2)
        {
            return ATTACK;
        }

        if (GameActionManager.turn == 2)
        {
            if (getRandomBoolean(0.5F))
            {
                return DEFEND;
            }
            return ATTACK;
        }

        if (GameActionManager.turn == 3 && lastMove == 4)
        {
            return DEFEND;
        }
        return ESCAPE;
    }

    public static String SentryIntent(AbstractMonster m)
    {
        int currentMove = m.nextMove;

        if (currentMove == 4)
        {
            return DEBUFF;
        }
        return ATTACK;
    }

    public static String BlueSlaverIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (moveRoll >= 40 && (currentMove != 1 && lastMove != 1)) {
            return ATTACK;
        }
        if (AbstractDungeon.ascensionLevel >= 17) {
            if (currentMove != 4) {
                return ATTACK_DEBUFF;
            }
            return ATTACK;
        }
        if (currentMove != 4 && lastMove != 4) {
            return ATTACK_DEBUFF;
        }
        return ATTACK;
    }

    public static String RedSlaverIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        boolean usedEntangle = m.moveHistory.contains(2) || currentMove == 2;

        if (moveRoll >= 75 && !usedEntangle) {
            return DEBUFF;
        }
        if (moveRoll >= 55 && usedEntangle && currentMove != 1 && lastMove != 1) {
            return ATTACK;
        }
        if (AbstractDungeon.ascensionLevel >= 17) {
            if (currentMove != 3) {
                return ATTACK_DEBUFF;
            }
            return ATTACK;
        }
        if (currentMove != 3 && lastMove != 3) {
            return ATTACK_DEBUFF;
        }
        return ATTACK;
    }

    public static String SlimeBossIntent(AbstractMonster m)
    {
        if (m.currentHealth <= m.maxHealth/2 || GameActionManager.turn % 3 == 1)
        {
            return UNKNOWN;
        }
        if (GameActionManager.turn % 3 == 2)
        {
            return ATTACK;
        }
        return DEBUFF;
    }

    public static String LargeSpikeSlimeIntent(AbstractMonster m )
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (m.currentHealth <= m.maxHealth/2)
        {
            return UNKNOWN;
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (moveRoll < 30) {
                if (lastMove == 1 && currentMove == 1) {
                    return DEBUFF;
                } else {
                    return ATTACK_DEBUFF;
                }
            } else if (currentMove == 4) {
                return ATTACK_DEBUFF;
            } else {
                return DEBUFF;
            }
        } else if (moveRoll < 30) {
            if (currentMove == 1 && lastMove == 1) {
                return DEBUFF;
            } else {
                return ATTACK_DEBUFF;
            }
        } else if (currentMove == 4 && lastMove == 4) {
            return ATTACK_DEBUFF;
        } else {
            return DEBUFF;
        }
    }

    public static String MediumSpikeSlimeIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (moveRoll < 30) {
                if (currentMove == 1 && lastMove == 1) {
                    return DEBUFF;
                } else {
                    return ATTACK_DEBUFF;
                }
            } else if (currentMove == 4) {
                return ATTACK_DEBUFF;
            } else {
                return DEBUFF;
            }
        } else if (moveRoll < 30) {
            if (currentMove == 1 && lastMove == 1) {
                return DEBUFF;
            } else {
                return ATTACK_DEBUFF;
            }
        } else if (currentMove == 4 && lastMove == 4) {
            return ATTACK_DEBUFF;
        } else {
            return DEBUFF;
        }
    }

    public static String TheGuardianIntent(AbstractMonster m)
    {
       switch (m.nextMove)
       {
           case 1:
               return ATTACK;
           case 2:
               return DEBUFF;
           case 7:
               return ATTACK;
           case 3:
               return ATTACK;
           case 4:
               return ATTACK;
           case 5:
               return BUFF;
           case 6:
               return ATTACK;
           default:
               return "Something went wrong, modder didn't his job correctly.";
       }
    }

    public static String TheHeartIntent(AbstractMonster m)
    {
        if (GameActionManager.turn < 3)
        {
            return ATTACK;
        }
        else if (GameActionManager.turn % 3 == 0)
        {
            return BUFF;
        }
        return ATTACK;
    }

    public static String SpireShieldIntent(AbstractMonster m)
    {
        boolean randomBoolean = getRandomBoolean();
        int currentMove = m.nextMove;

        switch (GameActionManager.turn % 3)
        {
            case 1:
                if (currentMove == 2)
                {
                    return ATTACK_DEBUFF;
                }
                return DEFEND;
            case 2:
                return ATTACK;
            case 0:
                if (randomBoolean)
                {
                    return DEFEND;
                }
                return ATTACK_DEBUFF;
            default:
                return "Something went wrong, whoops";
        }
    }

    public static String SpireSpearIntent(AbstractMonster m)
    {
        boolean randomBoolean = getRandomBoolean();
        int currentMove = m.nextMove;

        if (GameActionManager.turn == 1)
        {
            return ATTACK;
        }

        switch (GameActionManager.turn % 3)
        {
            case 0:
                if (currentMove == 1)
                {
                    return BUFF;
                }
                return ATTACK_DEBUFF;
            case 1:
                return ATTACK;
            case 2:
                if (randomBoolean)
                {
                    return BUFF;
                }
                return ATTACK_DEBUFF;
            default:
                return "Whoops, something went wrong";
        }
    }

    public static String BearIntent(AbstractMonster m)
    {
        int currentMove = m.nextMove;

        if (GameActionManager.turn == 1)
        {
            return ATTACK_DEFEND;
        }

        if (currentMove == 3)
        {
            return ATTACK;
        }
        return ATTACK_DEFEND;
    }

    public static String RomeoIntent(AbstractMonster m)
    {
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (GameActionManager.turn == 1)
        {
            return ATTACK_DEBUFF;
        }

        if (AbstractDungeon.ascensionLevel >= 17)
        {
            if (currentMove != 1 && lastMove != 1)
            {
                return ATTACK;
            }
            return ATTACK_DEBUFF;
        }

        if (currentMove == 1)
        {
            return ATTACK_DEBUFF;
        }
        return ATTACK;
    }

    public static String BronzeAutomatonIntent(AbstractMonster m)
    {
        switch (GameActionManager.turn % 6)
        {
            case 0:
                return UNKNOWN;
            case 1:
                return ATTACK;
            case 2:
                return BUFF;
            case 3:
                return ATTACK;
            case 4:
                return BUFF;
            case 5:
                return ATTACK;
            default:
                return "Incorrect pattern, my bad";
        }
    }

    public static String BronzeOrbIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;
        boolean hasUsedStasis = m.moveHistory.contains((byte) 3);

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (!hasUsedStasis && moveRoll >= 25 && currentMove != 3) {
            return DEBUFF;
        }
        if (moveRoll >= 70 && currentMove != 2 && lastMove != 2) {
            return DEFEND;
        }
        if (currentMove != 1 && lastMove != 1) {
            return ATTACK;
        }
        return DEFEND;
    }

    public static String ByrdIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (lastMove == 4 || !m.moveHistory.contains((byte) 4) || currentMove == 4) {
            if (moveRoll < 50) {
                if (currentMove == 1 && lastMove == 1) {
                    if (getRandomBoolean(0.4F)) {
                        return ATTACK;
                    } else {
                        return BUFF;
                    }
                } else {
                    return ATTACK;
                }
            } else if (moveRoll < 70) {
                if (currentMove == 3) {
                    if (getRandomBoolean(0.375F)) {
                        return BUFF;
                    } else {
                        return ATTACK;
                    }
                } else {
                    return ATTACK;
                }
            } else if (currentMove == 6) {
                if (getRandomBoolean(0.2857F)) {
                    return ATTACK;
                } else {
                    return ATTACK;
                }
            } else {
                return BUFF;
            }
        } else {
            return ATTACK;
        }
    }

    public static String CenturionIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (moveRoll >= 65 && (currentMove == 1 && lastMove == 1)) {
            int i = 0;
            for (AbstractMonster monsters : (AbstractDungeon.getMonsters()).monsters) {
                if (!monsters.isDying && !monsters.isEscaping)
                    i++;
            }
            if (i > 1) {
                return DEFEND;
            }
            return ATTACK;
        }
        if (currentMove != 1 && lastMove != 1) {
            return ATTACK;
        }
        int aliveCount = 0;
        for (AbstractMonster monsters : (AbstractDungeon.getMonsters()).monsters) {
            if (!monsters.isDying && !monsters.isEscaping)
                aliveCount++;
        }
        if (aliveCount > 1) {
            return DEFEND;
        }
        return ATTACK;
    }

    public static String ChampIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        boolean bigBuffDone = m.moveHistory.contains((byte) 7) || currentMove == 7;
        boolean currentMoveAccountedFor = false;
        int forgeTimes = 0;

        int nbTurnsSinceLastReset = m.moveHistory.lastIndexOf((byte) 6);
        int moveRollTurns = GameActionManager.turn - nbTurnsSinceLastReset;

        for (byte move: m.moveHistory)
        {
            if (move == 2 && forgeTimes < 2)
            {
                forgeTimes++;
            }
            if (currentMove == 2 && forgeTimes < 2 && !currentMoveAccountedFor)
            {
                forgeTimes++;
                currentMoveAccountedFor = true;
            }
        }

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (m.currentHealth < m.maxHealth / 2 && !bigBuffDone) {
            return BUFF;
        }
        if (currentMove != 3 && lastMove != 3 && bigBuffDone) {
            return ATTACK;
        }
        if (moveRollTurns == 4 && !bigBuffDone) {
            return DEBUFF;
        }
        if (AbstractDungeon.ascensionLevel >= 19) {
            if (currentMove != 2 && forgeTimes < 2 && moveRoll <= 30) {
                return DEFEND_BUFF;
            }
        } else if (currentMove != 2 && forgeTimes < 2 && moveRoll <= 15) {
            return DEFEND_BUFF;
        }
        if (currentMove != 5 && currentMove != 2 && moveRoll <= 30) {
            return BUFF;
        }
        if (currentMove != 4 && moveRoll <= 55) {
            return ATTACK_DEBUFF;
        }
        if (currentMove != 1) {
            return ATTACK;
        } else {
            return ATTACK_DEBUFF;
        }
    }

    public static String ChosenIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        boolean usedHex = m.moveHistory.contains((byte) 4) || currentMove == 4;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (currentMove != 3 && lastMove != 3 && currentMove != 2 && lastMove != 2) {
                if (moveRoll < 50) {
                    return ATTACK_DEBUFF;
                }
                return DEBUFF;
            }
            if (moveRoll < 40) {
                return ATTACK;
            }
            return ATTACK;
        }
        if (!usedHex) {
            return DEBUFF;
        }
        if (currentMove != 3 && currentMove != 2) {
            if (moveRoll < 50) {
                return ATTACK_DEBUFF;
            }
            return DEBUFF;
        }
        if (moveRoll < 40) {
            return ATTACK;
        }
        return ATTACK;
    }

    public static String GremlinLeaderIntent(AbstractMonster m, int recursiveRoll)
    {
        int moveRoll = 0;
        if (recursiveRoll > 0)
        {
            moveRoll = getMoveRoll();
        }
        else
        {
            moveRoll = recursiveRoll;
        }
        int currentMove = m.nextMove;

        int moveRollAliveGremlins = 0;
        for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters)
        {
            if (monster.id != m.id)
            {
                moveRollAliveGremlins++;
            }
        }

        if (moveRollAliveGremlins == 0) {
            if (moveRoll < 75) {
                if (currentMove != 2) {
                    return UNKNOWN;
                } else {
                    return ATTACK;
                }
            } else if (currentMove != 4) {
                return ATTACK;
            } else {
                return UNKNOWN;
            }
        } else if (moveRollAliveGremlins == 1) {
            if (moveRoll < 50) {
                if (currentMove != 2) {
                    return UNKNOWN;
                } else {
                    GremlinLeaderIntent(m, getMoveRoll(50, 99));
                }
            } else if (moveRoll < 80) {
                if (currentMove != 3) {
                    return DEFEND_BUFF;
                } else {
                    return ATTACK;
                }
            } else if (currentMove != 4) {
                return ATTACK;
            } else {
                GremlinLeaderIntent(m, getMoveRoll(0, 80));
            }
        } else {
            if (moveRoll < 66) {
                if (currentMove != 3) {
                    return DEFEND_BUFF;
                } else {
                    return ATTACK;
                }
            } else if (currentMove != 4) {
                return ATTACK;
            } else {
                return DEFEND_BUFF;
            }
        }
        return "Went out of the branches... Whoops"; //shouldn't get used
    }

    public static String HealerIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        int needToHeal = 0;

        for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
            if (!monster.isDying && !monster.isEscaping)
                needToHeal += monster.maxHealth - monster.currentHealth;
        }
        if (AbstractDungeon.ascensionLevel >= 17) {
            if (needToHeal > 20 && currentMove != 2 && lastMove != 2) {
                return BUFF;
            }
        } else if (needToHeal > 15 && currentMove != 2 && lastMove != 2) {
            return BUFF;
        }
        if (AbstractDungeon.ascensionLevel >= 17) {
            if (moveRoll >= 40 && currentMove != 1) {
                return ATTACK_DEBUFF;
            }
        } else if (moveRoll >= 40 && currentMove != 1 && lastMove != 1) {
            return ATTACK_DEBUFF;
        }
        if (currentMove != 3 && lastMove != 3) {
            return BUFF;
        }
        return ATTACK_DEBUFF;
    }

    public static String ShelledParasiteIntent(AbstractMonster m, int recursiveRoll)
    {
        int moveRoll = 0;
        if (recursiveRoll > 0)
        {
            moveRoll = getMoveRoll();
        }
        else
        {
            moveRoll = recursiveRoll;
        }
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (moveRoll < 20) {
            if (currentMove != 1) {
                return ATTACK_DEBUFF;
            } else {
                ShelledParasiteIntent(m, getMoveRoll(20, 99));
            }
        } else if (moveRoll < 60) {
            if (currentMove != 2 && lastMove != 2) {
                return ATTACK;
            } else {
                return ATTACK_BUFF;
            }
        } else if (currentMove != 3 && lastMove != 3) {
            return ATTACK_BUFF;
        } else {
            return ATTACK;
        }
        return "Shouldn't be used, ShelledParasite";
    }

    public static String SnakePlantIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (moveRoll < 65) {
                if (currentMove == 1 && lastMove == 1) {
                    return DEBUFF;
                } else {
                    return ATTACK;
                }
            } else if (currentMove == 2 || lastMove == 2) {
                return ATTACK;
            } else {
                return DEBUFF;
            }
        } else if (moveRoll < 65) {
            if (currentMove == 1 && lastMove == 1) {
                return DEBUFF;
            } else {
                return ATTACK;
            }
        } else if (currentMove == 2) {
            return ATTACK;
        } else {
            return DEBUFF;
        }
    }

    public static String SneckoIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (moveRoll < 40) {
            return ATTACK_DEBUFF;
        }
        if (currentMove == 2 && lastMove == 2) {
            return ATTACK_DEBUFF;
        } else {
            return ATTACK;
        }
    }

    public static String SphericGuardianIntent(AbstractMonster m)
    {
        int currentMove = m.nextMove;

        if (GameActionManager.turn == 2) {
            return ATTACK_DEBUFF;
        }
        if (currentMove == 1) {
            return ATTACK_DEFEND;
        } else {
            return ATTACK;
        }
    }

    public static String TaskmasterIntent()
    {
        if (AbstractDungeon.ascensionLevel >= 18)
        {
            return ATTACK_DEBUFF_BUFF;
        }
        return ATTACK_DEBUFF;
    }

    public static String TheCollector(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        boolean isMinionDead = false;
        for (AbstractMonster minions: AbstractDungeon.getMonsters().monsters)
        {
            if (minions.isDying)
            {
                isMinionDead = true;
                break;
            }
        }

        boolean ultUsed = m.moveHistory.contains((byte) 4) || currentMove == 4;

        if (GameActionManager.turn >= 3 && !ultUsed) {
            return DEBUFF;
        }
        if (moveRoll <= 25 && isMinionDead && currentMove != 5) {
            return UNKNOWN;
        }
        if (moveRoll <= 70 && currentMove != 2 && lastMove != 2) {
            return ATTACK;
        }
        if (currentMove != 3) {
            return DEFEND_BUFF;
        } else {
            return ATTACK;
        }
    }

    public static String AwakenedOneIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        boolean isPhase1 = false;
        for (AbstractPower powers: m.powers)
        {
            if (powers.ID == "Unawakened")
            {
                isPhase1 = true;
                break;
            }
        }
        if (isPhase1) {
            return ATTACK;
        } else {
            if (moveRoll < 50) {
                if (currentMove != 6 && lastMove != 6) {
                    return ATTACK_DEBUFF;
                } else {
                    return ATTACK;
                }
            } else if (currentMove != 8 && lastMove != 8) {
                return ATTACK;
            } else {
                return ATTACK_DEBUFF;
            }
        }
    }

    public static String DarklingIntent(AbstractMonster m, int newRoll)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (m.halfDead) {
            return BUFF;
        }
        if (moveRoll < 40) {
            if (currentMove != 1 && (AbstractDungeon.getMonsters()).monsters.lastIndexOf(m) % 2 == 0) {
                return ATTACK;
            } else {
                DarklingIntent(m, getMoveRoll(40, 99));
            }
        } else if (moveRoll < 70) {
            if (currentMove != 2) {
                if (AbstractDungeon.ascensionLevel >= 17) {
                    return DEFEND_BUFF;
                } else {
                    return DEFEND;
                }
            } else {
                return ATTACK;
            }
        } else if (currentMove != 3 && lastMove != 3) {
            return ATTACK;
        } else {
            DarklingIntent(m, getMoveRoll(0, 99));
        }
        return "Darkling function went wrong";
    }

    public static String DecaIntent()
    {
        switch (GameActionManager.turn % 2)
        {
            case 0:
                return DEFEND;
            case 1:
                return ATTACK_DEBUFF;
        }
        return "Deca function messed up";
    }

    public static String DonuIntent()
    {
        switch (GameActionManager.turn % 2)
        {
            case 0:
                return BUFF;
            case 1:
                return ATTACK;
        }
        return "Donu function went wrong";
    }

    public static String ExploderIntent()
    {
        if (GameActionManager.turn == 1)
        {
            return ATTACK;
        }
        return UNKNOWN;
    }

    public static String GiantHeadIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 18)
        {
            if (GameActionManager.turn > 3)
            {
                return ATTACK;
            }
        }
        else
        {
            if (GameActionManager.turn > 4)
            {
                return ATTACK;
            }
        }
        if (moveRoll < 50) {
            if (currentMove != 1 && lastMove != 1) {
                return DEBUFF;
            } else {
                return ATTACK;
            }
        } else if (currentMove != 3 && lastMove  != 3) {
            return ATTACK;
        } else {
            return DEBUFF;
        }
    }

    public static String MawIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        boolean hasRoared = m.moveHistory.contains((byte) 2) || currentMove == 2;

        if (!hasRoared) {
            return DEBUFF;
        }
        if (moveRoll < 50 && currentMove != 5) {
            return ATTACK;
        }
        if (currentMove == 3 || currentMove == 5) {
            return BUFF;
        }
        return ATTACK;
    }

    public static String NemesisIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        boolean canUseScythe = lastMove != 3 && currentMove != 3;

        if (moveRoll < 30) {
            if (currentMove != 3 && canUseScythe) {
                return ATTACK;
            } else if (getRandomBoolean()) {
                if (currentMove != 2 && lastMove != 2) {
                    return ATTACK;
                } else {
                    return DEBUFF;
                }
            } else if (currentMove != 4) {
                return DEBUFF;
            } else {
                return ATTACK;
            }
        } else if (moveRoll < 65) {
            if (currentMove != 2 && lastMove != 2) {
                return ATTACK;
            } else if (getRandomBoolean()) {
                if (!canUseScythe) {
                    return DEBUFF;
                } else {
                    return ATTACK;
                }
            } else {
                return DEBUFF;
            }
        } else if (currentMove != 4) {
            return DEBUFF;
        } else if (getRandomBoolean() && canUseScythe) {
            return ATTACK;
        } else {
            return ATTACK;
        }
    }

    public static String OrbWalkerIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (moveRoll < 40) {
            if (currentMove != 2 && lastMove != 2) {
                return ATTACK;
            } else {
                return ATTACK_DEBUFF;
            }
        } else if (currentMove != 1 && lastMove != 1) {
            return ATTACK_DEBUFF;
        } else {
            return ATTACK;
        }
    }

    public static String ReptomancerIntent(AbstractMonster m, int recursiveRoll)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        boolean canSpawn = false;
        int aliveCount = 0;
        for (AbstractMonster monsters : (AbstractDungeon.getMonsters()).monsters) {
            if (!monsters.id.equals(m.id) && !monsters.isDying)
                aliveCount++;
        }
        if (aliveCount <= 3)
        {
            canSpawn = true;
        }

        if (moveRoll < 33) {
            if (currentMove != 1) {
                return ATTACK_DEBUFF;
            } else {
                ReptomancerIntent(m, getMoveRoll(33, 99));
            }
        } else if (moveRoll < 66) {
            if (currentMove != 2 && lastMove != 2) {
                if (canSpawn) {
                    return UNKNOWN;
                } else {
                    return ATTACK_DEBUFF;
                }
            } else {
                return ATTACK_DEBUFF;
            }
        } else if (currentMove != 3 && lastMove != 3) {
            return ATTACK;
        } else {
            ReptomancerIntent(m, getMoveRoll(65));
        }
        return "Reptomancer messed up";
    }

    public static String RepulsorIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;

        if (moveRoll < 20 && currentMove != 2) {
            return ATTACK;
        }
        return DEBUFF;
    }

    public static String SpikerIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int thornsAmount = 0;
        for (AbstractPower p: m.powers)
        {
            if (p.ID.equals("Thorns"))
            {
                thornsAmount = p.amount;
                break;
            }
        }

        if (currentMove == 2)
        {
            thornsAmount += 2;
        }

        if (thornsAmount > 5) {
            return ATTACK;
        }
        if (moveRoll < 50 && currentMove != 1) {
            return ATTACK;
        }
        return BUFF;
    }

    public static String SpireGrowthIntent(AbstractMonster m)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        if (AbstractDungeon.ascensionLevel >= 17 && !AbstractDungeon.player.hasPower("Constricted") && currentMove != 2) {
            return DEBUFF;
        }
        if (moveRoll < 50 && currentMove != 1 && lastMove != 1) {
            return ATTACK;
        }
        if (!AbstractDungeon.player.hasPower("Constricted") && currentMove != 2) {
            return DEBUFF;
        }
        return ATTACK;
    }

    public static String TimeEaterIntent(AbstractMonster m, int recursiveRoll)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;
        int lastMove = -1;

        if (m.moveHistory.size() > 1)
        {
            lastMove = m.moveHistory.get(m.moveHistory.size() - 1);
        }

        boolean usedHaste = m.moveHistory.contains((byte) 5) || currentMove == 5;

        if (m.currentHealth < m.maxHealth / 2 && !usedHaste) {
            return BUFF;
        }
        if (moveRoll < 45) {
            if (currentMove != 2 && lastMove != 6) {
                return ATTACK;
            }
            TimeEaterIntent(m, getMoveRoll(50, 99));
        }
        if (moveRoll < 80) {
            if (currentMove != 4) {
                return ATTACK_DEBUFF;
            }
            if (getRandomBoolean(0.66F)) {
                return ATTACK;
            }
            return DEFEND_DEBUFF;
        }
        if (currentMove != 3) {
            return DEFEND_DEBUFF;
        }
        TimeEaterIntent(m, getMoveRoll(74));
        return "Time Eater messed up";
    }

    public static String WrithingMassIntent(AbstractMonster m, int recursiveRoll)
    {
        int moveRoll = getMoveRoll();
        int currentMove = m.nextMove;

        boolean hasCursed = m.moveHistory.contains((byte) 4);
        
        if (moveRoll < 10) {
            if (currentMove != 0) {
                return ATTACK;
            } else {
                WrithingMassIntent(m, getMoveRoll(10, 99));
            }
        } else if (moveRoll < 20) {
            if (!hasCursed && currentMove != 4) {
                return DEBUFF;
            } else if (getRandomBoolean(0.1F)) {
                return ATTACK;
            } else {
                WrithingMassIntent(m, getMoveRoll(20, 99));
            }
        } else if (moveRoll < 40) {
            if (currentMove != 3) {
                return ATTACK_DEBUFF;
            } else if (getRandomBoolean(0.4F)) {
                WrithingMassIntent(m, getMoveRoll(19));
            } else {
                WrithingMassIntent(m, getMoveRoll(40, 99));
            }
        } else if (moveRoll < 70) {
            if (currentMove != 1) {
                return ATTACK;
            } else if (getRandomBoolean(0.3F)) {
                return ATTACK_DEFEND;
            } else {
                WrithingMassIntent(m, getMoveRoll(39));
            }
        } else if (currentMove != 2) {
            return ATTACK_DEFEND;
        } else {
            WrithingMassIntent(m, getMoveRoll(69));
        }
    }
}

package corvoattanomod.util;

import com.badlogic.gdx.Game;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
                return ATTACK; //never getting used but will shut up the compiler
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
                return ATTACK; //not getting used
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
               return ATTACK;
       }
    }
}

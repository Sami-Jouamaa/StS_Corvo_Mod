package corvoattanomod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static corvoattanomod.CorvoAttanoMod.makeID;

public class DominoEffect extends BasePower{
    public static final String POWER_ID = makeID("DominoEffect");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public static int damageReflect;
    public static ArrayList<AbstractMonster> roomMonsters;
    public static AbstractMonster monsterOwner;
    public static AbstractPlayer player;

    public DominoEffect(AbstractMonster owner, int dmgReflect, ArrayList<AbstractMonster> mo, AbstractPlayer source)
    {
        super(POWER_ID, TYPE, TURN_BASED, owner, dmgReflect);
        DominoEffect.damageReflect = dmgReflect;
        DominoEffect.roomMonsters = mo;
        DominoEffect.player = source;
        DominoEffect.monsterOwner = owner;
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + damageReflect + DESCRIPTIONS[1];
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null) {
            flash();
        }
        if (info.type == DamageInfo.DamageType.NORMAL)
        {
            for (AbstractMonster mo: roomMonsters)
            {
                int currentBlock = mo.currentBlock;
                float dmgMultiplier = (float)damageReflect/100;
                int damageToDeal = (int)Math.round(dmgMultiplier * damageAmount - currentBlock);
                addToBot(new DamageAction(mo, new DamageInfo(player, damageToDeal, DamageInfo.DamageType.HP_LOSS)));
            }
        }
        return damageAmount;
    }
}

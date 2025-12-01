package corvoattanomod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import corvoattanomod.cards.skills.ChainingArcMine;
import corvoattanomod.cards.skills.ChargedUpArcMine;

import static corvoattanomod.CorvoAttanoMod.makeID;

public class ChargedUpArcMineEffect extends BasePower{
    public static final String POWER_ID = makeID("ChargedUpArcMineEffect");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    public static int damageToDeal;
    public static int vulnStacks;
    public static int repeats;
    public static AbstractCreature enemyToDamage;

    public ChargedUpArcMineEffect(AbstractCreature owner, int amount, int damage, int vuln, AbstractCreature m, int hitNumbers)
    {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        ChargedUpArcMineEffect.damageToDeal = damage;
        ChargedUpArcMineEffect.vulnStacks = vuln;
        ChargedUpArcMineEffect.repeats = hitNumbers;
        ChargedUpArcMineEffect.enemyToDamage = m;
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + damageToDeal + DESCRIPTIONS[1] + repeats + DESCRIPTIONS[2] + vulnStacks + DESCRIPTIONS[3];
    }

    public void atStartOfTurnPostDraw()
    {
        flash();
        for (int i = 0; i < repeats; i++)
        {
            addToBot(new ApplyPowerAction(enemyToDamage, owner, new VulnerablePower(enemyToDamage, vulnStacks, false), 1, true, AbstractGameAction.AttackEffect.NONE));
            addToBot(new DamageAction(enemyToDamage, new DamageInfo(owner, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.LIGHTNING));
        }
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

}

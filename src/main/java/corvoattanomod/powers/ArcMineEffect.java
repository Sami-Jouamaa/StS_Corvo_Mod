package corvoattanomod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static corvoattanomod.CorvoAttanoMod.makeID;

public class ArcMineEffect extends BasePower{
    public static final String POWER_ID = makeID("ArcMineEffect");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public static int damage;
    public static int weakStacks;
    public static AbstractCreature target;

    public ArcMineEffect(AbstractCreature owner, int amount, int damage, int weak, AbstractCreature m)
    {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        ArcMineEffect.damage = damage;
        ArcMineEffect.weakStacks = weak;
        target = m;
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1] + weakStacks + DESCRIPTIONS[2];
    }

    public void atStartOfTurnPostDraw()
    {
        flash();
        addToBot(new ReducePowerAction(this.owner, this.owner, "ArcMineEffect", 1));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)target, (AbstractCreature)this.owner, (AbstractPower)new WeakPower((AbstractCreature)target, weakStacks, false), 3, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new DamageAction(target, new DamageInfo(owner, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}

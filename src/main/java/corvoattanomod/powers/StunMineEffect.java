package corvoattanomod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import corvoattanomod.cards.skills.StunMine;

import static corvoattanomod.CorvoAttanoMod.makeID;

public class StunMineEffect extends BasePower {
    public static final String POWER_ID = makeID("StunMineEffect");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    public static int weakVulnStacks;
    public static AbstractCreature targetEnemy;

    public StunMineEffect(AbstractCreature owner, int stacksAmount, AbstractCreature m)
    {
        super(POWER_ID, TYPE, TURN_BASED, owner, stacksAmount);
        StunMineEffect.weakVulnStacks = stacksAmount;
        StunMineEffect.targetEnemy = m;
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + weakVulnStacks + DESCRIPTIONS[1] + weakVulnStacks + DESCRIPTIONS[2];
    }

    public void atStartOfTurnPostDraw()
    {
        flash();
        addToBot(new ApplyPowerAction(targetEnemy, owner, new VulnerablePower(targetEnemy, weakVulnStacks, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(targetEnemy, owner, new WeakPower(targetEnemy, weakVulnStacks, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
}


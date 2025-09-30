package corvoattanomod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static corvoattanomod.CorvoAttanoMod.makeID;

public class ChainingArcMineEffect extends BasePower{
    public static final String POWER_ID = makeID("ChainingArcMineEffect");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public static int damage;
    public static int weakStacks;

    public ChainingArcMineEffect(AbstractCreature owner, int amount, int damage, int weak)
    {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        ChainingArcMineEffect.damage = damage;
        ChainingArcMineEffect.weakStacks = weak;
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1] + weakStacks + DESCRIPTIONS[2];
    }

    public void atStartOfTurnPostDraw()
    {
        flash();
        for (AbstractCreature mo: AbstractDungeon.getCurrRoom().monsters.monsters)
        {
            addToBot(new ApplyPowerAction(mo, owner, new WeakPower(mo, weakStacks, false), 3, true, AbstractGameAction.AttackEffect.NONE));
        }
        addToBot(new DamageAllEnemiesAction((AbstractPlayer) owner, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
}

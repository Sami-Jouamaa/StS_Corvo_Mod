package corvoattanomod.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SleepNextTurn extends AbstractPower {
    public static final String POWER_ID = "SleepNextTurn";

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("SleepNextTurn");

    public static final String NAME = powerStrings.NAME;

    public static final String[] Descriptions = powerStrings.DESCRIPTIONS;

    public SleepNextTurn(AbstractCreature owner, int amount)
    {
        this.name = NAME;
        this.ID = "SleepNextTurn";
        this.owner = owner;
        this.amount = amount;
        updateDescriptions();
        loadRegion("poison");
    }

    public void updateDescriptions()
    {
        this.description = Descriptions[0];
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        flash();
        addToBot(new ReducePowerAction(this.owner, this.owner, "SleepNextTurn", 1));
        addToBot((new StunMonsterAction((AbstractMonster) this.owner, null, 1)));
    }
}

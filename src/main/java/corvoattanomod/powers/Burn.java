package corvoattanomod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import corvoattanomod.util.BurnLoseHPAction;

public class Burn extends AbstractPower {
    public static final String POWER_ID = "Burn";

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Burn");

    public static final String NAME = powerStrings.NAME;

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private AbstractCreature source;

    public Burn(AbstractCreature owner, AbstractCreature source, int burnAmount)
    {
        this.name = NAME;
        this.ID = "Burn";
        this.owner = owner;
        this.source = source;
        this.amount = burnAmount;
        if (this.amount >= 9999)
            this.amount = 9999;
        updateDescription();
        loadRegion("poison");
        this.type = AbstractPower.PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
    }

    public void updateDescription() {
        if (this.owner == null || this.owner.isPlayer) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[1];
        }
    }

    public void atStartOfTurn() {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flashWithoutSound();
            addToBot((AbstractGameAction)new BurnLoseHPAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.FIRE));
        }
    }
}

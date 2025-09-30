package corvoattanomod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import corvoattanomod.util.SpecialBonuses;

import static corvoattanomod.CorvoAttanoMod.makeID;

public class SpringrazorEffect extends BasePower {
    public static final String POWER_ID = makeID("SpringrazorEffect");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public static int damage;
    public static int dmgRepeat;
    public static int fearMaxNumber;

    public SpringrazorEffect(AbstractCreature owner, int amount, int damage, int repeat, int fearNumber)
    {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        SpringrazorEffect.damage = damage;
        SpringrazorEffect.dmgRepeat = repeat;
        SpringrazorEffect.fearMaxNumber = fearNumber;
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1];
    }

    public void atStartOfTurnPostDraw()
    {
        flash();
        addToBot(new ReducePowerAction(this.owner, this.owner, "SpringrazorEffect", 1));
        int nbFearToApply = 0;

        for (int i = 0; i < dmgRepeat; i++)
        {
            if (SpecialBonuses.checkGore() || nbFearToApply < fearMaxNumber)
            {
                nbFearToApply++;
            }
            addToBot(new DamageAllEnemiesAction((AbstractPlayer) this.owner, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }

        for (int i = 0; i < nbFearToApply; i++)
        {
            addToBot(new SFXAction("INTIMIDATE"));
            addToBot(new VFXAction(this.owner, new IntimidateEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 1.0F));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            {
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)this.owner, (AbstractPower)new WeakPower((AbstractCreature)mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)this.owner, (AbstractPower)new VulnerablePower((AbstractCreature)mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }
}

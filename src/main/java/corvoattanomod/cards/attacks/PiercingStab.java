package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class PiercingStab extends BaseCard {
    public static final String ID = makeID("PiercingStab");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;
    private static final int REPEAT_DAMAGE = 30;
    private static final int UPG_REPEAT_DAMAGE = 20;

    public PiercingStab()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(REPEAT_DAMAGE, UPG_REPEAT_DAMAGE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean triggersBonus = SpecialBonuses.checkMelee();
        if (triggersBonus)
        {
            addToBot(new SFXAction("INTIMIDATE"));
            addToBot(new VFXAction(p, new IntimidateEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 1.0F));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            {
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));

        AbstractCreature previousMonster = null;
        for (AbstractCreature mo: (AbstractDungeon.getCurrRoom()).monsters.monsters)
        {
            if (previousMonster == m)
            {
                addToBot(new DamageAction(mo, new DamageInfo(p, (int)Math.round((float) (magicNumber) /100*damage), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
            previousMonster = mo;
        }
    }
}

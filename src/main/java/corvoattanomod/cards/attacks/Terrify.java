package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class Terrify extends BaseCard {
    public static final String ID = makeID("Terrify");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE =  12;
    private static final int UPG_DAMAGE = 3;

    public Terrify()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(m.currentHealth - damage <= 0 || SpecialBonuses.checkMelee())
        {
            addToBot(new DamageAction(m, new DamageInfo(p, damage/2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            addToBot(new SFXAction("INTIMIDATE"));
            addToBot(new VFXAction(p, new IntimidateEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 1.0F));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            {
                addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));
                addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        else
        {
            addToBot(new DamageAction(m, new DamageInfo(p, damage/2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }
}

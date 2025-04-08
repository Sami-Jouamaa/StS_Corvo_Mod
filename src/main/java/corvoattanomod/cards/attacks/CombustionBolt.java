package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.Burn;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class CombustionBolt extends BaseCard {
    public static final String ID = makeID("CombustionBolt");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 6;
    private static final int BURN = 6;
    private static final int UPG_BURN = 2;

    public CombustionBolt()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE);
        setMagic(BURN, UPG_BURN);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean triggersBonus = SpecialBonuses.checkRanged();
        int damageToDeal = damage;
        if (triggersBonus)
        {
            damageToDeal = (int)Math.round(1.5*damageToDeal);
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        for (AbstractMonster mo: (AbstractDungeon.getCurrRoom()).monsters.monsters)
        {
            addToBot(new ApplyPowerAction(mo, p, new Burn(mo, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.FIRE));
        }

    }
}

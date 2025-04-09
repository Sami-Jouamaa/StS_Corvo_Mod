package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.SleepNextTurn;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class SleepDart extends BaseCard {
    public static final String ID = makeID("SleepDart");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 3;

    public SleepDart()
    {
        super(ID, cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int damageToDeal = damage;
        if (SpecialBonuses.checkRanged())
        {
            damageToDeal = (int)Math.round(1.5*damageToDeal);
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(m, p, new SleepNextTurn(m, 1, 0), 1));
    }
}

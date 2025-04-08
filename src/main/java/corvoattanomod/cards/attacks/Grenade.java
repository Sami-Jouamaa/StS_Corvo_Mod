package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.SpecialBonuses;

public class Grenade extends BaseCard {
    public static final String ID = makeID("Grenade");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 11;

    public Grenade()
    {
        super(ID, cardInfo);
        this.isMultiDamage = true;
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean triggersBonus = SpecialBonuses.checkRanged();
        int damageToDeal = damage;
        if (triggersBonus)
        {
            damageToDeal = (int)Math.round(1.5*damageToDeal);
        }

        addToBot(new DamageAllEnemiesAction(p, damageToDeal, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }
}

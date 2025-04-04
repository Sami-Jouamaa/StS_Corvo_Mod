package corvoattanomod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.MeleeRanged;

public class Strike extends BaseCard {
    public static final String ID = makeID("Strike");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );
    private static final int damage = 6;
    private static final int upg_damage = 9;

    public Strike()
    {
        super(ID, cardInfo);

        setDamage(damage, upg_damage);
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean triggersBonus = MeleeRanged.checkMelee();
        if (triggersBonus)
        {
            addToBot(new DamageAction(m, new DamageInfo(p, (int) Math.round(1.5*damage), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        else
        {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }
}

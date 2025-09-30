package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.ArcMineEffect;
import corvoattanomod.util.CardStats;

public class ArcMine extends BaseCard {
    public static final String ID = makeID("ArcMine");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMGE = 4;
    private static final int WEAK = 3;
    private static final int UPG_WEAK = 1;

    public ArcMine()
    {
        super(ID, cardInfo);
        setDamage(DAMAGE, UPG_DAMGE);
        setMagic(WEAK, UPG_WEAK);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(p, p, new ArcMineEffect(p, 1, damage, magicNumber, m)));
    }
}

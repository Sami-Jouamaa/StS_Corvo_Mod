package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.ChainingArcMineEffect;
import corvoattanomod.util.CardStats;

public class ChainingArcMine extends BaseCard {
    public static final String ID = makeID("ChainingArcMine");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int WEAK = 2;
    private static final int UPG_WEAK = 1;

    public ChainingArcMine()
    {
        super(ID, cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(WEAK, UPG_WEAK);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(p, p, new ChainingArcMineEffect(p, 1, damage, magicNumber)));
    }
}

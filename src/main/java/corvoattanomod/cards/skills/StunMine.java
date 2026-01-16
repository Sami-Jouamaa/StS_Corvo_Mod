package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.ChargedUpArcMineEffect;
import corvoattanomod.powers.StunMineEffect;
import corvoattanomod.util.CardStats;

public class StunMine extends BaseCard {
    public static final String ID = makeID("StunMine");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int STACKS = 2;
    private static final int UPG_STACKS = 2;

    public StunMine()
    {
        super(ID, cardInfo);
        setMagic(STACKS, UPG_STACKS);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(m, m, new StunMineEffect(m, magicNumber, m)));
    }
}

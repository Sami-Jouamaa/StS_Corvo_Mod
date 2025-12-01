package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.powers.ChainingArcMineEffect;
import corvoattanomod.powers.ChargedUpArcMineEffect;
import corvoattanomod.util.CardStats;

public class ChargedUpArcMine extends BaseCard {
    public static final String ID = makeID("ChargedUpArcMine");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;
    private static final int REPEATS = 2;
    private static final int VULN_STACKS = 1;

    public ChargedUpArcMine()
    {
        super(ID, cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(REPEATS);
        setMagic(VULN_STACKS);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(p, p, new ChargedUpArcMineEffect(m, 1, damage, VULN_STACKS, m, REPEATS)));
    }
}

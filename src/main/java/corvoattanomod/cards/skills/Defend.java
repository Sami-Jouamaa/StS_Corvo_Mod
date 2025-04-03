package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;

import static corvoattanomod.CorvoAttanoMod.makeID;

public class Defend extends BaseCard {
    public static final String ID = makeID("Defend");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );
    private static final int block = 5;
    private static final int upg_block = 7;

    public Defend()
    {
        super(ID, cardInfo);

        setBlock(block, upg_block);
        tags.add(AbstractCard.CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new GainBlockAction(p, block));
    }
}

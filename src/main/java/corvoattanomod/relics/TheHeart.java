package corvoattanomod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import corvoattanomod.cards.skills.Clairvoyance;
import corvoattanomod.character.CorvoCharacter;

import static corvoattanomod.CorvoAttanoMod.makeID;

public class TheHeart extends BaseRelic{
    private static final String NAME = "TheHeart";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public TheHeart()
    {
        super(ID, NAME, CorvoCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStartPreDraw()
    {
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Clairvoyance(), 1, false));
    }
}

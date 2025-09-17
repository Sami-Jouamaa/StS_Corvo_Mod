package corvoattanomod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import corvoattanomod.cards.BaseCard;
import corvoattanomod.character.CorvoCharacter;
import corvoattanomod.util.CardStats;
import corvoattanomod.util.MonsterIntentFinder;

import java.time.MonthDay;

public class Clairvoyance extends BaseCard {
    public static final String ID = makeID("Clairvoyance");
    private static final CardStats cardInfo = new CardStats(
            CorvoCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    public Clairvoyance()
    {
        super(ID, cardInfo);
        setExhaust(true);
        setSelfRetain(true);
        setCostUpgrade(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        String intent = "";
        switch(m.id)
        {
            case "AcidSlime_L":
                intent = MonsterIntentFinder.LargeAcidSlimeIntent(m);
                break;
            case "JawWorm":
                intent = MonsterIntentFinder.JawWormIntent(m);
                break;
            case "FuzzyLouseNormal":
                intent = MonsterIntentFinder.NormalLouseIntent(m);
                break;
            case "Cultist":
                intent = "Attack";
                break;
            case "FuzzyLouseDefensive":
                intent = MonsterIntentFinder.DefensiveLouseIntent(m);
                break;
            case "AcidSlime_M":
                intent = MonsterIntentFinder.MediumAcidSlimeIntent(m);
                break;
            case "AcidSlime_S":
                intent = MonsterIntentFinder.SmallAcidSlimeIntent(m);
                break;
            case "FungiBeast":
                intent = MonsterIntentFinder.FungiBestIntent(m);
                break;
            case "GremlinFat":
                intent = "Attack and Debuff";
                break;
            case "GremlinNob":
                intent = MonsterIntentFinder.GremlinNobIntent(m, p);
                break;
            case "GremlinThief":
                intent = "Attack";
                break;
            case "GremlinTsundere":
                intent = MonsterIntentFinder.ShieldGremlinIntent();
                break;
            case "GremlinWarrior":
                intent = "Attack";
                break;
            case "GremlinWizard":
                intent = MonsterIntentFinder.WizardGremlinIntent(m);
                break;
            case "Hexaghost":
                intent = MonsterIntentFinder.HexaghostIntent(m);
                break;
            case "Lagavulin":
                intent = MonsterIntentFinder.LagavulinIntent(m);
                break;
            case "Looter":
                intent = MonsterIntentFinder.LooterIntent(m);
                break;
            case "Sentry":
                intent = MonsterIntentFinder.SentryIntent(m);
                break;
            case "SlaverBlue":
                intent = MonsterIntentFinder.BlueSlaverIntent(m);
                break;
            case "SlaverRed":
                intent = MonsterIntentFinder.RedSlaverIntent(m);
                break;
            case "SlimeBoss":
                intent = MonsterIntentFinder.SlimeBossIntent(m);
                break;
            case "SpikeSlime_L":
                intent = MonsterIntentFinder.LargeSpikeSlimeIntent(m);
                break;
            case "SpikeSlime_M":
                intent = MonsterIntentFinder.MediumSpikeSlimeIntent(m);
                break;
            case "SpikeSlime_S":
                intent = "Attack";
                break;
            case "TheGuardian":
                intent = MonsterIntentFinder.TheGuardianIntent(m);
                break;
        }
        AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, intent, true));
    }
}

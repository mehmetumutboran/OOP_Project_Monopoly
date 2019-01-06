
package domain.server.card.chanceDeck;

        import domain.client.PlayerActionController;
        import domain.client.UIUpdater;
        import domain.server.card.ChanceCard;
        import domain.server.player.Player;
        import domain.util.GameInfo;

public class SocialMediaFail extends ChanceCard {

    public SocialMediaFail(String name) {
        super(name);
    }

    @Override
    public boolean doAction(String name) {

        for(Player player : GameInfo.getInstance().getPlayerList()){
            GameInfo.getInstance().getPlayer(player.getName()).increaseMoney(50);
        }
        int money = 50*GameInfo.getInstance().getPlayerList().size();
        GameInfo.getInstance().getPlayer(name).increaseMoney(-money);
        UIUpdater.getInstance().setMessage(name + " paid each player $50 to restore his/her PR" );

        return true;
    }
}

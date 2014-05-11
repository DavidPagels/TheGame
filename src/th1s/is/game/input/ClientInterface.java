package th1s.is.game.input;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import th1s.is.game.entity.mob.Player;
import th1s.is.game.level.Level;

public class ClientInterface extends Client{
	
	public ClientInterface (Level level) throws UnknownHostException, SocketException{
		super(level);
	}
	
	public void connect(String name){
		send(new String("connect/" + name + "/").getBytes());
	}
	
	public void move(Player player){
		send(new String("move/" + ID + "/" + player.x + ", " + player.y + ", /").getBytes());
	}
	
	public void sendMessage(){
		Thread sendMessage = new Thread("messages"){
			public void run() {
				byte [] message = new byte[512];
				try {
					while(System.in.read(message) > 0){
					     send(new String("message/" + ID + "/" + new String(message)).getBytes());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
		sendMessage.start();
	}
}

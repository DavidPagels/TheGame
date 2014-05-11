package th1s.is.game.input;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import th1s.is.game.entity.mob.OtherPlayers;
import th1s.is.game.level.Level;

public class Client {
	InetAddress address;
	DatagramSocket socket;
	private Thread send;
	private Thread receive;
	private Level level;
	protected int ID;
	HashMap<Integer, OtherPlayers> players;

	public Client(Level level) throws UnknownHostException, SocketException{
		this.level = level;
		players = new HashMap<Integer, OtherPlayers>();
		address = InetAddress.getByName("localhost");
		socket = new DatagramSocket();
		receive();
	}

	public void connect(String name){
		send(new String("connect/" + name + "/").getBytes());
	}

	public void send(final byte [] data){
		send = new Thread("send"){
			public void run() {
				DatagramPacket packet = new DatagramPacket(data, data.length, address, 31336);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		send.start();
	}
	
	public String receive(){
		receive = new Thread("receive"){
			public void run() {
				while(true){
					byte [] data = new byte[1024];
					DatagramPacket packet = new DatagramPacket(data, data.length);
					try {
						socket.receive(packet);
					} catch (IOException e) {
						e.printStackTrace();
					}
					process(packet);
				}
			}
		};
		receive.start();
		return new String();
	}
	
	public void process(DatagramPacket packet){
		String packetString = new String(packet.getData());
		String[] type = packetString.split("/");
		if(type[0].equals("createSuccess")){
			int newID = Integer.parseInt(type[1]);
			this.ID= newID;
		}
		
		if(type[0].equals("addPlayer")){
			int newID = Integer.parseInt(type[1]);
			if(newID != ID && ID != 0){
				OtherPlayers newPlayer = new OtherPlayers(32, 32);
				level.add(newPlayer);
				players.put(newID, newPlayer);
			}
		}
		
		if(type[0].equals("move")){
			int[] xy = {Integer.parseInt(type[2].split(", ")[0]), Integer.parseInt(type[2].split(", ")[1])};
			int theID = Integer.parseInt(type[1]);
			if(players.get(theID) == null){
				OtherPlayers newPlayer = new OtherPlayers(32, 32);
				level.add(newPlayer);
				players.put(theID, newPlayer);
			}
				
			if(theID !=this.ID)
				players.get(theID).setCoords(xy);
		}
		
		if(type[0].equals("message")){
			String message = type[1] + " : " + type[2];
			System.out.println(message);
			//display the message
		}
	}

}
